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
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Aziz
 */
public class CourService {
    public ArrayList<Cour> tasks;
   

    ArrayList<Cour> listCou = new ArrayList<>();
    ArrayList<Cour> listCouOne = new ArrayList<>();

    public ArrayList<Cour> CouParseJson(String json) throws ParseException {

        ArrayList<Cour> listEx1 = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();

            Map<String, Object> experiences = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) experiences.get("root");

            for (Map<String, Object> obj : list) {
                System.out.println(obj);
                Cour e = new Cour();
                
//
              float id = Float.parseFloat(obj.get("idcour").toString());
                float prix = Float.parseFloat(obj.get("prix").toString());

                e.setId((int) id);
                e.setNom_cour(obj.get("nom").toString());
                e.setNiveau(obj.get("niveau").toString());
                e.setPrix((int) prix);
                e.setDescription((String) obj.get("description"));
                e.setDuration((String) obj.get("duration"));
                e.setFormateur((String) obj.get("formateur"));
                e.setCategorie((String) obj.get("nomcat"));
                e.setImage_v((String) obj.get("img"));
                
                
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

    public ArrayList<Cour> getListCour() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/mobile/cour");

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                CourService sl = new CourService();
                try {
                    listCou = sl.CouParseJson(new String(con.getResponseData()));
                } catch (ParseException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        System.out.println(listCou);
        return listCou;

    }
    
}
