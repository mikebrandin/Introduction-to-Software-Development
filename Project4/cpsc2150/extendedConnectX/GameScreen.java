package cpsc2150.extendedConnectX;

import java.util.*;
import java.lang.*;


/**
 * @author Mike Brandin
 * class: CPSC 2150
 * section: 002
 * date: 2/27/2021
 *
 * Description:
 *          Interacts with user to create a Extended ConnectX game experience.
 *
 *
 * defines:
 *           Column:     the column position on the board (y-coordinate)
 *           Row:        the row position on the board (x-coordinate)
 * @invariant
 *           (0 <= Row <= 5)(0 <= Column <= 8)
 */


public class GameScreen {


    /**
     * @pre none
     * @post none
     * @param args     string array for holding command line arguments for the main function
     */
    public static void main(String[] args) {
        boolean loop = true;
        IGameBoard masterBoard = new GameBoard(0,0,0);//initialized to avoid compiler warning
        Scanner input = new Scanner(System.in);
        int columnSelect = 0;
        boolean invalid = true;
        int totalMoves = 0;
        Character playAgain;

        int numRows = 0;
        int numColumns = 0;
        int numToWin = 0;

        int playerAmount = 0;
        Character[] players = new Character[0];




        game: while (loop) {//while loop runs for length of the game session
            if (totalMoves == 0) {
                while (invalid) {//validate input for number of players and initialize player array
                    System.out.println("How many players?");
                    if (input.hasNextInt()) {//check for overflow/non-integer input
                        playerAmount = input.nextInt();

                        if (playerAmount > IGameBoard.MAX_NUM_PLAYERS) {
                            System.out.println("Must be 10 players or fewer");
                        }
                        else if (playerAmount < IGameBoard.MIN_NUM_PLAYERS) {
                            System.out.println("Must be at least 2 players");
                        }
                        else{
                            players = new Character[playerAmount];
                            break;
                        }
                    }
                    else{
                        System.out.println("Please enter a valid integer.");
                        input.next();
                    }
                }

                for (int i = 0; i < playerAmount; i++){//validate input for player characters
                    playerCheck : while (invalid) {
                        System.out.println("Enter the character to represent player " + (i + 1));

                        Character tempPlayer = input.next().charAt(0);
                        tempPlayer = Character.toUpperCase(tempPlayer);

                        for (int j = 0; j < i; j++) {
                            if (players[j].equals(tempPlayer)) {//if character is already used by another player
                                System.out.println(tempPlayer + " is already taken as a player token!");
                                continue playerCheck;
                            }
                        }
                        players[i] = tempPlayer;
                        break playerCheck;
                    }
                }

                while (invalid) {//validate user input for row length
                    System.out.println("How many rows should be on the board?");
                    if (input.hasNextInt()) {//check for overflow/non-integer input
                        numRows = input.nextInt();
                        if (numRows > IGameBoard.MAX_ROW) {
                            System.out.println("Must be 100 rows or fewer");
                        } else if (numRows < IGameBoard.MIN_ROW) {
                            System.out.println("Must be at least 3 rows");
                        } else {
                            break;
                        }
                    }
                    else{
                        System.out.println("Please enter a valid integer.");
                        input.next();
                    }
                }

                while (invalid) {//validate user input for column length
                    System.out.println("How many columns should be on the board?");
                    if (input.hasNextInt()) {//check for overflow/non-integer input
                        numColumns = input.nextInt();
                        if (numColumns > IGameBoard.MAX_COLUMN) {
                            System.out.println("Must be 100 columns or fewer");
                        } else if (numColumns < IGameBoard.MIN_COLUMN) {
                            System.out.println("Must be at least 3 columns");
                        } else {
                            break;
                        }
                    }
                    else{
                        System.out.println("Please enter a valid integer.");
                        input.next();
                    }
                }

                while (invalid) {//validate user input for num-to-win
                    System.out.println("How many in a row to win?");
                    if (input.hasNextInt()) {//check for overflow/non-integer input

                        numToWin = input.nextInt();
                        if (numToWin > numRows){
                            System.out.println("Must be less than or equal to number of rows");

                        }
                        else if (numToWin > numColumns){
                            System.out.println("Must be less than or equal to number of columns");
                        }
                        else if (numToWin > IGameBoard.MAX_NUM_TO_WIN) {
                            System.out.println("Must be 25 or fewer");
                        } else if (numToWin < IGameBoard.MIN_NUM_TO_WIN) {
                            System.out.println("Must be at least 3");
                        } else {
                            break;
                        }
                    }
                    else{
                        System.out.println("Please enter a valid integer.");
                        input.next();
                    }
                }
                while (invalid) {//validate user input for game constructor type
                    System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m)?");
                    Character tempGameType = input.next().charAt(0);
                    tempGameType = Character.toUpperCase(tempGameType);

                    if(tempGameType.equals('F')) {
                        masterBoard = new GameBoard(numRows,numColumns,numToWin);
                        break;
                    }
                    else if (tempGameType.equals('M')) {
                        masterBoard = new GameBoardMem(numRows,numColumns,numToWin);
                        break;
                    }
                    else{
                        System.out.println("Please enter F or M");
                        continue;
                    }
                }

                System.out.println(masterBoard.toString());//output the gameboard

            }//total moves == 0

            for (int i = 0; i < playerAmount; i++){//loop to go through each players turn
                while (invalid) {//players[i]'s turn
                    System.out.println("Player " + players[i] + ", what column do you want to place your marker in?");
                    if (input.hasNextInt()) {//check for overflow/non-integer input
                        columnSelect = input.nextInt();
                        //check the preconditions for placeToken
                        if (columnSelect < IGameBoard.LOWER_BOUND) {
                            System.out.println("Column cannot be less than 0");
                        } else if (columnSelect > (masterBoard.getNumColumns() - 1)) {
                            System.out.println("Column cannot be greater than " + (masterBoard.getNumColumns() - 1) + "");
                        } else if (!masterBoard.checkIfFree(columnSelect)) {
                            System.out.println("Column is full");
                        } else {//if valid input place token in selected Column
                            masterBoard.placeToken(players[i], columnSelect);
                            totalMoves++;
                            System.out.println(masterBoard.toString());//output the gameboard
                            break;//break from turn and continue game
                        }
                    }
                    else{
                        System.out.println("Please enter a valid integer.");
                        input.next();
                    }
                }
                if (totalMoves != 0 && masterBoard.checkForWin(columnSelect)) {//check if player O won and skip the first turn
                    System.out.println("Player " + players[i] + " Won!");
                    while (invalid) {
                        System.out.println("Would you like to play again? Y/N");//prompt to play again
                        playAgain = input.next().charAt(0);
                        playAgain = Character.toUpperCase(playAgain);

                        if (playAgain.equals('Y')) {//if player wants to play again reset values and continue the loop
                            totalMoves = 0;
                            continue game;
                        }
                        else if (playAgain.equals('N')) {//if player does not want to play again end game (break for loop)
                            break game;
                        }
                    }
                }

                if (masterBoard.checkTie()) {//check for tie game
                    System.out.println("Tie game!");
                    while (invalid) {
                        System.out.println("Would you like to play again? Y/N");//prompt to play again
                        playAgain = input.next().charAt(0);
                        playAgain = Character.toUpperCase(playAgain);


                        if (playAgain.equals('Y')) {//if player wants to play again reset values and continue the loop
                            totalMoves = 0;
                            continue game;
                        }
                        else if (playAgain.equals('N')) {//if player does not want to play again end game (break for loop)
                            break game;
                        }
                    }
                }

            }

        }//game loop
    }

}
