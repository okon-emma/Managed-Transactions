/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

/**
 *
 * @author Guest
 */
public class InsufficientFundsException extends Exception {

    private String message;
    public InsufficientFundsException(String low_funds) {
        this.message = low_funds;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getMessage(){
        return message;
    }
    
}
