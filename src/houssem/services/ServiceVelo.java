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
import houssem.entities.Velo;
import houssem.utils.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author houss
 */
public class ServiceVelo {
    public ArrayList<Velo> velos;

    public static ServiceVelo instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceVelo() {
        req = new ConnectionRequest();
    }

    public static ServiceVelo getInstance() {
        if (instance == null) {
            instance = new ServiceVelo();
        }
        return instance;
    }
///////////////////////////////////////////////////////
//    public boolean addTask(Velo t) {
//
//        String name = t.getName();
//        int status =  t.getStatus();
//        
//        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
//        String url = Statics.BASE_URL + "create/" + status + "/" + name;
//                                       //create?status=0&name=houssem
//                                       //modified
//        req.setUrl(url);
//        req.setPost(false);
//        
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return resultOK;
//    }

    public ArrayList<Velo> parseTasks(String jsonText) {
        try {
            velos = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Velo t = new Velo();
                /////////////////////////////////////////////////
                float id = Float.parseFloat(obj.get("idVelo").toString());
                t.setId_velo((int) id);
                t.setPrix(((int) Float.parseFloat(obj.get("prix").toString())));
                t.setQte(((int) Float.parseFloat(obj.get("qte").toString())));
                if (obj.get("titre") == null) {
                    t.setTitre("null");
                } else {
                    t.setTitre(obj.get("titre").toString());
                }
                if (obj.get("image") == null) {
                    t.setImage("null");
                } else {
                    t.setImage(obj.get("image").toString());
                }
                velos.add(t);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return velos;
    }

    public ArrayList<Velo> getAllTasks() {
        String url = DataSource.BASE_URL + "/velo/getall";
        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                velos = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return velos;
    }
    
}
