/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.pastorini.jms.topic.server;

import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ArtistImpressionProducer implements Runnable {
    
    final String tags[] = {"Album", "Singolo", "Gossip", "Premio"};
    final String sources[] = {"Facebook", "Twitter", "LinkedIn", "Giornale"};
    
    private static final Logger LOG = LoggerFactory.getLogger(ArtistImpressionProducer.class);
    
    private String artistName;
    private String artistTopic;
    private InitialContext initialContext;
    private ConnectionFactory connectionFactory;
    private Destination destination;
    private boolean continueRunning;

    public ArtistImpressionProducer(String artistName) throws NamingException {
        this.artistName = artistName;
        this.artistTopic = generateArtistsTopic(this.artistName);
        
        this.initialContext = createInitialContext();
        this.connectionFactory = (ConnectionFactory) this.initialContext.lookup("ConnectionFactory");
        
        this.continueRunning = true;
    }
    
    /*
    * Create a JNDI API InitialContext object
    */
    private InitialContext createInitialContext() throws NamingException {
        Properties props = new Properties();

        props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        props.setProperty(Context.PROVIDER_URL, "tcp://pfp-VirtualBox:61616");

        return new InitialContext(props);
    }
    
    public static String generateArtistsTopic(String artistName) {
        //return String.format("dynamicTopics/artists/%s", artistName);
        return String.format("%s", artistName);
    }
    
    private String randomTag() {
        int nextTag = new Random().nextInt(this.tags.length);
        return this.tags[nextTag];
    }
    
    private String randomSource() {
        int nextSource =  new Random().nextInt(this.sources.length);
        return this.sources[nextSource];
    }
    
    public void start() throws NamingException, JMSException {
        Thread worker = new Thread(this);
        worker.start();
    }
    
    public void stop() {
        this.continueRunning = false;
    }

    @Override
    public void run() {
        /*
         * Create connection. Create session from connection; false means
         * session is not transacted. Create sender and text message. Send
         * messages, varying text slightly. Send end-of-messages message.
         * Finally, close connection.
         */
        Connection connection;
        try {
            connection = connectionFactory.createConnection();
        } catch (JMSException ex) {
            LOG.error(ex.getLocalizedMessage());
            return;
        }
        
        Session session;
        try {
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        } catch (JMSException ex) {
            LOG.error(ex.getLocalizedMessage());
            return;
        }
        
        try {
            this.destination = session.createTopic(this.artistTopic);
        } catch (JMSException ex) {
            LOG.error(ex.getLocalizedMessage());
            return;
        }
        
        MessageProducer producer;
        try {
            producer = session.createProducer(destination);
        } catch (JMSException ex) {
            LOG.error(ex.getLocalizedMessage());
            return;
        }

        TextMessage message;
        try {
            message = session.createTextMessage();
        } catch (JMSException ex) {
            LOG.error(ex.getLocalizedMessage());
            return;
        }

        int i = 0;
        while (this.continueRunning) {
            try {
                i++;
                
                String randomTag = randomTag();
                String randomSource = randomSource();
                
                message.setStringProperty("Tag", randomTag);
                message.setStringProperty("Source", randomSource);
                message.setStringProperty("ArtistName", this.artistName);
                message.setIntProperty("IncrementalNumber", i);
                message.setText(String.format("Notizia numero %d: Nuova impressione su %s riguardo %s", i, randomSource, randomTag));
                
                producer.send(message);
                LOG.info(String.format("%s - Inviata nuova impressione: %s", this.artistName, message.getText()));
                
                try {
                    Thread.sleep(5000 + new Random().nextInt(2000));
                } catch (InterruptedException ex) {
                    LOG.error(ex.getLocalizedMessage());
                }
            } catch (JMSException ex) {
                LOG.error(ex.getLocalizedMessage());
                return;
            }
        }
        
        try {
            connection.close();
        } catch (JMSException ex) {
            LOG.error(ex.getLocalizedMessage());
            return;
        }

    }
}
