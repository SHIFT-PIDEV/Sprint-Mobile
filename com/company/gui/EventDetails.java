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
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.company.entities.Commentaire;
import com.company.entities.Event;
import com.company.entities.InscriEvent;
import com.company.entities.Like;
import com.mycompany.services.CommentaireS;
import com.mycompany.services.InscriEventS;
import com.mycompany.services.LikeS;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.sun.mail.smtp.SMTPTransport;

/**
 *
 * @author asus
 */
class EventDetails extends Form {
  public Event event; 
  public void sendEmail(String email) {
        try {
            Properties props = new Properties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtps.host", "smtp.gmail.com");
            props.put("mail.smtps.auth", "true");

            Session session = Session.getInstance(props, null);

            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("UPGRADI <monEmail@domaine.com>"));
            msg.setRecipients(Message.RecipientType.TO, email);
            msg.setSubject("[Succes] Confirmation Inscription");
            msg.setSentDate(new Date(System.currentTimeMillis()));

            String txt = "Bienvenue Mr/Mme "+Login.client.getPrenom()+" "+Login.client.getPrenom()+  
                ", votre demande d'inscription a été acceptée pour l'event "+this.event.getNomE()+"  "+
               "l'event sera le "+this.event.getDateD()+ " à "+this.event.getDuree()+" H";
            msg.setText(txt);

            SMTPTransport st = (SMTPTransport) session.getTransport("smtps");
            st.connect("smtp.gmail.com", 465, "hamdiskander5@gmail.com", "skanderhamdi200998*");

            st.sendMessage(msg, msg.getAllRecipients());
            System.out.println("server response :" + st.getLastServerResponse());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
  public EventDetails(String title,Event e) {
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
         LikeS ls1=LikeS.getInstance();
            ArrayList<Like> likeList1=new ArrayList<>();
            likeList1=ls1.getLikes(Login.client.getId(),this.event.getIdE());
            if(likeList1.isEmpty()){
                 try {
           ec = EncodedImage.create("/noLike.png");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
            }
            else{
                try {
           ec = EncodedImage.create("/like.png");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
            }
       
        String s=this.event.getLikesNumber()+"";
        Button like=new Button(s,ec);
        like.addActionListener(l->{
            LikeS ls=LikeS.getInstance();
            ArrayList<Like> likeList=new ArrayList<>();
            likeList=ls.getLikes(Login.client.getId(),this.event.getIdE());
            if(likeList.isEmpty()){
                ls.addLike(Login.client.getId(),this.event.getIdE());
           this.event.setLikesNumber(this.event.getLikesNumber()+1);
            EventDetails ed1=new EventDetails("details",this.event);
            ed1.show();
            }
            else{
                ls.deleteLike(Login.client.getId(),this.event.getIdE());
                this.event.setLikesNumber(this.event.getLikesNumber()-1);
            EventDetails ed1=new EventDetails("details",this.event);
            ed1.show();
            }
            
        });
        Label eventName=new Label("Event Name: "+this.event.getNomE());
        Label eventDate=new Label("Date : "+this.event.getDateD().substring(0, 10));
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
             this.sendEmail(Login.client.getEmail());
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
       
        CommentaireS cs=CommentaireS.getInstance();
        ArrayList<Commentaire> commList=new ArrayList<>(); 
        commList=cs.getComms(this.event.getIdE());
        for(Commentaire c:commList){
             ImageViewer clientPic=null;
        try{
             clientPic= new ImageViewer();
               EncodedImage ec1 = EncodedImage.create("/load.jpg").scaledEncoded(220,150);
                Image img = URLImage.createToStorage(ec1,"http://127.0.0.1:8000"+c.getPicClient(), 
                        "http://127.0.0.1:8000"+c.getPicClient(), URLImage.RESIZE_SCALE);
                clientPic.setImage(img);
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
             c4.addAll(clientPic,new Label(c.getNameClient()),new Label(c.getDatecomm().substring(0,10))
                     ,new Label(c.getDesc()),
                     new Label("---------------------------------------------------------")
             
                     );
        }
        
        //*****
        //C4
        TextField mail=new TextField(Login.client.getEmail());
        TextField yourComm=new TextField("","add your comment here...");
        Button add=new Button("Add");
        add.addActionListener(l->{
            if(!"".equals(yourComm.getText()))
            cs.addComm(Login.client, this.event,yourComm.getText());
            EventDetails ed=new EventDetails("details",this.event);
            ed.show();
        });
        c5.addAll(mail,yourComm,add);
        //*****
        
        
        big.addAll(c1,c2,c11,c3,new Label("---------------------------------------------------------"),c4,c5);
        this.add(big);
         this.getToolbar().addCommandToLeftBar("Back", null, t->{
            AllEvents lf=new AllEvents("Events");
                lf.showBack();
                
        });
  }
}
