package ModeleResto;
//TO DO
import ControleurResto;
import java.util.*;
import java.sql.*;
public class Table extends Observable {
    private Connection conn;
    public Table(){
    }

    public void setCon(Connection conn) {
        this.conn = conn;
    }



    consultationTableLibre(String localisation, int nombrePlace) {

        String requeteNombreMaxTable = new String("SELECT count(*) from tables group by tables.localisation");
        //On fait la requete et on met le resultat dans un int nbTable
        int nombreMaxTable;
        int nbTableActuel = 0;
        int nombrePlaceDisponible = 0;

        while ( !(nbTableActuel <= nombreMaxTable) or  ) {
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
            //Il faut que les tables soient voisines s'il y en a plusieurs (on va jusqu'à -1 du coup
            for(int i = 0; i < nbTableActuel - 1; i++) { 
                requete += "and exists (SELECT SS.numerotable" + i + ", SS.numerotable" + (i+1) + "From sontvoisine SS where (SS.numerotable" + i + " = T" + i + ".numerotable and SS.numerotable" + (i+1) + " = T" + (i+1) + ".numerotable) or (SS.numerotable" + i + " = T" + (i+1) + ".numerotable and SS.numerotable" + (i+1) + " = T" + (i+1) + ".numerotable)";
            }


            //on fait la requete et on met la réponse dans nombrePlaceDisponible
            //??? sait pas comment faire


        }  

        selectionTableLibre(LinkedList<Integer> liste) {

        }

    }
}
