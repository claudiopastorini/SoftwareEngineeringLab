/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cappaipastorini.lab1.server;

import java.util.List;
import javax.jws.WebService;

/**
 *
 * @author studente
 */
@WebService
public interface AAAWS {
    public List<Client> getClients();
}
