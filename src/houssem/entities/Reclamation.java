    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package houssem.entities;

/**
 *
 * @author houss
 */
public class Reclamation {
    private int idRec;
    private String dateRec,descriptionRec,image,etatRec;

    public Reclamation() {
    }

    public Reclamation(int idRec, String dateRec, String descriptionRec, String image, String etatRec) {
        this.idRec = idRec;
        this.dateRec = dateRec;
        this.descriptionRec = descriptionRec;
        this.image = image;
        this.etatRec = etatRec;
    }
    
    public Reclamation(String dateRec, String descriptionRec, String image, String etatRec) {
        this.dateRec = dateRec;
        this.descriptionRec = descriptionRec;
        this.image = image;
        this.etatRec = etatRec;
    }
    
    public Reclamation(int idRec) {
        this.idRec = idRec;
    }

    public int getIdRec() {
        return idRec;
    }

    public void setIdRec(int idRec) {
        this.idRec = idRec;
    }

    public String getDateRec() {
        return dateRec;
    }

    public void setDateRec(String dateRec) {
        this.dateRec = dateRec;
    }

    public String getDescriptionRec() {
        return descriptionRec;
    }

    public void setDescriptionRec(String descriptionRec) {
        this.descriptionRec = descriptionRec;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEtatRec() {
        return etatRec;
    }

    public void setEtatRec(String etatRec) {
        this.etatRec = etatRec;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "idRec=" + idRec + ", dateRec=" + dateRec + ", descriptionRec=" + descriptionRec + ", image=" + image + ", etatRec=" + etatRec + '}';
    }
    
}
