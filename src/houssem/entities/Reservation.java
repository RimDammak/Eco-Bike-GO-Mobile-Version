/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package houssem.entities;

import java.util.Date;

/**
 * @author houss
 */
public class Reservation {
    private int id_reservation;
    private Date date_debut;
    private Date date_fin;
    private int id_station;   
    private int iduser;        
    private int id_velo;
    private int nbr;
    private double prixr;

    public int getNbr() {
        return nbr;
    }

    public void setNbr(int nbr) {
        this.nbr = nbr;
    }

    public double getPrixr() {
        return prixr;
    }

    public void setPrixr(double prixr) {
        this.prixr = prixr;
    }


    public Reservation() {
    }

    public Reservation(int id_reservation, Date date_debut, Date date_fin, int id_station, int iduser, int id_velo, int nbr, double prixr) {
        this.id_reservation = id_reservation;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.id_station = id_station;
        this.iduser = iduser;
        this.id_velo = id_velo;
        this.nbr = nbr;
        this.prixr = prixr;
    }

    public Reservation(Date date_debut, Date date_fin, int id_station, int iduser, int id_velo, int nbr, double prixr) {
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.id_station = id_station;
        this.iduser = iduser;
        this.id_velo = id_velo;
        this.nbr = nbr;
        this.prixr = prixr;
    }

    public int getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public int getId_station() {
        return id_station;
    }

    public void setId_station(int id_station) {
        this.id_station = id_station;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getId_velo() {
        return id_velo;
    }

    public void setId_velo(int id_velo) {
        this.id_velo = id_velo;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id_reservation=" + id_reservation + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", id_station=" + id_station + ", iduser=" + iduser + ", id_velo=" + id_velo + ", nbr=" + nbr + ", prixr=" + prixr + '}';
    }

}