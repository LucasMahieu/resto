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

	public void checkerCommande(int numeroCommande){

	}
}
