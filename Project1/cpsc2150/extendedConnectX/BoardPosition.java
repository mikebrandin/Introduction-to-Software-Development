package cpsc2150.extendedConnectX;
/**
 * Description:
 *          This class is used to keep track of a single coordinate on the gameboard. Has variables
 *          to hold integer values for the row and column (x & y-coordinates) as well as getters for
 *          these two values and two overridden methods for the object class's .equals() and toString()
 *
 *
 * @author Mike Brandin
 * class: CPSC 2150
 * section: 002
 * date: 2/7/2021
 */
/**
 * defines:
 *          dColumn:     the column position on the board (y-coordinate)
 *          dRow:        the row position on the board (x-coordinate)
 * @invariant
 *          (0 <= dRow <= 5)(0 <= dColumn <= 8)
 *
 */
public class BoardPosition {
    private int Row;
    private int Column;

    /**
     * @pre 0 <= r <= 5 AND 0 <= c <= 8
     * @post Row = r AND Column = c
     * @param r     value for the row coordinate
     * @param c     value for the column coordinate
     */
    public BoardPosition(int r, int c){
        Row = r;
        Column = c;
    }

    /**
     * @pre     none
     * @post    getRow() = dRow
     * @return  the number of the row positions
     */
    public int getRow(){
        //returns the row
        return Row;
    }
    /**
     * @pre     none
     * @post    getColumn() = dColumn
     * @return  the number of the column positions
     */
    public int getColumn(){
        //returns the column
        return Column;
    }
    /**
     * @pre     none
     * @post    BoardPosition as String object is returned
     * @return  a string in the following format “<row>,<column>”.
     */
    public String toString(){}

    /**
     * @pre     none
     * @post    true iff (BoardPosition.getRow() == dRow AND BoardPosition.getColumn == dColumn)
     * @return  true if row and column are equal otherwise false
     */
    public void equals(){}

}

