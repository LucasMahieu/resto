package ModeleResto;

import java.util.*;
import ControleurResto.*;

public class SuiviCommande extends Observable{

    private HashMap<String, Integer> boissons;
    private HashMap<String, Integer> entrees;
    private HashMap<String, Integer> plats;
    private HashMap<String, Integer> desserts;
    private String etatCommande;

    public SuiviCommande() {
        boissons = new HashMap<String, Integer>();
        entrees = new HashMap<String, Integer>();
        plats = new HashMap<String, Integer>();
        desserts = new HashMap<String, Integer>();
        etatCommande = new String("VIDE");
    }

    public String getEtatCommande() {
        refresh();
        System.out.println(etatCommande);
        return this.etatCommande;
    }

    public void refresh() {
        if (!boissons.isEmpty()) {
            etatCommande = "BOISSON";
        }
        if (!entrees.isEmpty()) {
            etatCommande = "ENTREE";
        }
        if (!plats.isEmpty()) {
            etatCommande = "PLAT";
        }
        if (!desserts.isEmpty()) {
            etatCommande = "DESSERT";
        }
    }


    public int estEnvoye(String type, String nomArticle, int quantite) {
        Integer previous = 0;
        if (type.equals("BOISSON")) {
            previous = boissons.remove(nomArticle);
            if (previous == null) {
                return -1;
            }
            else if (previous - quantite > 0) {
                boissons.put(nomArticle, previous - quantite);
            }
            else if (previous - quantite < 0) {
                return -1;
            }
            if (boissons.isEmpty()) {
                refresh();
            }
            return 0;
        }
        else if (type.equals("ENTREE")) {
            previous = entrees.remove(nomArticle);
            if (previous == null) {
                return -1;
            }
            else if (previous - quantite > 0) {
                entrees.put(nomArticle, previous - quantite);
            }
            else if (previous - quantite < 0) {
                return -1;
            }
            if (entrees.isEmpty()) {
                refresh();
            }
            return 0;
        }
        else if (type.equals("PLAT")) {
            previous = plats.remove(nomArticle);
            if (previous == null) {
                return -1;
            }
            else if (previous - quantite > 0) {
                plats.put(nomArticle, previous - quantite);
            }
            else if (previous - quantite < 0) {
                return -1;
            }
            if (plats.isEmpty()) {
                refresh();
            }
            return 0;
        }
        else if (type.equals("DESSERT")) {
            previous = desserts.remove(nomArticle);
            if (previous == null) {
                return -1;
            }
            else if (previous - quantite > 0) {
                desserts.put(nomArticle, previous - quantite);
            }
            else if (previous - quantite < 0) {
                return -1;
            }
            return 0;
        }
        else {
            return -1;
        }
    }

    public int supprimer(String type, String nomArticle, int quantite) {
        Integer previous = 0;
        if (type.equals("BOISSON")) {
            previous = boissons.remove(nomArticle);
            if (previous == null) {
                return -1;
            }
            else if (previous - quantite > 0) {
                boissons.put(nomArticle, previous - quantite);
            }
            else if (previous - quantite < 0) {
                return -1;
            }
            return 0;
        }
        else if (type.equals("ENTREE")) {
            previous = entrees.remove(nomArticle);
            if (previous == null) {
                return -1;
            }
            else if (previous - quantite > 0) {
                entrees.put(nomArticle, previous - quantite);
            }
            else if (previous - quantite < 0) {
                return -1;
            }
            return 0;
        }
        else if (type.equals("PLAT")) {
            previous = plats.remove(nomArticle);
            if (previous == null) {
                return -1;
            }
            else if (previous - quantite > 0) {
                plats.put(nomArticle, previous - quantite);
            }
            else if (previous - quantite < 0) {
                return -1;
            }
            return 0;
        }
        else if (type.equals("DESSERT")) {
            previous = desserts.remove(nomArticle);
            if (previous == null) {
                return -1;
            }
            else if (previous - quantite > 0) {
                desserts.put(nomArticle, previous - quantite);
            }
            else if (previous - quantite < 0) {
                return -1;
            }
            return 0;
        }
        else {
            return -1;
        }
    }

    public int ajouterArticle(String type, String nomArticle, int quantite) {
        Integer previous = 0;
        if (type.equals("BOISSON")) {
            previous = boissons.remove(nomArticle);
            if (previous == null) {
                boissons.put(nomArticle, quantite);
            }
            else {
                boissons.put(nomArticle, previous + quantite);
            }
            refresh();
            return 0;
        }
        else if (type.equals("ENTREE")) {
            previous = entrees.remove(nomArticle);
            if (previous == null) {
                entrees.put(nomArticle, quantite);
            }
            else {
                entrees.put(nomArticle, previous + quantite);
            }
            refresh();
            return 0;
        }
        else if (type.equals("PLAT")) {
            previous = plats.remove(nomArticle);
            if (previous == null) {
                plats.put(nomArticle, quantite);
            }
            else {
                plats.put(nomArticle, previous + quantite);
            }
            refresh();
            return 0;
        }
        else if (type.equals("DESSERT")) {
            previous = desserts.remove(nomArticle);
            if (previous == null) {
                desserts.put(nomArticle, quantite);
            }
            else {
                desserts.put(nomArticle, previous + quantite);
            }
            refresh();
            return 0;
        }
        else {
            return -1;
        }
    }

	/**
	* ajouter l'article au suivi de commande
	*/
    public int ajouterMenu(String  nomBoisson, String nomEntree, String nomPlat, String nomDessert, int quantite) {
        Integer previous = 0;
        previous = boissons.remove(nomBoisson);
        if (previous == null) {
            boissons.put(nomBoisson, quantite);
        }
        else {
            boissons.put(nomBoisson, previous + quantite);
        }
        previous = entrees.remove(nomEntree);
        if (previous == null) {
            entrees.put(nomEntree, quantite);
        }
        else {
            entrees.put(nomEntree, previous + quantite);
        }
        previous = plats.remove(nomPlat);
        if (previous == null) {
            plats.put(nomPlat, quantite);
        }
        else {
            plats.put(nomPlat, previous + quantite);
        }
        previous = desserts.remove(nomDessert);
        if (previous == null) {
            desserts.put(nomDessert, quantite);
        }
        else {
            desserts.put(nomDessert, previous + quantite);
        }
	return 0;
    }

    public HashMap<String, Integer> aEnvoyer() {
        if (etatCommande.equals("BOISSON") || etatCommande.equals("VIDE")) {
            return boissons;
        }
        else if (etatCommande.equals("ENTREE"))
            return entrees;
        else if (etatCommande.equals("PLAT"))
            return plats;
        else if (etatCommande.equals("DESSERT"))
            return desserts;
        else
            return null;
    }
}
