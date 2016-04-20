package ControleurResto;

import VueResto.*;
import ModeleResto.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.HashMap;
import java.sql.*;
import java.text.SimpleDateFormat;

/**
 * Tous les attribue de cette classe doivent etre static !!!!!!!!
 * De la sorte, chaque inteface pourra avoir un attribut controleur 
 * et les informations, états, variables du controleur sera cohérent 
 * entre toutes les autres classes
 *
 */
public class Controleur{
    private static int numResaCmdSelectionee;
    private static int numResaSuiviSelectionee;
    private static Date date;
    private static final SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
    private static final SimpleDateFormat sdfHeure = new SimpleDateFormat("HH");
    private static String dateNow;
    private static String heureNow;
    private static String serviceNow;
    private static final int DEBUT_SERVICE_SOIR = 17;

    final private static Controleur instanceUnique = new Controleur();

    /* à modifier pour le patron Singleton */
    private Controleur(){
        this.numResaCmdSelectionee = 0;
        this.numResaSuiviSelectionee = 0;
        date = new Date();
        dateNow = sdfDate.format(date);
        heureNow = sdfHeure.format(date);
        serviceNow = "midi";
        if(Integer.parseInt(heureNow) >= DEBUT_SERVICE_SOIR){
            serviceNow = "soir";
        }
    }

    public static Controleur get()
    {
        return instanceUnique;
    }

	/**
	 * Ajoute à la BD la quantité d'article 'nom' à la reservation numResa
	 */
	public void ajouterArticle(String nom, int quantite, int numResa){
		// ajouter à la resa l'article donner avec les bonnes quantités dans la BD
	}
	public void creerFacture(String client){
       // Facture factureFinale = new Facture();
    }

    public String getDateNow(){
        date = new Date();
        dateNow = sdfDate.format(date);
        return this.dateNow;
    }
    public String getHeureNow(){
        date = new Date();
        heureNow = sdfHeure.format(date);
        return this.heureNow;
    }
    public String getServiceNow() {
        serviceNow = "midi";
        if(Integer.parseInt(getHeureNow()) >= DEBUT_SERVICE_SOIR){
            serviceNow = "soir";
        }
        return this.serviceNow;
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
        /*
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
        */
        try {
            ResultSet rset = ReservationFactoryConcrete.get().getArticleBD().getArticle(null, -1, null, type);
            if (rset == null) {
                return resultat;
            }
            while(rset.next())
            {
                resultat.add(rset.getString(1));
            }
            rset.close();
        }
        catch (SQLException e) {
            System.err.println("Erreur pour faire la requête.");
            e.printStackTrace(System.err);
        }
        return resultat;
    }

    public float getPrixArticle(String nomArticle)
    {
        //ResultSet rset = getArticles(nomArticle, -1, null, null);
        //rset.getFloat(2);

        //rset.close();
        //return resultat;
        return (float)10.0;
    }

    public int getNumeroReservation(String date, int nTable, String service){
        return 123456;
    }
    public int getNumeroReservation(String date, String nom, String service){
        return 123456;
    }
    public int getNumeroReservation(String nom){
        return 123456;
    }
    public int getNumeroReservation(int numTable){
        return 123456;
    }
    /**
     * Donne une string comportant toutes les tables associée à une réservation
     * Convention : séparer les numeros par des '-'.
     */
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

    public int getNumResaCmdSelectionne(){
        return this.numResaCmdSelectionee;
    }
    public int getNumResaSuiviSelectionne(){
        return this.numResaSuiviSelectionee;
    }
    public void setNumResaCmdSelectionne(int n){
        this.numResaCmdSelectionee = n;
    }
    public void setNumResaSuiviSelectionne(int n){
        this.numResaSuiviSelectionee = n;
    }
}
