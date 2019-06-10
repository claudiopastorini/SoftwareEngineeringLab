/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.pastorini.soap.server.entities;

import javax.xml.bind.annotation.adapters.XmlAdapter;


public class ArtistAdapter extends XmlAdapter<ArtistImpl, Artist> {

    @Override
    public Artist unmarshal(ArtistImpl artist) throws Exception {
        return artist;
    }

    @Override
    public ArtistImpl marshal(Artist artist) throws Exception {
        if(artist instanceof ArtistImpl){
            return (ArtistImpl) artist;
        }
        return new ArtistImpl(artist.getSurname(), artist.getSurname(), artist.getAge());
    }
}
