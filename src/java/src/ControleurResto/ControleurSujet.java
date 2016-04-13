package ControleurResto.SujetControleur;

import VueResto.*;
import java.util.*;

public abstract class ControleurSujet{

	ArrayList<Observateur> observateurs;

	public ControleurSujet(){
		this.observateurs = new ArrayList<Observateur>();
	}
	
	public void informe(){
		for(Observateur o : this.observateurs){
			o.miseAJour();
		}
	}

	public void checkerFacture(int numeroFacture){
        Facture factureFinale = new Facture();
        if(factureFinale.existe(numeroFacture)){
            factureFinale.getFacture(numeroFacture);
        }
	}
}
