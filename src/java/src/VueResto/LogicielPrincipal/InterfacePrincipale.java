package VueResto.LogicielPrincipal;
import VueResto.*;
import VueResto.LogicielPrincipal.*;
import ControleurResto.*;
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
		super("La bonne fourchetté");
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
					JOptionPane.showMessageDialog(this,"La Réservation a échouée",
						"Erreur Réservation",JOptionPane.ERROR_MESSAGE);
				}else if(resa==0){
					System.out.println("Le resto est plein");
					JOptionPane.showMessageDialog(this,"Le resto est full, A+",
						"Le resto est plein",JOptionPane.INFORMATION_MESSAGE);
				}else{
					System.out.println("Réservation réussite");
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
		/* COMMANDE */
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
				interfaceCommande.ajouterArticlesSelectionnes(interfaceCommande.getButtonArticleMenu());
			}
			System.out.println(message);
			interfaceCommande.setSelectedButtonArticle(false);
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
		}else if(source == interfaceCommande.getButtonRecherche()){
			if ( interfaceCommande.getTextFieldNTable().getText().equals("") && interfaceCommande.getTextFieldNom().getText().equals("")){
				System.out.println("Erreur Recherche Commande");
				JOptionPane.showMessageDialog(this,"La recherche ne peut aboutir sans aucun paramètre\n BOUGRRRR !!!",
						"Erreur Recherche Commande",JOptionPane.ERROR_MESSAGE);
			}else{
				// Dans ce else on peut aboutir à une resa
				int numResa = 0;
				int numTable=0;
				if(interfaceCommande.getTextFieldNTable().getText().equals("")){
					numResa = Controleur.get().getNumeroReservation(interfaceCommande.getTextFieldNom().getText());
				}else{
					try{
						numTable = Integer.parseInt(interfaceCommande.getTextFieldNTable().getText());
					}catch ( NumberFormatException n){
						System.out.println("Erreur Recherche Commande");
						JOptionPane.showMessageDialog(this,"La recherche ne peut aboutir\n"
							+"Le numéro de table entré ("+interfaceCommande.getTextFieldNTable().getText()
							+") n'est pas un nombre"
							+"\nBOUGRRRR !!!","Erreur Recherche Commande",JOptionPane.ERROR_MESSAGE);
					}
					numResa = Controleur.get().getNumeroReservation(numTable);
				}
				Controleur.get().setNumResaCmdSelectionne(numResa);
				interfaceCommande.createNewRecap(numResa);
				interfaceCommande.getPanelCommande().updateUI();
				System.out.println("Bouton de Recherche de réservation");
				String message = "";
				message = interfaceCommande.getTextFieldNTable().getText()
					+" "+interfaceCommande.getTextFieldNom().getText() + " n°" + numResa;
				System.out.println(message);
			}
		/* SUIVI COMMANDE */
		}else if(source == interfaceSuiviCommande.getButtonRechercheSuivi()){
			if ( interfaceSuiviCommande.getTextFieldNTable().getText().equals("") 
					&& interfaceSuiviCommande.getTextFieldNom().getText().equals("")){
				System.out.println("Erreur Recherche Commande");
				JOptionPane.showMessageDialog(this,"La recherche ne peut aboutir sans aucun paramètre\n BOUGRRRR !!!",
						"Erreur Recherche Commande",JOptionPane.ERROR_MESSAGE);
			}else{
				// Dans ce else on peut aboutir à une resa

				Object[] o = {"llllllllll","111","000","ppp"};
				((DefaultTableModel)interfaceSuiviCommande.getTableau().getModel()).addRow(o);
			}
		}else if (source == interfaceSuiviCommande.getButtonOuvrir()){
		
		}else if (source == interfaceSuiviCommande.getButtonFermer()){
		
		}
	}
}
