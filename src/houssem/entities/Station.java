/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package houssem.entities;

/**
 *
 * @author houss
 */

public class Station {
    private int idStation;
    private String nomStation;
    private String localisationStation;
    private int veloStation;

    public Station() {
    }

    public Station(int idStation, String nomStation, String localisationStation, int veloStation) {
        this.idStation = idStation;
        this.nomStation = nomStation;
        this.localisationStation = localisationStation;
        this.veloStation = veloStation;
    }

    public Station(String nomStation, String localisationStation, int veloStation) {
        this.nomStation = nomStation;
        this.localisationStation = localisationStation;
        this.veloStation = veloStation;
    }

    public int getIdStation() {
        return idStation;
    }

    public void setIdStation(int idStation) {
        this.idStation = idStation;
    }

    public String getNomStation() {
        return nomStation;
    }

    public void setNomStation(String nomStation) {
        this.nomStation = nomStation;
    }

    public String getLocalisationStation() {
        return localisationStation;
    }

    public void setLocalisationStation(String localisationStation) {
        this.localisationStation = localisationStation;
    }

    public int getVeloStation() {
        return veloStation;
    }

    public void setVeloStation(int veloStation) {
        this.veloStation = veloStation;
    }

    @Override
    public String toString() {
        return "Station{" + "idStation=" + idStation + ", nomStation=" + nomStation + ", localisationStation=" + localisationStation + ", veloStation=" + veloStation + '\n';
    }

}
