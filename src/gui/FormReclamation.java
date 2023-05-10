/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
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
 * @author houss
 */
public class FormReclamation extends Form{
     public FormReclamation(Form previous) {
        setTitle("Liste Reclamations");
        setLayout(BoxLayout.y());
        TextField etatRecherche = new TextField("", "Rrchercher par etat");
        TextField typeRecherche = new TextField("", "Rrchercher par type");
        Button recherche = new Button("Rechercher");

        recherche.setUIID("LoginButton");

        add(etatRecherche);
        add(typeRecherche);
        add(recherche);

        Button btnValider = new Button("Ajouter une reclamation");
        btnValider.setUIID("LoginButton");
         btnValider.addActionListener(e -> {
            new FormAjoutReclamation(this).show();
        });
        ArrayList<Reclamation> tasks = ServiceReclamation.getInstance().getAllTasks();
        Display(tasks);


        recherche.addActionListener((e) -> {
            ArrayList<Reclamation> newrec = new ArrayList<>();
            for (Reclamation r : tasks) {

                if (r.getImage().toLowerCase().indexOf(typeRecherche.getText().toLowerCase()) != -1) {
                    if (r.getEtatRec().toLowerCase().indexOf(etatRecherche.getText().toLowerCase()) != -1) {
                        newrec.add(r);
                    }
                }

            }

            revalidate();
            removeAll();
            add(etatRecherche);
            add(typeRecherche);
            add(recherche);
            Display(newrec);
        });        
        recherche.addActionListener((e) -> {
            ArrayList<Reclamation> newrec = new ArrayList<>();
            for (Reclamation r : tasks) {

                if (r.getImage().toLowerCase().indexOf(typeRecherche.getText().toLowerCase()) != -1) {
                    if (r.getEtatRec().toLowerCase().indexOf(etatRecherche.getText().toLowerCase()) != -1) {
                        newrec.add(r);
                    }
                }

            }

            revalidate();
            removeAll();
            add(etatRecherche);
            add(typeRecherche);
            add(recherche);
            Display(newrec);
        });        
        for (Reclamation t : tasks) {
            // addElement(t);
        }

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



                cnt2.add(btnRemoveReclamation);
                cnt2.add(btnFindReclamation);

                add(cnt2);
                add(cnt1);
        }
    }

}
