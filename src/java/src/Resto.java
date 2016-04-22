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
		JFrame fenetrePrincipale = new InterfacePrincipale();

        // Pour fermer la connection à la BD
        // à mettre surement ailleurs.
        /*
        System.out.print("Déconnection de la base de données ... ");
        ReservationFactoryConcrete.get().close();
        System.out.println("Déconnection réussie.");
        */
	}
}
