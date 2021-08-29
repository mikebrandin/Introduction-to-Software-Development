package cpsc2150.extendedConnectX;

import java.util.*;

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
 *          boardMap.size() <= NUM_ROWS * NUM_COLUMNS
 *
 * correspondence:
 *      NUM_ROWS = maxRows
 *      NUM_COLUMNS = maxColumns
 *      NUM_TO_WIN = minToWin
 *      boardMap = self
 *
 */
public class GameBoardMem extends AbsGameBoard{

    //private data members
    //private char [][] board;
    Map<Character, List<BoardPosition>> boardMap;
    private final int NUM_ROWS;
    private final int NUM_COLUMNS;
    private final int NUM_TO_WIN;

    /**
     * @pre     self instanceof(GameBoard) = true
     * @post    board = new char[NUM_ROWS][NUM_COLUMNS]
     */

    public GameBoardMem(int rows, int columns, int numToWin){
        NUM_ROWS = rows;
        NUM_COLUMNS = columns;
        NUM_TO_WIN = numToWin;

        boardMap = new HashMap<Character, List<BoardPosition>>();
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

        if (!boardMap.containsKey(p)){//if this is not first placement from a player add to an existing list
            List<BoardPosition> pList = new ArrayList<>();
            boardMap.put(p, pList);
        }

        for (Map.Entry<Character, List<BoardPosition>> mList : boardMap.entrySet()){//find row of last played token
            for (BoardPosition temp : mList.getValue()){
                if (temp.getColumn() == c){
                    rowCounter++;
                }
            }
        }

        BoardPosition pos = new BoardPosition(rowCounter, c);
        List<BoardPosition> tempList = boardMap.get(p);

        if (!tempList.contains(pos)){//create a new list for a new token
            tempList.add(pos);
        }

    }

    public char whatsAtPos(BoardPosition pos){
        for (Map.Entry<Character, List<BoardPosition>> mList : boardMap.entrySet()){
            for (BoardPosition temp : mList.getValue()){
                if (temp.equals(pos)){
                    if (mList.getKey() == 0){
                        return ' ';
                    }
                    else {
                        return mList.getKey();
                    }
                }
            }

        }
        return ' ';
    }
    @Override
    public  boolean isPlayerAtPos(BoardPosition pos, char player){
        List<BoardPosition> tempList = boardMap.get(player);//get all entry's at key of player
        if (player != ' ') {
            for (BoardPosition tempPos : tempList) {//loop through each entry
                if (tempPos.equals(pos)) {
                    return true;//if entry matches the passed position return true
                }
            }
        }
        if (player == ' '){
            return true;
        }
        return false;//otherwise, false

    }
}
