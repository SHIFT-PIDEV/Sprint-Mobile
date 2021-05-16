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
import com.company.entities.InscriEvent;
import com.company.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author asus
 */
public class InscriEventS {
    
    public static InscriEventS instance=null;
    public final ConnectionRequest req;
    public boolean resultOK;
    public ArrayList<InscriEvent> inscris;
    public InscriEventS(){
        this.req=new ConnectionRequest();
    }
    
    public static InscriEventS getInstance(){
        if( instance==null)
                instance=new InscriEventS();
        return instance;
    }
    
    public boolean sinscrire(InscriEvent i) {
  
         String url = Statics.BASE_URL + "/sinscrire/newInscriJSON?idclient="+i.getIdClient()+ 
                 "&idevent=" + i.getIdEvent(); //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
              
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    public ArrayList<InscriEvent> parseInscris(String jsonText){
        try {
            inscris=new ArrayList<>();
            JSONParser j = new JSONParser();
           
            Map<String,Object> inscriListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)inscriListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                
                InscriEvent ie = new InscriEvent();
                
                /*ie.setIdClient(((int)Float.parseFloat(obj.get("client").toString())));
                ie.setIdEvent(((int)Float.parseFloat(obj.get("event").toString())));*/
                ie.setIdinscri(((int)Float.parseFloat(obj.get("idinscri").toString())));
                //Ajouter la tâche extraite de la réponse Json à la liste
                inscris.add(ie);
            }
            
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
         
        return inscris;
    }
     
     public ArrayList<InscriEvent> getInscris(int idclient,int idevent){
        String url = Statics.BASE_URL+"/sinscrire/testInscriExist/"+idclient+"/"+idevent;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                inscris = parseInscris(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return inscris;
    }
}
