package ModeleResto;

import ControleurResto.*;
import java.io.File;
import java.io.PrintWriter;
import java.io.*;
import java.util.*;

public class Facture {
	private PrintWriter factureOut = null;

	/**
	 * Construire la facture, fait appel à l'editeur , qui ecrit dns le fichier la facture
	 */
	public Facture(int numResa){
		try{
			factureOut = new PrintWriter(new File("./factures/"+numResa+".txt"), "UTF-8");
			editerFacture(numResa);
		}catch (FileNotFoundException e){
			System.err.println("ouverture de fichier facture");
			e.printStackTrace(System.err);
		} catch (UnsupportedEncodingException u){
			System.err.println("ouverture de fichier facture");
			u.printStackTrace(System.err);
		}
	}

	/**
	 * methode priver qui ecirt dans le fichier de sortie la facture
	 */
	private void editerFacture(int numResa){
		//factureOut.println("coucou, c'est moi la facture salée");
		String date = Controleur.get().getDateNow();
		String service = Controleur.get().getServiceNow();

		String fact = "Réservation n°"+numResa+" au nom de "+Controleur.get().getNom(numResa) + "\n";
		LinkedList<Integer> tables = Controleur.get().getNumeroTables(numResa);
		fact += "Table n°"+ (tables == null ? "" : tables.toString()) + "\n";
		fact += "Service du "+date+" au "+service +"\n";
		fact += "--------------------------------------------------- \n";
		int q = 0;
		float somme = 0;
		float prix = 0;
		String a = "";
		HashMap<String,Integer> articlesCommandes = Controleur.get().getArticlesCommandes(numResa);
		Set<String> articles = articlesCommandes.keySet();
		Iterator<String> itArticles = articles.iterator();

		while(itArticles.hasNext()){
			a=itArticles.next();
			q=articlesCommandes.get(a);
			prix = Controleur.get().getPrixArticle(a);
			somme += prix*q;
			fact +=""
					+ String.format("%0$-"+(45)+"s","- (x"+q+") "+a+" ")
					+ prix*q
					+ "€ \n";
		}
		fact += "--------------------------------------------------- \n";
		fact += String.format("%0$-45s","TOTAL = ") + somme + "€ \n";

		fact += "La bonne fourchettée vous remercie de votre visite, à Bientôt \n";
		factureOut.println(fact);
		factureOut.flush();
	}
}
