/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

import java.util.Date;
import java.util.Objects;


/**
 *
 * @author Fedy
 */
public class Panier {
    
    
    private int id ;
    private String nom;
    private int prix;
    
    public Panier(){
        
    }
    public Panier(int id, String nom, int prix) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
    }

    public Panier(String nom, int prix) {
        this.nom = nom;
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getPrix() {
        return prix;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Panier{" + "id=" + id + ", nom=" + nom + ", prix=" + prix + '}';
    }
    
    
}
