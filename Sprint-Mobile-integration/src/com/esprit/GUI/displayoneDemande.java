/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.GUI;

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.execute;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.SwipeableContainer;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.esprit.entities.Cour;
import com.esprit.entities.Demande;
import com.esprit.services.CourService;
import com.esprit.services.DemandeService;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author mahdi
 */
public class displayoneDemande {
             Form hi = new Form("", new BoxLayout(BoxLayout.Y_AXIS));
    EncodedImage enc;
    Image imgs;
    ImageViewer imgv;
 private Resources theme;
 
  public displayoneDemande(Demande l,Resources res) {
 
     String url="http://127.0.0.1:8000/images/"+l.getCv();

        
        hi.getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Demandes", "Title")
                        
                )
        );
        hi.getToolbar().addCommandToLeftBar("Back ", res.getImage(""),new ActionListener() {
        
       @Override
         public void actionPerformed(ActionEvent evt) {
               displayDemande a = new displayDemande(res);
                a.hi.showBack();
         }
     });
    
  TextArea popupBody = new TextArea("Objet:"+ l.getObjet() + "\n\n"+ "Description:"+ l.getDescription()+ "\n\n" +"CV:"+ l.getCv()+  "\n\n",8, 12);
        popupBody.setEditable(false);
         try {
            enc = EncodedImage.create("/load.png");
                    } catch (IOException ex) {
     
        }
        // Form f1 = new Form("",new BoxLayout(BoxLayout.Y_AXIS));
                                                                
      
   imgs = URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE_TO_FILL);
  
   imgv = new ImageViewer(imgs);
  
        
         
         Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        TextField objet = new TextField("", "objet", 201, TextField.ANY);
        objet.setHint("L'objet de votre Demande ");
        TextField description = new TextField("", "description", 201, TextField.ANY);
        description.setHint("La description de votre Demande ");
        
        C1.add(popupBody);
       // C1.add(nom);
       // C1.add(prenom);
       // C1.add(email);
       //  Button reserver = new Button("S'inscrire");
       //   reserver.addActionListener(e -> {
              // System.out.println("dfsgdhfjgkhg");
           //   ExamenService sU= new ExamenService();
            //  InscripExam u= new InscripExam(l.getIdE(), nom.getText(), prenom.getText(), email.getText());
            //  sU.addinscri(u);
             // nom.clear();
             // prenom.clear();
              //email.clear();
              //Dialog.show("Examens Notif", "Inscription éffectué avec succée !", "ok", null);
       // });
        Container C2= new Container(new BoxLayout(BoxLayout.Y_AXIS));
        C2.add(imgv);
       hi.add(C2);
      hi.add(C1);
     // hi.add(reserver);
       
       hi.show();   
    }
     public void installSidemenu(Resources res) {
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
    
       hi.getToolbar().addCommandToSideMenu("Examens", null, e -> {
            displayExams a = new displayExams(res);
            a.hi.show();
        });
        hi.getToolbar().addCommandToSideMenu("Mes Examens", null, e -> {
            execute("http://127.0.0.1:8000/inscripexam/inscripexam/mesexamens");

        });
         
        hi.getToolbar().addCommandToSideMenu("Cours", null, e -> {
            displayCours dc= new displayCours(res);
        });
         hi.getToolbar().addCommandToSideMenu("Packages", null, e -> {
            displayPackages dc= new displayPackages(res);
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
