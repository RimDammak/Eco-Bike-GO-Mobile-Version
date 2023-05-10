/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import houssem.entities.Station;
import houssem.services.ServiceStation;
import java.util.ArrayList;

/**
 *
 * @author houss
 */
public class FormDetailsStation extends Form{
    
    public FormDetailsStation(Form previous, int s) {
        setTitle("detail de station");
        setLayout(BoxLayout.y());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        ArrayList<Station> tasks = ServiceStation.getInstance().getAllStations();
         if(tasks != null && !tasks.isEmpty()){
        for (Station t : tasks) {
            if(t.getIdStation() == s){
            addElement(t);
            Button bb=new Button("Réserver des vélos dans "+t.getNomStation());
            bb.setUIID("LoginButton");
            bb.addActionListener(e->{
                new FormAjoutReservation(this,s).show();
            });
                add(bb);
            }
            
        }
    }
    }
    public final void addElement(Station task) { 
        add(new Label(" "));
        add(new Label("Bienvenue chez Station "+task.getNomStation()));
        add(new Label("Ville : "+task.getLocalisationStation()));
        add(new Label("Vélos disponible : "+task.getVeloStation()));
        add(new Label(" "));
    } 
    
}
