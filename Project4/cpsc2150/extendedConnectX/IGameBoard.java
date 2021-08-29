package cpsc2150.extendedConnectX;

/**
 * @author Mike Brandin
 * class: CPSC 2150
 * section: 002
 * date: 2/27/2021
 *
 * Description:
 *          This class represents the gameboard itself. Will be utilized to
 *          facilitate all in-game operations and store the 2d array of tokens.
 *
 * Defines:
 *          maxRows:     number value of the last(furthest right) row index (x-axis)
 *          maxColumns:  number value of the last(highest) column index (y-axis)
 *          minToWin:    number of tokens in line vertically, horizontally,
 *                       or diagonally in order to win the connectX game
 *          count:       counter to keep track of if there are five consecutive tokens in a winning formation
 *          topRow:      row position of the lowest empty row in a column
 *          self:        the maxRows * maxColumns sized gameboard instance.
 *
 *Initialization Ensures:   A 6 by 9 2d array of chars representing a gameboard is created with methods to check for
 *                          free columns, check wins, check ties, place tokens, check the token at a position, and print
 *                          the grid formatted.
 *
 * Constraints:
 *              maxRows = 6
 *              maxColumns = 9
 *              minToWin = 5
 *              count >= 0
 *              0 <= topRow < maxRows
 *
 */
public interface IGameBoard {
    // CONSTANTS
    /**
     * <p>This represents the maximum number of allowed players.</p>
     */
    public static final int MAX_NUM_PLAYERS = 10;
    /**
     * <p>This represents the minimum number of allowed players.</p>
     */
    public static final int MIN_NUM_PLAYERS = 2;
    /**
     * <p>This represents the minimum column number allowed on the board.</p>
     */
    public static final int MIN_COLUMN = 3;
    /**
     * <p>This represents the maximum column number allowed on the board.</p>
     */
    public static final int MAX_COLUMN = 100;
    /**
     * <p>This represents the minimum row number allowed on the board.</p>
     */
    public static final int MIN_ROW = 3;
    /**
     * <p>This represents the maximum row number allowed on the board.</p>
     */
    public static final int MAX_ROW = 100;
    /**
     * <p>This represents the maximum number of characters allowed to be needed to win.</p>
     */
    public static final int MIN_NUM_TO_WIN = 3;
    /**
     * <p>This represents the minimum number of characters allowed to be needed to win.</p>
     */
    public static final int MAX_NUM_TO_WIN = 25;
    /**
     * <p>This represents the lower bound of column coordinates.</p>
     */
    public static final int LOWER_BOUND = 0;

    // METHODS
    /**
     * @pre     none
     * @post    self = #self AND getNumRows = maxRows
     * @return  the largest index # on the row axis of the gameboard
     */
    public int getNumRows();

    /**
     * @pre     none
     * @post    self = #self AND getNumColumns = maxColumns
     * @return  the largest index # on the column axis of the gameboard
     */
    public int getNumColumns();

    /**
     * @pre     none
     * @post    self = #self AND getNumToWin = minToWin
     * @return  the number of tokens in a row, necessary to win the the game
     */
    public int getNumToWin();

    /**
     * @pre     c >= 0 AND c < MAX_COLUMN
     * @post    self = #self AND CheckIfFree == true iff (topRow < MaxRows)
     * @param   c       the Column to check whether their is space for a token
     * @return  true if the Column has available space otherwise false
     */
    public default boolean checkIfFree(int c){
        int tokenCount = 0;
        for (int i = 0; i < getNumRows(); i++){
            BoardPosition temp = new BoardPosition(i, c);//declare and initiate a temporary position variable

            if (whatsAtPos(temp) != ' '){//use function calls to check if player is at position
                tokenCount++;
            }
        }
        if (tokenCount != getNumRows()){//if counter does not equal number of rows, column is free for a token
            return true;
        }
        else{
            return false;
        }
    }
    /**
     * @pre     c >= 0 AND c < MAX_COLUMN
     * @post    self = #self AND checkForWin = true iff ((checkHorizWin() || checkVertWin() || checkDiagWin()) == true)
     * @param   c       column where last token was placed in
     * @return  true if the token placed resulted in the player winning the game otherwise false
     */

