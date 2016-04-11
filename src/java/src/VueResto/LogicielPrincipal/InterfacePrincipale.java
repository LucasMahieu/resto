package VueResto.LogicielPrincipal;
import VueResto.*;
import VueResto.LogicielPrincipal.*;
import javax.swing.*;
import java.awt.event.*;

public class InterfacePrincipale extends JFrame{
	private InterfaceCommande interfaceCommande;
	private InterfaceReservation interfaceReservation;

	public InterfacePrincipale(){
		super("La bonne fourchette");
		addWindowListener( new WindowAdapter(){
				public void windowClosing(WindowEvent e){
					System.exit(0);
				}
			}
		);
		JButton buttonOngletResa = new JButton("Reservation");
		JPanel panneau = new JPanel();
		panneau.add(buttonOngletResa);
		setContentPane(panneau);
		setSize(1000,1000);
		setVisible(true);
		this.interfaceCommande = new InterfaceCommande();
		this.interfaceReservation = new InterfaceReservation();
	}

	public InterfaceCommande getInterfaceCommande(){
		return this.interfaceCommande;
	}
	public InterfaceReservation getInterfaceReservation(){
		return this.interfaceReservation;
	}
}

