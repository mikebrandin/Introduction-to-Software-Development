package cpsc2150.extendedConnectX;
/**
 * Description:
 *          This class represents the gameboard itself. Will be utilized to
 *          facilitate all in-game operations and store the 2d array of tokens.
 *
 * @author Mike Brandin
 * class: CPSC 2150
 * section: 002
 * date: 2/7/2021
 */
/**
 *Initialization ensures:
 *
 *          A 6 by 9 2d array of chars representing a gameboard is created with methods to check for free columns,
 *          check wins, check ties, place tokens, check the token at a position, and print the grid formatted.
 *
 *defines:
 *          Column:     the column position on the board of the highest placed token (y-coordinate)
 *          Row:        the row position on the board of the highest placed token (x-coordinate)
 *          count:      a counter to check that there are 5 tokens in a row
 *          isChar(x):  returns true if x is of type char otherwise false
 *          TotalMoves: total of number moves made by both users
 *          MaxMoves:   maximum possible moves to be made
 *
 * @invariant
 *          (NUM_TO_WIN == 5)  (Max_Moves = MAX_ROW * MAX_COLUMN) (MaxMoves >= Total_Moves >= 0)
 *          (count >= 0) (Column >= 0 AND Column <= 8) (Row >= 0 AND Row <= 5)
 *
 *
 */
public class GameBoard {

    //private data members
    private char [][] board;
    private static final int NUM_TO_WIN = 5;
    private static final int MAX_ROW = 5;
    private static final int MAX_COLUMN = 8;

    /**
     * @pre     Row = MAX_ROW AND Column = MAX_COLUMN AND TotalMoves = #TotalMoves AND MaxMoves = Row * Column
     * @post    board[MAX_ROW][MAX_COLUMN]
     * @return  true if the Column has available space otherwise false
     */
    public GameBoard(){
        board = new char[MAX_ROW+1][MAX_COLUMN+1];
    }

    /**
     * @pre     c >= 0 AND c <= 7
     * @post    CheckIfFree() == true iff (Column <= MAX_COLUMN)
     * @param   c       the Column to check whether their is space for a token
     * @return  true if the Column has available space otherwise false
     */
    public boolean checkIfFree(int c){}
    /**
     * @pre     c >= 0 AND c <= 7
     * @post    checkForWin() = true iff Column < MAX_COLUMN
     * @param   c        which last token was placed at
     * @return  returns true if last token placed resulted in a win otherwise false
     */
    public boolean checkForWin(int c){} /
    /**
     * @pre     c >= 0 AND c <= 7
     * @post    checkForWin() = true iff ((checkHorizWin() || checkVertWin() || checkDiagWin()) == true)
     * @param   c       column which last token was placed at
     * @return  true if the last token placed resulted in the player winning the game otherwise false
     */
    public boolean checkTie(){}
    /**
     * @pre     c >= 0 AND c <= 7 AND isChar(p) AND checkIfFree(c)
     * @post    TotalMoves = #TotalMoves + 1
     * @param   p       player making the move
     * @param   c       column being selected
     */
    public void  placeToken(char  p,  int  c){}
    /**
     * @pre     pos.getRow() >= 0 AND pos.getRow() <= MAX_ROW AND pos.getColumn() >= 0 AND pos.getColumn() <= MAX_COLUMN
     * @post    checkHorizWin() = true iff count = NUM_TO_WIN for (board[pos.getRow()][(0...8)] = p then count++ else count = 0)
     * @param   pos     position of last token placed
     * @param   p       character representing player who is placing token
     * @return  true if the last token placed results in a horizontal 5 in a row win for player p
     */
    public boolean checkHorizWin(BoardPosition pos, char p){}
    /**
     * @pre     pos.getRow() >= 0 AND pos.getRow() <= MAX_ROW AND pos.getColumn() >= 0 AND pos.getColumn() <= MAX_COLUMN
     * @post    checkVertWin() = true iff count = NUM_TO_WIN (for (board[(0...8)][pos.getColumn()] = p) then count++ else count = 0)
     * @param   pos     position of last token placed
     * @param   p       character representing player who is placing token
     * @return  true if the last token placed results in a vertical 5 in a row win for player p
     */
    public boolean checkVertWin(BoardPosition pos, char p){}
    /**
     * @pre     pos.getRow() >= 0 AND pos.getRow() <= MAX_ROW AND pos.getColumn() >= 0 AND pos.getColumn() <= MAX_COLUMN
     * @post    checkDiagWin() = true iff count = NUM_TO_WIN (for (board[(0...pos.getRow())+1][(0...pos.getColumn())+1] = p) then count++ else count = 0)
     * @param   pos     position of last token placed
     * @param   p       character representing player who is placing token
     * @return  true if the last token placed results in a diagonal 5 in a row win for player p
     */
    public boolean checkDiagWin(BoardPosition pos, char p){}
    /**
     * @pre     pos.getRow() >= 0 AND pos.getRow() <= MAX_ROW AND pos.getColumn() >= 0 AND pos.getColumn() <= MAX_COLUMN
     * @post    board[pos.getRow()][pos.getColumn()]
     * @param   pos     position of last token placed
     * @return  the char that is at that position on the gameboard. If there is no token at that spot, return ' '(a space character)
     */
    public char whatsAtPos(BoardPosition pos){}
    /**
     * @pre     isChar(player) AND pos.getRow() >= 0 AND pos.getRow() <= MAX_ROW AND pos.getColumn() >= 0 AND pos.getColumn() <= MAX_COLUMN
     * @post    isPlayerAtPos() = true iff (whatAtPos(pos) == player)
     * @param   pos         position of last token placed
     * @param   player      char representation of the player
     * @return  returns true if the player is at that position otherwise false
     */
    public boolean isPlayerAtPos(BoardPosition pos, char player){}
    /**
     * @pre     none
     * @post    GameBoard as String object is returned
     * @return  a fully formatted string displaying the current gameboard
     */
    public String toString(){}


}
