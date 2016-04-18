import VueResto.*;
import VueResto.InterfaceTextCommande.*;
import VueResto.LogicielPrincipal.*;
import ModeleResto.*;
import ControleurResto.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class Resto{
	public static void main(String[] args) {
		
		Controleur controleurResto = new Controleur();
		JFrame fenetrePrincipale = new InterfacePrincipale(controleurResto);
	}
}
