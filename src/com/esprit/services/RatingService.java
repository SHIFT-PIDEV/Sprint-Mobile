/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.List;
import com.codename1.ui.events.ActionListener;
import com.esprit.entities.Examen;
import com.esprit.entities.InscripExam;
import com.esprit.entities.RatingR;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author mahdi
 */
public class RatingService {
      public ArrayList<RatingR> tasks;
   

    ArrayList<RatingR> listrate = new ArrayList<>();
  

    public ArrayList<RatingR> RateParseJson(String json) throws ParseException {

        ArrayList<RatingR> listR = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();

            Map<String, Object> experiences = j.parseJSON(new CharArrayReader(json.toCharArray()));

            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) experiences.get("root");

            for (Map<String, Object> obj : list) {

                RatingR e = new RatingR();
               


               float idcc = Float.parseFloat(obj.get("idclient").toString());
                float idee = Float.parseFloat(obj.get("idexam").toString());
                float idrr = Float.parseFloat(obj.get("idr").toString());
                float rate = Float.parseFloat(obj.get("rate").toString());
            

                e.setIdClient((int) idcc);
                 e.setIdExam((int) idee);
                  e.setIdR((int) idrr);
                e.setCommentaire(obj.get("commentaire").toString());
                e.setRate((int) rate);
 

                listR.add(e);
                

            }

        } catch (IOException ex) {
        }
        //System.out.println(listEx1);
        return listR;
    }


    public ArrayList<RatingR> getListrating() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/mobile/rating");

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                RatingService sl = new RatingService();
                try {
                    listrate = sl.RateParseJson(new String(con.getResponseData()));
                } catch (ParseException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        System.out.println(listrate);
        return listrate;

    }
    ////////////////////////////////////////
    public void addcom(RatingR r) {
        MultipartRequest con = new MultipartRequest();// création d'une nouvelle demande de connexion
        String Url = "http://127.0.0.1:8000/mobile/newcomment?"+ "idexam=" + r.getIdExam() + "&commentaire=" + r.getCommentaire() ;// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion
          
        con.addResponseListener((ee) -> {
            
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
             NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
}
