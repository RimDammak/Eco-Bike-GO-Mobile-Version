/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import ServiceEvent.ServiceEvent;
import com.codename1.components.ImageViewer;
import com.codename1.io.Storage;
import com.codename1.io.URL;
import com.codename1.io.URL.HttpURLConnection;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import static com.codename1.ui.events.ActionEvent.Type.Response;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import entity.Event;


import java.io.DataInputStream;

import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import utile.Staticts;




/**
 *
 * @author choua
 */
public class Evenement extends Form {    
    private ServiceEvent serviceEvent;
    private ArrayList<Event> events;
   public Evenement(Form previous) {
        serviceEvent = ServiceEvent.getInstance();
        events = serviceEvent.getAllTasks();
        setTitle("Liste des événements");
        // Créer un style pour le titre
TextField searchField = new TextField("", "Nom de l'événement");
Button searchButton = new Button("Rechercher");
add(searchField);
add(searchButton);

      
        
        Style titleStyle = getTitleStyle();
        titleStyle.setFgColor(0xff0000);
        titleStyle.setBgColor(0xffffff);
        titleStyle.setPaddingTop(10);
        titleStyle.setPaddingBottom(10);
        titleStyle.setPaddingLeft(5);
        titleStyle.setPaddingRight(5);
        titleStyle.setBorder(Border.createLineBorder(2, 0xff0000));        
        // Créer un style pour la liste des événements
        Style listStyle = getStyle();
        listStyle.setBgColor(0xf5f5f5);
        listStyle.setPaddingTop(5);
        listStyle.setPaddingBottom(5);
        listStyle.setPaddingLeft(5);
        listStyle.setPaddingRight(5);
        for (Event event : events) {
            Container eventContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            eventContainer.getStyle().setMarginBottom(10);
            addComponent(eventContainer);          
            // Ajouter une image si elle existe
            String imageUrl = event.getPhoto_event();
            if (imageUrl != null && !imageUrl.isEmpty()) {
                String fullImageUrl = "http://127.0.0.1:8000/uploads/event_photos/" + imageUrl;
                try {
                    EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(900, 1600, 0xffff0000), true);
                    URLImage urlImage = URLImage.createToStorage(placeholder, "image_" + imageUrl, fullImageUrl, URLImage.RESIZE_SCALE_TO_FILL);
                    Label photoEvent = new Label(urlImage);
                    eventContainer.addComponent(photoEvent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // Ajouter le nom de l'événement
            Label nomEvent = new Label(event.getNom_event());
            nomEvent.getStyle().setFgColor(0x000000);
            nomEvent.getStyle().setPaddingTop(10);
            nomEvent.getStyle().setPaddingBottom(5);
            nomEvent.getStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
            eventContainer.addComponent(nomEvent);

            // Ajouter la date de l'événement
            Label dateEvent = new Label();
            if (event.getDate_event() != null) {
                dateEvent.setText("Date: " + event.getDate_event().toString());
                dateEvent.getStyle().setFgColor(0x808080);
                dateEvent.getStyle().setPaddingBottom(5);
                eventContainer.addComponent(dateEvent);
            }

            // Ajouter la localisation de l'événement
            Label locateEvent = new Label(event.getLocate_event());
            locateEvent.getStyle().setFgColor(0x808080);
            locateEvent.getStyle().setPaddingBottom(5);
            eventContainer.addComponent(locateEvent);

            // Ajouter le nombre de places disponibles pour l'événement
            Label dispoplaceEvent = new Label(String.valueOf(event.getDispoplace_event()) + " places disponibles");
            dispoplaceEvent.getStyle().setFgColor(0x808080);
            dispoplaceEvent.getStyle().setPaddingBottom(10);
            eventContainer.addComponent(dispoplaceEvent);
            
            
            
             Button reserverBtn = new Button("Réserver");
            Button qrBtn = new Button("Code QR");
            
            // Ajouter des listeners d'événements aux boutons
reserverBtn.addActionListener(e -> {
    String url = Staticts.BASE_URL + "/reserver/94?idUser=28"; // Remplacez 94 par l'ID de l'événement approprié et 28 par l'ID de l'utilisateur approprié

    try {
        URL urlObj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);

        String jsonData = "{\"idUser\":28}";

        OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
        out.write(jsonData);
        out.flush();
        out.close();

        int responseCode = con.getConnectTimeout();

        if (responseCode == 200) { // vérifier que le code de réponse est égal à 200 (HTTP_OK)
            DataInputStream in = new DataInputStream(con.getInputStream());
            byte[] responseData = new byte[con.getContentLength()];
            in.readFully(responseData);
            in.close();
            String response = new String(responseData, "UTF-8");
            System.out.println(response);
        } else {
            System.out.println("POST request not successful");
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
});



searchButton.addActionListener(e -> {
    String searchText = searchField.getText();
    ArrayList<Event> filteredEvents = new ArrayList<>();

    for (Event ev : events) {
        if (ev.getNom_event().toLowerCase().contains(searchText.toLowerCase())) {
            filteredEvents.add(ev);
        }
    }

    // Create a new form to display the search results
    Form searchResultsForm = new Form("Résultats de recherche", new BoxLayout(BoxLayout.Y_AXIS));

    if (filteredEvents.isEmpty()) {
        searchResultsForm.add(new Label("Aucun événement trouvé"));
    } else {
      for (Event ev : filteredEvents) {
    // Add the event details to the form
    Label nameLabel = new Label("Nom : " + ev.getNom_event());
    Label locationLabel = new Label("Lieu : " + ev.getLocate_event());
    Label availablePlacesLabel = new Label("Places disponibles : " + ev.getDispoplace_event());

    // Add the labels to the form
    searchResultsForm.add(nameLabel);
    searchResultsForm.add(locationLabel);
    searchResultsForm.add(availablePlacesLabel);
}

    }

    // Show the search results form
    searchResultsForm.show();
});


// Ajouter un bouton de tri
Button trierButton = new Button("Trier");
add(trierButton);

trierButton.addActionListener(e -> {
    // Trier les événements par ordre alphabétique croissant en fonction de leur nom d'événement
    Collections.sort(events, new Comparator<Event>() {
        @Override
        public int compare(Event event1, Event event2) {
            return event1.getNom_event().compareToIgnoreCase(event2.getNom_event());
        }
    });

    // Afficher la liste triée des événements
    Form triResultsForm = new Form("Résultats du tri", new BoxLayout(BoxLayout.Y_AXIS));
    for (Event za  : events) {
        Label eventLabel = new Label(za.getNom_event());
        Label eventLabel1 = new Label(za.getLocate_event());
          Label availablePlacesLabel = new Label("Places disponibles : " + za.getDispoplace_event());

        triResultsForm.add(eventLabel);
        triResultsForm.add(eventLabel1);
        triResultsForm.add(availablePlacesLabel);
    }
    triResultsForm.show();
});













            
 qrBtn.addActionListener(e -> {

     String url = Staticts.BASE_URL + "qr-code/95";
    Image qrCodeImage = ServiceEvent.getInstance().getQRCode(url);
    Label qrCodeLabel = new Label(qrCodeImage);

     
});


            
             Container btnContainer = new Container(new FlowLayout(Component.CENTER));
    btnContainer.add(reserverBtn);
    btnContainer.add(qrBtn);

    // Ajouter le conteneur de boutons au conteneur d'événement
    eventContainer.addComponent(btnContainer);

    // Ajouter le conteneur d'événement à la page
  //  addComponent(eventContainer);
            
            
        }
    }
   public void start() {
        Form hi = new Form("Events", BoxLayout.y());
        hi.setLayout(BoxLayout.y());
        hi.addComponent(new Label("Liste des événements :"));
        hi.addComponent(this);
        hi.show();
    }
}



    

