/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
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
        setLayout(new FlowLayout(CENTER, BOTTOM));
        UIBuilder uib =new UIBuilder();
        Container con = uib.createContainer(theme, "GUI 1");
        add(con) ;
        
        Button blogin =(Button) uib.findByName("login", con);
        Button bb =(Button) uib.findByName("retour", con);
        Button bguest=(Button) uib.findByName("Button", con);
        blogin.addActionListener(e->{
           //new FormStation().show();
           new FormAcceuil(this).show();
        });
        bb.addActionListener(e->{
           previous.show();
        });
        bguest.addActionListener(e->{
           new FormVelo(this).show();
        });
    }
}
