/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package houssem.entities;

/**
 *
 * @author houss
 */
public class ReservationVelo {
    private int idReservation;
    private String dateDebut;
    private String dateFin;
    private int nbr;
    private float prixr;
    private String idVelo;
    private String idStation;

    public ReservationVelo() {
    }

    public ReservationVelo(int idReservation, String dateDebut, String dateFin, int nbr, float prixr, String idVelo, String idStation) {
        this.idReservation = idReservation;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nbr = nbr;
        this.prixr = prixr;
        this.idVelo = idVelo;
        this.idStation = idStation;
    }

    public ReservationVelo(String dateDebut, String dateFin, int nbr, float prixr, String idVelo, String idStation) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nbr = nbr;
        this.prixr = prixr;
        this.idVelo = idVelo;
        this.idStation = idStation;
    }

    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public int getNbr() {
        return nbr;
    }

    public void setNbr(int nbr) {
        this.nbr = nbr;
    }

    public float getPrixr() {
        return prixr;
    }

    public void setPrixr(float prixr) {
        this.prixr = prixr;
    }

    public String getIdVelo() {
        return idVelo;
    }

    public void setIdVelo(String idVelo) {
        this.idVelo = idVelo;
    }

    public String getIdStation() {
        return idStation;
    }

    public void setIdStation(String idStation) {
        this.idStation = idStation;
    }

    @Override
    public String toString() {
        return "ReservationVelo{" + "idReservation=" + idReservation + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", nbr=" + nbr + ", prixr=" + prixr + ", idVelo=" + idVelo + ", idStation=" + idStation + '}';
    }
}
