/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cappaipastorini.lab1.server;

import java.util.Objects;

/**
 *
 * @author studente
 */
public class ClientImpl implements Client {
    private String name;
    private int id;

    public ClientImpl(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public ClientImpl() {
    }
          
    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name; 
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Client{" + "name=" + name + ", id=" + id + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.name);
        hash = 83 * hash + this.id;
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
        final ClientImpl other = (ClientImpl) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    
}
