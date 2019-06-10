/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.pastorini.soap.server.entities;

public class ArtistImpl implements Artist {

    public String name;
    
    public String surname;
    
    public int age;
    
    public ArtistImpl() {
        
    }
    
    public ArtistImpl(String surname, String name) {
        this.name = name;
        this.surname = surname;
        this.age = 0;
    }
    
    public ArtistImpl(String surname, String name, int age) {
        super();
        this.age = age;
    }
    
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getSurname() {
        return this.surname;
    }

    @Override
    public String toString() {
        return "ArtistImpl{" + "name=" + name + ", surname=" + surname + '}';
    }

    @Override
    public int getAge() {
        return this.age;
    }

    @Override
    public String getBio() {
        return String.format("%s %s è un artista di %d anni.", this.surname, this.name, this.age);
    }
    
}
