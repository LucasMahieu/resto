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
		super("La bonne fourchette");
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
		interfaceReservation.getBoutonReservation().addActionListener(this);
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
            if ( interfaceReservation.getTexteNomReservation().getText().equals("") || interfaceReservation.getTextePrenomReservation().getText().equals("")){

              System.out.println("Erreur Reservation");
              JOptionPane.showMessageDialog(this,"Des champs obligatoires n'ont pas été remplis","Erreur Reservation",JOptionPane.ERROR_MESSAGE);
            }
            else {
              message = interfaceReservation.getTexteNomReservation().getText() + " "
                + interfaceReservation.getTextePrenomReservation().getText() + " "
				+ interfaceReservation.getSpinnerNombrePersonnes().getValue() + " "
				+ new SimpleDateFormat("dd-MM-yyyy").format(interfaceReservation.getSpinnerDate().getValue()) + " "
				+ interfaceReservation.getComboBoxService().getSelectedItem() + " " + interfaceReservation.getTexteLocalisation().getText();
              System.out.println(message);
            }
		}
		if(source == interfaceReservation.getBoutonReservation()){

		}
	}

}
