/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.pastorini.rest.server.resources;

import java.util.Objects;
import java.util.UUID;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Artist")
public class Artist {
    private String uuid;
    
    private String name;
    
    private String surname;
    
    private int age;

    public Artist() {
    }
    
    public Artist(Artist oldArtist) {
        this.uuid = UUID.randomUUID().toString();
        this.name = oldArtist.getName();
        this.surname = oldArtist.getSurname();
        this.age = oldArtist.getAge();
    }
    
    public Artist(String name, String surname) {
        this.uuid = UUID.randomUUID().toString();
        this.name = name;
        this.surname = surname;
        this.age = 0;
    }
    
    public Artist(String name, String surname, int age) {
        super();
        this.age = age;
    }
    
    public String getName() {
        return name;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Artist{" + "uuid=" + uuid + ", name=" + name + ", surname=" + surname + ", age=" + age + '}';
    }   

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.uuid);
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + Objects.hashCode(this.surname);
        hash = 37 * hash + this.age;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Artist other = (Artist) obj;
        if (Objects.equals(this.uuid, other.uuid)) {
            return true;
        }
        if (Objects.equals(this.name, other.name) && Objects.equals(this.surname, other.surname)) {
            return true;
        }
        
        return false;
    }

   
    
    
}
