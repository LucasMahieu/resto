package ControleurResto.SujetControleur;

import VueResto.*;
import java.util.*;

public abstract class ControleurSujet {

	public ControleurSujet(){
      
	}

	public void checkerFacture(int numeroFacture){
        Facture factureFinale = new Facture();
        if(factureFinale.existe(numeroFacture)){
            factureFinale.getFacture(numeroFacture);
        }

    public void passerReservation(String nom, String prenom, int nbPersonnes, String date, String service, String localisation){
      //Vérification des disponibilités des tables
      //si ok:
      //Appel à la création de réservation dans la BD

    }

    public void modifierReservation(String nom, String prenom, int nbPersonnes, String date, String service, String localisation){
      //Vérification de l'existence de la réservation 
      //Vérification des disponibilités des tables
      //si ok:
      //Appel à la modification de réservation dans la BD

    }

    public void supprimerReservation(String nom, String prenom, int nbPersonnes, String date, String service, String localisation){
      //Vérification de l'existence de la réservation 
      //si ok:
      //Appel à la suppression de réservation dans la BD

    }

}
