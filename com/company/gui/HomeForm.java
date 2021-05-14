/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.gui;

import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;

/**
 *
 * @author asus
 */
class HomeForm extends Form {
    private Label nomPrenom;

    public HomeForm(String title) {
        super(title);
        this.setLayout(new FlowLayout(CENTER, CENTER));
        nomPrenom=new Label(Login.client.getNom()+" "+Login.client.getPrenom());
        this.add(nomPrenom);
        this.getToolbar().addCommandToLeftBar("Logout", null, t->{
            Login lf=new Login("login");
            Login.client=null;
                lf.showBack();
                
        });
        this.getToolbar().addCommandToRightSideMenu("Profile",null,t->{
            
        });
        this.getToolbar().addCommandToRightSideMenu("Events",null,t->{
            AllEvents ae=new AllEvents("Events");
            ae.show();
            
        });
    }
}
