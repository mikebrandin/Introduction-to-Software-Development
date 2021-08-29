package cpsc2150.MyDeque;

import java.util.Objects;

/**
 * A deque containing characters.
 * A deque is a data structure a double-ended queue that allows you
 * to insert and remove from both ends.
 * This deque is bounded by MAX_LENGTH
 *
 * @author Mike Brandin
 * @author Davis Weitzel
 * date 02/10/2021
 *
 * Description:
 *      IDeque supports abstractly a one dimensional array/list of generic types with
 *      methods to add and remove characters to the array/list as well as returning the
 *      length and clearing the entire array.
 *
 * Initialization ensures:
 *      IDeque is guaranteed to support abstract one dimensional arrays of MAX_LENGTH and smaller
 *
 * defines:
 *      Q: an array/list of generic types functioning as a queue
 *      X: generic type to add to Q
 *      Size: number of generic types in Q
 *
 * constraints:
 *      0 < Size <= MAX_LENGTH, length >= 0
 *
 * @invariant (Q != null)(Size > 0)(Size <= MAX_LENGTH)(MAX_LENGTH > 0)
 *
 */
public interface IDeque<T> {
    public final int MAX_LENGTH = 100;
    /**
     * @pre Size < MAX_LENGTH
     * @post Q[MAX_LENGTH] = X
     * @param x the generic type to insert to end of queue
     */
    // Adds x to the end of the deque
    public void enqueue(T x);
    /**
     * @pre Size > 1
     * @post Size = #Size - 1
     * @return the generic type at the front of the deque (Q[0])
     */
    //removes and returns the generic types at the front of the deque
    public T dequeue();
    /**
     * @pre Size <= MAX_LENGTH
     * @post Q[0] = X, length() = #length - 1
     * @param x the generic type to insert to front of queue
     */
    // Adds x to the front of the deque
    public void inject(T x);
    /**
     * @pre Size > 1
     * @post removeLast() = Q[MAX_LENGTH], length() = #length - 1
     * @post Size = #Size - 1
     * @return the generic type at the end of the deque (Q[MAX_LENGTH])
     */
    //removes and returns the generic types at the end of the deque
    public T removeLast();
    /**
     * @pre none
     * @post length() = Size
     * @return the length of the deque (Size)
     */
    //returns the number of generic types in the deque
    public int length();
    /**
     * @pre none
     * @post Q[0...MAX_LENGTH] = null, Size = 0
     */
    //clears the entire deque
    public void clear();
    /**
     * @pre     self = #self AND Size >= 1
     * @post    peek() = Q[0]
     * @return  the generic type from the front the deque
     */
    default T peek(){
        T temp = dequeue();
        inject(temp);

        return temp;
    }
    /**
     * @pre     self = #self AND Size >= 1
     * @post    endOfDeque() = q[MAX_LENGTH - 1]
     * @return  the generic types from the end of the deque
     */
    default T endOfDeque(){
        T temp = removeLast();
        enqueue(temp);
        return temp;
    }
    /**
     * @pre     Size < MAX_LENGTH, pos - 1 > 0, pos - 1 <= Size
     * @post    Q[pos] = c, Size = #Size + 1
     * @param   pos  the position to insert the generic type into in the generic type deque
     * @param   c    the generic type to insert into the deque
     */
    default void insert(T c, int pos){
        int length = length();
        T[] temp = (T[]) new Object[length() + 1];
        T inserted;
        //j represents location in temp
        int j = 0;
        for (int i = 0; i < pos-1; i++) {
            temp[j] = dequeue();
            j++;
        }
        inserted = c;
        temp[j] = inserted;
        j++;
        length = length();
        for (int i = 0; i < length; i++) {
            temp[j] = dequeue();
            j++;
        }
        for (int i = 0; i < j; i++) {
            enqueue(temp[i]);
        }
    }
    /**
     * @pre     Size >= 1, pos > 0, pos - 1 <= Size
     * @post    Size = #Size - 1
     * @param   pos     the position of the generic type to remove from the deque
     * @return  the generic type that was removed from the deque
     */
    default T remove(int pos) {
        int length = length();
        T[] temp = (T[]) new Object[length() - 1];
        T removed;
        //j represents location in temp
        int j = 0;
        for (int i = 0; i < pos-1; i++) {
            temp[j] = dequeue();
            j++;
        }
        removed = dequeue();
        length = length();
        for (int i = 0; i < length; i++) {
            temp[j] = dequeue();
            j++;
        }
        for (int i = 0; i < j; i++) {
            enqueue(temp[i]);
        }
        return removed;
    }


    /**
     * @pre     self = #self AND Size >= 1, pos - 1 > 0, pos - 1 <= Size
     * @post    get() = Q[0]
     * @param   pos     the position of the generic type to get from the deque
     * @return  the generic type at the position passed through
     */
    default T get(int pos){

        int length = length();
        T[] temp = (T[]) new Object[length()];
        T got;
        //j represents location in temp
        int j = 0;
        for (int i = 0; i < pos-1; i++) {
            temp[j] = dequeue();
            j++;
        }
        got = dequeue();
        temp[j] = got;
        j++;
        length = length();
        for (int i = 0; i < length; i++) {
            temp[j] = dequeue();
            j++;
        }
        for (int i = 0; i < j; i++) {
            enqueue(temp[i]);
        }
        return got;
    }

}
