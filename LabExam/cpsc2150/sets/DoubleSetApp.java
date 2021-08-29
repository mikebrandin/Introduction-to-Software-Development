package cpsc2150.sets;

import java.util.*;

/**
 * MIKE BRANDIN 4/21/2021
 */
public class DoubleSetApp {

    private static final int EXIT_CHOICE = 2;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ISet<Double> preCond = new ListSet();
        boolean invalid = true;
        //Print a menu of options
        printMenu();
        int input = Integer.parseInt(in.nextLine());//ask user for input on making a union or exiting
        while (input != EXIT_CHOICE) {
            if (input == 1) {//if they want to make a union
                //Make each set
                System.out.println("Make set 1");
                ISet set1 = getSet(in);//get set 1

                System.out.println("Make set 2");
                ISet set2 = getSet(in);//get set 2

                //print the sets
                System.out.println("Set 1 is:");
                System.out.println(set1.toString());
                System.out.println("Set 2 is:");
                System.out.println(set2.toString());

                //union the sets together
                if (set1.getSize() + set2.getSize() <= preCond.MAX_SIZE) {
                    set1.union(set2);
                    //print the union
                    System.out.println("The union is:");
                    System.out.println(set1.toString());
                }
                else{
                    System.out.println("The combined size of the two sets is too large.");
                }

            } else {
                System.out.println("That was not an option");
            }

            //print the menu and get the next option
            printMenu();
            input = Integer.parseInt(in.nextLine());
        }
    }

    /**
     * This method will print the options menu
     *
     * @pre none
     * @post [menu will be displayed to user]
     */
    private static void printMenu() {
        System.out.println("\nMake a selection");
        System.out.println("1. Find the UNION of Two Sets");
        System.out.println(EXIT_CHOICE + ". Exit");
    }

    /**
     * This method will get the values from a user and build a set
     *
     * @param in a scanner object to use to get data from the user
     * @return a set that the user built
     * @pre in is open and connected to the user
     * @post getSet = [set containing the user provided values]
     */
    private static ISet getSet(Scanner in) {
        ISet<Double> s;
        //replace this so the user can choose between ListSet and ArraySet
        s = new ListSet();
        boolean invalid = true;
        int input;

        while (invalid){//check that valid input the implementation
            System.out.println("Enter 1 for a ListSet implementation or Enter 2 for an ArraySet implementation.");
            input = Integer.parseInt(in.nextLine());

            if (input == 1){
                s = new ListSet<Double>();
                break;
            }
            else if (input == 2){
                s = new ArraySet<Double>();
                break;
            }
            else{
                System.out.println("Please enter a valid option.");//if user does not enter 1 or 2
            }
        }

        // add values to the set until user enters "qt" to stop entering values
            System.out.println("Enter a value to add to the set. Enter qt to stop adding to the set");
            String val = in.nextLine();

        while (!val.equals("qt")) {
            //we can assume if they did not enter "qt", they did enter a number
            double addVal = Double.parseDouble(val);

            //add the value to our set
            //!contains(val) and size < MAX_SIZE
            if (s.contains(addVal)) {
                System.out.println("Set already contains the value.");
            }
            else if (s.getSize() >= s.MAX_SIZE) {
                System.out.println("Set is at maximum capacity.");
            }
            else{
                s.add(addVal);
            }

            //get the next value before looping
            System.out.println("Enter a value to add to the set. Enter qt to stop adding to the set");
            val = in.nextLine();
        }

        //we have filled our set, so we can return it
        return s;
    }
}
