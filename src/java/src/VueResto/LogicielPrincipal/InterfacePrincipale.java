package LogicielPrincipal;
import VueResto.*;

public class InterfacePrincipale{
	private InterfaceCommande interfaceCommande;
	private InterfaceReservation interfaceReservation;

	public InterfacePrincipale(){
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

