/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import houssem.entities.Reclamation;
import houssem.services.ServiceReclamation;
import java.util.ArrayList;

/**
 *
 * @author damak
 */
public class FormReclamation extends Form{
     public FormReclamation(Form previous) {
        setTitle("Liste Reclamations");
        setLayout(BoxLayout.y());
        TextField tfRech = new TextField("", "Rrchercher par etat");
        Button recherche = new Button("Rechercher");
        Button addReclamation = new Button("Add reclamation");

        recherche.setUIID("LoginButton");

        add(tfRech);
        add(recherche);
        add(addReclamation);
        addReclamation.setUIID("LoginButton");
         addReclamation.addActionListener(e -> {
            new FormReclamationAdd(this).show();
        });

        Button btnValider = new Button("Ajouter une reclamation");
        btnValider.setUIID("LoginButton");
         btnValider.addActionListener(e -> {
            new FormReclamationAdd(this).show();
        });
        ArrayList<Reclamation> tasks = ServiceReclamation.getInstance().getAllTasks();
        Display(tasks);


        recherche.addActionListener((e) -> {
            ArrayList<Reclamation> newrec = new ArrayList<>();
            for (Reclamation r : tasks) {

                if (r.getImage().toLowerCase().indexOf(tfRech.getText().toLowerCase()) != -1) {
                    newrec.add(r);
                }

            }

            revalidate();
            removeAll();
            add(tfRech);
            add(recherche);
            Display(newrec);
        });        

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

    public void Display(ArrayList<Reclamation> r1) {

        for (Reclamation r : r1) {

                Container cnt1 = new Container(BoxLayout.y());

                Label lbType = new Label(" Etat= " + r.getImage());
                SpanLabel lbDesc = new SpanLabel(" Description= " + r.getDescriptionRec());
                Label lbDate = new Label(" Date= " + r.getDateRec());
                Label lbEtat = new Label(" Type= " + r.getEtatRec());

                //lbDesc.setAutoSizeMode(true);
                SpanLabel lbSeparator = new SpanLabel(" \n ");
                //cnt1.add(lbUsername);

                cnt1.add(lbType);
                cnt1.add(lbDesc);
                cnt1.add(lbDate);
                cnt1.add(lbEtat);
                cnt1.add(lbSeparator);

                Container cnt2 = new Container(BoxLayout.x());
                Button btnRemoveReclamation = new Button("Remove Reclamation");
                Button btnFindReclamation = new Button("Get Reclamation");
                btnRemoveReclamation.setUIID("LoginButton");
                btnFindReclamation.setUIID("LoginButton");
                
                btnFindReclamation.addActionListener(e -> new FormReclamationDetails(this, r).show());

                btnRemoveReclamation.addActionListener((e) -> {
                    try {
                        Reclamation rec = new Reclamation(r.getIdRec());
                        if (ServiceReclamation.getInstance().delete(rec)) {
                            Dialog.show("Success", "Reclamation avec ID= " + rec.getIdRec() + " a ete supprimee avec succees", "OK", null);
                            revalidate();
                            new FormReclamation(this).show();
                        } else {
                            Dialog.show("ERROR", "Server error", "OK", null);
                        }
                    } catch (NumberFormatException err) {
                        Dialog.show("ERROR", "Status must be a number", "OK", null);
                    }
                });

                cnt2.add(btnRemoveReclamation);
                cnt2.add(btnFindReclamation);

                add(cnt2);
                add(cnt1);
        }
    }

}
