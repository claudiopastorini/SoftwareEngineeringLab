/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.pastorini.soap.server;

import it.pastorini.soap.server.entities.Artist;
import it.pastorini.soap.server.entities.ArtistImpl;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;

@WebService(endpointInterface = "it.pastorini.soap.server.ArtistsCatalog")
public class ArtistsCatalogImpl implements ArtistsCatalog {

    String[] oldArtists = {"Mecella,Massimo","Ceriani,Miguel"};
    
    @Override
    public List<Artist> getArtists() {
        List<Artist> artists = new ArrayList<>();
        
        for (String artist : this.oldArtists) {
            String[] splitted = artist.split(",");
            String surname = splitted[0];
            String name = splitted[1];
            
            Artist newArtist = new ArtistImpl(surname, name);
            artists.add(newArtist);
        }
            
        return artists;
    }
    
    @Override
    public List<String> getBios() {
        List<String> bios = new ArrayList<>();
        
        for (String artistString : this.oldArtists) {
            String[] splitted = artistString.split(",");
            String surname = splitted[0];
            String name = splitted[1];
            Artist artist = new ArtistImpl(surname, name);
            
            bios.add(artist.getBio());
        }
            
        return bios;
    }

    @Override
    public int getNumberOfArtists() {
        return this.oldArtists.length;
    }
    
}
