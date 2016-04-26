package VueResto.LogicielPrincipalText;
import VueResto.*;
import ControleurResto.*;
import java.lang.*;
import java.util.Observable;
import java.util.Scanner;


public class InterfaceTextCommande extends Observateur{

	public InterfaceTextCommande(){
	}
	Scanner sc = new Scanner(System.in);
	int table;
	public void commander(){
		System.out.println("Voici les différentes fonctions possibles:");
		System.out.println("(1) Prendre une commande individuel");
		System.out.println("(2) Produire la facture d'une commande");
		String choix = sc.nextLine();
		

		if(choix.equals("1")){// on prend  une commande
			System.out.println("Veuillez indiquer le numéro de table de la commande");
			table = Integer.parseInt(sc.nextLine());
			String choix2 = "1";
			while(true) {
				ajouterPlat();
				System.out.println("(1) Continuer la commande");
				System.out.println("(2) Terminer la commande");
				choix2 = sc.nextLine();
				if(choix2.equals("2"))  
					break;
				else if (choix2.equals("1")) 
					continue;
				else {
					System.out.println("Veuillez entrer 1 ou 2 au clavier");
					System.out.println("(1) Continuer la commande");
					System.out.println("(2) Terminer la commande");
					choix2 = sc.nextLine();
				}
				
			}
		}
		else if (choix.equals("2")){// on produit la facture
			System.out.println("Choississez le nom du client pour récupérer la facture");
			String str= sc.nextLine();
			Controleur.get().creerFacture(str);
		}
		
		else {
			System.out.println("Veuillez entrer 1 ou 2 au clavier");
			commander();		
		}

	}
	
	public void  ajouterPlat() {
			System.out.println("Veuillez indiquer le plat commandé");
			/*
			System.out.println("(1) Entrée");
			System.out.println("(2) Plat principal");
			System.out.println("(3) Dessert");
			System.out.println("(4) Boisson");
			System.out.println("(5) Menu");
			*/
			String plat = sc.nextLine();
			
			System.out.println("Veuillez indiquer la quantité de ce plat commandé");
			int quantite = Integer.parseInt(sc.nextLine());
			
			
			Controleur.get().ajouterArticle(plat, quantite, 
				Controleur.get().getNumeroReservation(table));
	}
	public void update(Observable o, Object arg) {

	}
}
