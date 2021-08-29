package cpsc2150.extendedConnectX;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestGameBoardMem {

    private IGameBoard IGameBoardFactory(int rows, int columns, int numToWin){
        return new GameBoardMem(rows, columns, numToWin);
    }

    private String ArrayToString(char[][] input, int rows, int columns){

        String output;
        //add top row
        output = "|";
        for (int i = 0; i < columns && i < 10; i++){
            output += " " + i;
            output += "|";
        }
        if (columns >= 10){
            for (int i = 10; i < columns; i++){
                output += i;
                output += "|";
            }
        }
        output += "\n";

        //output rest of board
        for (int i = rows - 1; i >= 0; i--){
            for (int j = 0; j < columns; j++){//double for loop to iterate through gameboard
                BoardPosition temp = new BoardPosition(i,j);//declare and initiate a temporary position variable
                output += "|";//add lines around board positions
                if (input[temp.getRow()][temp.getColumn()] != 0) {//if player is at positions output that player to the string
                    output += input[temp.getRow()][temp.getColumn()] + " ";
                }
                else{//otherwise output a blank space
                    output += "  ";
                }
            }
            output += "|\n";//add a new line after every line
        }

        return output;//returns the fully formatted string
    }

    @Test//This test case is unique and distinct because I am testing the smallest possible row, columns, and numToWin inputs.
    public void testConstructor_rows3_columns3_numToWin3(){
        int rows = 3;
        int columns = 3;
        int numToWin = 3;

        char[][] expected = new char[rows][columns];
        IGameBoard gb = IGameBoardFactory(rows, columns, numToWin);

        assertEquals(ArrayToString(expected, rows, columns), gb.toString());
    }

    @Test//This test case is unique and distinct because I am testing the largest possible row, columns, and numToWin inputs.
    public void testConstructor_rows100_columns100_numToWin25(){
        int rows = 100;
        int columns = 100;
        int numToWin = 25;

        char[][] expected = new char[rows][columns];
        IGameBoard gb = IGameBoardFactory(rows, columns, numToWin);

        assertEquals(ArrayToString(expected, rows, columns), gb.toString());
    }

    @Test//This test case is unique and distinct because I am testing the largest possible column input with the smallest possible row and numToWin inputs.
    public void testConstructor_rows3_columns100_numToWin3(){
        int rows = 3;
        int columns = 100;
        int numToWin = 3;

        char[][] expected = new char[rows][columns];
        IGameBoard gb = IGameBoardFactory(rows, columns, numToWin);

        assertEquals(ArrayToString(expected, rows, columns), gb.toString());
    }

    @Test//This test case is unique and distinct because I am testing an empty board and thus testing an empty no boundary column.
    public void testCheckIfFree_EmptyBoard_Col5(){
        int rows = 10;
        int columns = 10;
        int numToWin = 3;

        char[][] expected = new char[rows][columns];
        IGameBoard gb = IGameBoardFactory(rows, columns, numToWin);


        assertTrue(gb.checkIfFree(5));//check if 5th column is free
        assertEquals(ArrayToString(expected, rows, columns), gb.toString());//check that board is unchanged
    }

    @Test//This test case is unique and distinct because I am testing a full board and testing the lower boundary input to make sure it returns false.
    public void testCheckIfFree_FilledBoard_Col0(){
        int rows = 10;
        int columns = 10;
        int numToWin = 3;

        char[][] expected = new char[rows][columns];
        IGameBoard gb = IGameBoardFactory(rows, columns, numToWin);

        for (int i = 0; i < rows; i++){//fill the board with X's
            for (int j = 0; j < columns; j++){
                expected[i][j] = 'X';
                gb.placeToken('X', j);
            }
        }

        assertTrue(!(gb.checkIfFree(0)));//check if column 0 is not free
        assertEquals(ArrayToString(expected, rows, columns), gb.toString());//check that board is unchanged
    }

    @Test//This test case is unique and distinct because a partially full board in the one column (that is also a boundary input) that has a free place to ensure that checkIfFree returns true.
    public void testCheckIfFree_PartiallyFilledBoard_EmptyCol9Row9(){//prove a full board with one place empty. that column is free.
        int rows = 10;
        int columns = 10;
        int numToWin = 3;

        char[][] expected = new char[rows][columns];
        IGameBoard gb = IGameBoardFactory(rows, columns, numToWin);

        for (int i = 0; i < rows; i++){//fill board with X's except for topright spot
            for (int j = 0; j < columns - 1; j++) {
                expected[i][j] = 'X';
                gb.placeToken('X', j);
            }
        }

        for (int i = 0; i < rows - 1; i++){
            expected[i][9] = 'X';
            gb.placeToken('X', 9);

        }

        assertTrue((gb.checkIfFree(9)));//check if column 9 is free
        assertEquals(ArrayToString(expected, rows, columns), gb.toString());//check that board is unchanged
    }

    @Test//This test case is unique and distinct because the last placed position is the middle of the tokens and it involves no boundary cases.
    public void testCheckHorizWin_NonBoundary_LastPlacedMiddle(){
        int rows = 10;
        int columns = 10;
        int numToWin = 5;

        char[][] expected = new char[rows][columns];
        IGameBoard gb = IGameBoardFactory(rows, columns, numToWin);

        for (int i = 2; i < 4; i++){//place 4 x's in the middle of the first row with a space in the middle
            gb.placeToken('X', i);
            expected[0][i] = 'X';
        }
        for (int i = 5; i < 7; i++){
            gb.placeToken('X', i);
            expected[0][i] = 'X';
        }

        expected[0][4] = 'X';//place x in the middle of the four previously placed
        gb.placeToken('X', 4);

        BoardPosition pos = new BoardPosition(0, 4);//position of last placed token

        assertTrue(gb.checkHorizWin(pos, 'X'));//check for horizontal win in last placed position for 'X'
        assertEquals(ArrayToString(expected, rows, columns), gb.toString());//check that board is unchanged
    }

    @Test//This test case is unique and distinct because the last placed position is the middle of the tokens the top left boundary place.
    public void testCheckHorizWin_TopLeft_LastPlacedMiddle(){
        int rows = 10;
        int columns = 10;
        int numToWin = 5;

        char[][] expected = new char[rows][columns];
        IGameBoard gb = IGameBoardFactory(rows, columns, numToWin);

        for (int i = 0; i < rows-1; i++){//fill all but the top row with O's
            for (int j = 0; j < columns ; j++) {
                gb.placeToken('O', j);
                expected[i][j] = 'O';
            }
        }

        //place 4 X's on the top left row with one space in the center
        for (int i = 0; i < 2; i++){
            gb.placeToken('X', i);
            expected[rows-1][i] = 'X';
        }

        for (int i = 3; i < 5; i++){
            gb.placeToken('X', i);
            expected[rows-1][i] = 'X';
        }

        //place in the middle of the X's
        expected[rows-1][2] = 'X';
        gb.placeToken('X', 2);

        BoardPosition pos = new BoardPosition(rows-1, 2);//position of last placed token

        assertTrue(gb.checkHorizWin(pos, 'X'));//check for horizontal win in last placed position for 'X'
        assertEquals(ArrayToString(expected, rows, columns), gb.toString());//check that board is unchanged
    }

    @Test//This test case is unique and distinct because the last placed position is on the right and also on the lower bottom boundary.
    public void testCheckHorizWin_BottomRight_LastPlacedRight(){
        int rows = 10;
        int columns = 10;
        int numToWin = 5;

        char[][] expected = new char[rows][columns];
        IGameBoard gb = IGameBoardFactory(rows, columns, numToWin);

        //place 4 X's on the bottom right row with one space in the center
        for (int i = rows-numToWin; i < rows-1; i++){
            gb.placeToken('X', i);
            expected[0][i] = 'X';
        }

        //place in the middle of the X's
        expected[0][columns-1] = 'X';
        gb.placeToken('X', columns-1);

        BoardPosition pos = new BoardPosition(0, columns-1);//position of last placed token

        assertTrue(gb.checkHorizWin(pos, 'X'));//check for horizontal win in last placed position for 'X'
        assertEquals(ArrayToString(expected, rows, columns), gb.toString());//check that board is unchanged
    }

    @Test//This test case is unique and distinct because the last placed position is on the right it tests both the left and right boundaries by taking up the entire row. Also, the number of consecutive X’s exceeds the numToWin to test.
    public void testCheckHorizWin_R2_EntireRow_LastPlacedLeft(){
        int rows = 10;
        int columns = 10;
        int numToWin = 5;

        char[][] expected = new char[rows][columns];
        IGameBoard gb = IGameBoardFactory(rows, columns, numToWin);

        for (int i = 0; i < 2; i++){//fill first two rows with O's
            for (int j = 0; j < columns ; j++) {
                gb.placeToken('O', j);
                expected[i][j] = 'O';
            }
        }

        for (int i = 0; i < columns-1; i++){//fill second row with x's except right most space
            gb.placeToken('X', i);
            expected[2][i] = 'X';
        }

        gb.placeToken('X',columns-1);//fill right most space on row 2
        expected[2][columns-1] = 'X';

        BoardPosition pos = new BoardPosition(2, columns-1);//position of last placed token

        assertTrue(gb.checkHorizWin(pos, 'X'));//check for horizontal win in last placed position for 'X'
        assertEquals(ArrayToString(expected, rows, columns), gb.toString());//check that board is unchanged
    }

    @Test//This test case is unique and distinct because the last placed position is on the upper left boundary of the game board in a partially filled board.
    public void testCheckVertWin_TopLeft_C0(){
        int rows = 10;
        int columns = 10;
        int numToWin = 5;

        char[][] expected = new char[rows][columns];
        IGameBoard gb = IGameBoardFactory(rows, columns, numToWin);

        for (int i = 0; i < rows-numToWin; i++){//fill first five rows with O's
            for (int j = 0; j < columns ; j++) {
                gb.placeToken('O', j);
                expected[i][j] = 'O';
            }
        }

        for (int i = rows-numToWin; i < rows-1; i++){//make vertical column of 4 X's
            gb.placeToken('X', 0);
            expected[i][0] = 'X';
        }

        gb.placeToken('X', 0);//place 5th X on top
        expected[rows-1][0] = 'X';

        BoardPosition pos = new BoardPosition(rows-1, 0);//position of last placed token

        assertTrue(gb.checkVertWin(pos, 'X'));//check for vertical win in last placed position for 'X'
        assertEquals(ArrayToString(expected, rows, columns), gb.toString());//check that board is unchanged
    }

    @Test//This test case is unique and distinct because the last placed position is on the upper right boundary of the game board in a partially filled board.
    public void testCheckVertWin_TopRight_C9(){
        int rows = 10;
        int columns = 10;
        int numToWin = 5;

        char[][] expected = new char[rows][columns];
        IGameBoard gb = IGameBoardFactory(rows, columns, numToWin);

        for (int i = 0; i < rows-numToWin; i++){//fill first five rows with O's
            for (int j = 0; j < columns ; j++) {
                gb.placeToken('O', j);
                expected[i][j] = 'O';
            }
        }

        for (int i = rows-numToWin; i < rows-1; i++){//make vertical column of 4 X's
            gb.placeToken('X', columns-1);
            expected[i][columns-1] = 'X';
        }

        gb.placeToken('X', columns-1);//place 5th X on top
        expected[rows-1][columns-1] = 'X';

        BoardPosition pos = new BoardPosition(rows-1, columns-1);//position of last placed token

        assertTrue(gb.checkVertWin(pos, 'X'));//check for vertical win in last placed position for 'X'
        assertEquals(ArrayToString(expected, rows, columns), gb.toString());//check that board is unchanged
    }

    @Test//This test case is unique and distinct because the last placed position right boundary and it involves the lower right boundary.
    public void testCheckVertWin_BottomRight_C9(){
        int rows = 10;
        int columns = 10;
        int numToWin = 5;

        char[][] expected = new char[rows][columns];
        IGameBoard gb = IGameBoardFactory(rows, columns, numToWin);


        for (int i = 0; i < numToWin-1; i++){//make vertical column of 4 X's
            gb.placeToken('X', columns-1);
            expected[i][columns-1] = 'X';
        }

        gb.placeToken('X', columns-1);//place 5th X on top
        expected[numToWin-1][columns-1] = 'X';

        BoardPosition pos = new BoardPosition(numToWin-1, columns-1);//position of last placed token

        assertTrue(gb.checkVertWin(pos, 'X'));//check for vertical win in last placed position for 'X'
        assertEquals(ArrayToString(expected, rows, columns), gb.toString());//check that board is unchanged
    }

    @Test//This test case is unique and distinct because the last placed position involves no boundary cases and is approximately in the center.
    public void testCheckVertWin_MiddleCenter_C4(){
        int rows = 10;
        int columns = 10;
        int numToWin = 5;

        char[][] expected = new char[rows][columns];
        IGameBoard gb = IGameBoardFactory(rows, columns, numToWin);

        for (int i = 0; i < 3; i++){//fill first three rows with O's
            for (int j = 0; j < columns ; j++) {
                gb.placeToken('O', j);
                expected[i][j] = 'O';
            }
        }

        for (int i = 3; i < 7; i++){//make a stack of 4 X's at column 4 (rear center)
            gb.placeToken('X', 4);
            expected[i][4] = 'X';
        }

        gb.placeToken('X', 4);//add a 5th X to the top
        expected[7][4] = 'X';

        BoardPosition pos = new BoardPosition(7, 4);//position of last placed token

        assertTrue(gb.checkVertWin(pos, 'X'));//check for vertical win in last placed position for 'X'
        assertEquals(ArrayToString(expected, rows, columns), gb.toString());//check that board is unchanged
    }

    @Test//This test case is unique and distinct because the last placed position is the top left boundary position and the function must check in the NW/SE direction.
    public void testCheckDiagWin_LastPlacedTopLeft_C0_NWSE(){
        int rows = 10;
        int columns = 10;
        int numToWin = 5;

        char[][] expected = new char[rows][columns];
        IGameBoard gb = IGameBoardFactory(rows, columns, numToWin);

        for (int j = 0; j < rows-1; j++){//place O's to lay foundation for X's
            gb.placeToken('O', 0);
            expected[j][0] = 'O';
        }

        for (int j = 0; j < rows-2; j++){
            gb.placeToken('O', 1);
            expected[j][1] = 'O';
        }

        gb.placeToken('X', 1);//place X's on top of O's
        expected[rows-2][1] = 'X';

        for (int j = 0; j < rows-3; j++){
            gb.placeToken('O', 2);
            expected[j][2] = 'O';
        }

        gb.placeToken('X', 2);
        expected[rows-3][2] = 'X';

        for (int j = 0; j < rows-4; j++){
            gb.placeToken('O', 3);
            expected[j][3] = 'O';
        }

        gb.placeToken('X', 3);
        expected[rows-4][3] = 'X';

        for (int j = 0; j < rows-5; j++){
            gb.placeToken('O', 4);
            expected[j][4] = 'O';
        }

        gb.placeToken('X', 4);
        expected[rows-5][4] = 'X';

        gb.placeToken('X', 0);//last placed position is the top left
        expected[rows-1][0] = 'X';

        BoardPosition pos = new BoardPosition(rows-1, 0);//position of last placed token

        assertTrue(gb.checkDiagWin(pos, 'X'));//check for diagonal win in last placed position for 'X'
        assertEquals(ArrayToString(expected, rows, columns), gb.toString());//check that board is unchanged
    }

    @Test//This test case is unique and distinct because the last placed position is the top right boundary position, and the function must check in the NE/SW direction.
    public void testCheckDiagWin_LastPlacedTopRight_C9_NESW(){
        int rows = 10;
        int columns = 10;
        int numToWin = 5;

        char[][] expected = new char[rows][columns];
        IGameBoard gb = IGameBoardFactory(rows, columns, numToWin);

        for (int j = 0; j < rows-1; j++){//place O's to lay foundation for X's
            gb.placeToken('O', columns-1);
            expected[j][columns-1] = 'O';
        }

        for (int j = 0; j < rows-2; j++){
            gb.placeToken('O', columns-2);
            expected[j][columns-2] = 'O';
        }

        gb.placeToken('X', columns-2);//place X's on top of O's
        expected[rows-2][columns-2] = 'X';

        for (int j = 0; j < rows-3; j++){
            gb.placeToken('O', columns-3);
            expected[j][columns-3] = 'O';
        }

        gb.placeToken('X', columns-3);
        expected[rows-3][columns-3] = 'X';

        for (int j = 0; j < rows-4; j++){
            gb.placeToken('O', columns-4);
            expected[j][columns-4] = 'O';
        }

        gb.placeToken('X', columns-4);
        expected[rows-4][columns-4] = 'X';

        for (int j = 0; j < rows-5; j++){
            gb.placeToken('O', columns-5);
            expected[j][columns-5] = 'O';
        }

        gb.placeToken('X', columns-5);
        expected[rows-5][columns-5] = 'X';

        gb.placeToken('X', columns-1);//last placed position is the top right
        expected[rows-1][columns-1] = 'X';

        BoardPosition pos = new BoardPosition(rows-1, columns-1);//position of last placed token

        assertTrue(gb.checkDiagWin(pos, 'X'));//check for diagonal win in last placed position for 'X'
        assertEquals(ArrayToString(expected, rows, columns), gb.toString());//check that board is unchanged
    }

    @Test//This test case is unique and distinct because the last placed position is the bottom right boundary position, and the function must check in the NW/SE direction.
    public void testCheckDiagWin_LastPlacedBottomRight_C9_NWSE(){
        int rows = 10;
        int columns = 10;
        int numToWin = 5;

        char[][] expected = new char[rows][columns];
        IGameBoard gb = IGameBoardFactory(rows, columns, numToWin);


        gb.placeToken('O', 8);//place O's to lay foundation for X's
        expected[0][columns-2] = 'O';

        gb.placeToken('X', columns-2);//place X's on top of O's
        expected[1][columns-2] = 'X';

        for (int j = 0; j < 2; j++){
            gb.placeToken('O', columns-3);
            expected[j][columns-3] = 'O';
        }

        gb.placeToken('X', columns-3);
        expected[2][columns-3] = 'X';

        for (int j = 0; j < 3; j++){
            gb.placeToken('O', columns-4);
            expected[j][columns-4] = 'O';
        }

        gb.placeToken('X', columns-4);
        expected[3][columns-4] = 'X';

        for (int j = 0; j < 4; j++){
            gb.placeToken('O', columns-5);
            expected[j][columns-5] = 'O';
        }

        gb.placeToken('X', columns-5);
        expected[4][columns-5] = 'X';


        gb.placeToken('X', columns-1);//last placed position is the bottom right
        expected[0][columns-1] = 'X';

        BoardPosition pos = new BoardPosition(rows-1, 0);//position of last placed token

        assertTrue(gb.checkDiagWin(pos, 'X'));//check for diagonal win in last placed position for 'X'
        assertEquals(ArrayToString(expected, rows, columns), gb.toString());//check that board is unchanged
    }

    @Test//This test case is unique and distinct because the last placed position is the bottom left boundary position, and the function must check in the NE/SW direction.
    public void testCheckDiagWin_LastPlacedBottomLeft_C0_NESW(){
        int rows = 10;
        int columns = 10;
        int numToWin = 5;

        char[][] expected = new char[rows][columns];
        IGameBoard gb = IGameBoardFactory(rows, columns, numToWin);


        gb.placeToken('O', 1);//place O's to lay foundation for X's
        expected[0][1] = 'O';

        gb.placeToken('X', 1);//place X's on top of O's
        expected[1][1] = 'X';

        for (int j = 0; j < 2; j++){
            gb.placeToken('O', 2);
            expected[j][2] = 'O';
        }

        gb.placeToken('X', 2);
        expected[2][2] = 'X';

        for (int j = 0; j < 3; j++){
            gb.placeToken('O', 3);
            expected[j][3] = 'O';
        }

        gb.placeToken('X', 3);
        expected[3][3] = 'X';

        for (int j = 0; j < 4; j++){
            gb.placeToken('O', 4);
            expected[j][4] = 'O';
        }

        gb.placeToken('X', 4);
        expected[4][4] = 'X';


        gb.placeToken('X', 0);//last placed position is the bottom left
        expected[0][0] = 'X';

        BoardPosition pos = new BoardPosition(0, 0);//position of last placed token

        assertTrue(gb.checkDiagWin(pos, 'X'));//check for diagonal win in last placed position for 'X'
        assertEquals(ArrayToString(expected, rows, columns), gb.toString());//check that board is unchanged
    }

    @Test//This test case is unique and distinct because the last placed position is the bottom left boundary position and the top right boundary is also involved and the function must check in the NE/SW direction.
    public void testCheckDiagWin_LastPlacedLeftMost_C0_NESW_TopRight(){
        int rows = 10;
        int columns = 10;
        int numToWin = 5;

        char[][] expected = new char[rows][columns];
        IGameBoard gb = IGameBoardFactory(rows, columns, numToWin);

        int tempCol = columns;

        for (int i = 0; i < rows-1; i++){//place O's to lay foundation for X's
            for (int j = tempCol-1; j > 0; j--) {
                gb.placeToken('O', tempCol - 1);
            }
            tempCol-=1;
        }
        int tempCol2 = columns;

        for (int i = 0; i < rows-1; i++){
            tempCol2 -= 1;
        }

        for (int j = 0; j < rows-1; j++){
            expected[j][columns-1] = 'O';
        }

        for (int j = 0; j < rows-2; j++){
            expected[j][columns-2] = 'O';
        }

        for (int j = 0; j < rows-3; j++){
            expected[j][columns-3] = 'O';
        }
        for (int j = 0; j < rows-4; j++){
            expected[j][columns-4] = 'O';
        }

        for (int j = 0; j < rows-5; j++){
            expected[j][columns-5] = 'O';
        }
        for (int j = 0; j < rows-6; j++){
            expected[j][columns-6] = 'O';
        }
        for (int j = 0; j < rows-7; j++){
            expected[j][columns-7] = 'O';
        }
        for (int j = 0; j < rows-8; j++){
            expected[j][columns-8] = 'O';
        }
        for (int j = 0; j < rows-9; j++){
            expected[j][columns-9] = 'O';
        }

        for (int i = rows-1; i > 0; i--){//place X's on top of O's
            expected[i][i] = 'X';
            gb.placeToken('X', i);
        }
        expected[0][0] = 'X';
        gb.placeToken('X', tempCol2-1);//last placed position is the left most

        BoardPosition pos = new BoardPosition(0, 0);//position of last placed token

        assertTrue(gb.checkDiagWin(pos, 'X'));//check for diagonal win in last placed position for 'X'
        assertEquals(ArrayToString(expected, rows, columns), gb.toString());//check that board is unchanged
    }

    @Test//This test case is unique and distinct because the last placed position is the bottom right boundary position, and the top left boundary position is also involved, and the function must check in the NW/SE direction.
    public void testCheckDiagWin_LastPlacedRightMost_C9_NWSE_TopLeft(){
        int rows = 10;
        int columns = 10;
        int numToWin = 5;

        char[][] expected = new char[rows][columns];
        IGameBoard gb = IGameBoardFactory(rows, columns, numToWin);

        for (int j = 0; j < rows-1; j++){//place O's to lay foundation for X's
            gb.placeToken('O', 0);
            expected[j][0] = 'O';
        }

        gb.placeToken('X', 0);//place X's on top of O's
        expected[rows-1][0] = 'X';

        for (int j = 0; j < rows-2; j++){
            gb.placeToken('O', 1);
            expected[j][1] = 'O';
        }

        gb.placeToken('X', 1);
        expected[rows-2][1] = 'X';

        for (int j = 0; j < rows-3; j++){
            gb.placeToken('O', 2);
            expected[j][2] = 'O';
        }

        gb.placeToken('X', 2);
        expected[rows-3][2] = 'X';

        for (int j = 0; j < rows-4; j++){
            gb.placeToken('O', 3);
            expected[j][3] = 'O';
        }

        gb.placeToken('X', 3);
        expected[rows-4][3] = 'X';

        for (int j = 0; j < rows-5; j++){
            gb.placeToken('O', 4);
            expected[j][4] = 'O';
        }

        gb.placeToken('X', 4);
        expected[rows-5][4] = 'X';

        for (int j = 0; j < rows-6; j++){
            gb.placeToken('O', 5);
            expected[j][5] = 'O';
        }

        gb.placeToken('X', 5);
        expected[rows-6][5] = 'X';

        for (int j = 0; j < rows-7; j++){
            gb.placeToken('O', 6);
            expected[j][6] = 'O';
        }

        gb.placeToken('X', 6);
        expected[rows-7][6] = 'X';

        for (int j = 0; j < rows-8; j++){
            gb.placeToken('O', 7);
            expected[j][7] = 'O';
        }

        gb.placeToken('X', 7);
        expected[rows-8][7] = 'X';

        for (int j = 0; j < rows-9; j++){
            gb.placeToken('O', 8);
            expected[j][8] = 'O';
        }

        gb.placeToken('X', 8);
        expected[rows-9][8] = 'X';

        gb.placeToken('X', 9);//last placed position is the right most
        expected[0][9] = 'X';

        BoardPosition pos = new BoardPosition(0, columns-1);//position of last placed token

        assertTrue(gb.checkDiagWin(pos, 'X'));//check for diagonal win in last placed position for 'X'
        assertEquals(ArrayToString(expected, rows, columns), gb.toString());//check that board is unchanged
    }

    @Test//This test case is unique and distinct because the last placed position is a nonboundary in the middle of a diagonal of X’s, and none of the X’s touch a boundary, and the function must check in the NW/SE direction.
    public void testCheckDiagWin_LastPlacedCentrally_C5_NWSE_Middle(){
        int rows = 10;
        int columns = 10;
        int numToWin = 5;

        char[][] expected = new char[rows][columns];
        IGameBoard gb = IGameBoardFactory(rows, columns, numToWin);

        for (int i = 0; i < rows; i++){//place O's to lay foundation for X's
            for (int j = 0; j < 2; j++) {
                gb.placeToken('O', i);
                expected[j][i] = 'O';
            }
        }

        int tempCol = columns;

        for (int i = 0; i < rows-4; i++){
            for (int j = 0; j < tempCol-1; j++) {
                gb.placeToken('O', j);
                expected[i][j] = 'O';
            }
            tempCol-=1;
        }

        for (int i = 0; i < rows-4; i++){
            for (int j = 0; j < 1; j++) {
                expected[i][j] = 'O';
            }
            tempCol-=1;
        }
        int tempCol2 = columns;

        expected[2][7] = 'O';
        expected[2][8] = 'O';
        expected[2][9] = 'O';
        gb.placeToken('O', 9);
        expected[3][6] = 'O';
        expected[3][7] = 'O';
        expected[4][5] = 'O';
        expected[4][5] = 'O';
        expected[4][6] = 'O';
        expected[5][5] = 'O';
        expected[5][4] = 'O';

        for (int i = 0; i < 4; i++){
            expected[7][i] = 'O';
        }
        for (int i = 0; i < 5; i++){
            expected[6][i] = 'O';
        }

        expected[8][3] = 'X';//place X's on top of O's
        gb.placeToken('X', 3);
        expected[7][4] = 'X';
        gb.placeToken('X', 4);

        expected[5][6] = 'X';
        gb.placeToken('X', 6);
        expected[4][7] = 'X';
        gb.placeToken('X', 7);

        expected[6][5] = 'X';
        gb.placeToken('X', 5);//last placed position is in the middle of the diagonal most

        BoardPosition pos = new BoardPosition(6, 5);//position of last placed token

        assertTrue(gb.checkDiagWin(pos, 'X'));//check for diagonal win in last placed position for 'X'
        assertEquals(ArrayToString(expected, rows, columns), gb.toString());//check that board is unchanged
    }

    @Test//This test case is unique and distinct we are testing a full board for a tie.
    public void testCheckTie_EmptyBoard(){
        int rows = 10;
        int columns = 10;
        int numToWin = 5;

        char[][] expected = new char[rows][columns];
        IGameBoard gb = IGameBoardFactory(rows, columns, numToWin);

        assertTrue(!(gb.checkTie()));//check tie should return false
        assertEquals(ArrayToString(expected, rows, columns), gb.toString());//check that board is unchanged
    }

    @Test//This test case is unique and distinct we are testing a full board for a tie.
    public void testCheckTie_FullBoard(){
        int rows = 10;
        int columns = 10;
        int numToWin = 5;

        char[][] expected = new char[rows][columns];
        IGameBoard gb = IGameBoardFactory(rows, columns, numToWin);

        for (int i = 0; i < rows; i++){//fill board with Xs
            for (int j = 0; j < columns; j++){
                expected[i][j] = 'X';
                gb.placeToken('X', i);
            }
        }

        assertTrue((gb.checkTie()));
        assertEquals(ArrayToString(expected, rows, columns), gb.toString());//check that board is unchanged
    }

    @Test//This test case is unique and distinct we are testing a nearly full board for a tie.
    public void testCheckTie_PartiallyFullBoard_EmptyPlaceTopRight(){
        int rows = 10;
        int columns = 10;
        int numToWin = 5;

        char[][] expected = new char[rows][columns];
        IGameBoard gb = IGameBoardFactory(rows, columns, numToWin);

        for (int i = 0; i < rows; i++){//fill entire board with Xs except for top right
            for (int j = 0; j < columns-1; j++){
                expected[i][j] = 'X';
                gb.placeToken('X', i);
            }
        }

        for (int i = 0; i < rows -1; i++){
            expected[i][columns-1] = 'X';
            gb.placeToken('X', i);
        }

        assertTrue(!(gb.checkTie()));//checkTie should return false
        assertEquals(ArrayToString(expected, rows, columns), gb.toString());//check that board is unchanged
    }

    @Test//This test case is unique and distinct we are testing a nearly empty board for a tie.
    public void testCheckTie_PartiallyFullBoard_OccupiedSpace_BottomLeft(){
        int rows = 10;
        int columns = 10;
        int numToWin = 5;

        char[][] expected = new char[rows][columns];
        IGameBoard gb = IGameBoardFactory(rows, columns, numToWin);

        expected[0][0] = 'X';//place one X in the bottom left
        gb.placeToken('X', 0);

        assertTrue(!(gb.checkTie()));
        assertEquals(ArrayToString(expected, rows, columns), gb.toString());//check that board is unchanged
    }

    @Test//This test case is unique and distinct because it tests the top right boundary case on an empty board.
    public void testWhatsAtPos_EmptyBoard_TopRight(){
        int rows = 10;
        int columns = 10;
        int numToWin = 3;

        char[][] expected = new char[rows][columns];
        IGameBoard gb = IGameBoardFactory(rows, columns, numToWin);

        BoardPosition pos = new BoardPosition(rows - 1, columns - 1);

        assertEquals(' ', gb.whatsAtPos(pos));//whatsAtPos should equal ' '
        assertEquals(ArrayToString(expected, rows, columns), gb.toString());//check that board is unchanged
    }

    @Test//This test case is unique and distinct because it tests the top right boundary case on a full board.
    public void testWhatsAtPos_FilledBoard_TopRight(){
        int rows = 10;
        int columns = 10;
        int numToWin = 3;

        char[][] expected = new char[rows][columns];
        IGameBoard gb = IGameBoardFactory(rows, columns, numToWin);

        for (int i = 0; i < rows; i++){//fill entire board with Xs
            for (int j = 0; j < columns; j++){
                gb.placeToken('X', j);
                expected[i][j] = 'X';
            }
        }

        BoardPosition pos = new BoardPosition(rows - 1, columns - 1);

        assertEquals('X', gb.whatsAtPos(pos));//whatsAtPos should equal 'X'
        assertEquals(ArrayToString(expected, rows, columns), gb.toString());//check that board is unchanged
    }

    @Test//This test case is unique and distinct because it tests the bottom left boundary case on a full board.
    public void testWhatsAtPos_FilledBoard_BottomLeft(){
        int rows = 10;
        int columns = 10;
        int numToWin = 3;

        char[][] expected = new char[rows][columns];
        IGameBoard gb = IGameBoardFactory(rows, columns, numToWin);

        for (int i = 0; i < rows; i++){//fill entire board with Xs
            for (int j = 0; j < columns; j++){
                gb.placeToken('X', j);
                expected[i][j] = 'X';
            }
        }

        BoardPosition pos = new BoardPosition(0, 0);

        assertEquals('X', gb.whatsAtPos(pos));
        assertEquals(ArrayToString(expected, rows, columns), gb.toString());//check that board is unchanged
    }

    @Test//This test case is unique and distinct because it tests the top right boundary case on a partially full board.
    public void testWhatsAtPos_PartiallyFilledBoard_EmptyTopRight(){
        int rows = 10;
        int columns = 10;
        int numToWin = 3;

        char[][] expected = new char[rows][columns];
        IGameBoard gb = IGameBoardFactory(rows, columns, numToWin);

        for (int i = 0; i < rows; i++){//fill entire board with X's except for top right space
            for (int j = 0; j < columns; j++){
                if (!(i == rows - 1 && j == columns - 1)) {
                    gb.placeToken('X', j);
                    expected[i][j] = 'X';
                }
            }
        }

        BoardPosition pos = new BoardPosition(rows - 1, columns - 1);

        assertEquals(' ', gb.whatsAtPos(pos));
        assertEquals(ArrayToString(expected, rows, columns), gb.toString());//check that board is unchanged
    }

    @Test//This test case is unique and distinct because it tests the top left boundary case on a partially filled board.
    public void testWhatsAtPos_PartiallyFilledBoard_OccupiedTopLeft(){
        int rows = 10;
        int columns = 10;
        int numToWin = 3;

        char[][] expected = new char[rows][columns];
        IGameBoard gb = IGameBoardFactory(rows, columns, numToWin);

        for (int i = 0; i < rows; i++){//fill first column with Os
            for (int j = 0; j < 1; j++){
                gb.placeToken('O', j);
                expected[i][j] = 'O';
            }
        }

        BoardPosition pos = new BoardPosition(rows - 1, 0);

        assertEquals('O', gb.whatsAtPos(pos));
        assertEquals(ArrayToString(expected, rows, columns), gb.toString());//check that board is unchanged
    }

    @Test//This test case is unique and distinct because it tests the top left boundary for an occupied O when the column has alternating Xs and Os.
    public void testIsPlayerAtPos_PartiallyFilledBoard_OccupiedTopLeft(){
        int rows = 10;
        int columns = 10;
        int numToWin = 3;

        char[][] expected = new char[rows][columns];
        IGameBoard gb = IGameBoardFactory(rows, columns, numToWin);

        for (int i = 0; i < rows/2; i++){//fill first column with alternating Xs and Os
            for (int j = 0; j < 1; j++){
                gb.placeToken('X', j);
                gb.placeToken('O', j);
            }
        }
        for (int i = 0; i < rows; i+=2){
            for (int j = 0; j < 1; j++){
                expected[i][j] = 'X';
            }
        }
        for (int i = 1; i < rows; i+=2){
            for (int j = 0; j < 1; j++){
                expected[i][j] = 'O';

            }
        }
        BoardPosition pos = new BoardPosition(rows - 1, 0);

        assertTrue(gb.isPlayerAtPos(pos, 'O'));//check if O is at pos
        assertEquals(ArrayToString(expected, rows, columns), gb.toString());//check that board is unchanged
    }

    @Test//This test case is unique and distinct because it tests the top right boundary for an empty space when the rest of the gameboard is full of alternating X’s and O’s.
    public void testIsPlayerAtPos_PartiallyFilledBoard_EmptyTopRight(){
        int rows = 10;
        int columns = 10;
        int numToWin = 3;

        char[][] expected = new char[rows][columns];
        IGameBoard gb = IGameBoardFactory(rows, columns, numToWin);
        for (int i = 0; i < rows/2; i++){//fill board with alternating Xs and Os excluding top right spot
            for (int j = 0; j < columns; j++){
                if (!(i == rows/2 - 1 && j == columns - 1)) {
                    gb.placeToken('X', j);
                    gb.placeToken('O', j);
                }
            }
        }
        gb.placeToken('X', columns - 1);


        for (int i = 0; i < rows; i+=2){
            for (int j = 0; j < columns; j++){
                expected[i][j] = 'X';
            }
        }
        for (int i = 1; i < rows; i+=2){
            for (int j = 0; j < columns; j++){
                if (!(i == rows - 1 && j == columns - 1)) {
                    expected[i][j] = 'O';
                }
            }
        }

        BoardPosition pos = new BoardPosition(rows - 1, columns - 1);//check top right pos

        assertTrue(gb.isPlayerAtPos(pos, ' '));//check if ' ' is at pos
        assertEquals(ArrayToString(expected, rows, columns), gb.toString());//check that board is unchanged
    }

    @Test//This test case is unique and distinct because it tests the top left boundary for an occupied X when the entire board is full of alternating X’s and O’s.
    public void testIsPlayerAtPos_FilledBoard_TopLeft(){
        int rows = 10;
        int columns = 10;
        int numToWin = 3;

        char[][] expected = new char[rows][columns];
        IGameBoard gb = IGameBoardFactory(rows, columns, numToWin);
        for (int i = 0; i < rows/2; i++){//fill gameboard entirely with alternating X's and O's
            for (int j = 0; j < columns; j++){
                gb.placeToken('O', j);
                gb.placeToken('X', j);
            }
        }

        for (int i = 0; i < rows; i+=2){
            for (int j = 0; j < columns; j++){
                expected[i][j] = 'O';

            }
        }
        for (int i = 1; i < rows; i+=2){
            for (int j = 0; j < columns; j++){
                expected[i][j] = 'X';
            }
        }

        BoardPosition pos = new BoardPosition(rows - 1, 0);//top left pos

        assertTrue(gb.isPlayerAtPos(pos, 'X'));//check if 'X' is at pos
        assertEquals(ArrayToString(expected, rows, columns), gb.toString());//check that board is unchanged
    }

    @Test//This test case is unique and distinct because it tests the bottom left boundary for an occupied O when the entire board is full of alternating X’s and O’s.
    public void testIsPlayerAtPos_FilledBoard_BottomLeft(){
        int rows = 10;
        int columns = 10;
        int numToWin = 3;

        char[][] expected = new char[rows][columns];
        IGameBoard gb = IGameBoardFactory(rows, columns, numToWin);
        for (int i = 0; i < rows/2; i++){//fill gameboard entirely with alternating X's and O's
            for (int j = 0; j < columns; j++){
                gb.placeToken('O', j);
                gb.placeToken('X', j);
            }
        }

        for (int i = 0; i < rows; i+=2){
            for (int j = 0; j < columns; j++){
                expected[i][j] = 'O';

            }
        }
        for (int i = 1; i < rows; i+=2){
            for (int j = 0; j < columns; j++){
                expected[i][j] = 'X';
            }
        }

        BoardPosition pos = new BoardPosition(0, 0);//bottom left pos

        assertTrue(gb.isPlayerAtPos(pos, 'O'));//check if 'O' is at pos
        assertEquals(ArrayToString(expected, rows, columns), gb.toString());//check that board is unchanged
    }

    @Test//This test case is unique and distinct because it tests the top left boundary for an empty space on a empty board.
    public void testIsPlayerAtPos_EmptyBoard_TopLeft(){
        int rows = 10;
        int columns = 10;
        int numToWin = 3;

        char[][] expected = new char[rows][columns];
        IGameBoard gb = IGameBoardFactory(rows, columns, numToWin);

        BoardPosition pos = new BoardPosition(0, columns - 1);//pos is top left

        assertTrue(gb.isPlayerAtPos(pos, ' '));//check if ' ' is at pos
        assertEquals(ArrayToString(expected, rows, columns), gb.toString());//check that board is unchanged
    }

    @Test//This test case is unique and distinct because it tests the bottom left boundary on in an empty board.
    public void testPlaceToken_BottomLeft_rows10_columns10(){
        int rows = 10;
        int columns = 10;
        int numToWin = 3;

        char[][] expected = new char[rows][columns];
        IGameBoard gb = IGameBoardFactory(rows, columns, numToWin);

        expected[0][0] = 'X';//place token in bottom left boundary
        gb.placeToken('X', 0);

        assertEquals(ArrayToString(expected, rows, columns), gb.toString());//check that board is unchanged
    }

    @Test//This test case is unique and distinct because it tests the bottom right boundary on an empty board of minimum size.
    public void testPlaceToken_BottomRigh_rows3_columns3(){
        int rows = 3;
        int columns = 3;
        int numToWin = 3;

        char[][] expected = new char[rows][columns];
        IGameBoard gb = IGameBoardFactory(rows, columns, numToWin);

        expected[0][columns - 1] = 'X';//place token in bottom right boundary
        gb.placeToken('X', columns - 1);

        assertEquals(ArrayToString(expected, rows, columns), gb.toString());//check that board is unchanged
    }

    @Test//This test case is unique and distinct because it tests the top right boundary on a partially full board of a max sized gameboard.
    public void testPlaceToken_TopRight_rows100_columns100(){
        int rows = 100;
        int columns = 100;
        int numToWin = 3;

        char[][] expected = new char[rows][columns];
        IGameBoard gb = IGameBoardFactory(rows, columns, numToWin);

        for (int i = 0; i < rows; i+=2){
            expected[i][columns - 1] = 'O';
        }
        for (int i = 1; i < rows; i+=2){
            expected[i][columns - 1] = 'X';
        }

        for (int i = 0; i < rows/2; i++){
            gb.placeToken('O', columns - 1);
            gb.placeToken('X', columns - 1);
        }

        assertEquals(ArrayToString(expected, rows, columns), gb.toString());//check that board is unchanged
    }

    @Test//This test case is unique and distinct because it tests the top left boundary on a nearly full column where the gameboard is has the minimum size number of columns.
    public void testPlaceToken_TopLeft_rows10_columns_3(){
        int rows = 10;
        int columns = 3;
        int numToWin = 3;

        char[][] expected = new char[rows][columns];
        IGameBoard gb = IGameBoardFactory(rows, columns, numToWin);

        for (int i = 0; i < rows; i+=2){
            expected[i][0] = 'O';
        }
        for (int i = 1; i < rows; i+=2){
            expected[i][0] = 'X';
        }

        for (int i = 0; i < rows/2; i++){
            gb.placeToken('O', 0);
            gb.placeToken('X', 0);
        }

        assertEquals(ArrayToString(expected, rows, columns), gb.toString());//check that board is unchanged
    }

    @Test//This test case is unique and distinct because it tests the center row and center column on a partially full board of a nonboundary touching gameboard size.
    public void testPlaceToken_Row5_Col5(){
        int rows = 10;
        int columns = 10;
        int numToWin = 3;

        char[][] expected = new char[rows][columns];
        IGameBoard gb = IGameBoardFactory(rows, columns, numToWin);

        for (int i = 0; i < rows/2; i+=2){
            expected[i][columns/2] = 'O';
        }
        for (int i = 1; i < rows/2; i+=2){
            expected[i][columns/2] = 'X';
        }

        for (int i = 0; i < rows/4; i++){
            gb.placeToken('O', columns/2);
            gb.placeToken('X', columns/2);
        }

        gb.placeToken('O', columns/2);

        assertEquals(ArrayToString(expected, rows, columns), gb.toString());//check that board is unchanged
    }
}