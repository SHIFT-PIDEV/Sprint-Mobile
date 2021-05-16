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
import com.company.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author asus
 */
public class ClientS {
    public static ClientS instance;
    private final ConnectionRequest req;
   public ArrayList<Client> users;
    
    public ClientS(){
        this.req=new ConnectionRequest();
    }
    
    public static ClientS getInstance(){
        if(instance==null)
            instance=new ClientS();
        return instance;
    }
    
    public ArrayList<Client> parseUsers(String jsonText){
        try {
            users=new ArrayList<>();
            JSONParser j = new JSONParser();
           
            Map<String,Object> usersListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)usersListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                
                Client c = new Client();
                c.setId(((int)Float.parseFloat(obj.get("id").toString())));
                c.setNom(obj.get("nom").toString());
                c.setPrenom(obj.get("prenom").toString());
                c.setUserName(obj.get("username").toString());
                c.setEmail(obj.get("email").toString());
                c.setMdp(obj.get("mdp").toString());
               c.setPic("pic.jpg");
                c.setNbrnotif(((int)Float.parseFloat(obj.get("nbrnotif").toString())));
                //Ajouter la tâche extraite de la réponse Json à la liste
                users.add(c);
            }
            
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
         
        return users;
    }
    
    public ArrayList<Client> getUser(String email,String mdp){
        String url = Statics.BASE_URL+"/getUserJson/"+email+"/"+mdp;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseUsers(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return users;
    }
    
}
