package cpsc2150.MyDeque;

import java.util.*;

/**
 * @author Mike Brandin
 * @author Davis Weitzel
 * date 02/10/2021
 *
 * defines:
 *          myQ = a list of characters functioning as a queue
 *
 * @invariant (myQ != null) (myLength >= 0)
 *
 * Correspondence Q = myQ, myQ = self
 */
public class ListDeque extends AbsDeque {
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
    public Character removeLast(){//fix
        return myQ.remove(myQ.size() - 1);
    }
    //returns the number of Characters in the deque
    public int length(){
        return myQ.size();
    }
    //clears the entire deque
    public void clear(){
        myQ.clear();
    }

}
