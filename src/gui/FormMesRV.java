/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import houssem.entities.ReservationVelo;
import houssem.services.ServiceReservationVelo;
import java.util.ArrayList;

/**
 *
 * @author houss
 */
public final class FormMesRV extends Form{
    public FormMesRV(Form previeus){
        setTitle("Mes réservations de vélos");
        setLayout(BoxLayout.y());
        System.out.println("aaa");
        /*SpanLabel sp = new SpanLabel();
        sp.setText(ServiceTask.getInstance().getAllTasks().toString());
        add(sp);
         */
        ArrayList<ReservationVelo> tasks = ServiceReservationVelo.getInstance().getAllTasks();
        for (ReservationVelo t : tasks) {
            addElement(t);
        }
        

        
getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK_IOS_NEW, e -> previeus.showBack());
    }

    public void addElement(ReservationVelo task) {

        add(new Label(" "));
        add(new Label("Date de réservation : "+task.getDateDebut()));
        add(new Label("Nombre de vélos : "+task.getNbr()));
        add(new Label("Total : "+task.getPrixr()));
        add(new Label("Vélo : "+task.getIdStation()));
        add(new Label("Station : "+task.getIdVelo()));
        add(new Label(" "));
    }

   
        
        
        

        
        
        
        
    
}
