package cpsc2150.MyDeque;
import java.util.*;

/**
 * Description:
 *       Uses an interface (IDeque) to prompt a user
 *       whether to use an array or a list implementation
 *       and prints out the output using the interface's methods
 *
 * Note:
 *      Please grade my submission separately.
 *      I am unsure what files my partner submitted. Thank you.
 *
 * @author Mike Brandin
 * @author Mark Auslander
 * @date 02/05/2021
 */
public class DequeApp {

    public static void main(String [] args){
        IDeque q = new ArrayDeque();//initialized early to get rid of compiler warning
        /*
        You will add in code here to ask the user whether they want an
        array implementation or a list implementation. Then use their
        answer to initialize q appropriately
        */
        int loop = 0;
        while (loop == 0){
            System.out.println("Which implementation would you like to use? " +
                    "Enter 1 for Array or 2 for List");

            Scanner myObj = new Scanner(System.in);

            int choice = myObj.nextInt();

            if (choice == 1) {
                loop++;
            } else if (choice == 2) {
                q = new ListDeque();
                loop++;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }


        char x = 'a';
        q.enqueue(x);
        x = 'k';
        q.enqueue(x);
        x = 'j';
        q.enqueue(x);
        x = '1';
        q.enqueue(x);
        x = 'f';
        q.enqueue(x);
        //Add the code to print the deque. After the code is finished the deque should still contain all its values in order

        Character temp;
        for(int i = 0; i < q.length(); i++){
            temp = q.dequeue();
            System.out.println(temp);
            q.enqueue(temp);
        }

    }

}
