/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.pastorini.soap.server;

import it.pastorini.soap.server.entities.Artist;
import java.util.List;
import javax.jws.WebService;

@WebService
public interface ArtistsCatalog {
    public List<Artist> getArtists();
    public List<String> getBios();
    public int getNumberOfArtists();
}
