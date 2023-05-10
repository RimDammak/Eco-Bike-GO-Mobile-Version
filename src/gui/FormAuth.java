/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.UIBuilder;
import static ecobikego.MyApplication.theme;

/**
 *
 * @author houss
 */
public class FormAuth extends Form{
    public FormAuth(Form previous){
        setUIID("Formauth");
        Toolbar tb = getToolbar();
        tb.setUIID("aaa");
        UIBuilder.registerCustomComponent("InfiniteProgress", InfiniteProgress.class);
        setLayout(new FlowLayout(CENTER, CENTER));
        UIBuilder uib =new UIBuilder();
        Container con = uib.createContainer(theme, "GUI 1");
        add(con) ;
        
        Button blogin =(Button) uib.findByName("login", con);
        Button bb =(Button) uib.findByName("retour", con);
        Button bguest=(Button) uib.findByName("Button", con);
        blogin.addActionListener((ActionEvent e)->{
           //new FormStation().show();
        TextField tfEmail = (TextField) uib.findByName("usename", con);
        TextField tfPwd = (TextField) uib.findByName("password", con);   
           
        String url = "http://localhost/pimobile/cnx.php?email="
                    +tfEmail.getText()+"&password="+tfPwd.getText();
            
            ConnectionRequest cnx = new ConnectionRequest(url, false);
            cnx.addResponseListener( evt -> {
                if (cnx.getResponseCode() == 200) {
                    String response = new String(cnx.getResponseData());        
                    if (response.equals("marhbee bik si houssem")) {
                        new FormAcceuil(this).show();
                    }else{
                        Dialog.show("Warning", "Veuillez vérifier vos données !: ", "OK", null);
                    }   
                }else{
                    Dialog.show("Erreur", "Problem de connexion !", "OK", null);
                }
            });    
            NetworkManager.getInstance().addToQueue(cnx);
        });
        
        bb.addActionListener(e->{
           previous.show();
        });
        bguest.addActionListener(e->{
           new FormVelo(this).show();
        });
    }
}
