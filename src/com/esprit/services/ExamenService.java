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
import com.codename1.ui.events.ActionListener;
import com.esprit.entities.Examen;
import com.esprit.entities.InscripExam;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mahdi
 */
public class ExamenService {
    public ArrayList<Examen> tasks;
   

    ArrayList<Examen> listExam = new ArrayList<>();
    ArrayList<Examen> listExamOne = new ArrayList<>();

    public ArrayList<Examen> ExamParseJson(String json) throws ParseException {

        ArrayList<Examen> listEx1 = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();

            Map<String, Object> experiences = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) experiences.get("root");

            for (Map<String, Object> obj : list) {

                Examen e = new Examen();
                // float id = Float.parseFloat(obj.get("idExperience").toString());
               DateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
              // String s= df.format(obj.get("date"));
               String t=obj.get("date").toString();
//               String datee=df.format(t);
//                System.out.println(datee+"date aaaa");
                 
        //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
            // DateFormatPatterns.ISO8601
       // Date date = format.parse(t);     
        //System.out.println(date);
//                Instant instant = Instant.parse( t ) ;
//                Date d = Date.from( instant );
//                System.out.println(d);

                float id = Float.parseFloat(obj.get("id").toString());
                float prix = Float.parseFloat(obj.get("prix").toString());

                e.setIdE((int) id);
                e.setTitreE(obj.get("titre").toString());
                e.setDate(obj.get("date").toString());
                e.setNiveau(obj.get("niveau").toString());
                e.setPrixE((int) prix);
                e.setSupport((String) obj.get("support"));
                
//                String dateStr = obj.get("date").toString();
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//                Date date = sdf.parse(dateStr);
//                sdf = new SimpleDateFormat("dd.MM.yyyy");
//                dateStr = sdf.format(date);
//                System.out.println(dateStr);

                listEx1.add(e);
                

            }

        } catch (IOException ex) {
        }
        //System.out.println(listEx1);
        return listEx1;
    }

    public ArrayList<Examen> getListExamen() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/mobile/exam");

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ExamenService sl = new ExamenService();
                try {
                    listExam = sl.ExamParseJson(new String(con.getResponseData()));
                } catch (ParseException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        System.out.println(listExam);
        return listExam;

    }
    public void addinscri(InscripExam e) {
        MultipartRequest con = new MultipartRequest();// création d'une nouvelle demande de connexion
        String Url = "http://127.0.0.1:8000/mobile/newinscri?" + "idexam=" + e.getIdExam()+ "&nom=" + e.getNom()+ "&prenom=" + e.getPrenom()+ "&email=" + e.getEmail() ;// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion
          
        con.addResponseListener((ee) -> {
            
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
}
