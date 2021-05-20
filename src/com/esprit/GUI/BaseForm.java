/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.esprit.GUI;

import com.codename1.admob.AdMobManager;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.execute;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

/**
 * Utility methods common to forms e.g. for binding the side menu
 *
 * @author Shai Almog
 */
public class BaseForm extends Form {
    private AdMobManager admob;
     int n = 0;
    public void installSidemenu(Resources res) {
        
        //   String admobId = "ca-app-pub-3940256099942544~3347511713";
        String admobId = "ca-app-pub-3940256099942544/1033173712";
        if (Display.getInstance().getPlatformName().equals("ios")) {
            admobId = "ca-app-pub-3940256099942544/1033173712";
        }

        // Note:  admobId is the ID of the target ads you want to display
        // not your admob App ID.
        // See instructions for Android and iOS below for specifying admob app ID
        // via build hints.
        admob = new AdMobManager(admobId);
        
        
        
        Image selection = res.getImage("selection-in-sidemenu.png");
        
        Image inboxImage = null;
        if(isCurrentInbox()) inboxImage = selection;

        Image trendingImage = null;
        if(isCurrentTrending()) trendingImage = selection;
        
        Image calendarImage = null;
        if(isCurrentCalendar()) calendarImage = selection;
        
        Image statsImage = null;
        if(isCurrentStats()) statsImage = selection;
        
        Button inboxButton = new Button("Acceuil", inboxImage);
        inboxButton.setUIID("SideCommand");
        inboxButton.getAllStyles().setPaddingBottom(0);
        Container inbox = FlowLayout.encloseMiddle(inboxButton, 
                new Label("18", "SideCommandNumber"));
        inbox.setLeadComponent(inboxButton);
        inbox.setUIID("SideCommand");
        inboxButton.addActionListener(e -> new InboxForm().show());
        getToolbar().addComponentToSideMenu(inbox);
        
//        getToolbar().addCommandToSideMenu("Stats", statsImage, e -> new StatsForm(res).show());
        getToolbar().addCommandToSideMenu("Calendar", calendarImage, e -> new CalendarForm(res).show());
      //////////////////////////////  
        
        getToolbar().addCommandToSideMenu("Examens", null, e -> {
          if (n % 2 == 0) {
                admob.loadAndShow();
               n++;
           } else {
                n++;
                displayExams de= new displayExams(res);
               de.hi.show();
           }
        });
       getToolbar().addCommandToSideMenu("Mes Examens", null, e -> {
            // execute("http://127.0.0.1:8000/inscripexam/inscripexam/mesexamens");
            displayMyExams dc = new displayMyExams(res);
        });
        getToolbar().addCommandToSideMenu("Cours", null, e -> {
            displayCours dc= new displayCours(res);
        });
         getToolbar().addCommandToSideMenu("Packages", null, e -> {
            displayPackages dc= new displayPackages(res);
        });
           getToolbar().addCommandToSideMenu("Demande", null, e -> {
              displayDemande dc= new displayDemande(res);
        });
             getToolbar().addCommandToSideMenu("Reclamtion", null, e -> {
              displayReclamation dc= new displayReclamation(res);
        });
          getToolbar().addCommandToSideMenu("Panier", null, e -> {
              displayPanier dc= new displayPanier(res);
        });
        
     
        // spacer
        getToolbar().addComponentToSideMenu(new Label(" ", "SideCommand"));
        getToolbar().addComponentToSideMenu(new Label(res.getImage("profile_image.png"), "Container"));
        getToolbar().addComponentToSideMenu(new Label("Detra Mcmunn", "SideCommandNoPad"));
        getToolbar().addComponentToSideMenu(new Label("Long Beach, CA", "SideCommandSmall"));
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
