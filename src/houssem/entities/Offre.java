/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package houssem.entities;

/**
 *
 * @author houss
 */
public class Offre {
    private int id,prix,nb_disp;
    private String cat,nom,description,location,date_val;

    public Offre() {
    }

    public Offre(int id, int prix, int nb_disp, String cat, String nom, String description, String location, String date_val) {
        this.id = id;
        this.prix = prix;
        this.nb_disp = nb_disp;
        this.cat = cat;
        this.nom = nom;
        this.description = description;
        this.location = location;
        this.date_val = date_val;
    }

    public Offre(int prix, int nb_disp, String cat, String nom, String description, String location, String date_val) {
        this.prix = prix;
        this.nb_disp = nb_disp;
        this.cat = cat;
        this.nom = nom;
        this.description = description;
        this.location = location;
        this.date_val = date_val;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getNb_disp() {
        return nb_disp;
    }

    public void setNb_disp(int nb_disp) {
        this.nb_disp = nb_disp;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate_val() {
        return date_val;
    }

    public void setDate_val(String date_val) {
        this.date_val = date_val;
    }

    @Override
    public String toString() {
        return "Offre{" + "id=" + id + ", prix=" + prix + ", nb_disp=" + nb_disp + ", cat=" + cat + ", nom=" + nom + ", description=" + description + ", location=" + location + ", date_val=" + date_val + '}';
    }
}
