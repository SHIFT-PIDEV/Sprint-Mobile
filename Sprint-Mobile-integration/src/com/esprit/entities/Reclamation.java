/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

import java.util.Objects;

/**
 *
 * @author pc
 */
public class Reclamation {
    private int id;
    private String objet;
    private String description;

    public Reclamation(int id, String objet, String description) {
        this.id = id;
        this.objet = objet;
        this.description = description;
    }

    public Reclamation(String objet, String description) {
        this.objet = objet;
        this.description = description;
    }


    public Reclamation(int id, String objet) {
        this.id = id;
        this.objet = objet;
    }

    public Reclamation() {
  }

   

   

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id;
        hash = 67 * hash + Objects.hashCode(this.objet);
        hash = 67 * hash + Objects.hashCode(this.description);
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
        final Reclamation other = (Reclamation) obj;
        if (this.id != other.id) {
            return false;
        }
      if (!Objects.equals(this.objet, other.objet)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id +  ", objet=" + objet + ", description=" + description + '}';
    }


   
    
    
    
}
