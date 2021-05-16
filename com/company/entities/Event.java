/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.entities;

//import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asus
 */
public class Event {
     private int idE;
    private int idF;
    private String nomE;
    //private LocalDate dateD;
    private String dateD;
    private int heure;
    private float duree;
    private String descE;
    private String image;
    public List<inscriView> listInscri;
    
    private int likesNumber;
    private int inscriptionsNumber;

    public int getLikesNumber() {
        return likesNumber;
    }

    public void setLikesNumber(int likesNumber) {
        this.likesNumber = likesNumber;
    }
     public int getInscriptionsNumber() {
        return inscriptionsNumber;
    }

    public void setInscriptionsNumber(int inscriptionsNumber) {
        this.inscriptionsNumber = inscriptionsNumber;
    }
    
 
    public Event(){
        this.listInscri=new ArrayList<>();
    }
    
    public Event( int idF, String nomE, String dateD, int heure, float duree, String descE, String image) {
        //this.idE = idE;
        this.idF = idF;
        this.nomE = nomE;
        this.dateD = dateD;
        this.heure = heure;
        this.duree = duree;
        this.descE = descE;
        this.image = image;
    }
    
    public int getIdE() {
        return idE;
    }

    public void setIdE(int idE) {
        this.idE = idE;
    }

    public int getIdF() {
        return idF;
    }

    public void setIdF(int idF) {
        this.idF = idF;
    }

    public String getNomE() {
        return nomE;
    }

    public void setNomE(String nomE) {
        this.nomE = nomE;
    }

    public String getDateD() {
        return this.dateD;
    }

    public void setDateD(String dateD) {
        this.dateD = dateD;
    }
    
    public int getHeure() {
        return heure;
    }

    public void setHeure(int heure) {
        this.heure = heure;
    }

    public float getDuree() {
        return duree;
    }

    public void setDuree(float duree) {
        this.duree = duree;
    }

    public String getDescE() {
        return descE;
    }

    public void setDescE(String descE) {
        this.descE = descE;
    }
    
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.idE;
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
        final Event other = (Event) obj;
        if (this.idE != other.idE) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Event{" + "idE=" + idE + ", idF=" + idF + ", nomE=" + nomE + ", dateD=" + dateD + ", heure=" + heure + ", duree=" + duree + ", descE=" + descE + ", image=" + image + '}';
    }

    
}
