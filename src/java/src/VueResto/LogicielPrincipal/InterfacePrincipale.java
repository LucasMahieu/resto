package VueResto.LogicielPrincipal;
import VueResto.*;
import VueResto.LogicielPrincipal.*;
import javax.swing.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;

public class InterfacePrincipale extends JFrame implements ActionListener {

	private InterfaceCommande interfaceCommande; // observateur disposant d'un panel 
	private InterfaceReservation interfaceReservation; // observateur disposant d'un panel 
	private InterfaceSuiviCommande interfaceSuiviCommande; // observateur disposant d'un panel 


	public InterfacePrincipale(){
		super("La bonne fourchetté");
		addWindowListener( new WindowAdapter(){
			public void windowClosing(WindowEvent e){
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

		//this.interfaceSuiviCommande = new InterfaceSuiviCommande();
		//onglets.addTab("Suivi Commande",this.interfaceSuiviCommande.getPanel());

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
	}

	public InterfaceCommande getInterfaceCommande(){
		return this.interfaceCommande;
	}
	public InterfaceReservation getInterfaceReservation(){
		return this.interfaceReservation;
	}

	@Override
	public void actionPerformed(ActionEvent e){
		Object source = e.getSource();
		if(source == interfaceReservation.getBoutonReservation()){
			System.out.println("Bouton de Reservation");
			String message = "";
			message = interfaceReservation.getTexteNomReservation().getText() + " "
				+ interfaceReservation.getTextePrenomReservation().getText() + " "
				+ interfaceReservation.getSpinnerNombrePersonnes().getValue() + " "
				+ new SimpleDateFormat("dd-MM-yyyy").format(interfaceReservation.getSpinnerDate().getValue()) + " "
				+ interfaceReservation.getComboBoxService().getSelectedItem() + " " + interfaceReservation.getTexteLocalisation().getText();
			System.out.println(message);
		}else if(source == interfaceCommande.getButtonAjout()){
			System.out.println("Bouton de Ajout");
			String message = "";
			message = interfaceCommande.getSpinnerQuantite().getValue() + " ";
			System.out.println(message);
		}else if(source == interfaceCommande.getButtonRecherche()){
			System.out.println("Bouton de Recherche");
			String message = "";
			message = interfaceCommande.getTextFieldNTable().getText()+" "+interfaceCommande.getTextFieldNom().getText() + " ";
			System.out.println(message);
			
		}
	}

}
