package gui;

import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.list.MultiList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.util.Resources;
import houssem.entities.User;
import houssem.services.UserWebService;
import java.util.Collections;
import java.util.Comparator;


public class GetUserForm extends BaseForm {

    private MultiList userList;
    private List<User> users;
    private TextField searchField;

    public GetUserForm() {
        

        Button sortButton = new Button("Sort by Name");
        sortButton.setUIID("LoginButton");
        sortButton.addActionListener(e -> {
            Collections.sort(users, new Comparator<User>() {
                @Override
                public int compare(User u1, User u2) {
                    return u1.getNomuser().compareToIgnoreCase(u2.getNomuser());
                }
            });
            updateList();
        });
        addComponent(BorderLayout.south(sortButton));
        
        this.init(Resources.getGlobalResources());
        userList = new MultiList(new DefaultListModel<>());
        add(userList);

        getAllUsers();
    }

    private void updateList() {
        DefaultListModel<Map<String, Object>> model = (DefaultListModel<Map<String, Object>>) userList.getModel();
        model.removeAll();
        for (User u : users) {
            Map<String, Object> item = new HashMap<>();
            item.put("Line1", "Name: " + u.getNomuser());
            item.put("Line2", "Email: " + u.getEmail());
            item.put("Line3", u.getIduser());
            model.addItem(item);
        }
    }
    
    private void getAllUsers() {
        searchField = new TextField("", "Enter User Name");
        Button searchButton = new Button("Search");
        searchButton.setUIID("LoginButton");
        searchButton.addActionListener(e -> {
            try {
                String searchName = searchField.getText();
                User selectedUser = null;
                for (User u : users) {
                    if (u.getNomuser()== null ? searchName == null : u.getNomuser().equals(searchName)) {
                        selectedUser = u;
                        break;
                    }
                }
                if (selectedUser != null) {
                    editUserForm myForm2 = new editUserForm(selectedUser);
                    myForm2.show();
                } else {
                    Dialog.show("Error", "User not found", "OK", null);
                }
            } catch (NumberFormatException ex) {
                Dialog.show("Error", "Invalid ID", "OK", null);
            } catch (ParseException ex) {
                System.out.println(ex);
            }
        });
        Container searchContainer = BorderLayout.east(searchField).add(BorderLayout.EAST, searchButton);
        addComponent(searchContainer);
        
        
        
        UserWebService service = new UserWebService();
        users = service.getAllUsers();
        updateList();

        userList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    Map<String, Object> selectedItem = (Map<String, Object>) userList.getSelectedItem();
                    int userId = (int) selectedItem.get("Line3");
                    User selectedUser = null;
                    for (User u : users) {
                        if (u.getIduser()== userId) {
                            selectedUser = u;
                            break;
                        }
                    }
                    editUserForm myForm2 = new editUserForm(selectedUser);
                    myForm2.show();
                } catch (ParseException ex) {
                    System.out.println(ex);
                }
            }
        });

        
    }

    
}
