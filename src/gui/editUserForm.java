package gui;

import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import houssem.entities.User;
import houssem.services.UserWebService;


public class editUserForm extends BaseForm {

    UserWebService service = new UserWebService();
    public editUserForm(User p) throws ParseException {
        this.init(Resources.getGlobalResources());
        TextField nomField = new TextField(p.getNomuser(), "NomUser");
        TextField prenomField = new TextField(p.getPrenomuser(), "PrenomUser");
        TextField numtelField = new TextField(p.getNumtel(), "NumTel");
        TextField emailField = new TextField(p.getEmail(), "Email");
        TextField adresseField = new TextField(p.getAdresse(), "Adresse");
        TextField imgField = new TextField(p.getImguser(), "ImgUser");
        TextField mdpField = new TextField(p.getMdp(), "Mdp");
        TextField roleField = new TextField(p.getRole(), "Role");
        TextField etatcompteField = new TextField(""+p.getEtatcompte(), "EtatCompte");
        
        this.add(nomField);
        this.add(prenomField);
        this.add(numtelField);
        this.add(emailField);
        this.add(adresseField);
        this.add(imgField);
        this.add(mdpField);
        this.add(roleField);
        this.add(etatcompteField);
        
        Button submitButton = new Button("Update");
        submitButton.setUIID("LoginButton");
        submitButton.addActionListener(s-> {
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
            user.setIduser(p.getIduser());
            user.setNomuser(nom);
            user.setPrenomuser(prenom);
            user.setNumtel(numtel);
            user.setEmail(email);
            user.setAdresse(adresse);
            user.setImguser(img);
            user.setMdp(mdp);
            user.setRole(role);
            user.setEtatcompte(etatcompte);
            service.editUser(user);
        }
        );
        Button goToFormButton = new Button("Go back");
        goToFormButton.setUIID("LoginButton");
        goToFormButton.addActionListener(ee -> {
            GetUserForm myForm = new GetUserForm();
            myForm.show();
        });
        Button deleteButton = new Button("Delete");
        deleteButton.setUIID("LoginButton");
        deleteButton.addActionListener(cc -> {
            service.delPromotion(p);
            GetUserForm myForm = new GetUserForm();
            myForm.show();
        });
        this.add(deleteButton);
        this.add(goToFormButton);
        this.add(submitButton);
    }
}
