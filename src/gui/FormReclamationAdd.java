/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import houssem.entities.Reclamation;
import houssem.services.ServiceReclamation;

/**
 *
 * @author rim
 */
public class FormReclamationAdd extends Form{
    public FormReclamationAdd(Form previous){
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    setTitle("Add a new task");
        setLayout(BoxLayout.y());
        
        // TextField tfType = new TextField("" , "Type Reclamation:");
        TextField tfDesc = new TextField("" , "Description Reclamation:");
        // TextField tfDate = new TextField("" , "Date Reclamation:");
        TextField tfEtat = new TextField("" , "Etat Reclamation:");
        // TextField tfUserId = new TextField("" , "User id Reclamation:");

        Button btnValider = new Button("Add Reclamation");
        btnValider.setUIID("LoginButton");

        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfDesc.getText().length()==0))
                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Reclamation reclamation = new Reclamation(0, "2023-05-01T00:00:00+02:00", tfDesc.getText(), "2", tfEtat.getText());
                        if( ServiceReclamation.getInstance().add(reclamation))
                        {
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        }else
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        
        // add(tfType);
        add(tfDesc);
        // add(tfDate);
        add(tfEtat);
        // add(tfUserId);
        add(btnValider);
        
        
        
    
    }
}
