/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cappaipastorini.wsclient;

import it.sapienza.softeng.bankws.BankIFace;
import it.sapienza.softeng.bankws.BankImplService;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 *
 * @author studente
 */
public class MainClient {
    
    
    public static void main(String[] args) {
        
        try { // Call Web Service Operation
            it.sapienza.softeng.bankws.BankImplService service = new it.sapienza.softeng.bankws.BankImplService();
            it.sapienza.softeng.bankws.BankIFace port = service.getBankImplPort();
            // TODO initialize WS operation arguments here
            int arg0 = 0;
            // TODO process result here
            java.lang.String result = port.getOperationDetailsByID(arg0);
            System.out.println("Result = "+result);
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }

    }
}
