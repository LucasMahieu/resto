package VueResto.LogicielPrincipalText;
import VueResto.*;
import ControleurResto.*;
import java.lang.*;
import java.util.Observable;
import java.util.Scanner;


public class InterfaceTextCommande extends Observateur{

	public InterfaceTextCommande(){
	}

	public void commander(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Voici les différentes fonctions possibles:");
		System.out.println("1)Prendre une commande individuel");
		System.out.println("2)Produire la facture d'une commande");
		System.out.println("choissisez entre 1 et 2");
		int b = sc.nextInt();
		while (b!=1 || b!=2){
			System.out.println("mauvaise valeur : ");
			System.out.println("Voici les différentes fonctions possibles:");
			System.out.println("1)Prendre une commande individuel");
			System.out.println("2)Produire la facture d'une commande");
			System.out.println("choissisez entre 1 et 2");
			b = sc.nextInt();
		}

		if(b==1){// on prend  une commande
			System.out.println("veuillez indiquer le numéro de table de la commande");
			int table = sc.nextInt();
			System.out.println("veuillez indiquer le plat commandé");
			String plat = sc.nextLine();
			System.out.println("veuiller indiquer la quantité de ce plat commandé");
			int quantite = sc.nextInt();
			Controleur.get().ajouterArticle(plat,quantite,Controleur.get().getNumeroReservation(table));
		}
		else if (b==2){// on produit la facture
			System.out.println("choississez le nom du client pour récupérer la facture");
			String str= sc.nextLine();
			Controleur.get().creerFacture(str);
		}

	}

	public void update(Observable o, Object arg) {

	}
}