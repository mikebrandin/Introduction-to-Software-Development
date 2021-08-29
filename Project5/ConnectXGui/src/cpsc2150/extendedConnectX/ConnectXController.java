package cpsc2150.extendedConnectX;

import java.util.Set;

/**
 * The controller class will handle communication between our View and our Model (IGameBoard)
 * <p>
 * This is where you will write code
 * <p>
 * You will need to include your IGameBoard interface
 * and both of the IGameBoard implementations from Project 4
 * If your code was correct you will not need to make any changes to your IGameBoard implementation class
 */
public class ConnectXController {

    //our current game that is being played
    private IGameBoard curGame;

    //The screen that provides our view
    private ConnectXView screen;

    public static final int MAX_PLAYERS = 10;
    //our play tokens are hard coded. We could make a screen to get those from the user, but

    private int numPlayers;

    private int playerTurn = 0;
    private boolean gameOver = false;
    private Character [] players = {'X','O','E','R','T','Y','Z','I','W','M'};


    /**
     * @param model the board implementation
     * @param view  the screen that is shown
     * @post the controller will respond to actions on the view using the model.
     */
    ConnectXController(IGameBoard model, ConnectXView view, int np) {
        this.curGame = model;
        this.screen = view;
        numPlayers = np;
    }

    /**
     * @param col the column of the activated button
     * @post will allow the player to place a token in the column if it is not full, otherwise it will display an error
     * and allow them to pick again. Will check for a win as well. If a player wins it will allow for them to play another
     * game hitting any button
     */
    public void processButtonClick(int col) {
        if (gameOver){//if the game is over restart the game on button click
            gameOver = false;//reset values of private data
            playerTurn = 0;
            this.newGame();
            return;//symbolic
        }

        if (playerTurn >= numPlayers){//if playerTurn exceeds the maximum number of players rest to zero
            playerTurn = 0;
        }

        if (!this.curGame.checkIfFree(col)) {
            this.screen.setMessage("Column is full");

        }
        else {//if valid input place token in selected Column
            char token = players[playerTurn];
            int rowHeight = 0;

            for (int i = 0; i < this.curGame.getNumRows(); i++) {//loop to find highest row in the column to create a temp position variable
                BoardPosition temp = new BoardPosition(i, col);//declare and initiate a temporary position variable
                if (this.curGame.whatsAtPos(temp) != ' '){//use function calls to check if player is at position
                    rowHeight++;
                }
            }
            //place token on gameboard and update screen to display this
            this.curGame.placeToken(token, col);
            this.screen.setMarker(rowHeight, col, token);

            if (this.curGame.checkForWin(col)){//if last placed token won game display message and set gameover to true
                this.screen.setMessage("Player " + players[playerTurn] + " Won!\nClick any button to start a new game.");
                gameOver = true;
            }
            else if (this.curGame.checkTie()){//if last placed token tied game display message and set gameover to true
                this.screen.setMessage("Tie game!\nClick any button to start a new game.");
                gameOver = true;
            }
            else{//otherwise advance the turn and prompt the next player for their turn
                playerTurn++;
                if (playerTurn >= numPlayers){//reset playerturn to 0 if it exceeds playernum
                    playerTurn = 0;
                }
                screen.setMessage("It is " + players[playerTurn] + "'s turn. ");

            }

        }
    }

    /**
     * This method will start a new game by returning to the setup screen and controller
     */
    private void newGame() {
        //close the current screen
        screen.dispose();
        //start back at the set up menu
        SetupView screen = new SetupView();
        SetupController controller = new SetupController(screen);
        screen.registerObserver(controller);
    }
}