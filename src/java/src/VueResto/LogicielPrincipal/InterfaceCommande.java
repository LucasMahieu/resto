package VueResto.LogicielPrincipal;
import VueResto.*;
import ModeleResto.*;
import javax.swing.*;
import java.awt.event.*;

public class InterfaceCommande extends ObservateurCommande{

	private JPanel panelCommande;
	
	public InterfaceCommande(){
		this.panelCommande = new JPanel();
	}
	
	public JPanel getPanel(){
		return this.panelCommande;
	}

	public void miseAJour(){
	}
}
