package cpsc2150.listDec;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public interface IShuffleList<T> extends List<T> {

    /**
     * @pre             swaps >= 0
     * @post            has performed swaps number of swaps between 2 random indexes
     * @param swaps     the number of swaps to perform
     */
    default void shuffle(int swaps){
        for(int i = 0; i < swaps; i++){
            Random rand = new Random();
            int random1 = rand.nextInt(this.size());
            int random2 = rand.nextInt(this.size());
            swap(random1,random2);
        }
    }

    /**
     * @pre         i >= 0 AND j >= 0 AND i < this.size() AND j < this.size()
     * @post        this.get(i) = #this.get(j) AND this.get(j) = #this.get(i)
     * @param i     the position of the first element to swap
     * @param j     the position of the second element to swap
     */
    default void swap(int i, int j){
        T temp1 = get(i);
        T temp2 = get(j);

        this.set(j, temp1);
        this.set(i, temp2);
    }

}
