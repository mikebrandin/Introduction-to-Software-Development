package cpsc2150.extendedConnectX;

public abstract class AbsGameBoard implements IGameBoard {

    /**
     * @override
     * @pre     none
     * @post    GameBoard as String object is returned
     * @return  a fully formatted string displaying the current gameboard
     */
    public String toString(){

        String output;
        //add top row
        output = "|";
        for (int i = 0; i < getNumColumns() && i < 10; i++){
            output += " " + i;
            output += "|";
        }
        if (getNumColumns() >= 10){
            for (int i = 10; i < getNumColumns(); i++){
                output += i;
                output += "|";
            }
        }
        output += "\n";

        //output rest of board
        for (int i = getNumRows() - 1; i >= 0; i--){
            for (int j = 0; j < getNumColumns(); j++){//double for loop to iterate through gameboard
                BoardPosition temp = new BoardPosition(i,j);//declare and initiate a temporary position variable
                output += "|";//add lines around board positions
                if (whatsAtPos(temp) != 0) {//if player is at positions output that player to the string
                    output += whatsAtPos(temp) + " ";
                }
                else{//otherwise output a blank space
                    output += "  ";
                }
            }
            output += "|\n";//add a new line after every line
        }

        return output;//returns the fully formatted string
    }



}
