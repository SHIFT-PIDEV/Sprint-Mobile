/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.company.entities.Event;
import com.company.entities.InscriEvent;
import com.mycompany.services.InscriEventS;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author asus
 */
class EventDetails extends Form {
  public Event event; 
  public EventDetails(String title,Event e){
      super(title);
      this.event=e;
      
      this.setLayout(new FlowLayout(TOP,CENTER));
      Container big=new Container(BoxLayout.y());
      Container c1=new Container( BoxLayout.y());
      Container c11=new Container(BoxLayout.y());
      Container c2=new Container(BoxLayout.y());
      Container c3=new Container(BoxLayout.y());
      Container c4=new Container(BoxLayout.y());
      Container c5=new Container(BoxLayout.y());
       
       //C1
        ImageViewer iv=null;
        try{
             iv= new ImageViewer();
               EncodedImage ec = EncodedImage.create("/load.jpg");
                Image img = URLImage.createToStorage(ec,"http://127.0.0.1:8000/uploads/"+this.event.getImage(), 
                        "http://127.0.0.1:8000/uploads/"+this.event.getImage(), URLImage.RESIZE_SCALE);
                iv.setImage(img);
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        EncodedImage ec=null;
        try {
           ec = EncodedImage.create("/noLike.png");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        String s=this.event.getLikesNumber()+"";
        Button like=new Button(s,ec);
        Label eventName=new Label("Event Name: "+this.event.getNomE());
        Label eventDate=new Label("Date : "+this.event.getDateD());
        Label eventTime=new Label("Time : "+this.event.getHeure()+"h");
         Label duration=new Label("duration : "+(int)this.event.getDuree()+"h");
       Label nbInscri=new Label("registrations : "+this.event.getInscriptionsNumber());
        c1.addAll(iv,like,eventName,eventDate,eventTime,duration,nbInscri);
        //********
        //C11
        Button inscri=new Button("Register");
        inscri.addActionListener(l->{
            
            InscriEventS ies=InscriEventS.getInstance();
            InscriEvent i=new InscriEvent();
            i.setIdClient(Login.client.getId());
            i.setIdEvent(this.event.getIdE());
            
            ArrayList<InscriEvent> list= ies.getInscris(Login.client.getId(),this.event.getIdE());
            if(list.isEmpty()){
                
                 ies.sinscrire(i);
            String ss="votre inscription à l'événement <<"+this.event.getNomE()+
                ">> a été enregistrée avec succès mr "+Login.client.getPrenom()+" please click here to check your mail";
            Dialog.show("Success!", ss, "OK", null);
            }
            else {
                  String aa="vous êtes déjà inscrit à l'événement <<"+this.event.getNomE()+
                ">> mr "+Login.client.getPrenom()+" consulter votre liste des events ";
            Dialog.show("warning!", aa, "OK", null);   
            }
              
           
        });
        c11.add(inscri);
        //********
        
        //C2
        SpanLabel desc=new SpanLabel("Description : "+event.getDescE());
        c2.add(desc);
        //*********
        
        //C3
        Label comms=new Label("Comments  :\n");
        c3.add(comms);
        //*****
        //C4
        Label co=new Label("Skander : Hello mother fuckers \n \n \n");
        c4.add(co);
        //*****
        //C4
        TextField mail=new TextField(Login.client.getEmail());
        TextField yourComm=new TextField("","add your comment here...");
        Button add=new Button("Add");
        c5.addAll(mail,yourComm,add);
        //*****
        
        
        big.addAll(c1,c2,c11,c3,c4,c5);
        this.add(big);
         this.getToolbar().addCommandToLeftBar("Back", null, t->{
            AllEvents lf=new AllEvents("Events");
                lf.showBack();
                
        });
  }
}
