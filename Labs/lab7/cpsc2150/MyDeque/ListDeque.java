package cpsc2150.MyDeque;

import java.util.*;

/**
 * @author Mike Brandin
 * @author Davis Weitzel
 * date 02/10/2021
 *
 * defines:
 *          myQ = a list of generic types functioning as a queue
 *
 * @invariant (myQ != null) (myLength >= 0)
 *
 * Correspondence Q = myQ, myQ = self
 */
public class ListDeque<T> extends AbsDeque<T> {
    // this time store the deque in a list
    // myQ.get(0) is the front of the deque
    private List<T> myQ;

// complete the class

    /**
     * @pre Q instanceof List<Character>
     * @post Q = new ArrayList<Character>()
     */
    //constructor
    public ListDeque(){
        myQ = new ArrayList<T>();
    }

    // Adds x to the end of the deque
    public void enqueue(T x){
        myQ.add(x);
    }
    //removes and returns the generic type at the front of the deque
    public T dequeue(){
        T temp = myQ.get(0);
        myQ.remove(0);
        return temp;

    }
    // Adds x to the front of the deque
    public void inject(T x){
        myQ.add(0, x);
    }
    //removes and returns the generic type at the end of the deque
    public T removeLast(){//fix
        return myQ.remove(myQ.size() - 1);
    }
    //returns the number of generic types in the deque
    public int length(){
        return myQ.size();
    }
    //clears the entire deque
    public void clear(){
        myQ.clear();
    }

}
