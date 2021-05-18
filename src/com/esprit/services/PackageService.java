/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.esprit.entities.Cour;
import com.esprit.entities.Packge;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Aziz
 */
public class PackageService {
        public ArrayList<Packge> tasks;
   

    ArrayList<Packge> listPack = new ArrayList<>();
    ArrayList<Packge> listPackOne = new ArrayList<>();

    public ArrayList<Packge> CouParseJson(String json) throws ParseException {

        ArrayList<Packge> listEx1 = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();

            Map<String, Object> experiences = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) experiences.get("root");

            for (Map<String, Object> obj : list) {
                System.out.println(obj);
                Packge e = new Packge();
            
                
            //
              float id = Float.parseFloat(obj.get("idpackage").toString());
                float prix = Float.parseFloat(obj.get("prix").toString());
                 float nbcours = Float.parseFloat(obj.get("nbrcours").toString());
//                 Cour courp = (Cour) obj.get("courp.getidcour");
//                 Cour courpp = (Cour) obj.get("courpp.getidcour");
//                 Cour courppp = (Cour) obj.get("courppp.getidcour");
                e.setId((int) id);
                
                e.setNompackage(obj.get("nompackage").toString());
                 e.setPrix((float) prix);
                e.setDescription((String) obj.get("description"));
                e.setDuree((String) obj.get("duree"));
                e.setNbr_cour((int) nbcours );
                e.setCategorie((String) obj.get("categorie"));
                e.setImage((String) obj.get("image"));
//                
                
                
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

    public ArrayList<Packge> getListPack() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/mobile/package");

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                PackageService sl = new PackageService();
                try {
                    listPack = sl.CouParseJson(new String(con.getResponseData()));
                } catch (ParseException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        System.out.println(listPack);
        return listPack;

    }
    
}
