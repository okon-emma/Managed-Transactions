/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import ejb.TransferEJB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Guest
 */
@WebServlet(urlPatterns = {"/tranfer"})
public class TransferServlet extends HttpServlet {

    @EJB TransferEJB txEJB;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String a1 = req.getParameter("acct1");
        String a2 = req.getParameter("acct2");
        String amt = req.getParameter("amt");
        try {
            txEJB.TransferFunds(Integer.parseInt(a1),Integer.parseInt(a2),Double.parseDouble(amt));
            PrintWriter writer = resp.getWriter();
            writer.println("Transfer Successful");
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        

    }
}
