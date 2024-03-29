package VueResto.LogicielPrincipal;;
import VueResto.*;
import ModeleResto.*;
import ControleurResto.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.text.SimpleDateFormat;


public class InterfaceSuiviCommande extends Observateur{
	private JPanel panelSuiviCommande;
	private JTextField textFieldNTable;
	private JTextField textFieldNom;
	private JLabel labelNTable;
	private JLabel labelNom;
	private JButton buttonRechercheSuivi;
	private JButton buttonOuvrir;
	private JButton buttonFermer;
	private JButton buttonEnvoyer;
	private SModel sModel;
    private int numeroReservationArticle;
	private String titre[] = {"Nom","n° Réservation","n° Table", "Nbr Personne","Etat"};
	private JTable tableau;
	private JScrollPane jScrollPane;
	private SModel sModelArticles;
	private String titreArticles[] = {"Article","Quantité"};
	private JTable tableauArticles;
	private JScrollPane jScrollPaneArticles;
	private static final int TAILLE_X_PANEL = 900;
	private static final int TAILLE_Y_PANEL = 600;
	private static final int TAILLE_X_FIELD_TABLE = 100;
	private static final int TAILLE_Y_FIELD_TABLE = 20;
	private static final int POS_X_TABLE = 10;
	private static final int POS_Y_TABLE = 30;
	private static final int TAILLE_X_FIELD_NOM = 100;
	private static final int TAILLE_Y_FIELD_NOM = 20;
	private static final int POS_X_NOM = POS_X_TABLE + TAILLE_X_FIELD_TABLE + 10;
	private static final int POS_Y_NOM = 30;
	private static final int POS_X_RECHERCHE = POS_X_NOM + TAILLE_X_FIELD_NOM + 10;
	private static final int POS_Y_RECHERCHE = 30;
	private static final int TAILLE_X_RECHERCHE = 90;
	private static final int TAILLE_Y_RECHERCHE = 20;
	private static final int TAILLE_OUVRIR = 25;
	private static final int POS_X_OUVRIR = POS_X_RECHERCHE + TAILLE_X_RECHERCHE  + 10;
	private static final int POS_Y_OUVRIR = 30;
	private static final int TAILLE_FERMER = 25;
	private static final int POS_X_FERMER = POS_X_OUVRIR + TAILLE_OUVRIR + 10;
	private static final int POS_Y_FERMER = 30;
	private static final int TAILLE_X_TAB = 650;
	private static final int TAILLE_Y_TAB = 550;
	private static final int POS_X_TAB = 10;
	private static final int POS_Y_TAB = POS_Y_TABLE + TAILLE_Y_FIELD_TABLE + 10;
	private static final int TAILLE_LIGNE = 20;

	private static final int TAILLE_X_TAB_ART = 300;
	private static final int TAILLE_Y_TAB_ART = 550;
	private static final int POS_X_TAB_ART = POS_X_TAB + TAILLE_X_TAB + 10;
	private static final int POS_Y_TAB_ART = POS_Y_TABLE + TAILLE_Y_FIELD_TABLE + 10;

	private static final int TAILLE_X_ENVOYER = 90;
	private static final int TAILLE_Y_ENVOYER = 20;
	private static final int POS_X_ENVOYER = POS_X_TAB_ART+ 10;
	private static final int POS_Y_ENVOYER = POS_Y_FERMER;

