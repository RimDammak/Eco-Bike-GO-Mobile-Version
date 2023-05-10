/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.UIBuilder;
import static ecobikego.MyApplication.theme;

/**
 *
 * @author houss
 */
public class FormAcceuil extends Form{
    public FormAcceuil(Form previous){
        GetUserForm hi = new GetUserForm();
        
        setUIID("Formacc");//formtaswira
        Home fee=new Home(this);
       // FormMesRV fe=new FormMesRV(this);
        FormStation f1 = new FormStation();
        UIBuilder.registerCustomComponent("ImageViewer", ImageViewer.class);       
        setTitle("Acceuil");
        setLayout(new FlowLayout(CENTER, CENTER));
        Toolbar tb = getToolbar();
        tb.addCommandToOverflowMenu("Se déconnecter",theme.getImage("130925.png") , (ActionListener) (ActionEvent evt) -> {
            previous.showBack();
        });
        //getAllStyles().setBgColor(0x75ffd3);
        ///////aziz
        tb.addMaterialCommandToSideMenu("Mon Profile", FontImage.MATERIAL_ANDROID,200, e -> {
            hi.getToolbar().addCommandToRightBar("Back", null, (ActionListener) (ActionEvent evt) -> {
            this.showBack();});
            hi.show();
            
        });   
        //Jallouli houssem eddin
        tb.addMaterialCommandToSideMenu("Nos Station", FontImage.MATERIAL_ADD_CHART,200, e -> {
            f1.show();
        }); 
        tb.addMaterialCommandToSideMenu("Mes Reservation", FontImage.MATERIAL_ADD_CHART,200, (ActionListener) (ActionEvent e) -> {
            new FormMesRV(FormAcceuil.this).show();
            //fe.getToolbar().addCommandToRightBar("Back", null, (ActionListener) (ActionEvent evt) -> {
            // this.showBack();});
        }); 
        //////Mouhamed
        tb.addMaterialCommandToSideMenu("Nos Evenement", FontImage.MATERIAL_ANDROID,200, e -> {
            fee.show();
            fee.getToolbar().addCommandToRightBar("Back", null, (ActionListener) (ActionEvent evt) -> {
            this.showBack();});
        }); 
        //////rim
        tb.addMaterialCommandToSideMenu("Mes Reclamations", FontImage.MATERIAL_ANDROID,200, e -> {
            new FormReclamation(this).show();
        }); 
        //////sahar
        tb.addMaterialCommandToSideMenu("Nos Velo", FontImage.MATERIAL_ANDROID,200, e -> {
            new FormVelo(this).show();
        }); 

        tb.addMaterialCommandToSideMenu("Deconnexion", FontImage.MATERIAL_SETTINGS, e -> {
            previous.showBack();
        });
        f1.getToolbar().addCommandToRightBar("Back", null, (ActionListener) (ActionEvent evt) -> {
            this.showBack();
        });
        //ImageViewer logo = new ImageViewer(theme.getImage("aaaaaaaa.gif"));
        //this.add(logo);
        Button myButton = new Button("Click me!"); 
        myButton.setUIID("LoginButton");
        add(myButton);
        myButton.addActionListener(e -> {
            Dialog.show("Salut", "Bienvenue Chez EcobikeGo!", "OK", null);
            f1.show();
            });
    }
}
