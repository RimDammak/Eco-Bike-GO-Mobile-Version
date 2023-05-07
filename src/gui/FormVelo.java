/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import houssem.entities.Velo;
import houssem.services.ServiceVelo;
import java.util.ArrayList;

/**
 *
 * @author houss
 */
public class FormVelo extends Form{
    private ServiceVelo servicevelo;
    private ArrayList<Velo> velos;
    public FormVelo(Form previous){
  
        servicevelo = servicevelo.getInstance();
        velos = servicevelo.getAllTasks();      
        setTitle("Velos");
setLayout(BoxLayout.y());
        for (Velo velo : velos) {           
            add(new Label(" "));
            // Créez un composant pour afficher chaque événement, par exemple :
            Label nomEvent = new Label("Nom de velo: "+velo.getTitre());
            //Label locateEvent = new Label(velo.getPrix());
           String imageUrl = velo.getImage();
if (imageUrl != null && !imageUrl.isEmpty()) {
  String fullImageUrl = "http://127.0.0.1:8000/tsawir/velo/" + imageUrl;
  try {
    EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(900, 1600), true);
    URLImage urlImage = URLImage.createToStorage(placeholder, "image_" + imageUrl, fullImageUrl, URLImage.RESIZE_SCALE);
    Label photoEvent = new Label(urlImage);
    Container imageContainer = new Container(new BorderLayout());
    imageContainer.add(BorderLayout.CENTER, photoEvent);
    this.add(imageContainer);
  } catch (Exception e) {
    e.printStackTrace();
  }
}
add(new Label(" "));
this.add(nomEvent);add(new Label(" "));
this.add(new Label("Prix: " + velo.getPrix() + " DT"));add(new Label("\n"));
this.add(new Label("Quantité disponible: " + velo.getQte()));
        }
this.show();

add(new Label("\n"));add(new Label("\n"));
 getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }        
}