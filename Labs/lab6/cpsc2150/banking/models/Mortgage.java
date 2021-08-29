package cpsc2150.banking.models;
import java.lang.Math;

/**
 *
 * @invariant 0 < homeCost AND 0 <= down AND 0 < totalYears
 *
 * Correspondence self.customer = Customer AND self.down/self.homeCost = PercentDown AND
 *                self.getPayment = Payment AND self.getRate/NUM_MONTHS = Rate AND
 *                self.homeCost - self.down = Principal AND
 *                self.customer.getMonthlyDebtPayments= DebtToIncomeRatio AND = NumberOfPayments
 *
 * @author Mike Brandin
 * @author Davis Weitzel
 * @date 02/25/2021
 */
public class Mortgage extends AbsMortgage implements IMortgage{
    private double homeCost;
    private double down;
    private int totalYears;
    private ICustomer customer;

    static final int NUM_MONTHS = 12;

    /**
     * @pre   cost > 0 AND downPayment >= 0 AND years > 0 AND pcustomer iff instanceof(ICustomer)
     * @post  homeCost = cost AND  down = downPayment AND totalYears = years AND customer = pcustomer
     * @param cost          total cost of the home
     * @param downPayment   amount payed down at the start of the mortgage
     * @param years         length of the loan in years
     * @param pcustomer     customer information
     */
    public Mortgage(double cost, double downPayment, int years, ICustomer pcustomer){
        homeCost = cost;
        totalYears = years;
        down = downPayment;
        customer = pcustomer;
    }
    public boolean loanApproved(){
        if(getRate() >= IMortgage.RATETOOHIGH){
            return false;
        }
        else if((down/homeCost) < IMortgage.MIN_PERCENT_DOWN){
            return false;
        }
        else if(((customer.getMonthlyDebtPayments() * NUM_MONTHS)/ (customer.getIncome())) >= IMortgage.DTOITOOHIGH){
            return false;
        }
        else {
            return true;
        }
    }

    public double getPayment(){
        double denominator = 1 - Math.pow(1+(getRate()/NUM_MONTHS), -(getYears() * (double)NUM_MONTHS));
        return ((getPrincipal() * (getRate()/NUM_MONTHS))/(denominator));
    }
    public double getRate(){
        double apr = IMortgage.BASERATE;
        int credit = customer.getCreditScore();
        double percentDown = down/homeCost;
        if(credit < IMortgage.BADCREDIT){
            apr+=IMortgage.VERYBADRATEADD;
        }
        else if(credit >= IMortgage.BADCREDIT && credit < IMortgage.FAIRCREDIT){
            apr+=IMortgage.BADRATEADD;
        }
        else if(credit >= IMortgage.FAIRCREDIT && credit < IMortgage.GOODCREDIT){
            apr+=IMortgage.NORMALRATEADD;
        }
        else if(credit >= IMortgage.GOODCREDIT && credit < IMortgage.GREATCREDIT){
            apr+=IMortgage.GOODRATEADD;
        }
        if(totalYears < IMortgage.MAX_YEARS){
            apr+=IMortgage.GOODRATEADD;
        }
        else{
            apr+=IMortgage.NORMALRATEADD;
        }
        if(percentDown < IMortgage.PREFERRED_PERCENT_DOWN) {
            apr += IMortgage.GOODRATEADD;
        }
        return apr;
    }
    public double getPrincipal(){
        return homeCost - down;
    }
    public int getYears(){
        return totalYears;
    }

}
