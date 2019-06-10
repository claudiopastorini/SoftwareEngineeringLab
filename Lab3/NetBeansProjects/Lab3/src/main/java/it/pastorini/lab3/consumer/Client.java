/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.pastorini.lab3.consumer;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 *
 * @author biar
 */
public class Client {
    
    private String servantIpAddress;
    
    private int servantPortNumber;
    
    private String topic;
    
    private String providerUrl;
    
    private ActiveMQConnectionFactory connectionFactory;
    
    private InitialContext jndiContext;

    private Destination destination;

    private MessageConsumer consumer;

    // TODO: use builder pattern
    public Client(String servantIpAddress, int servantPortNumber, String topic) {
        // TODO: validate IP
        this.servantIpAddress = servantIpAddress;
        // TODO: validate port number
        this.servantPortNumber = servantPortNumber;
        this.topic = topic;
        
        // Generates provider URL
        this.providerUrl = generateProviderUrl(this.servantIpAddress, this.servantPortNumber);
        
        Properties properties = new Properties();
        properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        properties.setProperty(Context.PROVIDER_URL, this.providerUrl);
        
        try {
            this.jndiContext = new InitialContext(properties);
        
            this.connectionFactory = (ActiveMQConnectionFactory) jndiContext.lookup("ConnectionFactory");
            
            this.destination = (Topic) jndiContext.lookup(this.topic);
        } catch (NamingException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Logger.getLogger(Client.class.getName()).log(Level.INFO, "Client initialized!");
    } 
    
    public void start(String selector, MessageListener messageListener) throws JMSException {
        Logger.getLogger(Client.class.getName()).log(Level.INFO, "Starting client...");
        
        // Create a Connection
        Connection connection = connectionFactory.createConnection();
        connection.start();
        
        Logger.getLogger(Client.class.getName()).log(Level.INFO, "Client initialized!");
        
        // Create a Session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        if (selector != null) {
            consumer = session.createConsumer(this.destination, selector);
        } else {
            consumer = session.createConsumer(this.destination);
        }
        
        if (messageListener != null) {
            consumer.setMessageListener(messageListener);
        } else {
            Message message = consumer.receive();
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                System.out.println(String.format("Received: %s", textMessage.getText()));
            } else {
                System.out.println(String.format("Message of wrong type: %s", message.getClass().getName()));
            }
            
            this.stop();
        }
    }
    
    public void stop() throws JMSException {
        Logger.getLogger(Client.class.getName()).log(Level.INFO, "Stopping client...");
        
        this.consumer.close();
        
        Logger.getLogger(Client.class.getName()).log(Level.INFO, "Client stopped!");
    }

    private String generateProviderUrl(String ipAddress, int portNumber) {
        return String.format("tcp://%s:%d", ipAddress, portNumber);
    }

}
