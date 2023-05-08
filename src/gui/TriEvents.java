/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import ServiceEvent.ServiceEvent;
import ServiceEvent.ServiceEvent.SortCallback;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import entity.Event;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 *
 * @author choua
 */
public class TriEvents extends Form {
  ArrayList<Event> data = new ArrayList<>();
    Form current;

    public TriEvents(Form previous) {
        setTitle("Sort by Type");

    ServiceEvent.getInstance().sortByType(new SortCallback() {
    @Override
    public void onSortCompleted(ArrayList<Event> sortedList) {
        data = sortedList;
        // Afficher les événements triés ici
    }

            @Override
            public void onSuccess(ArrayList<Event> sortedData) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    
        Container y = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        for (int i = 0; i < data.size(); i++) {
            Container x = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container xx = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container xxx = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container xxxx = new Container(new BoxLayout(BoxLayout.X_AXIS));

            Event e = new Event();

          
e.setId_event(data.get(i).getId_event());
e.setNom_event(data.get(i).getNom_event());

           

            Label separation = new Label("----------------------------");
            Label Id = new Label(" ID  :" + data.get(i).getId_event());
            Label type = new Label("Type : " + data.get(i).getNom_event());
          

            x.addAll(type);
           

            xx.addAll(Id);

            y.addAll(separation, x,  xx);

        }

        addAll(y);

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new Evenement(previous).show());

    }
}

