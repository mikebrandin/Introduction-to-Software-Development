package cpsc2150.MyDeque;
import java.util.*;

/**
 * Description:
 *       Uses an interface (IDeque) to prompt a user
 *       whether to use an array or a list of Characters implementation
 *       and prints out the output using the interface's methods
 *
 *
 * @author Mike Brandin
 * @author Davis Weitzel
 * date 02/10/2021
 */
public class DequeApp {

    public static void main(String [] args){
        IDeque<Character> q = new ArrayDeque<Character>();//initialized early to get rid of compiler warning
        /*
        You will add in code here to ask the user whether they want an
        array implementation or a list implementation. Then use their
        answer to initialize q appropriately
        */
        final int MIN_LENGTH = 0;
        int loop = 0;
        int loop2 = 0;
        int option = 0;
        char input;
        int input2;
        Scanner myOpt = new Scanner(System.in);
        Scanner myObj = new Scanner(System.in);


        while (loop == 0){//loop until user selects valid option for implementation
            System.out.println("Enter 1 for array implementation or 2 for List implementation");

            int choice = myObj.nextInt();

            if (choice == 1) {
                loop++;
            } else if (choice == 2) {
                q = new ListDeque<Character>();
                loop++;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }

        loop2: while (loop2 == 0) {//loop until user decides to quit program

            System.out.println("Select an option:\n1. Add to the end of the Deque\n" +
                    "2. Add to the front of the Deque\n3. Remove from the front of the Deque\n4. " +
                    "Remove from the end of the Deque\n5. Peek from the front of the Deque\n" +
                    "6. Peek from the end of the Deque\n7. Insert to a position in the Deque\n" +
                    "8. Remove from a position in the Deque\n9. Get a position in the Deque\n" +
                    "10. Get the length of the Deque\n11. Clear the Deque\n12. Quit");
            option = myOpt.nextInt();

            switch (option){
                case 1://add to end of deque
                    if(q.length() >= IDeque.MAX_LENGTH){//if preconditions fail
                        System.out.println("Deque is full!");
                        break;
                    }
                    System.out.println("What character to enqueue to the end of the Deque?");
                    input = myOpt.next().charAt(0);
                    q.enqueue(input);

                    break;
                case 2://add to front of deque
                    if(q.length() >= IDeque.MAX_LENGTH){//if preconditions fail
                        System.out.println("Deque is full!");
                        break;
                    }
                    System.out.println("What character to inject to the front of the Deque?");
                    input = myOpt.next().charAt(0);
                    q.inject(input);
                    break;
                case 3://remove from front of deque
                    if(q.length() <= MIN_LENGTH){//if preconditions fail
                        System.out.println("Deque is empty!");
                        break;
                    }
                    System.out.println("Removing from the front of the Deque...");
                    q.dequeue();
                    break;
                case 4://remove from end of deque
                    if(q.length() <= MIN_LENGTH){//if preconditions fail
                        System.out.println("Deque is empty!");
                        break;
                    }
                    System.out.println("Removing from the back of the Deque...");
                    q.removeLast();
                    break;
                case 5://peek from front of deque
                    if(q.length() <= MIN_LENGTH){//if preconditions fail
                        System.out.println("Deque is empty!");
                        break;
                    }
                    System.out.println("Peek: " + q.peek());
                    break;
                case 6://peek from end of deque
                    if(q.length() <= MIN_LENGTH){//if preconditions fail
                        System.out.println("Deque is empty!");
                        break;
                    }
                    System.out.println("EndOfDeque: " + q.endOfDeque());
                    break;
                case 7://insert at position on deque
                    if(q.length() >= IDeque.MAX_LENGTH){//if preconditions fail
                        System.out.println("Deque is full!");
                        break;
                    }
                    else if (q.length() <= MIN_LENGTH) {
                        System.out.println("Cannot Insert into An empty Deque");
                        break;
                    }
                    else {
                        System.out.println("What character to insert to the Deque?");
                        input = myOpt.next().charAt(0);

                        int loop7 = 0;
                        loop7:
                        while (loop7 == 0) {//loop until valid position is selected
                            System.out.println("What position to insert in?");
                            input2 = myOpt.nextInt();
                            if (input2 > q.length() || input2 <= MIN_LENGTH) {//if preconditions fail
                                System.out.println("Given position is invalid");
                            } else {
                                q.insert(input, input2);
                                break loop7;
                            }
                        }
                    }
                    break;


                case 8://remove from position on deque
                    if(q.length() <= MIN_LENGTH){//if preconditions fail
                        System.out.println("Deque is empty!");
                        break;
                    }

                    int loop8 = 0;
                    loop8: while (loop8 == 0) {//loop until valid position is selected

                        System.out.println("What position to remove from the Deque?");
                        input2 = myOpt.nextInt();
                        if (input2 > q.length() || input2 <= MIN_LENGTH) {//if preconditions fail
                            System.out.println("Given position is invalid");
                        }
                        else {
                            q.remove(input2);
                            break loop8;
                        }
                    }
                    break;
                case 9://get from position on deque
                    if(q.length() <= MIN_LENGTH){//if preconditions fail
                        System.out.println("Deque is empty!");
                        break;
                    }
                    int loop9 = 0;
                    loop9: while (loop9 == 0) {//loop until valid position is selected
                        System.out.println("What position to get from the Deque?");
                        input2 = myOpt.nextInt();
                        if (input2 > q.length() || input2 <= MIN_LENGTH) {//if preconditions fail
                            System.out.println("Given position is invalid");
                        }
                        else{
                            System.out.println(q.get(input2) + " is at position " + input2 + " in the Deque.");
                            break loop9;
                        }
                    }
                    break;
                case 10://get the length of deque
                    System.out.println("Length of Deque: " + q.length());
                    break;
                case 11://clear the deque
                    System.out.println("Deque is now empty!");
                    q.clear();
                    break;
                case 12://quit the program
                    break loop2;
                default:
                    System.out.println("Not a valid option!");
                    break;
            }//switch

            System.out.println(q.toString());//output the array as an formatted string output

        }//while

    }//main

}
