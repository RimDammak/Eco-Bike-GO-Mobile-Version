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
import houssem.entities.Reclamation;
import houssem.utils.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author damak
 */
public class ServiceReclamation {
    public ArrayList<Reclamation> tasks;

    public static ServiceReclamation instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    private static final String URI = "/reclamation_back/api";

    private ServiceReclamation() {
        req = new ConnectionRequest();
    }

    public static ServiceReclamation getInstance() {
        if (instance == null) {
            instance = new ServiceReclamation();
        }
        return instance;
    }

    ///////////////////////////////////////////////////////
    // public void newUser(User p) {
    //     connection = new ConnectionRequest();
    //     connection.setInsecure(true);
    //     this.connection.setUrl(BASE_URL + "/user/add");
    //     this.connection.setHttpMethod("POST");

    //     connection.addArgument("nom", p.getNomuser());
    //     connection.addArgument("prenom", p.getPrenomuser());
    //     connection.addArgument("date_naiss", p.getDatenaiss());
    //     connection.addArgument("num_tel", p.getNumtel());
    //     connection.addArgument("email", p.getEmail());
    //     connection.addArgument("adresse", p.getAdresse());
    //     connection.addArgument("img_user", p.getImguser());
    //     connection.addArgument("mdp", p.getMdp());
    //     connection.addArgument("role", p.getRole());
    //     connection.addArgument("etat_compte", p.getEtatcompte() + "");

    //     NetworkManager.getInstance().addToQueue(connection);
    // }
    public boolean add(Reclamation t) {
        // String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" +
        // t.getStatus();
        String url = DataSource.BASE_URL + URI + "/create";
        // create?status=0&name=houssem
        // modified
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("POST");
        
        req.addArgument("description", t.getDescriptionRec());
        req.addArgument("etat", t.getEtatRec());

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 201; // Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Reclamation> parseTasks(String jsonText) {
        try {
            tasks = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Reclamation t = new Reclamation();
                /////////////////////////////////////////////////
                float id = Float.parseFloat(obj.get("idRec").toString());
                t.setIdRec((int) id);
                if (obj.get("dateRec") == null) {
                    t.setDateRec("null");
                } else {
                    t.setDateRec(obj.get("dateRec").toString());
                }
                if (obj.get("descriptionRec") == null) {
                    t.setDescriptionRec("null");
                } else {
                    t.setDescriptionRec(obj.get("descriptionRec").toString());
                }
                if (obj.get("image") == null) {
                    t.setImage("null");
                } else {
                    t.setImage(obj.get("image").toString());
                }
                if (obj.get("typeRec") == null) {
                    t.setEtatRec("null");
                } else {
                    t.setEtatRec(obj.get("typeRec").toString());
                }

                tasks.add(t);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return tasks;
    }

    public boolean delete(Reclamation reclamation) {
        String url = DataSource.BASE_URL + URI + "/delete/" + reclamation.getIdRec();
        req.setUrl(url);
        req.setPost(true);

        req.setHttpMethod("DELETE");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Reclamation> getAllTasks() {
        String url = DataSource.BASE_URL + URI + "/getall";
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");

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
