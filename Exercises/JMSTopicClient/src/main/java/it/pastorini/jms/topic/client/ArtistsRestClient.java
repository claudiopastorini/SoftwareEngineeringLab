/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.pastorini.jms.topic.client;

import it.pastorini.jms.topic.client.resources.Artist;
import java.util.List;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;


public class ArtistsRestClient {
    private static String BASE_URL = "http://localhost:8081//";
    private static String ARTISTS_URL = BASE_URL + "artists/";
    
    private static javax.ws.rs.client.Client client = ClientBuilder.newClient();
    
    public static List<Artist> getArtists() {
        return client
                .target(ARTISTS_URL)
                .request(MediaType.APPLICATION_XML)
                .get(new GenericType<List<Artist>>() {});
    }
}
