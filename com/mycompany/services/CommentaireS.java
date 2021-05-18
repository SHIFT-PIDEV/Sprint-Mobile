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
import com.company.entities.Client;
import com.company.entities.Commentaire;
import com.company.entities.Event;
import com.company.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author asus
 */
public class CommentaireS {
    public static CommentaireS instance;
    private final ConnectionRequest req;
    private ArrayList<Commentaire> comms;
    public boolean resultOK;
    public CommentaireS(){
        this.req=new ConnectionRequest();
    }
    
    public static CommentaireS getInstance(){
        if(instance==null)
         instance=new CommentaireS();
        return instance;
    }
    
    public ArrayList<Commentaire> parseComms(String jsonText){
        try {
            comms=new ArrayList<>();
            JSONParser j = new JSONParser();
           
            Map<String,Object> usersListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)usersListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                
                Commentaire c = new Commentaire();
                c.setIdcomm(((int)Float.parseFloat(obj.get("idcomm").toString())));
                c.setIdClient((int)Float.parseFloat(obj.get("idUser").toString()));
                c.setIdEvent((int)Float.parseFloat(obj.get("idEvent").toString()));
                c.setDesc(obj.get("desccomm").toString());
                c.setDatecomm(obj.get("datecomm").toString());
                c.setNameClient(obj.get("nameUser").toString());
                //Ajouter la tâche extraite de la réponse Json à la liste
                comms.add(c);
            }
            
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
         
        return comms;
    }
    
    public ArrayList<Commentaire> getComms(int idEvent){
        String url = Statics.BASE_URL+"/getCommsJSON/"+idEvent+"";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                comms = parseComms(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return comms;
    }
    public boolean addComm(Client c,Event e,String desc){
        String url = Statics.BASE_URL + "/addCommJSON?idclient="+c.getId()+ 
                 "&idevent=" + e.getIdE()+"&desc="+desc; 
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this);
              
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        return true;
    }
}
