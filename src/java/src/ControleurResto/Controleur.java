package ControleurResto;

import VueResto.*;
import ModeleResto.*;
import java.util.*;

/**
 * Tous les attribue de cette classe doivent etre static !!!!!!!!
 * De la sorte, chaque inteface pourra avoir un attribut controleur 
 * et les informations, états, variables du controleur sera cohérent 
 * entre toutes les autres classes
 *
 */
public class Controleur{

	private static ReservationFactory reservationFactory;

	public Controleur(){
		this.reservationFactory = new ReservationFactoryConcrete();
	}

	public void creerFacture(String client){
       // Facture factureFinale = new Facture();

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
		if (type == "boisson"){
			resultat.add("Fanta");
			resultat.add("Vin-chaud");
			resultat.add("Coca");
			resultat.add("Vin-Blanc");
			resultat.add("perrier");
			resultat.add("champagneeee");
			resultat.add("Limonade");
			resultat.add("Duvel");
			resultat.add("Hougarden");
			resultat.add("Vodka");
			resultat.add("Wisky");
			resultat.add("Grenadine");
			resultat.add("Lait-fraise");
			resultat.add("Jus de papaye");
			resultat.add("eau de vie");
			resultat.add("sirop de caoutchou");
			resultat.add("infusion de chaussettes");
		}else if (type=="entree"){
			resultat.add("salade");
			resultat.add("chevre chaud");
			resultat.add("foie gras");
			resultat.add("charcuterie");
			resultat.add("surimi");
			resultat.add("bouillabesse");
			resultat.add("os à moelle");
			resultat.add("oeuf de caille");
			resultat.add("frillant au bleu");
			resultat.add("taboulet");
		}else if(type=="plat"){
			resultat.add("langue de boeuf");
			resultat.add("knaki");
			resultat.add("poulet rotis");
			resultat.add("couscous");
			resultat.add("raclette");
			resultat.add("fondu");
			resultat.add("plat du jour");
			resultat.add("steak frite");
			resultat.add("bolognaire");
			resultat.add("carbonara");
			resultat.add("sanglier mariné");
			resultat.add("poulpe");
			resultat.add("choux farcie");
			resultat.add("rognon");
			resultat.add("choucroutte");
			resultat.add("cassoulet");
			resultat.add("pizza");
		}else if (type=="dessert"){
			resultat.add("Ils-flottante");
			resultat.add("yaourt");
			resultat.add("glace");
			resultat.add("fruit");
			resultat.add("gaateau");
			resultat.add("chaucolot");
		}else if (type=="menu"){
			resultat.add("Chef");
			resultat.add("Maitre");
			resultat.add("Tourista");
		}
		/*
		   ResultSet rset = getArticles(null, -1, null, type);
		   while(rset.next())
        {
            resultat.add(rset.getString(1));
        }
*/
        return resultat;
    }

    public float getPrixArticle(String nomArticle)
    {
        //ResultSet rset = getArticles(nomArticle, -1, null, null);
        //rset.getFloat(2);

        //return resultat;
		return (float)10.0;
    }

	public int getNumeroReservation(String date, String nTable, String service){
		return 123456;
	}
	public String getNumeroTables(int numResa){
		return "10-11-12";
	}
	public String getNom(int numResa) {
		return "M. Dieudo";
	}

	public HashMap<String,Integer> getArticlesCommandes(int numResa){
		HashMap<String,Integer> h = new HashMap<String,Integer>();
		h.put("Menu du roi",1);
		h.put("Quenelles Farcies", 2);
		h.put("Hugarden", 100);
		h.put("Menu Tourista",1);
		h.put("Frite",7);
		return h;
	}
}
