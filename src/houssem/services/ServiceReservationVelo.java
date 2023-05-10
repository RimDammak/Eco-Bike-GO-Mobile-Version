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
import houssem.entities.ReservationVelo;
import houssem.utils.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author houss
 */
public class ServiceReservationVelo {
    public ArrayList<ReservationVelo> tasks;

    public static ServiceReservationVelo instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceReservationVelo() {
        req = new ConnectionRequest();
    }

    public static ServiceReservationVelo getInstance() {
        if (instance == null) {
            instance = new ServiceReservationVelo();
        }
        return instance;
    }
///////////////////////////////////////////////////////
    public boolean addTask(ReservationVelo t) {

     String dateDebut=t.getDateDebut();
     String dateFin=t.getDateFin();
     int nbr=t.getNbr();
     float prixr=t.getPrixr();
     String idVelo=t.getIdVelo();
     String idStation=t.getIdStation();
    

        
        String url = DataSource.BASE_URL + "/houssem/addr?nbr=1&&prix=140";
        //String url = DataSource.BASE_URL + "create/" + nbr + "/" + nbr;
                                       //create?status=0&name=houssem
                                       //modified
        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<ReservationVelo> parseTasks(String jsonText) {
        try {
            tasks = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                ReservationVelo t = new ReservationVelo();
                /////////////////////////////////////////////////
                float id = Float.parseFloat(obj.get("idReservation").toString());
                t.setIdReservation((int) id);
                t.setNbr(((int) Float.parseFloat(obj.get("nbr").toString())));
                t.setPrixr(((int) Float.parseFloat(obj.get("prixr").toString())));
                if (obj.get("dateDebut") == null) {
                    t.setDateDebut("null");
                } else {
                    t.setDateDebut(obj.get("dateDebut").toString());
                }
                if (obj.get("dateFin") == null) {
                    t.setDateFin("null");
                } else {
                    t.setDateFin(obj.get("dateFin").toString());
                }
                if (obj.get("idVelo") == null) {
                    t.setIdVelo("null");
                } else {
                    t.setIdVelo(obj.get("idVelo").toString());
                }
                if (obj.get("idStation") == null) {
                    t.setIdStation("null");
                } else {
                    t.setIdStation(obj.get("idStation").toString());
                }
                tasks.add(t);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return tasks;
    }

    public ArrayList<ReservationVelo> getAllTasks() {
        String url = DataSource.BASE_URL + "/houssem/getallr";
        System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueue(req);
        return tasks;
    }
}