	public InterfaceSuiviCommande(){
	// PANEL PRINCIPALE
		this.panelSuiviCommande = new JPanel();
		this.panelSuiviCommande.setPreferredSize(new Dimension(TAILLE_X_PANEL,TAILLE_Y_PANEL));
		this.panelSuiviCommande.setLayout(null);

		//CHAMP TEXT POUR CHOISIR LE NB DE TABLE
		this.textFieldNTable = new JTextField(TAILLE_X_FIELD_TABLE);
		textFieldNTable.setBounds(POS_X_TABLE,POS_Y_TABLE,TAILLE_X_FIELD_TABLE,TAILLE_Y_FIELD_TABLE);
		panelSuiviCommande.add(textFieldNTable);
		labelNTable = new JLabel("Num. Table");
		labelNTable.setBounds(POS_X_TABLE,10,TAILLE_X_FIELD_TABLE,TAILLE_Y_FIELD_TABLE);
		panelSuiviCommande.add(labelNTable);
		
		// Bouton de recherche de la resa
		this.buttonRechercheSuivi = new JButton("Rechercher");
		buttonRechercheSuivi.setBounds(POS_X_RECHERCHE,POS_Y_RECHERCHE,TAILLE_X_RECHERCHE,TAILLE_Y_RECHERCHE);
		panelSuiviCommande.add(buttonRechercheSuivi);
		
		// Bouton d'ajout de l'article
		this.buttonOuvrir = new JButton(new ImageIcon("./ressources/more_detail.png"));
		buttonOuvrir.setBounds(POS_X_OUVRIR,POS_Y_OUVRIR,TAILLE_OUVRIR,TAILLE_OUVRIR);
		panelSuiviCommande.add(buttonOuvrir);

		// Bouton de suppression de l'article
		this.buttonFermer = new JButton(new ImageIcon("./ressources/supprimer_panier.png"));
		buttonFermer.setBounds(POS_X_FERMER,POS_Y_FERMER,TAILLE_FERMER,TAILLE_FERMER);
		panelSuiviCommande.add(buttonFermer);

        //Bouton Envoyer Articles
		this.buttonEnvoyer= new JButton("Envoyer");
		buttonEnvoyer.setBounds(POS_X_ENVOYER,POS_Y_ENVOYER,TAILLE_X_ENVOYER,TAILLE_Y_ENVOYER);
		panelSuiviCommande.add(buttonEnvoyer);

        // Tableau contenant les commandes :
        // un bouton permet d'obtenir les détails de la commande sélectionnée
        // un autre bouton permet de fermer les détails de la commande ouverte.
		Object[][] data = {};

		this.sModel = new SModel(data,titre);
		//this.tableau = new JTable(sModel);
		this.tableau = new JTable(new DefaultTableModel(data,titre));
		this.tableau.setRowHeight(TAILLE_LIGNE);
		this.tableau.setBounds(POS_X_TAB,POS_Y_TAB,TAILLE_X_TAB,TAILLE_Y_TAB);
		this.jScrollPane = new JScrollPane(tableau);
		jScrollPane.setBounds(POS_X_TAB,POS_Y_TAB,TAILLE_X_TAB,TAILLE_Y_TAB);
		panelSuiviCommande.add( jScrollPane);
        // mise à jour du tableau 
        this.remiseAZeroTableau();

        // Liste articles de la table sélectionnée
		Object[][] dataArticles = {};

		this.sModelArticles = new SModel(dataArticles,titre);
		this.tableauArticles = new JTable(new DefaultTableModel(dataArticles,titreArticles));
		this.tableauArticles.setRowHeight(TAILLE_LIGNE);
		this.tableauArticles.setBounds(POS_X_TAB_ART,POS_Y_TAB_ART,TAILLE_X_TAB_ART,TAILLE_Y_TAB_ART);
		this.jScrollPaneArticles = new JScrollPane(tableauArticles);
		jScrollPaneArticles.setBounds(POS_X_TAB_ART,POS_Y_TAB_ART,TAILLE_X_TAB_ART,TAILLE_Y_TAB_ART);
		panelSuiviCommande.add( jScrollPaneArticles);
        // mise à jour du tableau 
        this.remiseAZeroTableau();
        this.miseAJourTableauArticles();
	}

	/**
	 * Active les Actions sur les boutons et autres composant de l'inteface
     * @param aL ActionListener de la fenetre
	 */
	public void activeListener(ActionListener aL){
		buttonRechercheSuivi.addActionListener(aL);
		buttonOuvrir.addActionListener(aL);
		buttonFermer.addActionListener(aL);
		buttonEnvoyer.addActionListener(aL);
	}

