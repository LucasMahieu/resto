package VueResto.LogicielPrincipal;
import VueResto.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class InterfaceCommande extends ObservateurCommande{

	private JPanel panelCommande;
	private JTabbedPane tabbedPaneArticle;
	private JPanel panelBoisson;
	private JPanel panelEntree;
	private JPanel panelPlat;
	private JPanel panelDessert;
	private JPanel panelMenu;
	private JTextField textFieldNbTable;
	private JTextField textFieldNom;
	private JLabel labelNbTable;
	private JLabel labelNom;
	private JButton buttonRecherche;
	private JButton buttonAjout;
	private SpinnerModel modelQuantite;    
	private JSpinner spinnerQuantite ;
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
	private static final int POS_X_ARTICLE = 10;
	private static final int POS_Y_ARTICLE = 60;
	private static final int PLACEMENT_TAB_ARTICLE = SwingConstants.LEFT;
	private static final int TAILLE_AJOUT_Q = 45;
	private static final int TAILLE_AJOUT = 25;
	private static final int POS_X_AJOUT = TAILLE_X_PANEL_ARTICLE - TAILLE_AJOUT;
	private static final int POS_X_AJOUT_Q = TAILLE_X_PANEL_ARTICLE - TAILLE_AJOUT - TAILLE_AJOUT_Q - 5;
	private static final int POS_Y_AJOUT = 30;
	private static final int POS_X_RECHERCHE = 240;
	private static final int POS_Y_RECHERCHE = 30;
	private static final int TAILLE_X_RECHERCHE = 80;
	private static final int TAILLE_Y_RECHERCHE = 20;

	
	public InterfaceCommande(){
		// PANEL PRINCIPALE
		this.panelCommande = new JPanel();
		this.panelCommande.setPreferredSize(new Dimension(TAILLE_X_PANEL,TAILLE_Y_PANEL));
		this.panelCommande.setLayout(null);

		//CHAMP TEXT POUR CHOISIR LE NB DE TABLE
		this.textFieldNbTable = new JTextField(TAILLE_X_FIELD_TABLE);
		textFieldNbTable.setBounds(POS_X_TABLE,POS_Y_TABLE,TAILLE_X_FIELD_TABLE,TAILLE_Y_FIELD_TABLE);
		panelCommande.add(textFieldNbTable);
		labelNbTable = new JLabel("Num. Table");
		labelNbTable.setBounds(POS_X_TABLE,10,TAILLE_X_FIELD_TABLE,TAILLE_Y_FIELD_TABLE);
		panelCommande.add(labelNbTable);
		//CHAMP TEXT POUR CHOISIR LE NOM DE LA RESA
		this.textFieldNom = new JTextField(TAILLE_X_FIELD_NOM);
		textFieldNom.setBounds(POS_X_NOM,POS_Y_NOM,TAILLE_X_FIELD_NOM,TAILLE_Y_FIELD_NOM);
		panelCommande.add(textFieldNom);
		labelNom = new JLabel("Nom Resa.");
		labelNom.setBounds(POS_X_NOM,10,TAILLE_X_FIELD_NOM,TAILLE_Y_FIELD_NOM);
		panelCommande.add(labelNom);
		// PANEL QUI VA CONTENIR LES ARTICLES
		this.tabbedPaneArticle = new JTabbedPane(PLACEMENT_TAB_ARTICLE);
		tabbedPaneArticle.setBounds(POS_X_ARTICLE,POS_Y_ARTICLE,TAILLE_X_PANEL_ARTICLE, TAILLE_Y_PANEL_ARTICLE);
		// Different onglets par article
		this.panelBoisson = new JPanel();
        panelBoisson.setPreferredSize(new Dimension(TAILLE_X_PANEL_ARTICLE,TAILLE_Y_PANEL_ARTICLE));
		this.tabbedPaneArticle.addTab("BOISSON",panelBoisson);
		this.panelEntree = new JPanel();
        panelEntree.setPreferredSize(new Dimension(TAILLE_X_PANEL_ARTICLE,TAILLE_Y_PANEL_ARTICLE));
		this.tabbedPaneArticle.addTab("ENTREE",panelEntree);
		this.panelPlat = new JPanel();
        panelPlat.setPreferredSize(new Dimension(TAILLE_X_PANEL_ARTICLE,TAILLE_Y_PANEL_ARTICLE));
		this.tabbedPaneArticle.addTab("PLAT",panelPlat);
		this.panelDessert = new JPanel();
        panelDessert.setPreferredSize(new Dimension(TAILLE_X_PANEL_ARTICLE,TAILLE_Y_PANEL_ARTICLE));
		this.tabbedPaneArticle.addTab("DESSERT",panelDessert);
		this.panelMenu = new JPanel();
        panelMenu.setPreferredSize(new Dimension(TAILLE_X_PANEL_ARTICLE,TAILLE_Y_PANEL_ARTICLE));
		this.tabbedPaneArticle.addTab("MENU",panelMenu);

		// Bouton de recherche de la resa
		this.buttonRecherche = new JButton("Rechercher");
		buttonRecherche.setBounds(POS_X_RECHERCHE,POS_Y_RECHERCHE,TAILLE_X_RECHERCHE,TAILLE_Y_RECHERCHE);
		panelCommande.add(buttonRecherche);
		
		// (valeur par defaut, minimum, maximun, increment)
		this.modelQuantite = new SpinnerNumberModel(1,1,10,1);     
		this.spinnerQuantite = new JSpinner(modelQuantite);
		spinnerQuantite.setBounds(POS_X_AJOUT_Q,POS_Y_AJOUT,TAILLE_AJOUT_Q,TAILLE_AJOUT);
		this.panelCommande.add(spinnerQuantite);

		// Bouton d'ajout de l'article
		this.buttonAjout = new JButton(new ImageIcon("./ressources/ajout_panier.png"));
		buttonAjout.setBounds(POS_X_AJOUT,POS_Y_AJOUT,TAILLE_AJOUT,TAILLE_AJOUT);
		panelCommande.add(buttonAjout);

		this.tabbedPaneArticle.setOpaque(true);
		this.panelCommande.add(tabbedPaneArticle);

	}
	
	public JPanel getPanel(){
		return this.panelCommande;
	}

	public void miseAJour(){
	}
}
