/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cappaipastorini.lab1.server;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 *
 * @author studente
 */
@XmlJavaTypeAdapter(ClientAdapter.class)
public interface Client {
    public int getId();
    public String getName();
    public String getDescription();
}
