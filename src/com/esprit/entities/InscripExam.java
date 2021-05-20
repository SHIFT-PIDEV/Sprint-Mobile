/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

import java.util.Date;




/**
 *
 * @author mahdi
 */
public class InscripExam {
     private int idinscri;
    private int idClient;
    private int idExam;
    private Date dateInscri;
    private String nom;
     private String prenom;
      private String email;

    public InscripExam() {
    }

    public int getIdinscri() {
        return idinscri;
    }

    public InscripExam(int idExam, String nom, String prenom, String email) {
        this.idExam = idExam;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }
    

    public void setIdinscri(int idinscri) {
        this.idinscri = idinscri;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdExam() {
        return idExam;
    }

    public void setIdExam(int idExam) {
        this.idExam = idExam;
    }

    public Date getDateInscri() {
        return dateInscri;
    }

    public void setDateInscri(Date dateInscri) {
        this.dateInscri = dateInscri;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "InscripExam{" + "idinscri=" + idinscri + ", idClient=" + idClient + ", idExam=" + idExam + ", dateInscri=" + dateInscri + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + '}';
    }
    
      
      
    
}
