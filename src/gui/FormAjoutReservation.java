/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import houssem.entities.ReservationVelo;
import houssem.entities.Velo;
import houssem.services.ServiceVelo;
import java.util.ArrayList;

/**
 *
 * @author houss
 */
public class FormAjoutReservation extends Form {
    private ServiceVelo servicevelo;
    private ArrayList<Velo> velos;
    
    public FormAjoutReservation(Form previous, int s) {
        servicevelo = ServiceVelo.getInstance();
        velos = servicevelo.getAllTasks();
        
        setTitle("Velos");
        setLayout(BoxLayout.y());
        
        for (Velo velo : velos) {
            add(new Label(" "));
            
            // Create a container to hold the image and details
            Container veloContainer = new Container(new BorderLayout());
            
            // Create a container to hold the image
            Container imageContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            String imageUrl = velo.getImage();
            if (imageUrl != null && !imageUrl.isEmpty()) {
                String fullImageUrl = "http://127.0.0.1:8000/tsawir/velo/" + imageUrl;
                try {
                    EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(350, 550), true);
                    URLImage urlImage = URLImage.createToStorage(placeholder, "rimage_" + imageUrl, fullImageUrl, URLImage.RESIZE_SCALE);
                    Label photoEvent = new Label(urlImage);
                    imageContainer.add(photoEvent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            veloContainer.add(BorderLayout.WEST, imageContainer);
            
            // Create a container to hold the details
            Container detailsContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Label nomEvent = new Label("Nom de velo: " + velo.getTitre());
            Label prixLabel = new Label("Prix: " + velo.getPrix() + " DT");
            Label qteLabel = new Label("Quantité disponible: " + velo.getQte());
            TextField tnbr=new TextField("","quantité à réserver");
            Button bb=new Button("reserver");
               
            detailsContainer.add(nomEvent);
            detailsContainer.add(prixLabel);
            detailsContainer.add(qteLabel);
            detailsContainer.add(tnbr);
            veloContainer.add(BorderLayout.CENTER, detailsContainer);
            veloContainer.add(bb);
            // Add the velo container to the form
            this.add(veloContainer);
            bb.addActionListener(e->{
                int nbr= Integer.parseInt(tnbr.toString());
                ReservationVelo r = new ReservationVelo(imageUrl, imageUrl, nbr, nbr, imageUrl, imageUrl);
                //do you
                //(String dateDebut, String dateFin, int nbr, float prixr, String idVelo, String idStation)
            //ServiceReservationVelo.getInstance().addTask(r);
                //Dialog.show("Success","reservation ajoutee",new Command("OK"));
                previous.show();
            });
        }
        
        add(new Label("\n"));
        add(new Label("\n"));
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}
