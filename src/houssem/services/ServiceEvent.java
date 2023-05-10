/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package houssem.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionListener;
import entity.Event;
import houssem.utils.DataSource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;


/**
 *
 * @author choua
 */
public class ServiceEvent {
    
    
     public ArrayList<Event> events;

    public static ServiceEvent instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceEvent() {
        req = new ConnectionRequest();
    }

    public static ServiceEvent getInstance() {
        if (instance == null) {
            instance = new ServiceEvent();
        }
        return instance;
    }
    
    
    public ArrayList<Event> parseEvents(String jsonText) throws ParseException {
    ArrayList<Event> events = new ArrayList<>();
    try {
        JSONParser jsonParser = new JSONParser();
        Map<String, Object> eventsJson = jsonParser.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        List<Map<String, Object>> eventsList = (List<Map<String, Object>>) eventsJson.get("root");
        for (Map<String, Object> eventJson : eventsList) {
            Event event = new Event();
            if (eventJson.get("nomEvent") != null) {
                event.setNom_event(eventJson.get("nomEvent").toString());
            }

          
            if (eventJson.get("locateEvent") != null) {
                event.setLocate_event(eventJson.get("locateEvent").toString());
            }
            if (eventJson.get("photoEvent") != null) {
                event.setPhoto_event(eventJson.get("photoEvent").toString());
            }
            if (eventJson.get("dispoplaceEvent") != null) {
    float dispoPlace = Float.parseFloat(eventJson.get("dispoplaceEvent").toString());
    event.setDispoplace_event(Math.round(dispoPlace));
}

            events.add(event);
        }
    } catch (IOException ex) {
        ex.printStackTrace();
    }
    return events;
}

      public ArrayList<Event> getAllTasks() {
        String url = DataSource.BASE_URL + "/med/get";
        req.setUrl(url);
          System.out.println(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    events = parseEvents(new String(req.getResponseData()));
                } catch (ParseException ex) {
                  
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return events;
    }
    
      
      
public Image getQRCode(String url) {
    try {
        Image qrCodeImage = URLImage.createToStorage(
                EncodedImage.createFromImage(Image.createImage(120, 120), false),
                "qr-code",
                url,
                URLImage.RESIZE_SCALE
        );
        return qrCodeImage;
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}




//////////tri

 ArrayList<Event> sort = new ArrayList<>();

public interface SortCallback {
   void onSuccess(ArrayList<Event> sortedData);
  void onSortCompleted(ArrayList<Event> sortedList);
}

public void sortByType(SortCallback callback) {
    String url = DataSource.BASE_URL + "/med/event/tri";
    ConnectionRequest req = new ConnectionRequest(url);

    req.addResponseListener((evt) -> {
        ArrayList<Event> sortedList = getList(new String(req.getResponseData()));
        Collections.sort(sortedList, new Comparator<Event>() {
            @Override
            public int compare(Event e1, Event e2) {
                return e1.getNom_event().compareToIgnoreCase(e2.getNom_event());
            }
        });

        callback.onSortCompleted(sortedList);
    });

    NetworkManager.getInstance().addToQueue(req);
}


   public ArrayList<Event> getList(String json)
    {
        String url = DataSource.BASE_URL + "/med/event/tri";
        req.setUrl(url);
        ArrayList<Event> result = new ArrayList<>();
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {                
                JSONParser jsonp;
                jsonp = new JSONParser();                
                try 
                {
                    Map<String,Object>map = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String,Object>> ListOfMaps = (List<Map<String,Object>>) map.get("root");
                    System.out.println(map);
                    for(Map<String, Object> obj : ListOfMaps)
                    {
                        System.out.println(obj);
                         System.out.println(" &&&&&&&&&&&&&&&&&&&Tri&&&&&&&&&&&&&&&&");
                       Event c = new Event();                      
                         String type = obj.get("nom_event").toString();                        
                         float id = Float.parseFloat(obj.get("id_event").toString());
                       c.setId_event((int)id);
                        c.setNom_event(type);
                        result.add(c);
                        System.out.println(c.getNom_event());
                    }
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;   
    }  
























//////////tri 2


public void sortEventsByType(String eventType, ActionListener<NetworkEvent> listener) {
    String url = DataSource.BASE_URL + "/event/tri?type=" + eventType;
    ConnectionRequest req = new ConnectionRequest(url);
    req.addResponseListener(listener);
    NetworkManager.getInstance().addToQueue(req);
}

public ArrayList<Event> sortEventsByType(String eventType) {
    ArrayList<Event> result = new ArrayList<>();
    ServiceEvent ser = new ServiceEvent();
    
    ser.sortEventsByType(eventType, new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            try {
                JSONParser jsonp = new JSONParser();
                Map<String,Object> eventsJson = jsonp.parseJSON(new InputStreamReader(new ByteArrayInputStream(evt.getConnectionRequest().getResponseData()), "UTF-8"));
                List<Map<String,Object>> ListOfMaps = (List<Map<String,Object>>) eventsJson.get("root");
                
                for(Map<String, Object> eventJson : ListOfMaps) {
                    Event e = new Event();
                    String nom = eventJson.get("nom_event").toString();
                    float id = Float.parseFloat(eventJson.get("id_event").toString());
                    e.setId_event((int)id);
                    e.setNom_event(nom);
                    result.add(e);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    });
    
    // Attendre que la réponse du serveur soit reçue
    while(result.isEmpty()) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
    // Trier la liste d'événements par ordre alphabétique
    Collections.sort(result, (e1, e2) -> e1.getNom_event().compareToIgnoreCase(e2.getNom_event()));
    
    return result;
}





















    
    
}
