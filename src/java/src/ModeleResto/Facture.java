package ModeleResto;

import java.io.File;
import java.io.PrintWriter;
import java.io.*;

public class Facture {
	private PrintWriter factureOut = null;

	/**
	 * Construire la facture, fait appel à l'editeur , qui ecrit dns le fichier la facture
	 */
	public Facture(int numResa){
		try{
			factureOut = new PrintWriter(new File("./factures/"+numResa+".txt"), "UTF-8");
			editerFacture();
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
	private void editerFacture(){
		factureOut.println("coucou, c'est moi la facture salée");
		factureOut.flush();
	}
}
