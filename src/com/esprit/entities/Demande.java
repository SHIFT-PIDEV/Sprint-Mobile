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
public class Demande    {
    private int id;
    private String objet;
    private String description;
    private String cv;


    public Demande(int id, String objet, String description, String cv) {
        this.id = id;
        this.objet = objet;
        this.description = description;
        this.cv=cv;
    }

    public Demande(String objet, String description, String cv) {
        this.objet = objet;
        this.description = description;
        this.cv = cv;
    }


    public Demande(int id, String objet) {
        this.id = id;
        this.objet = objet;
    }

    /**
     *
     * @param id
     * @param objet
     * @param description
     */
    public Demande(int id, String objet,String description) {
        this.id = id;
        this.objet = objet;
        this.description=description;
    }
    public Demande() {
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
 public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id;
        hash = 67 * hash + Objects.hashCode(this.objet);
        hash = 67 * hash + Objects.hashCode(this.description);
        hash = 67 * hash + Objects.hashCode(this.cv);

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
        final Demande other = (Demande) obj;
        if (this.id != other.id) {
            return false;
        }
      if (!Objects.equals(this.objet, other.objet)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
         if (!Objects.equals(this.cv, other.cv)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Demande{" + "id=" + id +  ", objet=" + objet + ", description=" + description + ", cv=" + cv +'}';
    }


   
    
    
    
}
