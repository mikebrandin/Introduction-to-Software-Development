package cpsc2150.banking.views;

import cpsc2150.banking.controllers.*;

import java.util.Scanner;

/**
 * @author Mike Brandin
 * @author Davis Weitzel
 * @date 04/01/2021
 */


public class MortgageView implements IMortgageView {
    IMortgageController control;
    Scanner input = new Scanner(System.in);


    public MortgageView(){

    }
    /**
     * This method adds a controller, so the view can inform it has
     * received information.
     *
     * @param c the Controller object
     *
     * @pre c != NULL
     * @post Controller = c
     */
    public void setController(IMortgageController c){
        control = c;
    }

    /**
     * This method retrieves the house cost.
     *
     * @return the house cost provided by the user
     */
    public double getHouseCost(){
        System.out.println("How much does the house cost?");
        double cost = input.nextDouble();
        input.nextLine();
        return cost;//return user input
    }

    /**
     * This method retrieves the down payment.
     *
     * @return the down payment provided by the user
     */
    public double getDownPayment(){
        System.out.println("How much is the down payment?");
        double down = input.nextDouble();
        input.nextLine();

        return down;//return user input
    }

    /**
     * This method returns the number of years the user intend to mortgage.
     *
     * @return the years provided by the user
     */
    public int getYears(){
        System.out.println("How many years?");
        int years = input.nextInt();
        input.nextLine();

        return years;//return user input
    }

    /**
     * This method returns the monthly debt the user has.
     *
     * @return the monthly debt provided by the user
     */
    public double getMonthlyDebt(){
        System.out.println("How much are your monthly debt payments?");
        double debt = input.nextDouble();
        input.nextLine();

        return debt;//return user input
    }

    /**
     * This method returns the user's yearly income.
     *
     * @return the income provided by the user
     */
    public double getYearlyIncome(){
        System.out.println("How much is your yearly income?");
        double income = input.nextDouble();
        input.nextLine();

        return income;//return user input
    }

    /**
     * This method returns the user's credit score.
     *
     * @return the credit score provided by the user
     */
    public int getCreditScore(){
        System.out.println("What is your credit score?");
        int score = input.nextInt();
        input.nextLine();

        return score;//return user input
    }

    /**
     * This method returns the user's name.
     *
     * @return the name provided by the user
     */
    public String getName(){
        System.out.println("What's your name?");
        String name = input.nextLine();
        return name;//return user input
    }

    /**
     * This method prints a message to the user.
     *
     * @param s the message to provide to the user
     */
    public void printToUser(String s){
        System.out.println(s);
    }

    /**
     * This method displays the monthly mortgage payment amount.
     *
     * @param p the monthly payment amount for the loan
     *
     * @pre p >= 0
     */
    public void displayPayment(double p){
        System.out.println("Monthly Payment: $" + p);
    }

    /**
     * This method displays the interest rate.
     *
     * @param r the (APR) interest rate for the loan
     *
     * @pre 0 <= r <= 1
     */
    public void displayRate(double r){
        System.out.println("Interest rate: " + r +"%");
    }

    /**
     * This method displays whether or not the loan was approved.
     *
     * @param a whether or not the mortgage application was approved
     *
     * @pre a iff [loan was approved]
     */
    public void displayApproved(boolean a){
        if (a){//if true return message that loan is approved
            System.out.println("Loan approved");
        }
        else{//if true return message that loan is not approved
            System.out.println("Loan not approved");
        }
    }

    /**
     * This method ask to see if the user would like to enter another
     * mortgage information.
     *
     * @return whether or not the user would like to apply for another mortgage with the same customer
     */
    public boolean getAnotherMortgage() {

        boolean bool = false;
        while (true) {//check that a Y or N is inputted
            System.out.println("Would you like to apply for another mortgage? Y/N");
            Character choice = input.next().charAt(0);
            choice = Character.toUpperCase(choice);
            if (choice == 'Y') {//return true if y or Y is inputted
                bool = true;
                break;
            } else if (choice == 'N') {//return false if n or N is inputted
                bool = false;
                break;
            } else {
                System.out.println("Please enter Y/N");
            }
        }
        return bool;
    }

    /**
     * This method ask to see if the user would like to enter another
     * user information.
     *
     * @return whether or not the user would like to enter another customer
     */
    public boolean getAnotherCustomer(){
        boolean bool = false;
        while (true) {//check that a Y or N is inputted
            System.out.println("Would you like to consider another customer? Y/N");
            Character choice = input.next().charAt(0);
            input.nextLine();
            choice = Character.toUpperCase(choice);
            if (choice == 'Y') {//return true if y or Y is inputted
                bool = true;
                break;
            } else if (choice == 'N') {//return false if n or N is inputted
                bool = false;
                break;
            } else {
                System.out.println("Please enter Y/N");
            }
        }
        return bool;
    }
}
