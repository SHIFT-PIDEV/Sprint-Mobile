/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.entities;

//import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author asus
 */
public class Commentaire {
    private int idcomm;
    private int idClient;
    private int idEvent;
   // private Timestamp datecomm;
    private Date datecomm;
    private String desc;

    public Commentaire() {
    }

    public Commentaire(int idcomm, int idClient, int idEvent, Date dateInscri,String desc) {
        this.idcomm = idcomm;
        this.idClient = idClient;
        this.idEvent = idEvent;
        this.datecomm = dateInscri;
        this.desc=desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getIdcomm() {
        return idcomm;
    }

    public void setIdcomm(int idcomm) {
        this.idcomm = idcomm;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public Date getDatecomm() {
        return datecomm;
    }

    public void setDatecomm(Date datecomm) {
        this.datecomm = datecomm;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "idcomm=" + idcomm + ", idClient=" + idClient + ", idEvent=" + idEvent + ", datecomm=" + datecomm + ", desc=" + desc + '}';
    }
}
