package cpsc2150.MyDeque;



/**
 * defines:
 *          myQ = an array of characters functioning as a queue
 *
 * @invariant (myQ != null) (MAX_LENGTH > 0)
 *
 * Correspondence Q = myQ
 */
public class ArrayDeque implements IDeque {

    // where the data is stored. myQ[0] is the front of the deque
    private Character[] myQ;
    // tracks how many items in the deque
    // also used to find the end of the deque
    private int myLength;
    // complete the class


    /**
     * @pre myQ instanceof Character[]
     * @post myQ = new Character[MAX_LENGTH]
     */
    //constructor
    public ArrayDeque(){
        myQ = new Character[MAX_LENGTH];

    }
    // Adds x to the end of the deque
    public void enqueue(Character x){
        myQ[myLength] = x;
        myLength++;
    }
    //removes and returns the Character at the front of the deque
    public Character dequeue(){
        Character temp = myQ[0];
        myQ[0] = null;

        for(int i = 1; i <= myLength; i++){
            myQ[i - 1] = myQ[i];
        }
        myLength--;
        return temp;
    }
    // Adds x to the front of the deque
    public void inject(Character x){
        Character temp;

        for (int i = myLength - 1 ; i >= 0; i++){
            myQ[i+1] = myQ[i];
        }
        myQ[0] = x;

        myLength++;
    }

    //removes and returns the Character at the end of the deque
    public Character removeLast(){

        myLength--;

        return myQ[myLength];
    }
    //returns the number of Characters in the deque
    public int length(){
        return myLength;
    }
    //clears the entire deque
    public void clear(){
        for (int i = 0; i < myLength; i++){
            myQ[i] = null;
        }
    }
}
