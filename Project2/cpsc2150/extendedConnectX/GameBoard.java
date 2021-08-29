package cpsc2150.extendedConnectX;

import java.util.Arrays;

/**
 * @author Mike Brandin
 * class: CPSC 2150
 * section: 002
 * date: 2/27/2021
 *
 *
 * @invariant
 *          NUM_ROWS = 6
 *          NUM_COLUMNS = 9
 *          NUM_TO_WIN = 5
 *
 * correspondence:
 *      NUM_ROWS = maxRows
 *      NUM_COLUMNS = maxColumns
 *      NUM_TO_WIN = minToWin
 *
 */
public class GameBoard extends AbsGameBoard{

    //private data members
    private char [][] board;
    private static final int NUM_ROWS = 6;
    private static final int NUM_COLUMNS = 9;
    private static final int NUM_TO_WIN = 5;

    /**
     * @pre     self instanceof(GameBoard) = true
     * @post    board = new char[NUM_ROWS][NUM_COLUMNS]
     */
    public GameBoard(){
        board = new char[getNumRows()][getNumColumns()];
    }

    public int getNumRows(){
        return NUM_ROWS;
    }

    public int getNumColumns(){
        return NUM_COLUMNS;
    }

    public int getNumToWin(){
        return NUM_TO_WIN;
    }

    public void  placeToken(char  p,  int  c){
        int rowCounter = 0;

        for (int i = 0; i < getNumRows(); i++){//find highest row without a player token
            if (board[i][c] == 'X' || board[i][c] == 'O'){
                rowCounter++;
            }
        }
        board[rowCounter][c] = p;//place token in that row
    }

    public char whatsAtPos(BoardPosition pos){

        return board[pos.getRow()][pos.getColumn()];
    }

}
