/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.GUI;

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.components.ToastBar;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.execute;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
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
import com.esprit.entities.Demande;
import com.esprit.entities.Examen;
import com.esprit.entities.InscripExam;
import com.esprit.entities.RatingR;
import com.esprit.entities.Reclamation;
import com.esprit.services.ExamenService;
import com.esprit.services.RatingService;
import com.esprit.services.DemandeService;
import java.io.IOException;
import java.util.ArrayList;
import com.codename1.components.ToastBar.Status;
import static com.codename1.ui.Component.BOTTOM;


/**
 *
 * @author mahdi
 */
public class AddDemande {

    Form hi = new Form("", new BoxLayout(BoxLayout.Y_AXIS));
    EncodedImage enc;
    Image imgs;
    ImageViewer imgv;
    private Resources theme;

    public AddDemande(Resources res) {

        hi.getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Ajout Demande", "demandeP")
                )
        );

        hi.getToolbar().addCommandToLeftBar("Back ", res.getImage(""), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                displayDemande a = new displayDemande(res);
                a.hi.showBack();

            }
        });

        // Form f1 = new Form("",new BoxLayout(BoxLayout.Y_AXIS));
        Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        TextField objet = new TextField("", "objet", 201, TextField.ANY);
        objet.setHint("L'objet de votre Demande ");
        TextField description = new TextField("", "description", 201, TextField.ANY);
        description.setHint("La description de votre Demande ");
       
        


        C1.add(objet);
        C1.add(description);
 Button cv = new Button("CV");
        Label lblimage = new Label();
       cv.addActionListener(e -> {
try{
    String path= Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
    if(path!= null){
            Image img= Image.createImage(path);
            lblimage.setIcon(img);
            hi.revalidate();
    }
    else{
       
    }
}
catch(Exception ex){
    ex.printStackTrace();
}
    
        });
        Button dem = new Button("Ajouter");
        
        dem.addActionListener(e -> {
try{
    if(objet.getText().equals("")|| description.getText().equals("")|| cv.getText().equals(" ")){
            Dialog.show("Verification Notif", "Veuillez verifier vos paramétres !", "ok", null);
    }
    else{
        DemandeService sU = new DemandeService();
            Demande u = new Demande(objet.getText(), description.getText(),cv.getText() );
            sU.addem(u);
            objet.clear();
            description.clear();

            ToastBar.Status status = ToastBar.getInstance().createStatus();
             status.setMessage("Ajout de la demande en progression");
             status.setShowProgressIndicator(true);
             status.show();
            Dialog.show("demande Notif", "Demande ajouter avec succees !", "ok", null);
            Message msg = new Message("Demande ajouté avec succes");
                Display.getInstance().sendMessage(new String[] {"hajouwa1998@gmail.com"}, "Demande", msg);
             status.setMessage("Demande ajouter ");
             status.setShowProgressIndicator(false);
             status.setExpires(3000);
             status.show();
    }
}
catch(Exception ex){
    ex.printStackTrace();
}
    
        });
        hi.add(cv);
        hi.add(lblimage);
        hi.add(C1);

        hi.add(dem);

        hi.show();
        hi.refreshTheme();
    }

    public void installSidemenu(Resources res, Demande l) {
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
        hi.getToolbar().addCommandToSideMenu("Reclamation", null, e -> {
            displayReclamation a = new displayReclamation(res);
            a.hi.show();
        });
        hi.getToolbar().addCommandToSideMenu("Demande", null, e -> {
            displayDemande a = new displayDemande(res);
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
