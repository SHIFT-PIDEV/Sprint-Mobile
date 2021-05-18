/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.GUI;

import com.codename1.components.ImageViewer;
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
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BorderLayout;
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
public class displayoneExam {

    Form hi = new Form("", new BoxLayout(BoxLayout.Y_AXIS));
    EncodedImage enc;
    Image imgs;
    ImageViewer imgv;
    private Resources theme;

    public displayoneExam(Examen l, Resources res) {

//    String img=l.getImage_name();
        String url = "http://127.0.0.1:8000/frontoffice/assets/img/Exam.jpg";

        hi.getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label(l.getTitreE(), "Title")
                )
        );
        //installSidemenu(res);

        hi.getToolbar().addCommandToLeftBar("Back ", res.getImage(""), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                displayExams a = new displayExams(res);
                a.hi.showBack();
            }
        });

        TextArea popupBody = new TextArea("Titre:" + l.getTitreE() + "\n\n" + "prix:" + l.getPrixE() + "dinars" + "\n\n" + "Niveau:" + l.getNiveau() + "\n\n", 8, 12);
        popupBody.setEditable(false);
        try {
            enc = EncodedImage.create("/load.png");
        } catch (IOException ex) {

        }
        // Form f1 = new Form("",new BoxLayout(BoxLayout.Y_AXIS));

        imgs = URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE_TO_FILL);

        imgv = new ImageViewer(imgs);

        Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        TextField nom = new TextField("", "nom", 201, TextField.ANY);
        nom.setHint("nom");

        TextField prenom = new TextField("", "prenom", 201, TextField.ANY);
        prenom.setHint("prenom");

        TextField email = new TextField("", "email", 201, TextField.ANY);
        email.setHint("email");

        C1.add(popupBody);
        C1.add(nom);
        C1.add(prenom);
        C1.add(email);
        Button reserver = new Button("S'inscrire");
        reserver.addActionListener(e -> {
            // System.out.println("dfsgdhfjgkhg");
            ExamenService sU = new ExamenService();
            InscripExam u = new InscripExam(l.getIdE(), nom.getText(), prenom.getText(), email.getText());
            sU.addinscri(u,l);
            nom.clear();
            prenom.clear();
            email.clear();
            Dialog.show("Examens Notif", "Inscription éffectué avec succée !", "ok", null);
        });
        Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        C2.add(imgv);

        Container C3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        RatingService serviceTask = new RatingService();
        String rate ="";
        ArrayList<RatingR> listR = serviceTask.getListrating();
        for (RatingR r : listR) {
            if(r.getIdExam()==l.getIdE())
            {
                if(r.getRate()==0)
                {
                    rate=r.getRate()+"étoiles";
                }
                if(r.getRate()==1)
                {
                    rate=r.getRate()+"étoiles";
                }
                if(r.getRate()==2)
                {
                    rate=r.getRate()+"étoiles";
                }
                if(r.getRate()==3)
                {
                    rate=r.getRate()+"étoiles";
                }
                if(r.getRate()==4)
                {
                    rate=r.getRate()+"étoiles";
                }
              TextArea popupBodyR = new TextArea("commentaire: " + r.getCommentaire() + "\n\n" + "rate : " +rate, 8, 12);
        popupBody.setEditable(false);  
        C3.add(popupBodyR);
            }
        }
Button commenter = new Button("ajouter commentaire");
        commenter.addActionListener(e -> {
            // System.out.println("dfsgdhfjgkhg");
           AddComment ac= new AddComment(l, res);
           ac.hi.show();
            
        });
        hi.add(C2);
        hi.add(C1);
        hi.add(reserver);
        hi.add(C3);
        hi.add(commenter);
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
