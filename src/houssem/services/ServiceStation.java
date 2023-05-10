/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package houssem.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import houssem.entities.Station;
import houssem.utils.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author houss
 */
public class ServiceStation {
    public ArrayList<Station> Stations;
    public static ServiceStation instance = null;
    public Boolean result =false;
    private ConnectionRequest req;
     private ConnectionRequest reqpost;
    private ServiceStation(){
        req =new ConnectionRequest();
        //reqpost =new ConnectionRequest();
    }
    public static ServiceStation getInstance() {
        if (instance == null) {
            instance = new ServiceStation();
        }
        return instance;
    }
    public boolean addStation(Station s) {
        String name = s.getNomStation();
        String vill = s.getLocalisationStation();
        int nbr =  s.getVeloStation();
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        //String url = Statics.BASE_URL + "create?nomStation=" + s.getNomStation() + "&localisationStation=" + s.getLocalisationStation() + "&veloStation=" + s.getVeloStation();
        //String url = DataSource.BASE_URL + "add/" + name + "/" + vill + "/" + nbr;
        String url = "http://127.0.0.1:8000" + "/houssem/add?nomStation="+name+"&localisationStation="+vill+"&veloStation="+nbr;
        System.out.println(url);
        //ConnectionRequest.setCertificateValidation(false);

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                result = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }
    public ArrayList<Station> parseStations(String jsonText) {
        try {
            Stations = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Station s = new Station();
                float id = Float.parseFloat(obj.get("idStation").toString());
                s.setIdStation((int) id);
                if (obj.get("nomStation") == null) {
                    s.setNomStation("null");
                }else{
                    s.setNomStation(obj.get("nomStation").toString());
                }
                if (obj.get("localisationStation") == null) {
                    s.setNomStation("null");
                } else {
                    s.setLocalisationStation(obj.get("localisationStation").toString());
                }
                s.setVeloStation(((int) Float.parseFloat(obj.get("veloStation").toString())));
                Stations.add(s);
            }
                
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return Stations;
    }  
    
    public ArrayList<Station> getAllStations() {
        String url = DataSource.BASE_URL + "/houssem/getall";
        System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Stations = parseStations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Stations;
    }
    
    
    public ArrayList<Station> affichageExcursion(int s) 
     {
        ArrayList<Station> result = new ArrayList<>();
        String  url = DataSource.BASE_URL +"/houssem/getone/" + s;
         req.setUrl(url);
         System.out.println(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;                
                jsonp = new JSONParser();
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> mapExcursion = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapExcursion.get("root");
                      
                    for (Map<String, Object> obj : listOfMaps) {
                     Station s=new Station();
                        int id = (int) Float.parseFloat(obj.get("idStation").toString());
                        int nbr = (int) Float.parseFloat(obj.get("veloStation").toString());
                        String loc = obj.get("localisationStation").toString();
                        String nom = obj.get("nomStation").toString();

                        s.setIdStation((int) id);
                        s.setVeloStation((int) nbr);
                        s.setLocalisationStation(loc);
                        s.setNomStation(nom);                      

//                        String DateConverter=obj.get("date").toString().substring(obj.get("Date").toString().indexOf("timestamp")+10 , obj.get("Date").toString().lastIndexOf("}"));      
   //             Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
   //             SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    //            String dateString = formatter.format(currentTime);
    //            v.setDate(dateString);
                result.add(s);
                  
                    }
                } 
       catch(Exception e ){
                       e.printStackTrace();
                   }
            }           
                });
        
         NetworkManager.getInstance().addToQueueAndWait(req);
                             
           return result;
    }

}
