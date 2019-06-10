/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.pastorini.soap.server.entities;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlJavaTypeAdapter(ArtistAdapter.class)
public interface Artist {
    public String getName();
    
    public String getSurname();
    
    public String getBio();
    
    public int getAge();
}
