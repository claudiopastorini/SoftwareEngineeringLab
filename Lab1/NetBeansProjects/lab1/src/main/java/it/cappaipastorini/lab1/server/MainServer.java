/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cappaipastorini.lab1.server;

import javax.xml.ws.Endpoint;

/**
 *
 * @author studente
 */
public class MainServer {
    public static void main(String args[]) throws InterruptedException {
        AAAWSImpl implementor = new AAAWSImpl();
        String address = "http://localhost:8080/AAAWS";
        Endpoint.publish(address, implementor);
        System.out.println("Server ready...");
    }
}
