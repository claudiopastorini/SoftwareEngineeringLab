/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.pastorini.rest.server.resources;

import static com.sun.xml.internal.org.jvnet.fastinfoset.EncodingAlgorithmIndexes.UUID;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class ArtistRepository {
    
    String[] oldArtists = {"Mecella,Massimo","Ceriani,Miguel"};
    private Map<String, Artist> artistsInMemory = new HashMap<>();
    
    {
        for (String artist : this.oldArtists) {
            String[] splitted = artist.split(",");
            String surname = splitted[1];
            String name = splitted[0];
            
            Artist newArtist = new Artist(surname, name);
            String newArtistUuid = newArtist.getUuid();
            System.out.println(String.format("Adding a new artist: %s", newArtist));
            
            artistsInMemory.put(newArtistUuid, newArtist);
        }
    }
    
    private Artist findByUuid(String uuid) {
        return artistsInMemory.get(uuid);
    }
    
    @GET
    @Path("artists")
    public List<Artist> getArtists() {
        return new ArrayList<Artist>(artistsInMemory.values());
    }
    
    @POST
    @Path("artists")
    public Response postArtist(Artist artist) {
        List<Artist> oldArtists = new ArrayList<Artist>(artistsInMemory.values());
        for (Artist oldArtist : oldArtists) {
            if (oldArtist.equals(artist)) {
                return Response.status(Response.Status.CONFLICT).build();
            }
        }
        
        Artist newArtist = new Artist(artist);
        
        artistsInMemory.put(newArtist.getUuid(), newArtist);
        
        return Response.ok(newArtist).build();
    }
    
    @GET
    @Path("artists/{artistUuid}")
    public Artist getArtist(@PathParam("artistUuid") String artistUuid) {
        return findByUuid(artistUuid);
    }
    
    @DELETE
    @Path("artists/{artistUuid}")
    public Response deleteArtist(@PathParam("artistUuid") String artistUuid) {
        Artist artist = findByUuid(artistUuid);
        if (artist == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        artistsInMemory.remove(artistUuid);
        return Response.ok().build();
    }
}
