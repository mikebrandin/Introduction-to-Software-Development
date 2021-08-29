package cpsc2150.banking.controllers;

import cpsc2150.banking.models.*;
import cpsc2150.banking.views.*;

/**
 * @author Mike Brandin
 * @author Davis Weitzel
 * @date 04/09/2021
 */

public class MortgageGUIController implements IMortgageController {
    private IMortgageView view;

    public MortgageGUIController(IMortgageView v) {
        view = v;
    }

    public void submitApplication(){
        view.printToUser("Provide Customer and Mortgage information");
        String name = "";
        double cost = 0;
        double dp = 0;
        int years = 0;
        double debt = 0;
        int score = 0;
        double inc = 0;
        final int MIN_NUM = 0;

        ICustomer c = new Customer(0,0,0,"");
        IMortgage m = new Mortgage(0,0,0,c);

        //get information from view
        name = view.getName();
        cost = view.getHouseCost();
        dp = view.getDownPayment();
        years = view.getYears();
        debt = view.getMonthlyDebt();
        score = view.getCreditScore();
        inc = view.getYearlyIncome();

        if(inc <= MIN_NUM){//check the preconditions for getYearlyIncome
            view.printToUser("Income must be greater than 0.");
        }
        else if(debt < MIN_NUM){//check the preconditions for getMonthlyDebt
            view.printToUser("Debt must be greater than or equal to 0.");
        }
        else if (score <= MIN_NUM || score >= c.MAX_CREDIT_SCORE){//check the preconditions for getCreditScore
            view.printToUser("Credit Score must be greater than 0 and less than 850");
        }
        else if (cost <= MIN_NUM) {//check the preconditions for getHouseCost
            view.printToUser("Cost must be greater than 0.");
        }
        else if (dp <= MIN_NUM || dp >= cost) {//check the preconditions for getDownPayment
            view.printToUser("Down Payment must be greater than 0 and less than the cost of the house.");
        }
        else if(years <= MIN_NUM || years > m.MAX_YEARS){//check the preconditions for getYears
            if (years <= MIN_NUM) {//check if less than or equal to 0
                view.printToUser("Years must be greater than 0.");
            }
            else if (years > m.MAX_YEARS) {//check if greater than 30
                view.printToUser("Years must be less than or equal to 30.");
            }
        }
        else{//check if loan is approved
            c = new Customer(debt, inc, score, name);
            // Print the results of mortgage application
            boolean loanSuccessful = c.applyForLoan(dp, cost, years);
            view.displayApproved(loanSuccessful);//display if approved or not
            if (loanSuccessful){//if loan is successful display the proper rates and payments
                view.displayRate(c.getRate());
                view.displayPayment(c.getMonthlyPay());
            }
            else{//if loan is not successful clear the previous messages with zeroes
                view.displayRate(0);
                view.displayPayment(0);
            }
        }
    }
}