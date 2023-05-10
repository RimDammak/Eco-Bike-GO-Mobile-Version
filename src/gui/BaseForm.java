package gui;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;

public class BaseForm extends com.codename1.ui.Form {

    public void init(Resources theme_1) {
        Toolbar tb = getToolbar();

        tb.getAllStyles().setBgColor(0xffffff);

        Image logo = theme_1.getImage("imgProf.jpg");
        Label logoLabel = new Label(logo);
        Container logoContainer = BorderLayout.center(logoLabel);
        logoContainer.setUIID("SideCommandLogo");
        tb.addComponentToSideMenu(logoContainer);

        Label taglineLabel = new Label("Gestion User");
        taglineLabel.setUIID("SideCommandTagline");
        Container taglineContainer = BorderLayout.south(taglineLabel);
        taglineContainer.setUIID("SideCommand");
        
        tb.addComponentToSideMenu(taglineContainer);
        
        tb.addMaterialCommandToSideMenu("Liste Users", FontImage.MATERIAL_LIST, e -> {
            GetUserForm f = new GetUserForm();
            f.show();
        });
        tb.addMaterialCommandToSideMenu("Ajouter User", FontImage.MATERIAL_ADD, e -> {
            newUserForm f = new newUserForm();
            f.show();
        });
    }
}
