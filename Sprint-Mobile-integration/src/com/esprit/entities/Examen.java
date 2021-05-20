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
 * @author mahdi
 */
public class Examen {
    
     private int idE;
    private String titreE;
    private String date;
    private String niveau ;
    private int prixE;
    private String support ;

    public Examen() {
    }

    public Examen( String titreE,  String niveau) {
       
        this.titreE = titreE;
        this.niveau = niveau;
    }

    public Examen( String titreE, String date, String niveau, int prixE) {
       
        this.titreE = titreE;
        this.date = date;
        this.niveau = niveau;
        this.prixE = prixE;
    }

    public Examen(int idE, String titreE, String date, String niveau, int prixE) {
        this.idE = idE;
        this.titreE = titreE;
        this.date = date;
        this.niveau = niveau;
        this.prixE = prixE;
    }

    public Examen(String titreE, String date, String niveau, int prixE, String support) {
        this.titreE = titreE;
        this.date = date;
        this.niveau = niveau;
        this.prixE = prixE;
        this.support = support;
    }
    
    
    public Examen(int idE, String titreE, String date, String niveau, int prixE, String support) {
        this.idE = idE;
        this.titreE = titreE;
        this.date = date;
        this.niveau = niveau;
        this.prixE = prixE;
        this.support = support;
    }


    

    public int getIdE() {
        return idE;
    }

    public void setIdE(int idE) {
        this.idE = idE;
    }

    public String getTitreE() {
        return titreE;
    }

    public void setTitreE(String titreE) {
        this.titreE = titreE;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public float getPrixE() {
        return prixE;
    }

    public void setPrixE(int prixE) {
        this.prixE = prixE;
    }

    public String getSupport() {
        return support;
    }

    public void setSupport(String support) {
        this.support = support;
    }

    @Override
    public String toString() {
        return "Examen{" + "idE=" + idE + ", titreE=" + titreE + ", date=" + date + ", niveau=" + niveau + ", prixE=" + prixE + ", support=" + support + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.idE;
        hash = 53 * hash + Objects.hashCode(this.titreE);
        hash = 53 * hash + Objects.hashCode(this.date);
        hash = 53 * hash + Objects.hashCode(this.niveau);
        hash = 53 * hash + Float.floatToIntBits(this.prixE);
        hash = 53 * hash + Objects.hashCode(this.support);
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
        final Examen other = (Examen) obj;
        if (this.idE != other.idE) {
            return false;
        }
        if (Float.floatToIntBits(this.prixE) != Float.floatToIntBits(other.prixE)) {
            return false;
        }
        if (!Objects.equals(this.titreE, other.titreE)) {
            return false;
        }
        if (!Objects.equals(this.niveau, other.niveau)) {
            return false;
        }
        if (!Objects.equals(this.support, other.support)) {
            return false;
        }
        return Objects.equals(this.date, other.date);
    }
    
    
    
}
