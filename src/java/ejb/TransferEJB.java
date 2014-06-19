/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import pojo.Account;

/**
 *
 * @author Guest
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class TransferEJB {

    @PersistenceContext
    EntityManager em;
    @Resource
    EJBContext ctx;

    public Account findAccount(int acct_id) throws ejb.AccountNotFoundException {

        Account acc = em.find(Account.class, acct_id);
        if (acc == null) {
            throw new AccountNotFoundException("no such account");
        } else {
            System.out.printf("account %d found",acc.getAccountId());
            return acc;
        }
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void TransferFunds(int acct1, int acct2, double amount) throws Exception {
        UserTransaction ut = ctx.getUserTransaction();

        try {
            ut.begin();
            //finds account1
            Account a1 = this.findAccount(acct1);
           
            
           //debits account1
            this.debit(a1, amount);
            
            //finds account2
            //
             Account a2 = this.findAccount(acct2);
             
             //credits account2 , account might fail on credit, should in case
             //rollback debit account
            this.credit(a2, amount);
           
            ut.commit();
            System.out.println("Transfer Succesfull");
        } catch (AccountNotFoundException ae) {
            System.out.println("no such account");
            ut.rollback();
           
        } catch (InsufficientFundsException ie) {
            System.out.println("low funds");
            ut.rollback();
          

        } catch (Exception ee) {
            ut.rollback();
            ee.printStackTrace();
        }
    }

    public void debit(Account acct, double amt) throws InsufficientFundsException {
        double bal;
        System.out.println("watch"+acct.getAccountBalance());
        System.out.println("see"+amt);
        if (acct.getAccountBalance() < amt) {
            throw new InsufficientFundsException("low funds");
        } else {
            bal = acct.getAccountBalance() - amt;
            acct.setAccountBalance(bal);
            em.merge(acct);
            System.out.println("deducted");
        }
    }

    public void credit(Account acct, double amt) {
        double bal = acct.getAccountBalance() + amt;
        acct.setAccountBalance(bal);
        em.merge(acct);
        System.out.println("credited");
    }
}
