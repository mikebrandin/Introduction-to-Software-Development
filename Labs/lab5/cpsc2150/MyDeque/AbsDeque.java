package cpsc2150.MyDeque;

/**
 * @author Mike Brandin
 * @author Davis Weitzel
 * date 02/10/2021
 *
 * defines:
 *          myQ = an array of generic type functioning as a queue
 *          Size = number of generic type in Q
 *
 * constraints:
 *          0 < Size <= MAX_LENGTH, length >= 0
 *
 * @invariant (myQ != null) (MAX_LENGTH > 0) (myLength >= 0)
 *
 * Correspondence Q = myQ, myQ = self
 */
public abstract class AbsDeque<T> implements IDeque<T>{
    /**
     * @override
     * @pre     Size >= 0, Size <= MAX_LENGTH
     * @post    Deque as a String object is returned
     * @return  a fully outputted string representing the array/list
     */
    public String toString(){
        String output = "Deque is:\n";
        output += "<";
        T[] temp = (T[]) new Object[length()];
        int length = length();
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                temp[i] = dequeue();
            }
            for (int i = 0; i < length; i++) {
                enqueue(temp[i]);
            }
            for (int i = 0; i < length - 1; i++) {
                output += temp[i];
                output += ", ";

            }
            output += temp[length - 1];
        }

        output += ">\n";
        return output;
    }




}
