/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package houssem.services;

import com.codename1.db.Database;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import houssem.entities.Offre;
import houssem.utils.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author houss
 */
public class ServiceOffre {
     public ArrayList<Offre> tasks;

    public static ServiceOffre instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceOffre() {
        req = new ConnectionRequest();
    }

    public static ServiceOffre getInstance() {
        if (instance == null) {
            instance = new ServiceOffre();
        }
        return instance;
    }
///////////////////////////////////////////////////////
    public boolean addTask(Offre t) {
        int id =  t.getId();
        String cat = t.getCat();
        String nom = t.getNom();
        String description = t.getDescription();
        String location = t.getLocation();
        int prix =  t.getPrix();
        int nb_disp =  t.getNb_disp();
        String date_val = t.getDate_val();
        
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = DataSource.BASE_URL + "/create/" + id + "/" + id;
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

    public ArrayList<Offre> parseTasks(String jsonText) {
        try {
            tasks = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Offre t = new Offre();
                /////////////////////////////////////////////////
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);  
                if (obj.get("cat") == null) {
                    t.setCat("null");
                } else {
                    t.setCat(obj.get("cat").toString());
                }
                if (obj.get("nom") == null) {
                    t.setNom("null");
                } else {
                    t.setNom(obj.get("nom").toString());
                }
                if (obj.get("description") == null) {
                    t.setDescription("null");
                } else {
                    t.setDescription(obj.get("description").toString());
                }
                if (obj.get("location") == null) {
                    t.setLocation("null");
                } else {
                    t.setLocation(obj.get("location").toString());
                } 
                t.setPrix(((int) Float.parseFloat(obj.get("prix").toString())));
                t.setNb_disp(((int) Float.parseFloat(obj.get("nb_disp").toString())));
                if (obj.get("date_val") == null) {
                    t.setDate_val("null");
                } else {
                    t.setDate_val(obj.get("date_val").toString());
                }
                tasks.add(t);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return tasks;
    }

    public ArrayList<Offre> getAllTasks() {
        String url = DataSource.BASE_URL + "/getall";
        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseTasks(new String(req.getResponseData()));
                System.out.println(tasks);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
}
