/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.GUI;

import com.codename1.components.MultiButton;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.execute;
import com.codename1.ui.Component;
import com.codename1.ui.ComponentGroup;
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
import com.esprit.entities.Examen;
import com.esprit.entities.InscripExam;
import com.esprit.services.ExamenService;
import java.util.ArrayList;

/**
 *
 * @author mahdi
 */
public class displayMyExams {

    public static int idu = 3;
    public static int idl = 0;
    Form hi = new Form("", new BoxLayout(BoxLayout.Y_AXIS));
//h.add(lb);

    public displayMyExams(Resources res) {

        hi.getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Mes Examens", "Title")
                )
        );

        installSidemenu(res);
        Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        TextField search = new TextField();
        String s = search.getText();
        Button rech = new Button("Ovrir dans Chrome");
        rech.addActionListener(ee -> {
            execute("http://127.0.0.1:8000/inscripexam/inscripexam/mesexamens");
        });
        // C1.add(search);
        C1.add(rech);
        //Website  WORKING
                BrowserComponent browser = new BrowserComponent();
              
                browser.setURL("http://127.0.0.1:8000/inscripexam/inscripexam/mesexamens");
                                       
               
                 //WEBSITE WORKING 
               C2.setLayout(new BorderLayout());
                C2.add(BorderLayout.CENTER, browser);
                 hi.add(C1);
                 hi.add(C2);

        hi.show();

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

        Button inboxButton = new Button("Acceuil", inboxImage);
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
            // execute("http://127.0.0.1:8000/inscripexam/inscripexam/mesexamens");
            displayMyExams dc = new displayMyExams(res);
        });
        hi.getToolbar().addCommandToSideMenu("Cours", null, e -> {
            displayCours dc = new displayCours(res);
        });
        hi.getToolbar().addCommandToSideMenu("Packages", null, e -> {
            displayPackages dc = new displayPackages(res);
        });
        hi.getToolbar().addCommandToSideMenu("Demande", null, e -> {
            displayDemande dc = new displayDemande(res);
        });
        hi.getToolbar().addCommandToSideMenu("Reclamtion", null, e -> {
            displayReclamation dc = new displayReclamation(res);
        });
        hi.getToolbar().addCommandToSideMenu("Panier", null, e -> {
            displayPanier dc = new displayPanier(res);
        });
// 
//       hi. getToolbar().addCommandToSideMenu("My Commande", null, e -> {
//         displayMycom a = new displayMycom(res);
//            a.hi.show();
//        });
//        
//       hi. getToolbar().addCommandToSideMenu("My Profile", null, e -> {
//         displayProfile a = new displayProfile(res);
//            a.hi.show();
//        });
//        
//
//            
//       hi. getToolbar().addCommandToSideMenu("Publication", statsImage, e -> {
//      
//                new PublicationForm(res).show();
//     
//        });
//         hi.   getToolbar().addCommandToSideMenu("Service", null, e -> {
//         displayService a = new displayService(res);
//           a.hi.show();
//        });
//        hi.     getToolbar().addCommandToSideMenu("Attestation", null, e -> {
//            addAttestation m = new addAttestation(res);
//           m.hi.show();
//             });
//             hi. getToolbar().addCommandToSideMenu("Reclamation", null, e -> {
//            addReclamation x = new addReclamation(res);
//           x.hi.show();
//             });
//      hi.  getToolbar().addCommandToSideMenu("Blog", calendarImage, e -> new BlogForm(res).show());
//         
//    hi.    getToolbar().addCommandToSideMenu("Event", null, e -> {
//          displayEvent a = new displayEvent(res);
//          a.hi.show();
//        });
//       hi. getToolbar().addCommandToSideMenu("Offre", null, e -> {
//          displayOffre a = new displayOffre(res);
//          a.hi.show();
//        });
//          hi.   getToolbar().addCommandToSideMenu("LogOut", null, e -> {
//                new SignInForm().showBack();
//        });

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
