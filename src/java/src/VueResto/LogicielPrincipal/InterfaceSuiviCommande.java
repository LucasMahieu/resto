package VueResto.LogicielPrincipal;
import VueResto.*;
import ModeleResto.*;
import ControleurResto.*;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
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
	private static final int TAILLE_X_PANEL = 900;
	private static final int TAILLE_Y_PANEL = 600;
	private static final int TAILLE_X_FIELD_TABLE = 100;
	private static final int TAILLE_Y_FIELD_TABLE = 20;
	private static final int POS_X_TABLE = 10;
	private static final int POS_Y_TABLE = 30;
	private static final int TAILLE_X_FIELD_NOM = 100;
	private static final int TAILLE_Y_FIELD_NOM = 20;
	private static final int POS_X_NOM = 120;
	private static final int POS_Y_NOM = 30;
	private static final int TAILLE_X_PANEL_ARTICLE = 500;
	private static final int TAILLE_Y_PANEL_ARTICLE = 600;
	private static final int POS_X_ARTICLE = 0;
	private static final int POS_Y_ARTICLE = 60;
	private static final int PLACEMENT_TAB_ARTICLE = SwingConstants.LEFT;
	private static final int TAILLE_SUPPRESSION = 25;
	private static final int POS_X_SUPPRESSION = TAILLE_X_PANEL_ARTICLE - TAILLE_SUPPRESSION;
	private static final int POS_Y_SUPPRESSION = 30;
	private static final int TAILLE_AJOUT_Q = 45;
	private static final int TAILLE_AJOUT = 25;
	private static final int POS_X_AJOUT = TAILLE_X_PANEL_ARTICLE - TAILLE_AJOUT - TAILLE_SUPPRESSION - 5;
	private static final int POS_X_AJOUT_Q = TAILLE_X_PANEL_ARTICLE - TAILLE_SUPPRESSION - TAILLE_AJOUT - TAILLE_AJOUT_Q - 10;
	private static final int POS_Y_AJOUT = 30;
	private static final int POS_X_RECHERCHE = 240;
	private static final int POS_Y_RECHERCHE = 30;
	private static final int TAILLE_X_RECHERCHE = 80;
	private static final int TAILLE_Y_RECHERCHE = 20;
	private static final int POS_B = 20;
	private static final int TAILLE_X_B = 80;
	private static final int TAILLE_Y_B = 80;
	private static final int POS_E = 20;
	private static final int TAILLE_X_E = 100;
	private static final int TAILLE_Y_E = 80;
	private static final int POS_P = 20;
	private static final int TAILLE_X_P = 100;
	private static final int TAILLE_Y_P = 80;
	private static final int POS_D = 20;
	private static final int TAILLE_X_D = 80;
	private static final int TAILLE_Y_D = 80;
	private static final int POS_M = 20;
	private static final int TAILLE_X_M = 80;
	private static final int TAILLE_Y_M = 80;
	private static final int POS_X_RECAP = TAILLE_X_PANEL_ARTICLE + 50;
	private static final int POS_Y_RECAP = 10;
	private static final int TAILLE_X_RECAP = TAILLE_X_PANEL - TAILLE_X_PANEL_ARTICLE;
	private static final int TAILLE_Y_RECAP = 20;


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
		//CHAMP TEXT POUR CHOISIR LE NOM DE LA RESA
		this.textFieldNom = new JTextField(TAILLE_X_FIELD_NOM);
		textFieldNom.setBounds(POS_X_NOM,POS_Y_NOM,TAILLE_X_FIELD_NOM,TAILLE_Y_FIELD_NOM);
		panelSuiviCommande.add(textFieldNom);
		labelNom = new JLabel("Nom Resa.");
		labelNom.setBounds(POS_X_NOM,10,TAILLE_X_FIELD_NOM,TAILLE_Y_FIELD_NOM);
		panelSuiviCommande.add(labelNom);
		
		// Bouton de recherche de la resa
		this.buttonRechercheSuivi = new JButton("Rechercher");
		buttonRechercheSuivi.setBounds(POS_X_RECHERCHE,POS_Y_RECHERCHE,TAILLE_X_RECHERCHE,TAILLE_Y_RECHERCHE);
		panelSuiviCommande.add(buttonRechercheSuivi);
		
		// Bouton d'ajout de l'article
		this.buttonOuvrir = new JButton(new ImageIcon("./ressources/more_detail.png"));
		buttonOuvrir.setBounds(POS_X_AJOUT,POS_Y_AJOUT,TAILLE_AJOUT,TAILLE_AJOUT);
		panelSuiviCommande.add(buttonOuvrir);

		// Bouton de suppression de l'article
		this.buttonFermer = new JButton(new ImageIcon("./ressources/supprimer_panier.png"));
		buttonFermer.setBounds(POS_X_SUPPRESSION,POS_Y_SUPPRESSION,TAILLE_SUPPRESSION,TAILLE_SUPPRESSION);
		panelSuiviCommande.add(buttonFermer);

	}
	/**
	 * Active les Actions sur les boutons et autres composant de l'inteface
	 *
	 */
	public void activeListener(ActionListener aL){
		buttonRechercheSuivi.addActionListener(aL);
		buttonOuvrir.addActionListener(aL);
		buttonFermer.addActionListener(aL);
	}

	public void update(Observable o, Object arg){
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
	public JButton getButtonRecherche(){
		return this.buttonRechercheSuivi;
	}
	public JButton getButtonOuvrir(){
		return this.buttonOuvrir;
	}
	public JButton getButtonFermer(){
		return this.buttonFermer;
	}

	/**
	 * Cette classe est un TableModel Pour le tableau de suivi des commande
	 */
	public class SModel extends AbstractTableModel {
		private Object[][] data;
		private String[] title;

		//Constructeur
		public SModel(Object[][] data, String[] title){
			this.data = data;
			this.title = title;
		}

		//Retourne le nombre de colonnes
		public int getColumnCount() {
			return this.title.length;
		}

		//Retourne le nombre de lignes
		public int getRowCount() {
			return this.data.length;
		}

		//Retourne la valeur à l'emplacement spécifié
		public Object getValueAt(int row, int col) {
			return this.data[row][col];
		}
		/**
		 * * Retourne le titre de la colonne à l'indice spécifié
		 * */
		public String getColumnName(int col) {
			  return this.title[col];
		}
		//Retourne la classe de la donnée de la colonne
		public Class getColumnClass(int col){
			//On retourne le type de la cellule à la colonne demandée
			//On se moque de la ligne puisque les types de données sont les mêmes quelle que soit la ligne
			//On choisit donc la première ligne
			return this.data[0][col].getClass();
		}
		//Retourne vrai si la cellule est éditable : celle-ci sera donc éditable
		public boolean isCellEditable(int row, int col){
			if(getValueAt(0, col) instanceof JButton)
				return false;
			return true; 
		}
	}
}
