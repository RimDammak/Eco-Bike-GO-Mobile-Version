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
public class FormReclamationDetails extends Form {
    public FormReclamationDetails(Form previous, Reclamation rec) {
        setTitle("Reclamation");
        setLayout(BoxLayout.y());
        Label title = new Label("Reclamation N°" + rec.getIdRec());

        add(title);
        Display(rec);

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

    public void Display(Reclamation r) {

        Container cnt1 = new Container(BoxLayout.y());

        Label lbType = new Label(" Etat= " + r.getImage());
        SpanLabel lbDesc = new SpanLabel(" Description= " + r.getDescriptionRec());
        Label lbDate = new Label(" Date= " + r.getDateRec());
        Label lbEtat = new Label(" Type= " + r.getEtatRec());

        // lbDesc.setAutoSizeMode(true);
        SpanLabel lbSeparator = new SpanLabel(" \n ");
        // cnt1.add(lbUsername);

        cnt1.add(lbType);
        cnt1.add(lbDesc);
        cnt1.add(lbDate);
        cnt1.add(lbEtat);
        cnt1.add(lbSeparator);

        Container cnt2 = new Container(BoxLayout.x());
        Button btnRemoveReclamation = new Button("Remove Reclamation");
        // Button btnEdit = new Button("Edit reclamation");
        btnRemoveReclamation.setUIID("LoginButton");
        // btnEdit.setUIID("LoginButton");
        btnRemoveReclamation.addActionListener((e) -> {
            try {
                Reclamation rec = new Reclamation(r.getIdRec());
                ServiceReclamation.getInstance().delete(rec);
                Dialog.show("Success", "Reclamation avec ID= " + rec.getIdRec() + " a ete supprimee avec succees",
                        "OK", null);
                revalidate();
                new FormReclamation(this).show();
            } catch (NumberFormatException err) {
                Dialog.show("ERROR", "Status must be a number", "OK", null);
            }
        });
        // btnEdit.addActionListener(e -> {
        // new FormReclamationAdd(this, r.getIdRec()).show();
        // });
        cnt2.add(btnRemoveReclamation);
        // cnt2.add(btnEdit);

        add(cnt2);
        add(cnt1);
    }

}
