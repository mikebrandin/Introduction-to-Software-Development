package cpsc2150.sets;
/**
 * MIKE BRANDIN 4/21/2021
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArraySet<T> extends SetAbs<T> implements ISet<T>{
    /**
     * Correspondences size = myLength and self = mySet and MAX_SIZE = MAX_LENGTH
     *
     * @invariants [set contains no duplicate values]
     */
    private final T[] mySet;
    private final int MAX_LENGTH = 100;//Maximum length of mySet
    private int myLength = 0;//keep track of actual size of array (number of vals)

    /**
     * @post [Set is empty]
     */
    public ArraySet() {
        mySet = (T[]) new Object[MAX_LENGTH];//create an max sized array for mySet
    }

    public void add(T val) {
        mySet[myLength] = val;//add value to end of mySet
        myLength++;
    }

    public T remove() {

        Random rand = new Random();//randomly remove a value from mySet

        int r_num = -1;
        while (r_num < 0 || r_num > myLength) {
            r_num = rand.nextInt(myLength);
        }

        T temp = mySet[r_num];
        mySet[r_num] = mySet[myLength-1];

        myLength--;

        return temp;
    }

    public boolean contains(T val) {//if mySet contains val return true
        for (int i = 0; i <= myLength; i++){
            if (mySet[i] == val){
                return true;
            }
        }
        return false;//otherwise false
    }

    public int getSize() {
        return myLength;//return myLength
    }
}
