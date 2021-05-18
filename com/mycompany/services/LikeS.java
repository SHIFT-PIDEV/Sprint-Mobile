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
import com.company.entities.Like;
import com.company.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author asus
 */
public class LikeS {
     public static LikeS instance=null;
    public final ConnectionRequest req;
    public boolean resultOK;
    public ArrayList<Like> likes;
    public LikeS(){
        this.req=new ConnectionRequest();
    }
    
    public static LikeS getInstance(){
        if( instance==null)
                instance=new LikeS();
        return instance;
    }
    
    public boolean addLike(int idClient,int idEvent) {
  
         String url = Statics.BASE_URL + "/addLikeJSON?&idclient="+idClient+ 
                 "&idevent=" + idEvent; //création de l'URL
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
    public boolean deleteLike(int idClient,int idEvent) {
  
         String url = Statics.BASE_URL + "/deleteLikeJSON?&idclient="+idClient+ 
                 "&idevent=" + idEvent; //création de l'URL
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
    public ArrayList<Like> parseLikes(String jsonText){
        try {
            likes=new ArrayList<>();
            JSONParser j = new JSONParser();
           
            Map<String,Object> inscriListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)inscriListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                
                Like like = new Like();
                //Ajouter la tâche extraite de la réponse Json à la liste
                likes.add(like);
            }
            
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
         
        return likes;
    }
     
     public ArrayList<Like> getLikes(int idclient,int idevent){
        String url = Statics.BASE_URL+"/like/testLikeExist/"+idclient+"/"+idevent;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                likes = parseLikes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return likes;
    }
}
