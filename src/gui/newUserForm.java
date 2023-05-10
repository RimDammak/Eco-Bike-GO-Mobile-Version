package gui;

import com.codename1.ui.Button;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import houssem.entities.User;
import houssem.services.UserWebService;


public class newUserForm extends BaseForm {

    public newUserForm() {
        this.init(Resources.getGlobalResources());
        
        TextField nomField = new TextField("", "NomUser");
        TextField prenomField = new TextField("", "PrenomUser");
        TextField numtelField = new TextField("", "NumTel");
        TextField emailField = new TextField("", "Email");
        TextField adresseField = new TextField("", "Adresse");
        TextField imgField = new TextField("", "ImgUser");
        TextField mdpField = new TextField("", "Mdp");
        TextField roleField = new TextField("", "Role");
        TextField etatcompteField = new TextField("", "EtatCompte");
        
        this.add(nomField);
        this.add(prenomField);
        this.add(numtelField);
        this.add(emailField);
        this.add(adresseField);
        this.add(imgField);
        this.add(mdpField);
        this.add(roleField);
        this.add(etatcompteField);

        Button submitButton = new Button("Ajouter");
        submitButton.setUIID("LoginButton");
        submitButton.addActionListener(s
                -> {
            
            String nom = nomField.getText();
            String prenom = prenomField.getText();
            String numtel = numtelField.getText();
            String email = emailField.getText();
            String adresse = adresseField.getText();
            String img = imgField.getText();
            String mdp = mdpField.getText();
            String role = roleField.getText();
            int etatcompte = Integer.valueOf(etatcompteField.getText());

            User user = new User();
            
            user.setNomuser(nom);
            user.setPrenomuser(prenom);
            user.setNumtel(numtel);
            user.setEmail(email);
            user.setAdresse(adresse);
            user.setImguser(img);
            user.setMdp(mdp);
            user.setRole(role);
            user.setEtatcompte(etatcompte);
            
            UserWebService service = new UserWebService();
            service.newUser(user);
            GetUserForm myForm = new GetUserForm();
            myForm.show();
        }
        );
        this.add(submitButton);
        Button goToFormButton = new Button("Go Back");
        goToFormButton.setUIID("LoginButton");
        goToFormButton.addActionListener(e -> {
            GetUserForm myForm = new GetUserForm();
            myForm.show();
        });
        this.add(goToFormButton);
    }

}