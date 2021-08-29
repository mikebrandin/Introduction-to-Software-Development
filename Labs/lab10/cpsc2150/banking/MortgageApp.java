package cpsc2150.banking;

import cpsc2150.banking.views.*;
import cpsc2150.banking.controllers.*;


public class MortgageApp{

    public static void main(String[] args) {
        MortgageView view = new MortgageView();
        IMortgageController controller = new MortgageController(view);
        view.setController(controller);
        controller.submitApplication();
    }
}
