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
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.List;
import com.codename1.ui.Slider;
import com.codename1.ui.SwipeableContainer;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
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
import com.esprit.entities.Examen;
import com.esprit.services.ExamenService;
import java.util.ArrayList;

/**
 *
 * @author mahdi
 */
public class displayExams {

    public static int idu = 3;
    public static int idl = 0;
    Form hi = new Form("", new BoxLayout(BoxLayout.Y_AXIS));
//h.add(lb);

    public displayExams(Resources res) {

        hi.getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Examens", "Title")
                )
        );

        installSidemenu(res);
        Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        TextField search = new TextField();
        String s = search.getText();
        Button rech = new Button("Trier");
        rech.addActionListener(ee -> {
            displayexamsAfterR de = new displayexamsAfterR(res, s);
            de.hi.show();

        });
       // C1.add(search);
        C1.add(rech);
        hi.add(C1);

        ExamenService serviceTask = new ExamenService();

        ArrayList<Examen> list = serviceTask.getListExamen();
        for (Examen l : list) {
            hi.add(createRankWidget(l, l.getIdE(), l.getTitreE(), l.getNiveau(), l.getPrixE(), l.getSupport(), res));
            hi.showBack();
        }

    }

    public SwipeableContainer createRankWidget(Examen l, int id, String titre, String niveau, float prix, String support, Resources res) {
        MultiButton button = new MultiButton(titre);
        Button reserver = new Button("s'inscrir");
        //add(reserver);

        button.setHeight(100);

        //button.setIcon(Image);
        button.setTextLine1(titre);
        button.setTextLine2(niveau);
        button.setTextLine3("" + prix);

        //button.setTextLine4(Contenu);
        button.addActionListener(e -> {

            displayoneExam a = new displayoneExam(l, res);

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
            displayCours dc= new displayCours(res);
        });
         hi.getToolbar().addCommandToSideMenu("Packages", null, e -> {
            displayPackages dc= new displayPackages(res);
        });
           hi.getToolbar().addCommandToSideMenu("Demande", null, e -> {
              displayDemande dc= new displayDemande(res);
        });
             hi.getToolbar().addCommandToSideMenu("Reclamtion", null, e -> {
              displayReclamation dc= new displayReclamation(res);
        });
          hi.getToolbar().addCommandToSideMenu("Panier", null, e -> {
              displayPanier dc= new displayPanier(res);
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
