package cpsc2150.MyDeque;

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
 *      IDeque supports abstractly a one dimensional array/list of characters with
 *      methods to add and remove characters to the array/list as well as returning the
 *      length and clearing the entire array.
 *
 * Initialization ensures:
 *      IDeque is guaranteed to support abstract one dimensional arrays of MAX_LENGTH and smaller
 *
 * defines:
 *      Q: an array/list of characters functioning as a queue
 *      X = character to add to Q
 *      Size = number of characters in Q
 *      isChar(a) returns true if a is a Character
 *
 * constraints:
 *      0 < Size <= MAX_LENGTH, length >= 0
 *
 * @invariant (Q != null)(Size > 0)(isChar(X))(Size <= MAX_LENGTH)(MAX_LENGTH > 0)
 *
 */
public interface IDeque {
    public static final int MAX_LENGTH = 100;
    /**
     * @pre Size < MAX_LENGTH
     * @post Q[MAX_LENGTH] = X
     * @param x the character to insert to end of queue
     */
    // Adds x to the end of the deque
    public void enqueue(Character x);
    /**
     * @pre Size > 1
     * @post Size = #Size - 1
     * @return the Character at the front of the deque (Q[0])
     */
    //removes and returns the Character at the front of the deque
    public Character dequeue();
    /**
     * @pre Size <= MAX_LENGTH
     * @post Q[0] = X, length() = #length - 1
     * @param x the character to insert to front of queue
     */
    // Adds x to the front of the deque
    public void inject(Character x);
    /**
     * @pre Size > 1
     * @post removeLast() = Q[MAX_LENGTH], length() = #length - 1
     * @post Size = #Size - 1
     * @return the Character at the end of the deque (Q[MAX_LENGTH])
     */
    //removes and returns the Character at the end of the deque
    public Character removeLast();
    /**
     * @pre none
     * @post length() = Size
     * @return the length of the deque (Size)
     */
    //returns the number of Characters in the deque
    public int length();
    /**
     * @pre none
     * @post Q[0...MAX_LENGTH] = null, Size = 0
     */
    //clears the entire deque
    public void clear();
    /**
     * @pre     Size >= 1
     * @post    peek() = Q[0]
     * @return  the character from the front the deque
     */
    default Character peek(){
        Character temp = dequeue();
        inject(temp);

        return temp;
    }
    /**
     * @pre     Size >= 1
     * @post    endOfDeque() = q[MAX_LENGTH - 1]
     * @return  the character from the end of the deque
     */
    default Character endOfDeque(){
        Character temp = removeLast();
        enqueue(temp);
        return temp;
    }
    /**
     * @pre     Size < MAX_LENGTH, pos - 1 > 0, pos - 1 <= Size
     * @post    Q[pos] = c, Size = #Size + 1
     * @param   pos  the position to insert the character into in the character deque
     * @param   c    the character to insert into the deque
     */
    default void insert(Character c, int pos){
        int length = length();
        Character[] temp = new Character[length() + 1];
        Character inserted;
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
     * @param   pos     the position of the character to remove from the deque
     * @return  the character that was removed from the deque
     */
    default Character remove(int pos) {
        int length = length();
        Character[] temp = new Character[length() - 1];
        Character removed;
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
     * @pre     Size >= 1, pos - 1 > 0, pos - 1 <= Size
     * @post    get() = Q[0]
     * @param   pos     the position of the character to get from the deque
     * @return  the character at the position passed through
     */
    default Character get(int pos){//DOES NOT WORK

        int length = length();
        Character[] temp = new Character[length()];
        Character got;
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
