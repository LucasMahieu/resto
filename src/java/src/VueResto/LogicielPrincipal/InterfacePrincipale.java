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

//import javax.swing.*;
//import java.awt.event.*;
//import java.awt.Dimension;
//
//public class InterfacePrincipale extends JFrame {
//    public InterfacePrincipale() {
//        super("Logiciel de Gestion du Restaurant");
//        WindowListener l = new WindowAdapter() {
//            public void windowClosing(WindowEvent e){
//                System.exit(0);
//            }
//        };
//        addWindowListener(l);
//
//        JPanel pannel = new JPanel(); // panneau d'interface principale
//        JTabbedPane onglets = new JTabbedPane(SwingConstants.TOP);
//
//        
//
//        JButton bouton = new JButton("Réservation");
//        JPanel pReservation = new JPanel();
//        pReservation.add(bouton);
//        pReservation.setPreferredSize(new Dimension(300,65));
//        
//        onglets.addTab("Reservation",pReservation);
//
//        JButton bouton2 = new JButton("SuiviCommande");
//        JPanel pSuiviCommande = new JPanel();
//        pSuiviCommande.add(bouton2);
//        pSuiviCommande.setPreferredSize(new Dimension(300,65));
//        
//        onglets.addTab("SuiviCommande",pSuiviCommande);
//
//
//        onglets.setOpaque(true);
//
//        pannel.add(onglets);
//        this.setContentPane(pannel);
//        this.setSize(320,150);
//        this.setVisible(true);
//
//        JMenuBar menuBar = new JMenuBar();
//        this.setJMenuBar(menuBar);
//        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//        JMenu menu = new JMenu("Fichier");
//        JMenuItem menuItem = new JMenuItem("MenuItem");
//        menu.add(menuItem);
//        menuBar.add(menu);
//        this.setVisible(true);
//
//    }
//    public static void main(String [] args){
//        JFrame frame = new InterfacePrincipale();
//
//    }
//}
