package cpsc2150.banking.controllers;

import cpsc2150.banking.views.*;
import cpsc2150.banking.models.*;

/**
 * @author Mike Brandin
 * @author Davis Weitzel
 * @date 04/01/2021
 */

public class MortgageController implements IMortgageController{
    private MortgageView view;
    public MortgageController(MortgageView a){
        view = a;
    }

    public void submitApplication(){
        while(true){//do for the entirety of the program run time
            String name = "";
            double cost = 0;
            double dp = 0;
            int years = 0;
            boolean infinite = true;
            double debt = 0;
            int score = 0;
            double inc = 0;
            final int MIN_NUM = 0;
            ICustomer c = new Customer(0,0,0,"");
            IMortgage m = new Mortgage(0,0,0,c);

            name = view.getName();

            while(infinite){//check the preconditions for getYearlyIncome
                inc = view.getYearlyIncome();
                if(inc < MIN_NUM){
                    view.printToUser("Income must be greater than 0.");
                }
                else{
                    break;
                }
            }
            while(infinite){//check the preconditions for getMonthlyDebt
                debt = view.getMonthlyDebt();
                if(debt < MIN_NUM){
                    view.printToUser("Debt must be greater than or equal to 0.");
                }
                else{
                    break;
                }
            }

            while (infinite){//check the preconditions for getCreditScore
                score = view.getCreditScore();
                if (score <= MIN_NUM || score >= c.MAX_CREDIT_SCORE){
                    view.printToUser("Credit Score must be greater than 0 and less than 850");
                }
                else{
                    break;
                }
            }

            while (true) {//do for the remainder of a single customers mortgage requests

                while (infinite) {//check the preconditions for getHouseCost
                    cost = view.getHouseCost();
                    if (cost < MIN_NUM) {
                        view.printToUser("Cost must be greater than 0.");
                    } else {
                        break;
                    }
                }

                while (infinite) {//check the preconditions for getDownPayment
                    dp = view.getDownPayment();
                    if (dp <= MIN_NUM || dp >= cost) {
                        view.printToUser("Down Payment must be greater than 0 and less than the cost of the house.");
                    } else {
                        break;
                    }
                }

                while (infinite) {//check the preconditions for getYears
                    years = view.getYears();
                    if (years <= MIN_NUM) {
                        view.printToUser("Years must be greater than 0.");
                    } else if (years > m.MAX_YEARS) {
                        view.printToUser("Years must be less than or equal to 30.");
                    } else {
                        break;
                    }
                }

                // Call customer constructor and apply for a loan
                c = new Customer(debt, inc, score, name);
                // Print the results of mortgage application
                boolean loanSuccessful = c.applyForLoan(dp, cost, years);

                view.printToUser(c.toString());

                if (view.getAnotherMortgage()){
                    continue;
                }
                else{
                    break;
                }

            }//while same customer completes loan process
            boolean newCustomer = view.getAnotherCustomer();
            if(newCustomer){
                continue;
            }
            else{
                break;
            }
        }
    }
}