	public void update(Observable o, Object arg){
		// Notify lancé par une reservation
        System.out.println("update");
        if(o instanceof ModeleResto.Article){
          System.out.println("Update article suivi commande");
          remiseAZeroTableau();
          miseAJourTableauArticles();
        }
        else if (o instanceof ModeleResto.ReservationConcrete){
          System.out.println("update reservation suivi");
          remiseAZeroTableau();
        }
        else if (o instanceof ModeleResto.Table){
          System.out.println("update table suivi");
          remiseAZeroTableau();
        }

	}
	public JPanel getPanel(){
		return this.panelSuiviCommande;
	}
	public JPanel getPanelSuiviCommande(){
		return this.panelSuiviCommande;
	}
	public JTextField getTextFieldNTable(){
		return this.textFieldNTable;
	}
	public JTextField getTextFieldNom(){
		return this.textFieldNom;
	}
	public JLabel getLabelNTable(){
		return this.labelNTable;
	}
	public JLabel getLabelNom(){
		return this.labelNom;
	}
	public JButton getButtonRechercheSuivi(){
		return this.buttonRechercheSuivi;
	}
	public JButton getButtonOuvrir(){
		return this.buttonOuvrir;
	}
	public JButton getButtonFermer(){
		return this.buttonFermer;
	}

	public JButton getButtonEnvoyer(){
		return this.buttonEnvoyer;
	}

	public JTable getTableau(){
		return this.tableau;
	}

    /** effet de l'appui sur le bouton Ouvrir
     */
    public void effetBoutonOuvrir(){
      System.out.println("Effet Bouton Ouvrir");
      int selectedRow = this.tableau.getSelectedRow();
      if(selectedRow >= 0){
        System.out.println("Selected row : " + selectedRow );
        int numReservationSelected = (int) (this.tableau.getModel()).getValueAt(selectedRow,1);
        Controleur.get().setNumResaSuiviSelectionne(numReservationSelected);
        System.out.println("Number of reservation : " + numReservationSelected);
        this.miseAJourTableauArticles();
      }
    }

    /** effet de l'appui sur le bouton Fermer
     */
    public void effetBoutonFermer(){
      // Efface les données du panneau de suivi de commande précis
      System.out.println("Effet Bouton Fermer");

    }

    /** effet de l'appui sur le bouton Ouvrir
     */
    public void effetBoutonEnvoyer(){
      System.out.println("Effet Bouton Envoyer");
      //TODO : vérifier le fonctionnement de la fonction
      int selectedRow = this.tableauArticles.getSelectedRow();
      if(selectedRow >= 0){
        System.out.println("Selected row : " + selectedRow );
        String numArticleSelected = (String) (this.tableauArticles.getModel()).getValueAt(selectedRow,0);
        int quantiteEnvoyee = (int) (this.tableauArticles.getModel()).getValueAt(selectedRow,1);
        System.out.println("Article: " + numArticleSelected);
        System.out.println("quantite: " + quantiteEnvoyee);
        if (numeroReservationArticle > 0){
          System.out.println("envoi article");
          if (Controleur.get().estEnvoye(numArticleSelected,numeroReservationArticle,quantiteEnvoyee) == -1) {
          }
        }
        this.miseAJourTableauReservationEnvoyer();
        this.miseAJourTableauArticlesEnvoyer();
      }
      
    }

    /** effet de l'appui sur le bouton RechercheSuivi
     */
    public void effetBoutonRechercheSuivi(){
      // Affiche dans le tableau uniquement les données correspondant au numéro de table ou numéro de réservation sélectionné
      System.out.println("Effet Bouton Recherche Suivi");

      if(this.getTextFieldNTable().getText().equals("")){
        Controleur.get().setNumResaSuiviSelectionne(-1);
        this.remiseAZeroTableau();
        this.miseAJourTableauArticles();
        return;
      }
      int table = Integer.parseInt(this.getTextFieldNTable().getText());
      int numeroReservationCourant = Controleur.get().getNumeroReservation(table);
      Controleur.get().setNumResaSuiviSelectionne(numeroReservationCourant);
      this.miseAJourTableauReservation();
      this.miseAJourTableauArticles();
    }

