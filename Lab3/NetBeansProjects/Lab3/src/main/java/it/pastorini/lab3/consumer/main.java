/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.pastorini.lab3.consumer;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author biar
 */
public class main {
    
    public static final String SERVANT_IP = "192.168.49.81";
    
    public static final int SERVANT_PORT = 61616;
    
    public static final String TOPIC = "dynamicTopics/Quotazioni";
        
    public static void main(String[] args) throws JMSException {
        Client client = new Client(SERVANT_IP, SERVANT_PORT, TOPIC);
        client.start("Nome = 'Barilla'", new MessageListener() {
            @Override
            public void onMessage(Message message) {
                Logger.getLogger(main.class.getName()).log(Level.INFO, message.toString());
                if (message instanceof TextMessage) {
                    try {
                        TextMessage textMessage = (TextMessage) message;
                    
                        System.out.println(String.format("Received: %s", textMessage.getText()));
                    } catch (JMSException ex) {
                        Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    System.out.println(String.format("Message of wrong type: %s", message.getClass().getName()));
                }
            }
        });
        
        
//        client.start(null);
    }
}
