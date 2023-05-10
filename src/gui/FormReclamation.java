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
import houssem.entities.Reclamation;
import houssem.services.ServiceReclamation;
import java.util.ArrayList;

/**
 *
 * @author houss
 */
public class FormReclamation extends Form{
     public FormReclamation(Form previous) {
        setTitle("Liste Reclamations");
        setLayout(BoxLayout.y());
Button btnValider = new Button("Ajouter une reclamation");
        btnValider.setUIID("LoginButton");
         btnValider.addActionListener(e -> {
            new FormAjoutReclamation(this).show();
        });
        ArrayList<Reclamation> tasks = ServiceReclamation.getInstance().getAllTasks();
        for (Reclamation t : tasks) {
            addElement(t);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
    public void addElement(Reclamation task) {
        add(new Label(" "));
        add(new Label("Reclamation:"));
        add(new Label("Date: "+task.getDateRec()));
        add(new Label("Type: "+task.getEtatRec()));
        add(new Label("Description: "+task.getDescriptionRec()));
        add(new Label("Reponse: "+task.getImage()));
        add(new Label(" "));
    } 
}