    public default boolean checkForWin(int c) {//fix uml activity
        char token = ' ';
        int rowHeight = 0;

        for (int i = 0; i < getNumRows(); i++) {//loop to find highest row in the column to create a temp position variable
            BoardPosition temp = new BoardPosition(i, c);//declare and initiate a temporary position variable
            if (whatsAtPos(temp) != ' '){//use function calls to check if player is at position
                token = whatsAtPos(temp);//assign token value
                rowHeight++;
            }
        }
        BoardPosition last = new BoardPosition(rowHeight - 1, c);//declare and initiate a position variable for the last placed token

        if (checkHorizWin(last, token) || checkVertWin(last, token) || checkDiagWin(last, token)) {//if either checkXWin function is true return true
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * @pre     none
     * @post    self = #self AND false iff (checkIfFree(0) || (checkIfFree(1) || ... || (checkIfFree(7))
     * @return  true if there is no free columns available, false otherwise
     */
    public default boolean checkTie(){
        for (int i = 0; i < getNumColumns(); i++){
            if (checkIfFree(i)){//if any column is free return false
                return false;
            }
        }
        return true;//if no columns are free return true
    }

    /**
     * @pre     c >= 0 AND c < MAX_COLUMN AND checkIfFree(c)
     * @post    board[topRow][c] = p AND topRow = #topRow + 1
     * @param   p       player making the move
     * @param   c       column being selected
     */
    public void  placeToken(char  p,  int  c);
    /**
     * @pre     pos.getRow() >= 0 AND pos.getRow() < MAX_ROW AND pos.getColumn() >= 0 AND pos.getColumn() < MAX_COLUMN
     * @post    self = #self AND checkHorizWin() = true iff counter = NUM_TO_WIN for (board[pos.getRow()][(0...8)] = p then count++ else count = 0)
     * @param   pos     position of last token placed
     * @param   p       character representing player who is placing token
     * @return  true if the last token placed results in a horizontal 5 in a row win for player p
     */
    public default boolean checkHorizWin(BoardPosition pos, char p){
        int successiveCounter = 0;

        for (int i = 0; i < getNumColumns(); i++){
            BoardPosition temp = new BoardPosition(pos.getRow(), i);//declare and initiate a temporary position variable

            if (isPlayerAtPos(temp, p)){//use function calls to check if player p is at position, update counter if it is
                successiveCounter++;
            }
            else{//if player p is not at position reset the counter
                successiveCounter = 0;
            }
            if (successiveCounter >= getNumToWin()){//if counter reaches or exceeds the number to win return true
                return true;
            }


        }
        return false;
    }
    /**
     * @pre     pos.getRow() >= 0 AND pos.getRow() < MAX_ROW AND pos.getColumn() >= 0 AND pos.getColumn() < MAX_COLUMN
     * @post    self = #self AND checkVertWin() = true iff count = NUM_TO_WIN (for (board[(0...8)][pos.getColumn()] = p) then count++ else count = 0)
     * @param   pos     position of last token placed
     * @param   p       character representing player who is placing token
     * @return  true if the last token placed results in a vertical 5 in a row win for player p
     */
    public default boolean checkVertWin(BoardPosition pos, char p){
        int successiveCounter = 0;

        for (int i = 0; i < getNumRows(); i++){
            BoardPosition temp = new BoardPosition(i, pos.getColumn());//declare and initiate a temporary position variable

            if (isPlayerAtPos(temp, p)){//use function calls to check if player p is at position, update counter if it is
                successiveCounter++;
            }
            else{//if player p is not at position reset the counter
                successiveCounter = 0;
            }

            if (successiveCounter >= getNumToWin()){//if counter reaches or exceeds the number to win return true
                return true;
            }
        }
        return false;
    }
    /**
     * @pre     pos.getRow() >= 0 AND pos.getRow() < MAX_ROW AND pos.getColumn() >= 0 AND pos.getColumn() < MAX_COLUMN
     * @post    self = #self AND checkDiagWin() = true iff count = NUM_TO_WIN (for (board[(0...pos.getRow())+/-1][(0...pos.getColumn())+/-1] = p) then count++ else count = 0)
     * @param   pos     position of last token placed
     * @param   p       character representing player who is placing token
     * @return  true if the last token placed results in a diagonal 5 in a row win for player p
     */
    public default boolean checkDiagWin(BoardPosition pos, char p){//fix uml diagram

        //check rising diagonal
        int tempRow = pos.getRow();
        int tempColumn = pos.getColumn();
        int successiveCounter = 0;

        while (tempRow > 0 && tempColumn > 0){//find index for furthest bottom left diagonal position from last placed marker
            tempRow--;
            tempColumn--;
        }

        while (tempColumn < getNumColumns() && tempRow < getNumRows()) {//check for 5 consecutive markers looping diagonally
            BoardPosition temp = new BoardPosition(tempRow, tempColumn);//declare and initiate a temporary position variable

            if(isPlayerAtPos(temp, p)){//use function calls to check if player p is at position, update counter if it is
                successiveCounter++;
            }
            else{//if player p is not at position reset the counter
                successiveCounter = 0;
            }
            if(successiveCounter == getNumToWin()){//if counter reaches the number to win return true
                return true;
            }
            tempColumn++;
            tempRow++;

        }

        tempRow = pos.getRow();//reset temporary pos variables
        tempColumn = pos.getColumn();
        successiveCounter = 0;//reset counter
        //rising diagonal is not true, so check falling diagonal
        while (tempRow < getNumRows() - 1 && tempColumn > 0){//find index for furthest bottom right diagonal position from last placed marker
            tempRow++;
            tempColumn--;
        }

        while (tempColumn < getNumColumns() && tempRow >= 0){//check for 5 consecutive markers looping diagonally
            BoardPosition temp = new BoardPosition(tempRow, tempColumn);//declare and initiate a temporary position variable

            if(isPlayerAtPos(temp, p)){//use function calls to check if player p is at position, update counter if it is
                successiveCounter++;
            }
            else{//if player p is not at position reset the counter
                successiveCounter = 0;
            }
            if(successiveCounter == getNumToWin()){//if counter reaches the number to win return true
                return true;
            }
            tempColumn++;
            tempRow--;
        }

        return false;//neither diagonals are valid
    }
    /**
     * @pre     pos.getRow() >= 0 AND pos.getRow() < MAX_ROW AND pos.getColumn() >= 0 AND pos.getColumn() < MAX_COLUMN
     * @post    self = #self AND whatsAtPos = board[pos.getRow()][pos.getColumn()]
     * @param   pos     position of last token placed
     * @return  the char that is at that position on the gameboard. If there is no token at that spot, return ' '(a space character)
     */
    public char whatsAtPos(BoardPosition pos);
    /**
     * @pre     pos.getRow() >= 0 AND pos.getRow() < MAX_ROW AND pos.getColumn() >= 0 AND pos.getColumn() < MAX_COLUMN
     * @post    self = #self AND isPlayerAtPos = true iff (whatAtPos(pos) == player)
     * @param   pos         position of last token placed
     * @param   player      char representation of the player
     * @return  returns true if the player is at that position otherwise false
     */
    public default boolean isPlayerAtPos(BoardPosition pos, char player){
        if (whatsAtPos(pos) == player){//call to see if player is at position pos
            return true;
        }
        else{
            return false;
        }
    }


}
