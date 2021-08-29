package cpsc2150.MyDeque;

/**
 * A deque containing characters.
 * A deque is a data structure a double-ended queue that allows you
 * to insert and remove from both ends.
 * This deque is bounded by MAX_LENGTH
 */

/**
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
 *      0 < Size <= MAX_LENGTH
 *
 * @invariant (Q != null)(Size > 0)(isChar(X))(Size <= MAX_LENGTH)(MAX_LENGTH > 0)
 *
 */
public interface IDeque {
    public static final int MAX_LENGTH = 100;
    /**
     * @pre Size <= MAX_LENGTH
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
     * @post Q[0] = X
     * @param x the character to insert to front of queue
     */
    // Adds x to the front of the deque
    public void inject(Character x);
    /**
     * @pre Size > 1
     * @post removeLast() = Q[MAX_LENGTH]
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
     * @post Q[0...MAX_LENGTH] = null
     */
    //clears the entire deque
    public void clear();
}
