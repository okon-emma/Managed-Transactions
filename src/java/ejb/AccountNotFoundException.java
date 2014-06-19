/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

/**
 *
 * @author Guest
 */
public class AccountNotFoundException extends Exception {

    private String message;
    public AccountNotFoundException(String no_such_account) {
       this.message = no_such_account;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getMessage(){
        return message;
    }
}
