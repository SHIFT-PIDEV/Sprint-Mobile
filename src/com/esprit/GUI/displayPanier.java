/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.GUI;

import com.codename1.components.MultiButton;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
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
import com.esprit.entities.Panier;
import com.esprit.services.PanierService;
import java.util.ArrayList;

/**
 *
 * @author Fedy
 */
public class displayPanier {

    public static int idu = 3;
    public static int idl = 0;
    Panier p;
    Form hi = new Form("", new BoxLayout(BoxLayout.Y_AXIS));

    public int somme = 0;

    public displayPanier(Resources res) {

        hi.getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Panier", "Title")
                )
        );
        installSidemenu(res);
        TextField search = new TextField();

        PanierService serviceTask = new PanierService();

        ArrayList<Panier> list = serviceTask.getListPanier();
        for (Panier l : list) {

            somme += l.getPrix();

            hi.add(createRankWidget(l, l.getId(), l.getNom(), l.getPrix(), res));
            Button delete = new Button("X");
            delete.addActionListener(e -> {

                PanierService ps = new PanierService();
                ps.delete(l);
                Dialog.show("Suppression article", "Suppression éffectué avec succée !", "ok", null);
                displayPanier de = new displayPanier(res);
                de.hi.show();
            });
            hi.add(delete);
            hi.showBack();

        }
        Label ta = new Label("Prix Total: " + String.valueOf(somme) + " Dt");
        hi.add(ta);

    }

    public SwipeableContainer createRankWidget(Panier l, int id, String nom, int prix, Resources res) {
        MultiButton button = new MultiButton(nom);

        button.setTextLine1(nom);

        button.setTextLine3("" + prix + " DT");

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

    private void dialog(Panier e, Resources res) {

        Dialog d = new Dialog(e.getNom());

        TextArea popupBody = new TextArea(e.getNom() + " " + e.getPrix() + "\n", 8, 12);
        popupBody.setUIID("Label");
        popupBody.setEditable(false);
        Button b = new Button("test");
        d.setLayout(new BorderLayout());

        d.addComponent(BorderLayout.CENTER, popupBody);

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

        hi.getToolbar().addCommandToSideMenu("Panier", null, e -> {
            displayPanier a = new displayPanier(res);
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
