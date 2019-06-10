/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.pastorini.lab3.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
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
public class JMSClient {
    
    private String servantIpAddress;
    
    private int servantPortNumber;
    
    private String providerUrl;
    
    private ActiveMQConnectionFactory connectionFactory;
    
    private InitialContext jndiContext;
    
    private Connection connection;
    
    private Session session;
    
    private List<MessageConsumer> consumerList = new ArrayList<>();
    
    // TODO: use builder pattern
    public JMSClient(String servantIpAddress, int servantPortNumber) throws NamingException, JMSException {
        // TODO: validate IP
        this.servantIpAddress = servantIpAddress;
        // TODO: validate port number
        this.servantPortNumber = servantPortNumber;
        
        // Generates provider URL
        this.providerUrl = generateProviderUrl(this.servantIpAddress, this.servantPortNumber);
        
        Properties properties = new Properties();
        properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        properties.setProperty(Context.PROVIDER_URL, this.providerUrl);
        
        
        this.jndiContext = new InitialContext(properties);
        
        this.connectionFactory = (ActiveMQConnectionFactory) jndiContext.lookup("ConnectionFactory");
 
        // Create a Connection
        this.connection = connectionFactory.createConnection();
        
        // Create a Session                
        try {
            this.session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        } catch (JMSException ex) {
            Logger.getLogger(JMSClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Logger.getLogger(JMSClient.class.getName()).log(Level.INFO, "Client initialized!");
    } 
    
    public void start() throws JMSException {
        Logger.getLogger(JMSClient.class.getName()).log(Level.INFO, "Starting client...");
        
        this.connection.start();
        
        Logger.getLogger(JMSClient.class.getName()).log(Level.INFO, "Client started!");
    }
    
    public void stop() throws JMSException {
        Logger.getLogger(JMSClient.class.getName()).log(Level.INFO, "Stopping client...");
        
        for (MessageConsumer consumer : this.consumerList) {
            consumer.close();
        }
        
        Logger.getLogger(JMSClient.class.getName()).log(Level.INFO, "Client stopped!");
    }
    
    public void receive(String topic, String selector, MessageListener messageListener) throws JMSException, NamingException {
            
        Destination destination = (Topic) jndiContext.lookup(topic);
        
        MessageConsumer consumer = null;
        if (selector != null) {
            consumer = session.createConsumer(destination, selector);
        } else {
            consumer = session.createConsumer(destination);
        }
        this.consumerList.add(consumer);
        
        if (messageListener != null) {
            consumer.setMessageListener(messageListener);
        } else {
            throw new IllegalArgumentException("You have to pass a MessageListner otherwise use the receive() method");
        }
    }

    public Message receive(String topic, String selector) throws JMSException, NamingException {
        
        Destination destination = (Topic) jndiContext.lookup(topic);
        
        MessageConsumer consumer = null;
        if (selector != null) {
            consumer = session.createConsumer(destination, selector);
        } else {
            consumer = session.createConsumer(destination);
        }
        
        this.consumerList.add(consumer);
        
        return consumer.receive();
    } 
    
    public TextMessage provideTextMessage() throws JMSException {
        return this.session.createTextMessage();
    }
    
    public void send(String topic, Message message) throws JMSException, NamingException {
        
        Destination destination = (Topic) jndiContext.lookup(topic);
        
        MessageProducer producer = this.session.createProducer(destination);
        producer.send(message);
    } 
    
    private String generateProviderUrl(String ipAddress, int portNumber) {
        return String.format("tcp://%s:%d", ipAddress, portNumber);
    }

}
