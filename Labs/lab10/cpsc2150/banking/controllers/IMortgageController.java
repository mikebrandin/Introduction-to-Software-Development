package cpsc2150.banking.controllers;

/**
 * This interface is the Controller that partners with IMortgageView
 *
 * Defines: 
 *		View: The IMortgageView
 * Initialization ensures: View != NULL
 */
/**
 * @author Mike Brandin
 * @author Davis Weitzel
 * @date 04/01/2021
 */
public interface IMortgageController {

    /**
     * This will handle the processing of a mortgage application
     * 
     * @pre: none
     * @post: none
     */
    void submitApplication();

}