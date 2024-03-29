import VueResto.*;
import VueResto.LogicielPrincipalText.*;
import VueResto.LogicielPrincipal.*;
import ModeleResto.*;
import ControleurResto.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class Resto{
	public static void main(String[] args) {
		// Interface Textuelle
		/*
		InterfaceTextPrincipale fenetrePrincipale = new InterfaceTextPrincipale();
		fenetrePrincipale.lancer();
		*/
		
		// Interface Graphique
		JFrame fenetrePrincipale = new InterfacePrincipale(); 
        Controleur.get().setInterface((InterfacePrincipale)fenetrePrincipale);
		ReservationFactoryConcrete.get().getArticleBD().addObserver(((InterfacePrincipale)fenetrePrincipale).getInterfaceCommande());
		ReservationFactoryConcrete.get().getArticleBD().addObserver(((InterfacePrincipale)fenetrePrincipale).getInterfaceSuiviCommande());
		ReservationFactoryConcrete.get().getTableBD().addObserver(((InterfacePrincipale)fenetrePrincipale).getInterfaceSuiviCommande());
		ReservationFactoryConcrete.get().getTableBD().addObserver(((InterfacePrincipale)fenetrePrincipale).getInterfaceReservation());
        //Controleur.get().setInterface((InterfacePrincipale)fenetrePrincipale);
		
		// -------------------------------------------------------- //

        // Pour fermer la connection à la BD
        // à mettre surement ailleurs.
        
        System.out.print("Valider les changements à la BD (0/1) ?");
        Scanner sc = new Scanner(System.in);
        if (sc.nextInt() == 1) {
            System.out.print("Validation des changements ... ");
            ReservationFactoryConcrete.get().validate();
            System.out.println("Validation réussie.");

            System.out.print("Déconnection de la base de données ... ");
            ReservationFactoryConcrete.get().close();
            System.out.println("Déconnection réussie.");
        }
        else {
            System.out.print("Annulation des changements ... ");
            ReservationFactoryConcrete.get().cancel();
            System.out.println("Changements annulés.");

            System.out.print("Déconnection de la base de données ... ");
            ReservationFactoryConcrete.get().close();
            System.out.println("Déconnection réussie.");
        }
	}
}
