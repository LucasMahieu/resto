package VueResto.InterfaceTextCommande;
import VueResto.*;
import java.lang.System;
import java.util.Scanner;


public class InterfaceTextCommande extends ObservateurCommande {
	ControleurSujet controler;
	String clients;
	String
	public InterfaceTextCommande(ControleurSujet controler){
	this.controler= controler;
	}

	public void miseAJour(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Voici les différentes fonctions possibles:");
		System.out.println("1)Prendre une commande individuel");
		System.out.println("2)Produire la facture d'une commande");
		System.out.println("choissisez entre 1 et 2");
		byte  b= new Byte(0);
		System.in.read(b);
		while (b.intValue()!=1 or b.intValue()!=2){
			System.out.println("mauvaise valeur : ");
			System.out.println("Voici les différentes fonctions possibles:");
			System.out.println("1)Prendre une commande individuel");
			System.out.println("2)Produire la facture d'une commande");
			System.out.println("choissisez entre 1 et 2");
			System.in.read(b);
		}

		if(b.intValue()==1){// on prend  une commande
			System.out.println("veuillez indiquer le numéro de table de la commande");
			int table = sc.nextLine();
			System.out.println("veuillez indiquer le plat commandé");
			String plat = sc.nextLine();
			Sytem.out.println("veuiller indiquer la quantité de ce plat commandé");
			int quantite = sc.nextLine();
		}
		else if (b.intValue()==2){// on produit la facture
			Sytem.out.println("choississez le nom du client pour récupérer la facture");
			String str= sc.nextLine();
			controler.checkerFacture(str);
		}

	}
}
