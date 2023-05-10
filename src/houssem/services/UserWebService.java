package houssem.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import houssem.entities.User;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserWebService {

    private static final String BASE_URL = "http://127.0.0.1:8000/api";
    private ConnectionRequest connection;

    public UserWebService() {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
    }

    public List<User> getAllUsers() {
        String url = BASE_URL + "/user";
        this.connection.setUrl(url);
        this.connection.setHttpMethod("GET");
        List<User> users = new ArrayList<>();
        this.connection.addResponseListener(e -> {
            if (this.connection.getResponseCode() == 200) {
                String response = new String(this.connection.getResponseData());
                try {
                    JSONArray jsonUsers = new JSONArray(response);
                    for (int i = 0; i < jsonUsers.length(); i++) {
                        JSONObject jsonUser = jsonUsers.getJSONObject(i);
                        User user = new User(
                                jsonUser.getInt("id"),
                                jsonUser.getString("nom"),
                                jsonUser.getString("prenom"),
                                jsonUser.getString("date_naiss"),
                                jsonUser.getString("num_tel"),
                                jsonUser.getString("email"),
                                jsonUser.getString("adresse"),
                                jsonUser.getString("img_user"),
                                jsonUser.getString("mdp"),
                                jsonUser.getString("role"),
                                jsonUser.getInt("etat_compte")
                        );
                        users.add(user);
                    }
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            } else {
                System.out.println("Request failed with response code: " + this.connection.getResponseCode());
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(this.connection);
        return users;
    }

    public void newUser(User p) {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
        this.connection.setUrl(BASE_URL + "/user/add");
        this.connection.setHttpMethod("POST");

        connection.addArgument("nom", p.getNomuser());
        connection.addArgument("prenom", p.getPrenomuser());
        connection.addArgument("date_naiss", p.getDatenaiss());
        connection.addArgument("num_tel", p.getNumtel());
        connection.addArgument("email", p.getEmail());
        connection.addArgument("adresse", p.getAdresse());
        connection.addArgument("img_user", p.getImguser());
        connection.addArgument("mdp", p.getMdp());
        connection.addArgument("role", p.getRole());
        connection.addArgument("etat_compte", p.getEtatcompte() + "");

        NetworkManager.getInstance().addToQueue(connection);
    }

    public void editUser(User p) {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
        this.connection.setUrl(BASE_URL + "/user/" + p.getIduser());
        this.connection.setHttpMethod("PUT");
        connection.addArgument("nom", p.getNomuser());
        connection.addArgument("prenom", p.getPrenomuser());
        connection.addArgument("date_naiss", p.getDatenaiss());
        connection.addArgument("num_tel", p.getNumtel());
        connection.addArgument("email", p.getEmail());
        connection.addArgument("adresse", p.getAdresse());
        connection.addArgument("img_user", p.getImguser());
        connection.addArgument("mdp", p.getMdp());
        connection.addArgument("role", p.getRole());
        connection.addArgument("etat_compte", p.getEtatcompte() + "");

        NetworkManager.getInstance().addToQueue(connection);
    }

    public void delPromotion(User p) {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
        this.connection.setUrl(BASE_URL + "/user/" + p.getIduser());
        this.connection.setHttpMethod("DELETE");
        NetworkManager.getInstance().addToQueue(connection);
    }

}
