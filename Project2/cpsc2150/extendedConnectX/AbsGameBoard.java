package cpsc2150.extendedConnectX;
/**
 * @author Mike Brandin
 * class: CPSC 2150
 * section: 002
 * date: 2/27/2021
 */
public abstract class AbsGameBoard implements IGameBoard {

    /**
     * @override
     * @pre     none
     * @post    GameBoard as String object is returned
     * @return  a fully formatted string displaying the current gameboard
     */
    public String toString(){

        String output;
        output = "|0|1|2|3|4|5|6|7|8|\n";//add top row
        for (int i = getNumRows() - 1; i >= 0; i--){
            for (int j = 0; j < getNumColumns(); j++){//double for loop to iterate through gameboard
                BoardPosition temp = new BoardPosition(i,j);//declare and initiate a temporary position variable
                output += "|";//add lines around board positions
                if (whatsAtPos(temp) == 'X' || whatsAtPos(temp) == 'O') {//if player is at positions output that player to the string
                    output += whatsAtPos(temp);
                }
                else{//otherwise output a blank space
                    output += " ";
                }
            }
            output += "|\n";//add a new line after every line
        }

        return output;//returns the fully formatted string
    }



}
