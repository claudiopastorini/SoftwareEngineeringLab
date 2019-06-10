/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.pastorini.jms.topic.client;

import it.pastorini.jms.topic.client.resources.Artist;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import org.slf4j.LoggerFactory;

public class Client {
    
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(Client.class);
    
    public static final String SERVANT_IP = "pfp-VirtualBox";
    
    public static final int SERVANT_PORT = 61616;
            
    public static void main(String[] args) throws JMSException {
        
        String tag = System.getProperty("Tag");
        String source = System.getProperty("Source");
                
        List<Artist> artists = ArtistsRestClient.getArtists();
        
        LOG.info(String.format("Found %d artists", artists.size()));
        
        for (Artist artist : artists) {
            LOG.info(String.format("Subscribe to %s's impressions", ArtistImpressionConsumer.generateArtistsTopic(artist.getSurname())));
        
            String topic = ArtistImpressionConsumer.generateArtistsTopic(artist.getSurname());
            
            ArtistImpressionConsumer client = new ArtistImpressionConsumer(SERVANT_IP, SERVANT_PORT, topic);
            
            String selector = null;
            if (tag != null) {
                selector = String.format("Tag = '%s'", tag);
            }
            if (source != null) {
                if (selector == null) {
                    selector = String.format("Source = '%s'", source);
                } else {
                    selector = String.format("%s AND Source = '%s'", selector, source);
                }
            }
            
            LOG.info(String.format("Selctor to use for filtering messages: %s", selector));
            
            client.start(selector, new MessageListener() {
                @Override
                public void onMessage(Message msg) {
                    TextMessage textMessage = (TextMessage) msg;
                    try {
                        LOG.info(String.format("%s - Received: %s", textMessage.getStringProperty("ArtistName"), textMessage.getText()));
                    } catch (JMSException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        }
    }
}
