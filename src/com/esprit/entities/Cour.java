/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

/**
 *
 * @author Aziz
 */
public class Cour {
         private int  id;
    private String nom_cour;
    private String categorie ;
    private String formateur;
    private String description;
    private String image_v ;
    private float  prix;
    private String niveau;
    private String duration;
    
    public Cour (){
        
    }

    public Cour(int id, String nom_cour, String categorie, String formateur, String description, String image_v, float prix, String niveau, String duration) {
        this.id = id;
        this.nom_cour = nom_cour;
        this.categorie = categorie;
        this.formateur = formateur;
        this.description = description;
        this.image_v = image_v;
        this.prix = prix;
        this.niveau = niveau;
        this.duration = duration;
    }

    public Cour(String nom_cour, String categorie, String formateur, String description, String image_v, float prix, String niveau, String duration) {
        this.nom_cour = nom_cour;
        this.categorie = categorie;
        this.formateur = formateur;
        this.description = description;
        this.image_v = image_v;
        this.prix = prix;
        this.niveau = niveau;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_cour() {
        return nom_cour;
    }

    public void setNom_cour(String nom_cour) {
        this.nom_cour = nom_cour;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getFormateur() {
        return formateur;
    }

    public void setFormateur(String formateur) {
        this.formateur = formateur;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_v() {
        return image_v;
    }

    public void setImage_v(String image_v) {
        this.image_v = image_v;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id;
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
        final Cour other = (Cour) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    

}
