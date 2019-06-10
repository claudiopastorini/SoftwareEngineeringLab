/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cappaipastorini.lab1.server;

import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;

/**
 *
 * @author studente
 */
@WebService(endpointInterface = "it.cappaipastorini.lab1.server.AAAWS")
public class AAAWSImpl implements AAAWS {
    
    String[] oldClients = {"1,Massimo Mecella","2,Miguel Ceriani"};

    @Override
    public List<Client> getClients() {
        List<Client> clients = new ArrayList<>();
        
        for (String client : this.oldClients) {
            String[] splitted = client.split(",");
            int clientId = Integer.parseInt(splitted[0]);
            String clientName = splitted[1];
            
            Client newClient = new ClientImpl(clientId, clientName);
            clients.add(newClient);
        }
            
        return clients;
    }
    
}
