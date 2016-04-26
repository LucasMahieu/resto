package ModeleResto;

import java.util.*;

public class SuiviCommande {

    private Mangeur grosMangeur;
    private HashMap<String, Integer> boissons;
    private HashMap<String, Integer> entrees;
    private HashMap<String, Integer> plats;
    private HashMap<String, Integer> desserts;
    private String etatCommande;

    public SuiviCommande(){
        boissons = new HashMap<String, Integer>();
        entrees = new HashMap<String, Integer>();
        plats = new HashMap<String, Integer>();
        desserts = new HashMap<String, Integer>();
        etatCommande = new String("BOISSON");
    }

    public String getEtatCommande() {
        return this.etatCommande;
    }

    public int ajouterArticle(String type, String nomArticle, int quantite) {
        return 0;
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

    public HashMap<String, Integer> aEnvoyer(String etat) {
        if (etat == "BOISSON")
            return boissons;
        else if (etat == "ENTREE")
            return entrees;
        else if (etat == "PLAT")
            return plats;
        else if (etat == "DESSERT")
            return desserts;
        else
            return null;
    }
}
