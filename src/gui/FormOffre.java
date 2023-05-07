/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import com.codename1.components.MultiButton;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import houssem.entities.Offre;
import houssem.services.ServiceOffre;
import java.util.ArrayList;

/**
 *
 * @author houss
 */
public final class FormOffre extends Form{
    public FormOffre(Form previous) {
        setTitle("Liste Offres");
        setLayout(BoxLayout.y());

         
        ArrayList<Offre> tasks = ServiceOffre.getInstance().getAllTasks();
        for (Offre t : tasks) {
            addElement(t);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
    public void addElement(Offre task) {
        
        MultiButton sp = new MultiButton(task.getNom()+" ( "+task.getDescription()+" )");
        sp.setTextLine2("Date : "+task.getDate_val());
        sp.setTextLine3("Categorie : "+task.getCat()+" | Prix : "+task.getPrix());
        sp.setTextLine4("Places : "+task.getNb_disp()+" | Ville : "+task.getLocation());
        //setUIID("CompletedTasks");
        add(sp);
    } 
}
