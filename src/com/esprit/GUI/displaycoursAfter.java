/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.GUI;

import com.codename1.components.MultiButton;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.execute;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.SwipeableContainer;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.esprit.entities.Cour;
import com.esprit.entities.Examen;
import com.esprit.services.CourService;
import com.esprit.services.ExamenService;
import java.util.ArrayList;

/**
 *
 * @author mahdi
 */
public class displaycoursAfter {
        Form hi = new Form("", new BoxLayout(BoxLayout.Y_AXIS));
//h.add(lb);

    public displaycoursAfter(Resources res) {

         hi.getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Cours", "Title")
                        
                )
        );
        installSidemenu(res);
        TextField search=new TextField();
        Button rech = new Button("Trier");
        rech.addActionListener(ee -> {
            displaycoursAfter de = new displaycoursAfter(res);
            de.hi.show();

        });
       // C1.add(search);
        hi.add(rech);
       
        CourService serviceTask = new   CourService();
      

     ArrayList<Cour> list = serviceTask.getListCourTrier();
          for (Cour l:list)
      {
        // String img=l.getImage_name();
  //Image Image = res.getImage("/"+img);
//        Image.scaledSmallerRatio(20, 20);
       // ImageViewer imgv = new ImageViewer(Image);
      //  System.out.println(Image);
        //  addItem(l);
     
    
hi.add(createRankWidget(l,l.getId(),l.getNom_cour(),l.getNiveau(),l.getPrix(),l.getFormateur(),res));
 hi.showBack();
    }
    
  }
  
     public SwipeableContainer createRankWidget(Cour l,int id,String titre, String niveau,float prix,String formateur,Resources res) {
            MultiButton button = new MultiButton(titre);  
        //add(reserver);
    

      
        button.setHeight(100);

        //button.setIcon(Image);
        button.setTextLine1(titre);
        button.setTextLine2(niveau);
        button.setTextLine3("" + prix);
        
             
         //button.setTextLine4(Contenu);
         button.addActionListener(e->{
            
              displayoneCour a = new displayoneCour(l,res);
         
             //dialog(l,res);
         });
          
//        reserver.addActionListener(e -> {
//         
//           // System.out.println(idl);
//            Commande ser = new Commande();
//            ServiceCommande se= new ServiceCommande();
//            se.Commender(id);
//            Dialog.show("Sign In", "your book "+name+"has been ordered", "ok", null);
//        });
       
    return new SwipeableContainer(FlowLayout.encloseCenterMiddle(createStarRankSlider()), 
            button);
}

    private Slider createStarRankSlider() {
        Slider starRank = new Slider();

        return starRank;
    }

    private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }

    private void dialog(Examen e, Resources res) {

        Dialog d = new Dialog(e.getTitreE());
        // String img=e.getImage_name();
        TextArea popupBody = new TextArea(e.getTitreE() + "\n" + e.getNiveau() + "\n" + e.getPrixE() + "\n", 8, 12);

        popupBody.setUIID("Label");
        popupBody.setEditable(false);
        Button b = new Button("test");
        d.setLayout(new BorderLayout());

        d.addComponent(BorderLayout.CENTER, popupBody);
        //  d.add(BorderLayout.SOUTH,imgv);

        d.setTransitionInAnimator(CommonTransitions.createEmpty());
        d.setTransitionOutAnimator(CommonTransitions.createFade(300));
        Rectangle rec = new Rectangle();
        rec.setX(700);
        rec.setY(1000);
        d.showPopupDialog(rec);
    }

    private void notif() {
        LocalNotification n = new LocalNotification();
        n.setId("demo-notification");
        n.setAlertBody("your book has been added to your Order list");
        n.setAlertTitle("Order added!");
        n.setAlertSound("/notification_sound_bells.mp3"); //file name must begin with notification_sound

        Display.getInstance().scheduleLocalNotification(
                n,
                System.currentTimeMillis() + 10 * 1000, // fire date/time
                LocalNotification.REPEAT_MINUTE // Whether to repeat and what frequency
        );
    }

    public void installSidemenu(Resources res) {
        Image selection = res.getImage("selection-in-sidemenu.png");

        Image inboxImage = null;
        if (isCurrentInbox()) {
            inboxImage = selection;
        }

        Image trendingImage = null;
        if (isCurrentTrending()) {
            trendingImage = selection;
        }

        Image calendarImage = null;
        if (isCurrentCalendar()) {
            calendarImage = selection;
        }

        Image statsImage = null;
        if (isCurrentStats()) {
            statsImage = selection;
        }

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
