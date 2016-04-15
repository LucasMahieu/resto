package ModeleResto;
//TO DO
import ControleurResto;
public class Table extends ControleurSujet{
  public Table(){
  }



    selectionTableLibre(String localisation, int nombrePlace) {
	
	String requeteNombreMaxTable = new String("SELECT count(*) from tables group by tables.localisation");
	//On fait la requete et on met le resultat dans un int nbTable
	int nombreMaxTable;
	int nbTableActuel = 0;
	
	while (nbTableActuel <= nombreMaxTable) {
	    nbTableActuel += 1;
	    String requete = new String("SELECT ");
	    
	    //on ajoute nbTableActuel tables à la requete 
	    for(int i = 0; i < nbTableActuel; i++) { 
		requete += "T" + i + ".numerotable",;
	    }

	    
	    //on calcule la somme des tables considérées
	    requete += "SUM("
	    for(int i = 0; i < nbTableActuel; i++) { 
		requete += "T" + i + ".nombreplaceisolee + ";
	    }
	    //on enlève le "+ " de fin
	    requete = requete.substring(0, requete.length() - 2);
	    requete += ")as somme";
	    requete += "CASE ";
	    requete += "when somme >= 'nombre' THEN 'OK' ELSE '0' ";
	    requete += "END";
	    requere += "FROM estreservee E, ";
	    for(int i = 0; i < nbTableActuel; i++) { 
		requete += "tables T" + i + ", ";
	    }
	    //on enlève le ", " de fin
	    requete = requete.substring(0, requete.length() - 2);
	    requete += "WHERE T0.numerotable not in (SELECT numeroreservation From estreservee)";
	    //on rajoute la meme condition, en partant de 1 cette fois
	    for(int i = 1; i < nbTableActuel; i++) { 
		requete += "and T" + i + ".numerotable not in SELECT numeroreservation From estreservee)";
	    }
	    //Il faut que les tables soient voisines s'il y en a plusieurs
	    for(int i = 0; i < nbTableActuel; i++) { 
		requete += "and exists (SELECT SS.numerotable" + i + ", SS.numerotable2 From sontvoisine SS where" + i + ".numerotable not in SELECT numeroreservation From estreservee)";
	    }
	    requete += 
	}  


	requete += "estreservee E"




	}




	    --le n dépendra du nombre max de tables renvoyé par la requête d'avant
SELECT T1.numerotable, T2.numerotable, Tn.numerotable, SUM(T1.nombreplaceisolee + T2.nombreplaceisolee + Tn.nombreplaceisolee) as somme

CASE
		when somme >= 'nombre' THEN 'OK' ELSE '0'
END

FROM estreservee E, tables T1, tables T2, tables Tn

WHERE T1.numerotable not in (SELECT numeroreservation From estreservee)
and T2.numerotable not in (SELECT numeroreservation From estreservee)
and Tn.numerotable not in (SELECT numeroreservation From estreservee)
and exists (SELECT SS.numerotable1, SS.numerotable2 From sontvoisine SS where (SS.numerotable1 = T1.numerotable and SS.numerotable2 = T2.numerotable) or (SS.numerotable1 = T2.numerotable and SS.numerotable2 = T2.numerotable)
and exists (SELECT SS.numerotable1, SS.numerotable2 From sontvoisine SS where (SS.numerotable1 = T2.numerotable and SS.numerotable2 = Tn.numerotable) or (SS.numerotable1 = Tn.numerotable and SS.numerotable2 = T2.numerotable);



}
