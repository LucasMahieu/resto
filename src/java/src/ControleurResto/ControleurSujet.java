package ControleurResto.SujetControleur;

import VueResto.*;
import java.util.*;

public abstract class ControleurSujet {

	public ControleurSujet(){
      
	}

	public void checkerFacture(String client){
        Facture factureFinale = new Facture();

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

    public LinkedList<String> getListeArticles(String type)
    {
        LinkedList<String> resultat = new LinkedList<String>();

        ResultSet rset = getArticles(null, -1, null, type);
        while(rset.next())
        {
            resultat.add(rset.getString(1));
        }
        return resultat;
    }

    public float getPrixArticle(String nomArticle)
    {
        LinkedList<String> resultat = new LinkedList<String>();

        ResultSet rset = getArticles(nomArticle, -1, null, null);
        while(rset.next())
        {
            resultat.add(rset.getFloat(2));
        }
        return resultat;
    }
}
