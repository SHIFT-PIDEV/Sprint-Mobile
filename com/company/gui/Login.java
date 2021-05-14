/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.gui;

import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.company.entities.Client;
import com.mycompany.services.ClientS;
import java.io.IOException;
import java.util.ArrayList;
/**
 *
 * @author asus
 */
public class Login extends Form {
    private Button sh;
    private Label upgradi;
  private TextField email;
  private TextField mdp;
  private final Button forgetMdp;
  private final Button connexion;
  private final Button createAccount;
  public static Client client;
  public ArrayList<Client> list;
  public Login(String title){
      super(title);
      this.setLayout(new FlowLayout(CENTER, CENTER));
        Container c=new Container(BoxLayout.y());
        Container c1=new Container(BoxLayout.y());
        c1.setLayout(new FlowLayout(TOP,CENTER));
        this.upgradi=new Label("Welcom in UPGRADI");
        this.email=new TextField("skander.hamdi@esprit.tn", "Email");
        this.mdp=new TextField("s200998", "Password", 1,TextField.PASSWORD);
        this.forgetMdp=new Button("forgot password");
        this.connexion=new Button("Connexion");
        this.createAccount=new Button("Create Account");
         EncodedImage ec=null;
        try {
           ec = EncodedImage.create("/heart2.png");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        this.sh=new Button(ec);
        this.connexion.addActionListener(l->{
            ClientS cs=ClientS.getInstance();
            list=cs.getUser(this.email.getText(), this.mdp.getText());
            if(!list.isEmpty()){
                client=list.get(0);
            }
            System.out.println(client);
            if(client==null){
                Dialog.show("error", "bad credantiels", "OK", null);
            }
            else {
                HomeForm hf=new HomeForm("Home");
                hf.show();
                
            }
        });
         c.addAll(this.email,this.mdp/*,this.forgetMdp*/,this.connexion/*,this.createAccount*/);
        c1.addAll(this.upgradi);
        this.addAll(c1,c);
  }
  
}
