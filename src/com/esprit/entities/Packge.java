/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

/**
 *
 * @author mahdi
 */
public class Packge {
     private int id;
    private String nompackage;
    private Float prix ;
    private String duree ;
    private String description ;
    private String categorie;

    private String image ;
        private int nbr_cour ;
        private int courp;
        private int courpp;
        private int courppp;

    public Packge() {
    }

    public Packge(int id, String nompackage, Float prix, String duree, String description, String categorie, String image, int nbr_cour, int courp, int courpp, int courppp) {
        this.id = id;
        this.nompackage = nompackage;
        this.prix = prix;
        this.duree = duree;
        this.description = description;
        this.categorie = categorie;
        this.image = image;
        this.nbr_cour = nbr_cour;
        this.courp = courp;
        this.courpp = courpp;
        this.courppp = courppp;
    }

    public Packge(String nompackage, Float prix, String duree, String description, String categorie, String image, int nbr_cour, int courp, int courpp, int courppp) {
        this.nompackage = nompackage;
        this.prix = prix;
        this.duree = duree;
        this.description = description;
        this.categorie = categorie;
        this.image = image;
        this.nbr_cour = nbr_cour;
        this.courp = courp;
        this.courpp = courpp;
        this.courppp = courppp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNompackage() {
        return nompackage;
    }

    public void setNompackage(String nompackage) {
        this.nompackage = nompackage;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getNbr_cour() {
        return nbr_cour;
    }

    public void setNbr_cour(int nbr_cour) {
        this.nbr_cour = nbr_cour;
    }

    public int getCourp() {
        return courp;
    }

    public void setCourp(int courp) {
        this.courp = courp;
    }

    public int getCourpp() {
        return courpp;
    }

    public void setCourpp(int courpp) {
        this.courpp = courpp;
    }

    public int getCourppp() {
        return courppp;
    }

    public void setCourppp(int courppp) {
        this.courppp = courppp;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + this.id;
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
        final Packge other = (Packge) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
