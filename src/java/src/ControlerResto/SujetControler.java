package ControleurResto.SujetControleur;

import VueResto.*;

public abstract class ControleurSujet{

	ArrayList<Observateur> observateur;

	public ControleurSujet(){
		observateur = new ArrayList<Observateur>();
	}
	
	public void informe(){
		for(Observateur o in this.observateur){
			o.miseAJour();
		}
	}
}
