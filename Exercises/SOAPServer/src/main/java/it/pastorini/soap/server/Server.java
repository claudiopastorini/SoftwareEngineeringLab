/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.pastorini.soap.server;

import javax.xml.ws.Endpoint;

/**
 *
 * @author biar
 */
public class Server {
    public static void main(String args[]) {
        ArtistsCatalogImpl implementor = new ArtistsCatalogImpl();
        String address = "http://localhost:8080/ArtistsCatalog";
        Endpoint.publish(address, implementor);
        System.out.println("Artists catalog server ready...");
    }
}
