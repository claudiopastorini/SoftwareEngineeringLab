/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.pastorini.soap.client;

import java.util.List;

import it.pastorini.soap.client.ArtistImpl;
import it.pastorini.soap.client.ArtistsCatalog;
import it.pastorini.soap.client.ArtistsCatalog;
import it.pastorini.soap.client.ArtistsCatalogImplService;

public class Client {
    public static void main(String argc[]) {

        ArtistsCatalogImplService service = new ArtistsCatalogImplService();
        ArtistsCatalog artistsCatalog = service.getArtistsCatalogImplPort();
        
        System.out.println(String.format("Ci sono %d artisti", artistsCatalog.getNumberOfArtists()));
        System.out.println(artistsCatalog.getBios());
    }
}
