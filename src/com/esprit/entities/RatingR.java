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
public class RatingR {
    private int idClient;
    private int idExam;
    private int idR;
    private String commentaire;
    private int rate;

    public RatingR(int idClient, int idExam, int idR, String commentaire, int rate) {
        this.idClient = idClient;
        this.idExam = idExam;
        this.idR = idR;
        this.commentaire = commentaire;
        this.rate = rate;
    }

    public RatingR() {
    }

    public RatingR(int idExam, String commentaire) {
        this.idExam = idExam;
        this.commentaire = commentaire;
    }

   

    public RatingR(int idClient, int idExam, String commentaire, int rate) {
        this.idClient = idClient;
        this.idExam = idExam;
        this.commentaire = commentaire;
        this.rate = rate;
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

    public int getIdR() {
        return idR;
    }

    public void setIdR(int idR) {
        this.idR = idR;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.idClient;
        hash = 67 * hash + this.idExam;
        hash = 67 * hash + this.idR;
        hash = 67 * hash + Objects.hashCode(this.commentaire);
        hash = 67 * hash + this.rate;
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
        final RatingR other = (RatingR) obj;
        if (this.idClient != other.idClient) {
            return false;
        }
        if (this.idExam != other.idExam) {
            return false;
        }
        if (this.idR != other.idR) {
            return false;
        }
        if (this.rate != other.rate) {
            return false;
        }
        if (!Objects.equals(this.commentaire, other.commentaire)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RatingR{" + "idClient=" + idClient + ", idExam=" + idExam + ", idR=" + idR + ", commentaire=" + commentaire + ", rate=" + rate + '}';
    }


   
    
    
    
}
