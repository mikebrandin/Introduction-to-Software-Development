package cpsc2150.MyDeque;

/**
 * @author Mike Brandin
 * @author Davis Weitzel
 * date 02/10/2021
 *
 * defines:
 *          myQ = an array of generic type functioning as a queue
 *
 * @invariant (myQ != null) (MAX_LENGTH > 0) (myLength >= 0)
 *
 * Correspondence Q = myQ, myQ = self
 */
public class ArrayDeque<T> extends AbsDeque<T> {

    // where the data is stored. myQ[0] is the front of the deque
    private T[] myQ;
    // tracks how many items in the deque
    // also used to find the end of the deque
    private int myLength = 0;
    // complete the class


    /**
     * @pre myQ instance of T[]
     * @post myQ = new T[MAX_LENGTH]
     */
    //constructor
    public ArrayDeque(){
        myQ = (T[]) new Object[MAX_LENGTH];

    }
    // Adds x to the end of the deque
    public void enqueue(T x){
        myQ[myLength] = x;
        myLength++;
    }
    //removes and returns the generic type at the front of the deque
    public T dequeue(){
        T temp = myQ[0];

        for(int i = 1; i < myLength; i++){
            myQ[i - 1] = myQ[i];
        }
        myLength--;
        return temp;
    }
    // Adds x to the front of the deque
    public void inject(T x){
        T temp;

        for (int i = myLength - 1 ; i >= 0; i--){
            myQ[i+1] = myQ[i];
        }
        myQ[0] = x;

        myLength++;
    }

    //removes and returns the generic type at the end of the deque
    public T removeLast(){

        myLength--;

        return myQ[myLength];
    }
    //returns the number of generic type in the deque
    public int length(){
        return myLength;
    }
    //clears the entire deque
    public void clear(){//is this right?
        myLength = 0;
    }
}
