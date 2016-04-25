package VueResto.InterfaceTextReservation;
import VueResto.*;
import java.util.Scanner;

import java.util.Observable;

public class InterfaceTextReservation extends Observateur{

	private String label;
	private String nom;
	private String prenom;
	private String nbPersonnes;
	private String service;
	private String date;
	//protected ControllerSujet controller;
	private Scanner sc = new Scanner(System.in);
	private String personneFormulation;
	private String serviceFormulation;
	
	public InterfaceTextReservation() {
		this.label = label;
		//this.controller = controller;
		//model.addObserver(this);
	}
	
	public void reserver() {
		sc.reset();
		choixNom();
		choixPrenom();
		choixNbPersonne();
		choixDate();
		choixService();
		recapitulation();	

		System.out.println("Valider la reservation : Oui/Non");
		String validation = sc.nextLine();
		if (validation.toLowerCase().equals("oui")) {}
		
		else if (validation.toLowerCase().equals("non")) {
			System.out.println(" (1) Refaire la réservation (celle-ci sera annulée)");
			System.out.println(" (2) Modifier le nom/prenom");
			System.out.println(" (3) Modifier la date et/ou le type de service");
			System.out.println(" (4) Modifier le nombre de personne(s)");
		
			String choix = new String();
			while(!(choix.equals("1") || choix.equals("2") || choix.equals("3") || 
			choix.equals("4"))) {
				choix = sc.nextLine(); 
				switch(choix) {
					case "1": reserver();
						break;
					case "2": choixNom(); choixPrenom(); System.out.println(choix);
						break;
					case "3": choixDate(); choixService();
						break;
					case "4": choixNbPersonne();
						break;
					default:
						System.out.println("Veuillez entrer un entier entre 1 et 4");
						break;
				}
			}
		}
		
		else {
			reserver();
		}				
	}
			
	
	public void choixNom() {
		System.out.println("A quel nom souhaitez-vous faire une reservation ?");
		System.out.println("Nom :");
		nom = sc.nextLine();
	}
	
	public void choixPrenom() {	
		System.out.println("Prenom :");
		prenom = sc.nextLine();
	}
	
	public void choixNbPersonne() {
		System.out.println("Combien y'aura-t-il de personnes ?");
		nbPersonnes = sc.nextLine();
		if(Integer.parseInt(nbPersonnes) > 1) {
			personneFormulation = " personnes";
		}
		else if(nbPersonnes == "1") {
			personneFormulation = " personne";
		}
		
		else { 
			System.out.print("Veuillez rentrer un nombre positif");
			choixNbPersonne();
		}
	}
	
	public void choixDate() {	
		System.out.println("A quelle date souhaitez-vous reserver JJ/MM/AAAA ?");
		date = sc.nextLine();
	}
	
	public void choixService() {
		System.out.println("le midi ou le soir ? (entrez midi ou soir)");
		service = sc.nextLine();
		
		if(service.toLowerCase().equals("midi")) {
			serviceFormulation = " à midi ";
		}
		else if(service.toLowerCase().equals("soir")) {
			serviceFormulation = " au soir ";
		}
		else {
			System.out.println("Veuillez choisir entre midi et soir");
			choixService();
		}
	}
	
	public void choixLocalisation() {
	
	}
	
	public void recapitulation() {
			System.out.println("Vous avez reservé au nom de "+ nom + " " + prenom + " le " + date + serviceFormulation + " pour " + nbPersonnes + personneFormulation);
	}
	
	public void update(Observable o, Object arg) {

	}
}
