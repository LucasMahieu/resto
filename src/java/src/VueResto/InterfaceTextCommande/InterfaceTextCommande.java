package VueResto.InterfaceTextCommande;
import VueResto.*;
import java.lang.System;

public class InterfaceTextCommande extends ObservateurCommande {
	ControleurSujet controler;

	public InterfaceTextCommande(ControleurSujet controler){
	this.controler= controler;
	}

	public void miseAJour(){
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

		}
		else if (b.intValue()==2){// on produit la facture
			Sytem.out.println("choississez le numéro de réservations pour récupérer la facture");
			System.in.read(b);
			controler.checkerFacture(b.intValue());
		}

	}
}
