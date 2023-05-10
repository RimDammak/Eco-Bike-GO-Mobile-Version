package houssem.entities;

public class User {

    private int iduser;
    private String nomuser;
    private String prenomuser;
    private String datenaiss;
    private String numtel;
    private String email;
    private String adresse;
    private String imguser;
    private String mdp;
    private String role;
    private int etatcompte;

    public User(int iduser, String nomuser, String prenomuser, String datenaiss, String numtel, String email, String adresse, String imguser, String mdp, String role, int etatcompte) {
        this.iduser = iduser;
        this.nomuser = nomuser;
        this.prenomuser = prenomuser;
        this.datenaiss = datenaiss;
        this.numtel = numtel;
        this.email = email;
        this.adresse = adresse;
        this.imguser = imguser;
        this.mdp = mdp;
        this.role = role;
        this.etatcompte = etatcompte;
    }

    public User() {
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getNomuser() {
        return nomuser;
    }

    public void setNomuser(String nomuser) {
        this.nomuser = nomuser;
    }

    public String getPrenomuser() {
        return prenomuser;
    }

    public void setPrenomuser(String prenomuser) {
        this.prenomuser = prenomuser;
    }

    public String getDatenaiss() {
        return datenaiss;
    }

    public void setDatenaiss(String datenaiss) {
        this.datenaiss = datenaiss;
    }

    public String getNumtel() {
        return numtel;
    }

    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getImguser() {
        return imguser;
    }

    public void setImguser(String imguser) {
        this.imguser = imguser;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getEtatcompte() {
        return etatcompte;
    }

    public void setEtatcompte(int etatcompte) {
        this.etatcompte = etatcompte;
    }

    @Override
    public String toString() {
        return "Promotion{" + "iduser=" + iduser + ", nomuser=" + nomuser + ", prenomuser=" + prenomuser + ", datenaiss=" + datenaiss + ", numtel=" + numtel + ", email=" + email + ", adresse=" + adresse + ", imguser=" + imguser + ", mdp=" + mdp + ", role=" + role + ", etatcompte=" + etatcompte + '}';
    }

    public User(String nomuser, String prenomuser, String datenaiss, String numtel, String email, String adresse, String imguser, String mdp, String role, int etatcompte) {
        this.nomuser = nomuser;
        this.prenomuser = prenomuser;
        this.datenaiss = datenaiss;
        this.numtel = numtel;
        this.email = email;
        this.adresse = adresse;
        this.imguser = imguser;
        this.mdp = mdp;
        this.role = role;
        this.etatcompte = etatcompte;
    }
    
    
}
