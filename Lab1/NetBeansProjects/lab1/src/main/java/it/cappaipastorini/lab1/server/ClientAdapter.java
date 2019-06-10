/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cappaipastorini.lab1.server;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author studente
 */
public class ClientAdapter extends XmlAdapter<ClientImpl, Client> {

    @Override
    public Client unmarshal(ClientImpl client) throws Exception {
        return client;
    }

    @Override
    public ClientImpl marshal(Client client) throws Exception {
        if(client instanceof ClientImpl){
            return (ClientImpl) client;
        }
        return new ClientImpl(client.getId(), client.getName());
    }

}
