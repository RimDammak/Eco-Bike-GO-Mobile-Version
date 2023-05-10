/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.UIBuilder;
import houssem.entities.Station;
import houssem.services.ServiceStation;
import java.util.ArrayList;


/**
 *
 * @author houss
 */
public class FormStation extends Form{
    public FormStation(){
        getAllStyles().setBgColor(0xebfff8);
        UIBuilder.registerCustomComponent("ImageViewer", ImageViewer.class);
        setTitle("Nos de Stations");
        //UIBuilder uib1 =new UIBuilder();
        //setLayout(new FlowLayout(CENTER, CENTER));
        setLayout(BoxLayout.y());
//        add(new Label("ma liste de station"));
//        SpanLabel sp = new SpanLabel();
//        sp.setText(ServiceStation.getInstance().getAllStations().toString());
//        add(sp);

        Button btnValider = new Button("Ajouter une station");
        btnValider.setUIID("LoginButton");
        btnValider.addActionListener(e -> {
            new FormAjoutStation(this).show();
        });
        
        addAll(btnValider);
        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        ArrayList<Station> tasks = ServiceStation.getInstance().getAllStations();
        for (Station t : tasks) {
            addElement(t);
            add(new Label(" "));
        }  
    }
    public void addElement(Station task) {
        
        MultiButton sp = new MultiButton(task.getNomStation());
        sp.setTextLine2("Ville : "+task.getLocalisationStation());
        sp.setTextLine3("Nombre de vélos disponible : "+task.getVeloStation());
        sp.addActionListener(e->{
            new FormDetailsStation(this,task.getIdStation()).show();
        });
        //setUIID("CompletedTasks");
        add(sp);
    } 
}
