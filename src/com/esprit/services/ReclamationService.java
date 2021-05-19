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
import com.codename1.io.rest.Response;
import com.codename1.io.rest.Rest;
import static com.codename1.ui.events.ActionEvent.Type.Response;
import com.codename1.ui.events.ActionListener;
import com.codename1.util.Base64;
import com.esprit.entities.Examen;
import com.esprit.entities.InscripExam;
import com.esprit.entities.RatingR;
import com.esprit.entities.Reclamation;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author medam
 */
public class ReclamationService {
     public ArrayList<Reclamation> tasks;
   

    ArrayList<Reclamation> listreclamation = new ArrayList<>();
  

   public ArrayList<Reclamation> RateParseJson(String json) throws ParseException {

        ArrayList<Reclamation> listR = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();

            Map<String, Object> experiences = j.parseJSON(new CharArrayReader(json.toCharArray()));

            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) experiences.get("root");

            for (Map<String, Object> obj : list) {

                Reclamation e = new Reclamation();
               


               float id = Float.parseFloat(obj.get("id").toString());
               
            

                e.setId((int) id);
                
                e.setObjet(obj.get("objet").toString());
                 e.setDescription(obj.get("description").toString());


                listR.add(e);
                

            }

        } catch (IOException ex) {
        }
        //System.out.println(listEx1);
        return listR;
    }


    public ArrayList<Reclamation> getListreclamation() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/mobile/reclamation");

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ReclamationService sl = new ReclamationService();
                try {
                    listreclamation = sl.RateParseJson(new String(con.getResponseData()));
                } catch (ParseException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        System.out.println(listreclamation);
        return listreclamation;

    }
    ////////////////////////////////////////
    public void addrec(Reclamation r) {
        MultipartRequest con = new MultipartRequest();// création d'une nouvelle demande de connexion
        String Url = "http://127.0.0.1:8000/mobile/newreclamation?" + "objet=" + r.getObjet()+ "&description=" + r.getDescription() ;// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion
          
        con.addResponseListener((ee) -> {
            
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
           // System.out.println(str);//Affichage de la réponse serveur sur la console

        });
             NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
}