    public void miseAJourTableauReservation(){
      // On nettoie le tableau
      ((DefaultTableModel)this.tableau.getModel()).getDataVector().removeAllElements();
      ((DefaultTableModel)this.tableau.getModel()).fireTableDataChanged();
      // On recharge une nouvelle table correspondant à la recherche 
      int numeroReservationCourant = Controleur.get().getNumResaSuiviSelectionne();
      LinkedList<Integer> tables = Controleur.get().getNumeroTables(numeroReservationCourant);
      if (tables.isEmpty()){
        System.out.println("pas de table");
        remiseAZeroTableau();
        return;
      }
      int table = tables.get(0);
      if ( numeroReservationCourant <= 0){
        return;
      }
      else if(Integer.parseInt(this.getTextFieldNTable().getText())==(table)){
        Controleur.get().setNumResaSuiviSelectionne(numeroReservationCourant);
        System.out.println("Une ou  plusieurs réservations ont été trouvées ");
        // On affiche les reservations trouvées
        String etatCommande = Controleur.get().getEtatCommande(numeroReservationCourant);
        String nomCommande = Controleur.get().getNom(numeroReservationCourant);
        String date = Controleur.get().getDateNow();
        System.out.println(etatCommande);
        Object[] o = {nomCommande,numeroReservationCourant,table,date,etatCommande};
        ((DefaultTableModel)this.tableau.getModel()).addRow(o);
        ((DefaultTableModel)this.tableau.getModel()).fireTableDataChanged();
      }
    }

    public void miseAJourTableauReservationEnvoyer(){
      // On nettoie le tableau
      ((DefaultTableModel)this.tableau.getModel()).getDataVector().removeAllElements();
      ((DefaultTableModel)this.tableau.getModel()).fireTableDataChanged();
      // On recharge une nouvelle table correspondant à la recherche 
      for(int table = 0; table < 100; table++){
        int numeroReservationCourant = Controleur.get().getNumeroReservation(table);
        if ( numeroReservationCourant <= 0){
          continue;
        }
        else{
          System.out.println("Une ou  plusieurs réservations ont été trouvées ");
          // On affiche les reservations trouvées
          String etatCommande = Controleur.get().getEtatCommande(numeroReservationCourant);
          String nomCommande = Controleur.get().getNom(numeroReservationCourant);
          String date = Controleur.get().getDateNow();
          String tempsEtat = Controleur.get().getDateNow();
          System.out.println(etatCommande);
          Object[] o = {nomCommande,numeroReservationCourant,table,date,etatCommande};
          ((DefaultTableModel)this.tableau.getModel()).addRow(o);
          ((DefaultTableModel)this.tableau.getModel()).fireTableDataChanged();
        }
      }
    }

    public void remiseAZeroTableau(){
      System.out.println("remiseAZeroTableau");
      // On nettoie la table
      ((DefaultTableModel)this.tableau.getModel()).getDataVector().removeAllElements();
      ((DefaultTableModel)this.tableau.getModel()).fireTableDataChanged();
      // On recharge une nouvelle table correspondant à la recherche 
      // TODO : définir le nombre de tables
      Controleur.get().setNumResaSuiviSelectionne(-1);
      for(int table = 0; table < 100; table++){
        int numeroReservationCourant = Controleur.get().getNumeroReservation(table);
        if ( numeroReservationCourant <= 0){
          continue;
        }
        else{
          System.out.println("Une ou  plusieurs réservations ont été trouvées ");
          // On affiche les reservations trouvées
          String etatCommande = Controleur.get().getEtatCommande(numeroReservationCourant);
          String nomCommande = Controleur.get().getNom(numeroReservationCourant);
          String date = Controleur.get().getDateNow();
          String tempsEtat = Controleur.get().getDateNow();
          System.out.println(etatCommande);
          Object[] o = {nomCommande,numeroReservationCourant,table,date,etatCommande};
          ((DefaultTableModel)this.tableau.getModel()).addRow(o);
          ((DefaultTableModel)this.tableau.getModel()).fireTableDataChanged();
        }
      }
    }

