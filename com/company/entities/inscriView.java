/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.entities;

/**
 *
 * @author asus
 */
class inscriView {
     private String nomClient;
    private String emailClient;

    public inscriView() {
        super();
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getEmailClient() {
        return emailClient;
    }

    public void setEmailClient(String emailClient) {
        this.emailClient = emailClient;
    }

    @Override
    public String toString() {
        super.toString();
        return "inscriView{" + "nomClient=" + nomClient + ", emailClient=" + emailClient + '}';
    }
}
