/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.pastorini.jms.topic.client.resources;

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
    
    public Artist(String name, String surname) {
        this.uuid = null;
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
        return "Artist{" + "uuid=" + (uuid != null ? uuid : "<NONE>")  + ", name=" + name + ", surname=" + surname + ", age=" + age + '}';
    }   
    
}
