/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.pastorini.jms.topic.server;

import it.pastorini.jms.topic.server.resources.Artist;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Server {
    
    private static final Logger LOG = LoggerFactory.getLogger(Server.class);
    
    public static void main(String args[]) throws Exception {

        List<Artist> artists = ArtistsRestClient.getArtists();
        
        LOG.info(String.format("Found %d artists", artists.size()));
        
        for (Artist artist : artists) {
            //ArtistImpressionProducer artistImpression = new ArtistImpressionProducer(String.format("%s-%s", artist.getSurname(), artist.getName()));
            ArtistImpressionProducer artistImpression = new ArtistImpressionProducer(artist.getSurname());
            artistImpression.start();
        }
	
    }
}
