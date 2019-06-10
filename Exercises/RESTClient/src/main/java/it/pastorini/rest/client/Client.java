/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.pastorini.rest.client;

import it.pastorini.rest.client.resources.Artist;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.StatusType;

public class Client {
    
    private final static String TAG = "RESTClient";
    
    private static String BASE_URL = "http://localhost:8081//";
    private static String ARTISTS_URL = BASE_URL + "artists/";
    
    private static javax.ws.rs.client.Client client = ClientBuilder.newClient();
    
    public static List<Artist> getArtists() {
        return client
                .target(ARTISTS_URL)
                .request(MediaType.APPLICATION_XML)
                .get(new GenericType<List<Artist>>() {});
    }
    
    public static Artist getArtist(String uuid) {
        return client
                .target(ARTISTS_URL)
                .path(String.valueOf(uuid))
                .request(MediaType.APPLICATION_XML)
                .get(Artist.class);
    }
    
    public static Artist postArtist(Artist artist) throws Exception {
        Response response = client
                .target(ARTISTS_URL)
                .request(MediaType.APPLICATION_XML)
                .post(Entity.entity(artist, MediaType.APPLICATION_XML));
        
        StatusType responseStatus = response.getStatusInfo();
        if (responseStatus.getFamily() != Response.Status.Family.SUCCESSFUL) {
            throw new Exception(String.format("%s (%d): %s", responseStatus.getFamily(), responseStatus.getStatusCode(), responseStatus.getReasonPhrase()));
        }
        
        return response.readEntity(Artist.class);
    }
    
    private static void deleteArtist(String uuid) throws Exception {
        Response response = client
            .target(ARTISTS_URL)
            .path(String.valueOf(uuid))
            .request(MediaType.APPLICATION_XML)
            .delete();
        
        StatusType responseStatus = response.getStatusInfo();
        if (responseStatus.getFamily() != Response.Status.Family.SUCCESSFUL) {
            throw new Exception(String.format("%s (%d): %s", responseStatus.getFamily(), responseStatus.getStatusCode(), responseStatus.getReasonPhrase()));
        }
    }
    
    public static void main(String argc[]) {
        List<Artist> artists = getArtists();
        Logger.getLogger(TAG).log(Level.INFO, String.format("Found '%d' artists", artists.size()));
        
        Artist lastArtist = null;
        for (Artist artist : artists) {
            Logger.getLogger(TAG).log(Level.INFO, String.format("Asking info for artist with UUID: '%s'", artist.getUuid()));
            
            Artist downloadedArtist = getArtist(artist.getUuid());
            Logger.getLogger(TAG).log(Level.INFO, String.format("%s", downloadedArtist.toString()));
            
            lastArtist = downloadedArtist;
        }
        
        try {
            Artist artistToPost = new Artist("Adriano", "Pastorini");
            Logger.getLogger(TAG).log(Level.INFO, String.format("Artist to post: %s", artistToPost.toString()));
            Artist artistJustPosted = postArtist(artistToPost);
            Logger.getLogger(TAG).log(Level.INFO, String.format("Artist received: %s", artistJustPosted.toString()));
        } catch (Exception ex) {
            Logger.getLogger(TAG).log(Level.SEVERE, "Error during the POST!", ex);
        }
        
        try {
            
            if (lastArtist != null) {
                Logger.getLogger(TAG).log(Level.INFO, String.format("Artist to delete: %s", lastArtist.toString()));
                deleteArtist(lastArtist.getUuid());
                Logger.getLogger(TAG).log(Level.INFO, "Artist delete!");
            }
               
        } catch (Exception ex) {
            Logger.getLogger(TAG).log(Level.SEVERE, "Error during the DELETE!", ex);
        }
    }
}
