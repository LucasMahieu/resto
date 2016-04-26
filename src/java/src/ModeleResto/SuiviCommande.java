package ModeleResto;

import java.util.*;

public class SuiviCommande {

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
        etatCommande = new String("BOISSON");
    }

    public String getEtatCommande() {
        return this.etatCommande;
    }

    public int estEnvoye(String type, String nomArticle, int quantite) {
        Integer previous = 0;
        if (type == "BOISSON") {
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
            else if (previous == quantite) {
                if (!next()) {
                    return -1;
                }
            }
            return 0;
        }
        else if (type == "ENTREE") {
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
            else if (previous == quantite) {
                if (!next()) {
                    return -1;
                }
            }
            return 0;
        }
        else if (type == "PLAT") {
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
            else if (previous == quantite) {
                if (!next()) {
                    return -1;
                }
            }
            return 0;
        }
        else if (type == "DESSERT") {
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
        if (type == "BOISSON") {
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
        else if (type == "ENTREE") {
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
        else if (type == "PLAT") {
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
        else if (type == "DESSERT") {
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
        if (type == "BOISSON") {
            previous = boissons.remove(nomArticle);
            if (previous == null) {
                boissons.put(nomArticle, quantite);
            }
            else {
                boissons.put(nomArticle, previous + quantite);
            }
            return 0;
        }
        else if (type == "ENTREE") {
            previous = entrees.remove(nomArticle);
            if (previous == null) {
                entrees.put(nomArticle, quantite);
            }
            else {
                entrees.put(nomArticle, previous + quantite);
            }
            return 0;
        }
        else if (type == "PLAT") {
            previous = plats.remove(nomArticle);
            if (previous == null) {
                plats.put(nomArticle, quantite);
            }
            else {
                plats.put(nomArticle, previous + quantite);
            }
            return 0;
        }
        else if (type == "DESSERT") {
            previous = desserts.remove(nomArticle);
            if (previous == null) {
                desserts.put(nomArticle, quantite);
            }
            else {
                desserts.put(nomArticle, previous + quantite);
            }
            return 0;
        }
        else {
            return -1;
        }
    }

    public boolean next() {
        if (etatCommande == "BOISSON") {
            etatCommande = "ENTREE";
            return true;
        }
        else if (etatCommande == "ENTREE") {
            etatCommande = "PLAT";
            return true;
        }
        else if (etatCommande == "PLAT") {
            etatCommande = "DESSERT";
            return true;
        }
        else if (etatCommande == "DESSERT") {
            return false;
        }
        else {
            return false;
        }
    }

    public HashMap<String, Integer> aEnvoyer() {
        if (etatCommande.equals("BOISSON")) {
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
