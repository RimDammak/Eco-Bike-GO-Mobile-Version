/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;

/**
 *
 * @author choua
 */
public class Home extends Form {
    

     private static final char ADD_ICON_NAME = FontImage.MATERIAL_ADD_CIRCLE_OUTLINE;
    private static final char LIST_ICON_NAME = FontImage.MATERIAL_LIST_ALT;
    private static final String LIST_BUTTON_TEXT = "Afficher Les Evenemnts";
    private static final String ADD_BUTTON_TEXT = "Afficher les evenements";
    
  public Home(Form previeus) {
       setTitle("Home");
    setLayout(BoxLayout.y());
getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previeus.showBack());
    add(new Label("Choose an option"));
    Button addButton = new Button("Evenement");
    addButton.setUIID("LoginButton");
    addButton.setIcon(FontImage.createMaterial(ADD_ICON_NAME, UIManager.getInstance().getComponentStyle("Button")));
    addButton.addActionListener(e -> new Evenement(this).show());
    Button sortCommand = new Button("Tri Evenemnt", FontImage.createMaterial(FontImage.MATERIAL_SORT, getUnselectedStyle()));
    sortCommand.setUIID("LoginButton");
     sortCommand.setIcon(FontImage.createMaterial(ADD_ICON_NAME, UIManager.getInstance().getComponentStyle("Button")));
    sortCommand.addActionListener(e -> new Evenement(this).show());
    
    Button listButton = new Button("");
    listButton.setUIID("LoginButton");
    listButton.setIcon(FontImage.createMaterial(LIST_ICON_NAME, UIManager.getInstance().getComponentStyle("Button")));
    listButton.setText(LIST_BUTTON_TEXT);

    addButton.addActionListener((evt) -> {
        new Evenement(this).show();
    });

    sortCommand.addActionListener((evt) -> {
       new Evenement(this).show();
    });
    add(addButton);
    add(sortCommand);
    add(listButton);
}

    
}
