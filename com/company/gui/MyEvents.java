/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.TOP;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.company.entities.Event;
import com.mycompany.services.EventS;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author asus
 */
public class MyEvents extends Form {
   public MyEvents(String title){
        super(title);
        this.setLayout(new FlowLayout(TOP,CENTER));
        Container bigContainer=new Container(BoxLayout.y());
        
        ArrayList<Event> eventsList=new ArrayList();
        EventS es=EventS.getInstance();
        eventsList=es.myEvents(Login.client.getId());
        for(Event e:eventsList){
            Container c=new Container(BoxLayout.y());
        
        //récuperer l image de l event
        ImageViewer iv=null;
        try{
             iv= new ImageViewer();
               EncodedImage ec = EncodedImage.create("/load.jpg");
                Image img = URLImage.createToStorage(ec,"http://127.0.0.1:8000/uploads/"+e.getImage(), 
                        "http://127.0.0.1:8000/uploads/"+e.getImage(), URLImage.RESIZE_SCALE);
                iv.setImage(img);
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        //**********
        this.getToolbar().addCommandToLeftBar("Back", null, t->{
            AllEvents allE=new AllEvents("Events");
            allE.show();
        });
        Label eventName=new Label("Event Name: "+e.getNomE());
        Label eventDate=new Label("Date : "+e.getDateD());
        Label eventTime=new Label("Time "+e.getHeure()+"h");
        Button details=new Button("view Details");
        details.addActionListener(l->{
            EventDetails ed=new EventDetails("Details",e);
            ed.show();
        });
        c.addAll(iv,eventName,eventDate,eventTime,details);
        bigContainer.addAll(c);
        }
        
       this.add(bigContainer);
    }
}
