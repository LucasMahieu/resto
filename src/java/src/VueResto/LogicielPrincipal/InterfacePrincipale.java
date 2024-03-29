package VueResto.LogicielPrincipal;
import VueResto.*;
import VueResto.LogicielPrincipal.*;
import ControleurResto.*;
import ModeleResto.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import javax.swing.table.*;

public class InterfacePrincipale extends JFrame implements ActionListener {

	private InterfaceCommande interfaceCommande; // observateur disposant d'un panel 
	private InterfaceReservation interfaceReservation; // observateur disposant d'un panel 
	private InterfaceSuiviCommande interfaceSuiviCommande; // observateur disposant d'un panel 

	public InterfacePrincipale(){
		super("La bonne fourchettée");
		addWindowListener( new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				ReservationFactoryConcrete.get().close();
				System.exit(0);
			}
		}
		);

		JPanel panelPrincipal = new JPanel(); // panneau d'interface principale
		JTabbedPane onglets = new JTabbedPane(SwingConstants.TOP);

		this.interfaceCommande = new InterfaceCommande();
		onglets.addTab("Commande",this.interfaceCommande.getPanel());

		this.interfaceReservation = new InterfaceReservation();
		onglets.addTab("Reservation",this.interfaceReservation.getPanel());

		this.interfaceSuiviCommande = new InterfaceSuiviCommande();
		onglets.addTab("Suivi Commande",this.interfaceSuiviCommande.getPanel());

		panelPrincipal.add(onglets);
		onglets.setOpaque(true);

		this.setContentPane(panelPrincipal);
		this.setSize(1000,1000);


		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		JMenu menu = new JMenu("Fichier");
		JMenuItem menuItem = new JMenuItem("MenuItem");
		menu.add(menuItem);
		menuBar.add(menu);
		this.setVisible(true);
		this.interfaceCommande.activeListener(this);
		this.interfaceReservation.activeListener(this);
		this.interfaceSuiviCommande.activeListener(this);
	}
	public InterfaceCommande getInterfaceCommande(){
		return this.interfaceCommande;
	}
	public InterfaceReservation getInterfaceReservation(){
		return this.interfaceReservation;
	}
	public InterfaceSuiviCommande getInterfaceSuiviCommande(){
		return this.interfaceSuiviCommande;
	}

	@Override
	public void actionPerformed(ActionEvent e){
		Object source = e.getSource();
		/* RESERVATION */
		if(source == interfaceReservation.getBoutonReservation()){
			System.out.println("Bouton de Reservation");
			String message = "";
			if ( interfaceReservation.getTexteNomReservation().getText().equals("") 
					|| interfaceReservation.getTexteTelephoneReservation().getText().equals("")){

				System.out.println("Erreur Reservation");
				JOptionPane.showMessageDialog(this,"Des champs obligatoires n'ont pas été remplis",
						"Erreur Reservation",JOptionPane.ERROR_MESSAGE);
			}else{
				message = interfaceReservation.getTexteNomReservation().getText() + " "
					+ interfaceReservation.getTexteTelephoneReservation().getText() + " "
					+ interfaceReservation.getSpinnerNombrePersonnes().getValue() + " "
					+ new SimpleDateFormat("dd/MM/yyyy").format(interfaceReservation.getSpinnerDate().getValue()) + " "
					+ interfaceReservation.getComboBoxService().getSelectedItem() 
					+ " " + interfaceReservation.getTexteLocalisation().getText();
				System.out.println(message);
				int resa = Controleur.get().creerReservation(
						interfaceReservation.getTexteNomReservation().getText(),
						new SimpleDateFormat("dd/MM/yyyy").format(interfaceReservation.getSpinnerDate().getValue()),
						interfaceReservation.getComboBoxService().getSelectedItem().toString(),
						Integer.parseInt(interfaceReservation.getSpinnerNombrePersonnes().getValue().toString()),
						interfaceReservation.getTexteLocalisation().getText(),
						interfaceReservation.getTexteTelephoneReservation().getText()
						);
				System.out.println("resa=" + resa);
				if(resa<0){
					System.out.println("Erreur Réservation");
					JOptionPane.showMessageDialog(this,"La Réservation a échoué",
							"Erreur Réservation",JOptionPane.ERROR_MESSAGE);
				}else if(resa==0){
					System.out.println("Le resto est plein");
					JOptionPane.showMessageDialog(this,"Le resto est full, A+",
							"Le resto est plein",JOptionPane.INFORMATION_MESSAGE);
				}else{
					System.out.println("Réservation réussie");
					// Faire la requete pour savoir quelle table et attribuée
					LinkedList<Integer> tables;
					tables = Controleur.get().getNumeroTables(resa);
					String tablesString = "";
					if (tables != null) {
						tablesString = tables.toString();
					}
					JOptionPane.showMessageDialog(this
							,"La Réservation a réussi,\n"
							+"la réservation a le n°"+resa 
							+", la table n°"+tablesString+" lui est réservée"
							,"Réservation réussite",JOptionPane.INFORMATION_MESSAGE
							);
				}
			}
		}else if ( source == interfaceReservation.getBoutonSupprimer()){
			System.out.println("Bouton Supprimer");
			interfaceReservation.effetBoutonSupprimer();
		}else if ( source == interfaceReservation.getBoutonActualiser()){
			System.out.println("Bouton Actualiser");
			interfaceReservation.effetBoutonActualiser();

			/* COMMANDE */
		}else if(source == interfaceCommande.getButtonFacture()){
			System.out.println("Bouton Facturation");
			Controleur.get().editerFacture(Controleur.get().getNumResaCmdSelectionne());
		}else if(source == interfaceCommande.getButtonAjout()){
			String message = "";
			message = "ajout de " + interfaceCommande.getSpinnerQuantite().getValue() + " ";
			if( interfaceCommande.getTabbedPaneArticle().getSelectedComponent() == interfaceCommande.getPanelBoisson()){
				System.out.println("Bouton d'Ajout d'une boisson");
				interfaceCommande.ajouterArticlesSelectionnes(interfaceCommande.getButtonArticleBoisson());
			}
			if( interfaceCommande.getTabbedPaneArticle().getSelectedComponent() == interfaceCommande.getPanelEntree()){
				System.out.println("Bouton d'Ajout d'une Entrée");
				interfaceCommande.ajouterArticlesSelectionnes(interfaceCommande.getButtonArticleEntree());
			}
			if( interfaceCommande.getTabbedPaneArticle().getSelectedComponent() == interfaceCommande.getPanelPlat()){
				System.out.println("Bouton d'Ajout d'un Plat");
				interfaceCommande.ajouterArticlesSelectionnes(interfaceCommande.getButtonArticlePlat());
			}
			if( interfaceCommande.getTabbedPaneArticle().getSelectedComponent() == interfaceCommande.getPanelDessert()){
				System.out.println("Bouton d'Ajout d'un Dessert");
				interfaceCommande.ajouterArticlesSelectionnes(interfaceCommande.getButtonArticleDessert());
			}
			if( interfaceCommande.getTabbedPaneArticle().getSelectedComponent() == interfaceCommande.getPanelMenu()){
				System.out.println("Bouton d'Ajout d'un Menu");
				interfaceCommande.ajouterArticlesMenuSelectionnes(interfaceCommande.getButtonArticleMenu());
			}
			System.out.println(message);
			interfaceCommande.setSelectedButtonArticle(false);
			interfaceCommande.createNewRecap(Controleur.get().getNumResaCmdSelectionne());

		}else if(source == interfaceCommande.getButtonSuppression()){
			String message = "";
			message = "suppression de " + interfaceCommande.getSpinnerQuantite().getValue() + " ";
			if( interfaceCommande.getTabbedPaneArticle().getSelectedComponent() == interfaceCommande.getPanelBoisson()){
				System.out.println("Bouton de Suppression d'une Boisson");
				interfaceCommande.supprimerArticlesSelectionnes(interfaceCommande.getButtonArticleBoisson());
			}
			if( interfaceCommande.getTabbedPaneArticle().getSelectedComponent() == interfaceCommande.getPanelEntree()){
				System.out.println("Bouton de Suppression d'une Entrée");
				interfaceCommande.supprimerArticlesSelectionnes(interfaceCommande.getButtonArticleEntree());
			}
			if( interfaceCommande.getTabbedPaneArticle().getSelectedComponent() == interfaceCommande.getPanelPlat()){
				System.out.println("Bouton de Suppression d'un Plat");
				interfaceCommande.supprimerArticlesSelectionnes(interfaceCommande.getButtonArticlePlat());
			}
			if( interfaceCommande.getTabbedPaneArticle().getSelectedComponent() == interfaceCommande.getPanelDessert()){
				System.out.println("Bouton de Suppression d'un Dessert");
				interfaceCommande.supprimerArticlesSelectionnes(interfaceCommande.getButtonArticleDessert());
			}
			if( interfaceCommande.getTabbedPaneArticle().getSelectedComponent() == interfaceCommande.getPanelMenu()){
				System.out.println("Bouton de Suppression d'un Menu");
				interfaceCommande.supprimerArticlesSelectionnes(interfaceCommande.getButtonArticleMenu());
			}
			System.out.println(message);
			interfaceCommande.setSelectedButtonArticle(false);
			interfaceCommande.createNewRecap(Controleur.get().getNumResaCmdSelectionne());

		}else if(source == interfaceCommande.getButtonRecherche()){
			if ( interfaceCommande.getTextFieldNTable().getText().equals("") ){
				System.out.println("Erreur Recherche Commande");
				JOptionPane.showMessageDialog(this,"La recherche ne peut aboutir sans aucun paramètre\n BOUGRRRR !!!",
						"Erreur Recherche Commande",JOptionPane.ERROR_MESSAGE);
			}else{
				// Dans ce else on peut aboutir à une resa
				int numResa = 0;
				int numTable=0;
				try{
					numTable = Integer.parseInt(interfaceCommande.getTextFieldNTable().getText());
				}catch ( NumberFormatException n){
					System.out.println("Erreur Recherche Commande");
					JOptionPane.showMessageDialog(this,"La recherche ne peut aboutir\n"
							+"Le numéro de table entré ("+interfaceCommande.getTextFieldNTable().getText()
							+") n'est pas un nombre",
							"Erreur Recherche Commande",JOptionPane.ERROR_MESSAGE);
				}
				numResa = Controleur.get().getNumeroReservation(numTable);

				Controleur.get().setNumResaCmdSelectionne(numResa);
				interfaceCommande.createNewRecap(numResa);
				System.out.println("Bouton de Recherche de réservation");
				String message = "";
				message = "Table n°"+interfaceCommande.getTextFieldNTable().getText()
					+ " n° de resa = " + numResa;
				System.out.println(message);
			}
			/* SUIVI COMMANDE */
		}else if(source == interfaceSuiviCommande.getButtonRechercheSuivi()){
			System.out.println("Bouton RechercherSuiviCommande");		
			interfaceSuiviCommande.effetBoutonRechercheSuivi();
		}else if (source == interfaceSuiviCommande.getButtonOuvrir()){
			System.out.println("Bouton ouvrir suiviCommande");		
			interfaceSuiviCommande.effetBoutonOuvrir();
		}else if (source == interfaceSuiviCommande.getButtonFermer()){
			System.out.println("Bouton fermer suiviCommande");		
			interfaceSuiviCommande.effetBoutonFermer();
		}
		else if (source == interfaceSuiviCommande.getButtonEnvoyer()){
			System.out.println("Bouton Envoyer suiviCommande");		
			interfaceSuiviCommande.effetBoutonEnvoyer();
		}
		else{
			for(JToggleButton jt : interfaceCommande.getButtonArticleMenu()){
				if(source == jt){
					interfaceCommande.updateComboBoxMenu(jt.getText());
				}
			}
		}
	}
}
