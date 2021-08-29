package cpsc2150.MyDeque;

import java.util.*;

/**
 * defines:
 *          myQ = a list of characters functioning as a queue
 *
 * @invariant myQ != null
 *
 * Correspondence Q = myQ
 */
public class ListDeque implements IDeque {
    // this time store the deque in a list
    // myQ.get(0) is the front of the deque
    private List<Character> myQ;

// complete the class

    /**
     * @pre Q instanceof List<Character>
     * @post Q = new ArrayList<Character>()
     */
    //constructor
    public ListDeque(){
        myQ = new ArrayList<Character>();
    }

    // Adds x to the end of the deque
    public void enqueue(Character x){
        myQ.add(x);
    }
    //removes and returns the Character at the front of the deque
    public Character dequeue(){
        Character temp = myQ.get(0);
        myQ.remove(0);
        return temp;

    }
    // Adds x to the front of the deque
    public void inject(Character x){
        myQ.add(0, x);
    }
    //removes and returns the Character at the end of the deque
    public Character removeLast(){
        return myQ.remove(myQ.size());
    }
    //returns the number of Characters in the deque
    public int length(){
        return myQ.size();
    }
    //clears the entire deque
    public void clear(){
        for(int i=0; myQ.get(i)!= null; i++) {
            myQ.remove(i);
        }
    }

}
