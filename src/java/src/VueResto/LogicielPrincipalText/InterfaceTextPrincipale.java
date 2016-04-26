package VueResto.LogicielPrincipalText;
import VueResto.*;
import VueResto.LogicielPrincipalText.*;
import ControleurResto.*;
import java.util.*;

public class InterfaceTextPrincipale {
	private InterfaceTextCommande interfaceCommande;
	private InterfaceTextReservation interfaceReservation;
	private Scanner sc = new Scanner(System.in);
	
	public InterfaceTextPrincipale() {
		this.interfaceCommande = new InterfaceTextCommande();
		this.interfaceReservation = new InterfaceTextReservation();
	}
	public void lancer() {
		System.out.println("Bienvenue à la bonne fourchettée");
		System.out.println("(1) Réaliser une reservation");
		System.out.println("(2) Réaliser une commande / produire la facture d'une commande");
		System.out.println("(0) Quitter");
		int choix = sc.nextInt();
		if(choix == 1) {
			interfaceReservation.reserver();
		}
		
		else if(choix == 2) {
			interfaceCommande.commander();
		}
		
		else if(choix == 0) {
			System.exit(0);
		}
		
		else {
			System.out.println("Veuillez choisir un chiffre entre 0 et 2");
			lancer();
		}
	
	}

}
