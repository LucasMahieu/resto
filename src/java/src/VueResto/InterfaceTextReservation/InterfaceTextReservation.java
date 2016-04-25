package VueResto.InterfaceTextReservation;
import VueResto.*;
import java.util.*;
import ControleurResto.*;
import java.util.Scanner;

import java.util.Observable;

public class InterfaceTextReservation extends Observateur{

	private String label;
	private String nom;
	private String nbPersonnes;
	private String service;
	private String date;
	private String telephone;
	private String localisation;
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
		choixTelephone();
		choixNbPersonne();
		choixDate();
		choixService();
		recapitulation();	

		System.out.println("Valider la reservation : Oui/Non");
		String validation = sc.nextLine();
		if (validation.toLowerCase().equals("oui")) {
		int reserv = Controleur.get().creerReservation(nom, date, service.toUpperCase(), Integer.parseInt(nbPersonnes), "", telephone);	
				System.out.println("reservation : " + reserv);
				if(reserv<0){
					System.out.println("Erreur Réservation");					
				}else if(reserv==0){
					System.out.println("Le resto est plein");
				}else{
					System.out.println("Réservation réussie");
					// Faire la requete pour savoir quelle table et attribuée
					LinkedList<Integer> tables;
					tables = Controleur.get().getNumeroTables(reserv);
                    String tablesString = "";
                    if (tables != null) {
                        tablesString = tables.toString();
                    }
				}
		}
		
		else if (validation.toLowerCase().equals("non")) {
			System.out.println(" (1) Refaire la réservation (celle-ci sera annulée)");
			System.out.println(" (2) Modifier le nom et le numero de telephone");
			System.out.println(" (3) Modifier la date et/ou le type de service");
			System.out.println(" (4) Modifier le nombre de personne(s)");
		
			String choix = new String();
			while(!(choix.equals("1") || choix.equals("2") || choix.equals("3") || 
			choix.equals("4"))) {
				choix = sc.nextLine(); 
				switch(choix) {
					case "1": reserver();
						break;
					case "2": choixNom(); choixTelephone();
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
	
	public void choixTelephone() {
		System.out.println("Entrez votre numero de telephone svp");
		telephone = sc.nextLine();
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
			System.out.println("Vous avez reservé au nom de "+ nom + " le " + date + serviceFormulation + " pour " + nbPersonnes + personneFormulation + " numero de telephone : " + telephone);
	}
	
	public void update(Observable o, Object arg) {

	}
}