    public void miseAJourTableauArticles(){
      System.out.println("miseAJourTableauArticles");
      // On nettoie la table
      ((DefaultTableModel)this.tableauArticles.getModel()).getDataVector().removeAllElements();
      ((DefaultTableModel)this.tableauArticles.getModel()).fireTableDataChanged();
      // On recharge une nouvelle table correspondant à la sélection
      int selectedRow = this.tableau.getSelectedRow();
      if(selectedRow < 0){
        this.numeroReservationArticle = -1;
        System.out.println("selectedRow <0");
        return;
      }
      int numeroReservationCourant = Integer.parseInt((this.tableau.getModel()).getValueAt(selectedRow,1).toString());
      if ( numeroReservationCourant <= 0){
        System.out.println("numeroReservation <= 0");
        this.numeroReservationArticle = -1;
        return;
      }
      this.numeroReservationArticle = numeroReservationCourant;

      System.out.println("numeroReservation :" + numeroReservationCourant);
      Map <String, Integer> articlesReservation;

      articlesReservation = Controleur.get().aEnvoyer(numeroReservationCourant);
      
      if( articlesReservation == null){
        System.out.println("articlesReservation = null ");
        return;
      }
      System.out.println("Une liste d'article a été trouvée");
      System.out.println("liste vide: " + articlesReservation.isEmpty());
      // On affiche les articles de la réservation trouvée
      for( Map.Entry<String,Integer> articleSuivi : articlesReservation.entrySet() ){
        System.out.println("Boucle");
        System.out.println(articleSuivi.getKey() + " " + articleSuivi.getValue());
        Object[] o = {articleSuivi.getKey(),articleSuivi.getValue()};
        ((DefaultTableModel)this.tableauArticles.getModel()).addRow(o);
        ((DefaultTableModel)this.tableauArticles.getModel()).fireTableDataChanged();
      }
      
    }

    public void miseAJourTableauArticlesEnvoyer(){
      System.out.println("miseAJourTableauArticlesEnvoyer");
      // On nettoie la table
      ((DefaultTableModel)this.tableauArticles.getModel()).getDataVector().removeAllElements();
      ((DefaultTableModel)this.tableauArticles.getModel()).fireTableDataChanged();
      // On recharge une nouvelle table correspondant à la sélection
      int numeroReservationCourant = Controleur.get().getNumResaSuiviSelectionne();
      if ( numeroReservationCourant <= 0){
        System.out.println("numeroReservation <= 0");
        this.numeroReservationArticle = -1;
        return;
      }
      this.numeroReservationArticle = numeroReservationCourant;

      System.out.println("numeroReservation :" + numeroReservationCourant);
      Map <String, Integer> articlesReservation;

      articlesReservation = Controleur.get().aEnvoyer(numeroReservationCourant);
      
      if( articlesReservation == null){
        System.out.println("articlesReservation = null ");
        return;
      }
      System.out.println("Une liste d'article a été trouvée");
      System.out.println("liste vide: " + articlesReservation.isEmpty());
      // On affiche les articles de la réservation trouvée
      for( Map.Entry<String,Integer> articleSuivi : articlesReservation.entrySet() ){
        System.out.println("Boucle");
        System.out.println(articleSuivi.getKey() + " " + articleSuivi.getValue());
        Object[] o = {articleSuivi.getKey(),articleSuivi.getValue()};
        ((DefaultTableModel)this.tableauArticles.getModel()).addRow(o);
        ((DefaultTableModel)this.tableauArticles.getModel()).fireTableDataChanged();
      }
      
    }

	/**
	 * Cette classe est un TableModel Pour le tableau de suivi des commande
	 */
	public class SModel extends AbstractTableModel {
		private Object[][] data;
		private String[] titre;

		public void setData(Object[][] data){
			this.data = data;
		}

        /** accesseur du tableau
         *
         * @return tableau de SModel
         */
        public Object[][] getData(){
          return this.data;
        }

		//Constructeur
		public SModel(Object[][] data, String[] titre){
			this.data = data;
			this.titre = titre;
		}

		//Retourne le nombre de colonnes
		public int getColumnCount() {
			return this.titre.length;
		}

		//Retourne le nombre de lignes
		public int getRowCount() {
			return this.data.length;
		}

		//Retourne la valeur à l'emplacement spécifié
		public Object getValueAt(int row, int col){
			return this.data[row][col];
		}
		public void setValueAt(Object value, int lig, int col){
			if(!(lig==0)){
				this.data[lig][col] = value;
			}
		}

		/**
		 * * Retourne le titre de la colonne à l'indice spécifié
		 * */
		public String getColumnName(int col) {
			  return this.titre[col];
		}
		//Retourne la classe de la donnée de la colonne
		public Class getColumnClass(int col){
			//On retourne le type de la cellule à la colonne demandée
			//On se moque de la ligne puisque les types de données sont les mêmes quelle que soit la ligne
			//On choisit donc la première ligne
			return this.data[0][col].getClass();
		}
		//Retourne vrai si la cellule est éditable : celle-ci sera donc éditable
		public boolean isCellEditable(int lig, int col){
			if(getValueAt(0, col) instanceof JButton)
				return false;
			return true; 
		}
	}
}
