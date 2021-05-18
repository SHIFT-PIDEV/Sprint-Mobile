/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.GUI;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.execute;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.esprit.entities.Examen;
import com.esprit.entities.InscripExam;
import com.esprit.entities.RatingR;
import com.esprit.services.ExamenService;
import com.esprit.services.RatingService;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author mahdi
 */
public class AddComment {
     Form hi = new Form("", new BoxLayout(BoxLayout.Y_AXIS));
    EncodedImage enc;
    Image imgs;
    ImageViewer imgv;
 private Resources theme;

  
    public AddComment(Examen l,Resources res)
    {
             
 
//    String img=l.getImage_name();
  //  String url="http://127.0.0.1:8000/frontoffice/assets/img/Exam.jpg";
   
        hi.getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("commenter", "Title")
                        
                )
        );
         //installSidemenu(res);
        
        hi.getToolbar().addCommandToLeftBar("Back ", res.getImage(""),new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
               displayExams a = new displayExams(res);
                a.hi.showBack();
         }
     });
       
      
        // Form f1 = new Form("",new BoxLayout(BoxLayout.Y_AXIS));
                                                                
      
  
         
        Container C1= new Container( new BoxLayout(BoxLayout.Y_AXIS));
        TextField com = new TextField("", "commentaire", 201, TextField.ANY);
        com.setHint("votre commentaire ");
        
        
       
        C1.add(com);
        
         Button comm = new Button("commenter");
          comm.addActionListener(e -> {
              // System.out.println("dfsgdhfjgkhg");
              RatingService sU= new RatingService();
              RatingR u= new RatingR( l.getIdE(),com.getText());
              sU.addcom(u);
              com.clear();
           
              Dialog.show("comment Notif", "commentaire ajouter avec succees !", "ok", null);
        });
       
       
      
      hi.add(C1);
      
      hi.add(comm);
       
       hi.show();   
    }
    
    
     public void installSidemenu(Resources res, Examen l) {
        Image selection = res.getImage("selection-in-sidemenu.png");
        
        Image inboxImage = null;
        if(isCurrentInbox()) inboxImage = selection;

        Image trendingImage = null;
        if(isCurrentTrending()) trendingImage = selection;
        
        Image calendarImage = null;
        if(isCurrentCalendar()) calendarImage = selection;
        
        Image statsImage = null;
        if(isCurrentStats()) statsImage = selection;
        
        Button inboxButton = new Button("Inbox", inboxImage);
        inboxButton.setUIID("SideCommand");
        inboxButton.getAllStyles().setPaddingBottom(0);
        Container inbox = FlowLayout.encloseMiddle(inboxButton, 
                new Label("18", "SideCommandNumber"));
        inbox.setLeadComponent(inboxButton);
        inbox.setUIID("SideCommand");
        inboxButton.addActionListener(e -> new InboxForm().show());
        hi.getToolbar().addComponentToSideMenu(inbox);
    
       hi. getToolbar().addCommandToSideMenu("Examens", null, e -> {
           displayExams a = new displayExams(res);
           a.hi.show();
        });
        hi. getToolbar().addCommandToSideMenu("Mes Examens", null, e -> {
          execute("http://127.0.0.1:8000/inscripexam/inscripexam/mesexamens");
          
        });

    
    }
     
      protected boolean isCurrentInbox() {
        return false;
    }
    
    protected boolean isCurrentTrending() {
        return false;
    }

    protected boolean isCurrentCalendar() {
        return false;
    }

    protected boolean isCurrentStats() {
        return false;
    }
}
