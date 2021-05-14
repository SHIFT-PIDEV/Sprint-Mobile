/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.company.entities.Event;
import com.company.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author asus
 */
public class EventS {
    public ArrayList<Event> events;
    public static EventS instance=null;
    private final ConnectionRequest req;

    private EventS() {
         req = new ConnectionRequest();
    }

    public static EventS getInstance() {
        if (instance == null) {
            instance = new EventS();
        }
        return instance;
    }
    
     public ArrayList<Event> parseEvents(String jsonText){
        try {
            events=new ArrayList<>();
            JSONParser j = new JSONParser();
           
            Map<String,Object> usersListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)usersListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                
                Event e = new Event();
                
                e.setIdE(((int)Float.parseFloat(obj.get("idevent").toString())));
                e.setIdF(((int)Float.parseFloat(obj.get("idformateur").toString())));
                e.setNomE(obj.get("nomevent").toString());
                
                /*DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
                String strDate = dateFormat.format(obj.get("dateDebut"));  
                System.out.println("Converted String: " + strDate);*/
                e.setDateD( obj.get("datedebut").toString());
                e.setHeure(((int)Float.parseFloat(obj.get("heure").toString())));
                e.setDuree(((int)Float.parseFloat(obj.get("duree").toString())));
                e.setDescE(obj.get("descevent").toString());
                e.setImage(obj.get("pic").toString());
                
                //Ajouter la tâche extraite de la réponse Json à la liste
                events.add(e);
            }
            
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
         
        return events;
    }
     
     public ArrayList<Event> getAllEvents(){
        String url = Statics.BASE_URL+"/AllUsersJSON";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                events = parseEvents(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return events;
    }
    
}
