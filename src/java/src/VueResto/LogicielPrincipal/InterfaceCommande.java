package VueResto.LogicielPrincipal;
import VueResto.*;
import ControleurResto.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.text.SimpleDateFormat;


/**
 * Cette classe est un observateur. Elle traite de la gestion des commandes
 * Fonctionnalités: 
 *		- Recherche d'une réservation par num de resa ou num de table
 *		- Ajout ou suppression d'articles d'une commande
 *		- Appercue d'un récapitulatif de la commande, avec son total
 */
public class InterfaceCommande extends Observateur{
	private Controleur controleur;
	private JPanel panelCommande;
	private JTabbedPane tabbedPaneArticle;
	private JPanel panelBoisson;
	private JPanel panelEntree;
	private JPanel panelPlat;
	private JPanel panelDessert;
	private JPanel panelMenu;
	private JTextField textFieldNTable;
	private JTextField textFieldNom;
	private JLabel labelNTable;
	private JLabel labelNom;
	private JButton buttonRecherche;
	private JButton buttonAjout;
	private JButton buttonSuppression;
	private SpinnerModel modelQuantite;    
	private JSpinner spinnerQuantite ;
	private ArrayList<JToggleButton> buttonArticleBoisson;
	private ArrayList<JToggleButton> buttonArticleEntree;
	private ArrayList<JToggleButton> buttonArticlePlat;
	private ArrayList<JToggleButton> buttonArticleDessert;
	private ArrayList<JToggleButton> buttonArticleMenu;
	private LinkedList<JLabel> labelRecapCommande;
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

	
	public InterfaceCommande(Controleur ctr){
		this.controleur = ctr;
		// PANEL PRINCIPALE
		this.panelCommande = new JPanel();
		this.panelCommande.setPreferredSize(new Dimension(TAILLE_X_PANEL,TAILLE_Y_PANEL));
		this.panelCommande.setLayout(null);

		//CHAMP TEXT POUR CHOISIR LE NB DE TABLE
		this.textFieldNTable = new JTextField(TAILLE_X_FIELD_TABLE);
		textFieldNTable.setBounds(POS_X_TABLE,POS_Y_TABLE,TAILLE_X_FIELD_TABLE,TAILLE_Y_FIELD_TABLE);
		panelCommande.add(textFieldNTable);
		labelNTable = new JLabel("Num. Table");
		labelNTable.setBounds(POS_X_TABLE,10,TAILLE_X_FIELD_TABLE,TAILLE_Y_FIELD_TABLE);
		panelCommande.add(labelNTable);
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
		this.panelBoisson.setLayout(null);
		this.tabbedPaneArticle.addTab("BOISSON",panelBoisson);
		this.panelEntree = new JPanel();
        panelEntree.setPreferredSize(new Dimension(TAILLE_X_PANEL_ARTICLE,TAILLE_Y_PANEL_ARTICLE));
		this.panelEntree.setLayout(null);
		this.tabbedPaneArticle.addTab("ENTREE",panelEntree);
		this.panelPlat = new JPanel();
        panelPlat.setPreferredSize(new Dimension(TAILLE_X_PANEL_ARTICLE,TAILLE_Y_PANEL_ARTICLE));
		this.panelPlat.setLayout(null);
		this.tabbedPaneArticle.addTab("PLAT",panelPlat);
		this.panelDessert = new JPanel();
        panelDessert.setPreferredSize(new Dimension(TAILLE_X_PANEL_ARTICLE,TAILLE_Y_PANEL_ARTICLE));
		this.panelDessert.setLayout(null);
		this.tabbedPaneArticle.addTab("DESSERT",panelDessert);
		this.panelMenu = new JPanel();
        panelMenu.setPreferredSize(new Dimension(TAILLE_X_PANEL_ARTICLE,TAILLE_Y_PANEL_ARTICLE));
		this.panelMenu.setLayout(null);
		this.tabbedPaneArticle.addTab("MENU",panelMenu);

		//Ajout des bouttons pour article BOISSON
		LinkedList<String> listArticleBoisson = controleur.getListeArticles("boisson");
		this.buttonArticleBoisson = new ArrayList<JToggleButton>();
		int lig=0,col=-1, j=0;
		for(j=0; j<listArticleBoisson.size(); j++){
			col++;
			buttonArticleBoisson.add(new JToggleButton(listArticleBoisson.get(j)));
			if (j%((TAILLE_X_PANEL_ARTICLE-TAILLE_X_B)/TAILLE_X_B)==0 && j!=0){
				lig++;
				col=0;
			}
			buttonArticleBoisson.get(j).setBounds(POS_B+col*TAILLE_X_B,POS_B+lig*TAILLE_Y_B,TAILLE_X_B,TAILLE_Y_B);
			panelBoisson.add(buttonArticleBoisson.get(j));
		}
		//Ajout des bouttons pour article ENTREE
		LinkedList<String> listArticleEntree = controleur.getListeArticles("entree");
		this.buttonArticleEntree = new ArrayList<JToggleButton>();
		lig=0;
		col=-1;
		for(j=0; j<listArticleEntree.size(); j++){
			col++;
			buttonArticleEntree.add(new JToggleButton(listArticleEntree.get(j)));
			if (j%((TAILLE_X_PANEL_ARTICLE-TAILLE_X_E)/TAILLE_X_E)==0 && j!=0){
				lig++;
				col=0;
			}
			buttonArticleEntree.get(j).setBounds(POS_E+col*TAILLE_X_E,POS_E+lig*TAILLE_Y_E,TAILLE_X_E,TAILLE_Y_E);
			panelEntree.add(buttonArticleEntree.get(j));
		}
		//Ajout des bouttons pour article PLAT
		LinkedList<String> listArticlePlat = controleur.getListeArticles("plat");
		this.buttonArticlePlat = new ArrayList<JToggleButton>();
		lig=0;
		col=-1;
		for(j=0; j<listArticlePlat.size(); j++){
			col++;
			buttonArticlePlat.add(new JToggleButton(listArticlePlat.get(j)));
			if (j%((TAILLE_X_PANEL_ARTICLE-TAILLE_X_P)/TAILLE_X_P)==0 && j!=0){
				lig++;
				col=0;
			}
			buttonArticlePlat.get(j).setBounds(POS_P+col*TAILLE_X_P,POS_P+lig*TAILLE_Y_P,TAILLE_X_P,TAILLE_Y_P);
			panelPlat.add(buttonArticlePlat.get(j));
		}
		//Ajout des bouttons pour article Dessert
		LinkedList<String> listArticleDessert = controleur.getListeArticles("dessert");
		this.buttonArticleDessert = new ArrayList<JToggleButton>();
		lig=0;
		col=-1;
		for(j=0; j<listArticleDessert.size(); j++){
			col++;
			buttonArticleDessert.add(new JToggleButton(listArticleDessert.get(j)));
			if (j%((TAILLE_X_PANEL_ARTICLE-TAILLE_X_D)/TAILLE_X_D)==0 && j!=0){
				lig++;
				col=0;
			}
			buttonArticleDessert.get(j).setBounds(POS_D+col*TAILLE_X_D,POS_D+lig*TAILLE_Y_D,TAILLE_X_D,TAILLE_Y_D);
			panelDessert.add(buttonArticleDessert.get(j));
		}
		//Ajout des bouttons pour article Menu
		LinkedList<String> listArticleMenu = controleur.getListeArticles("menu");
		this.buttonArticleMenu = new ArrayList<JToggleButton>();
		lig=0;
		col=-1;
		for(j=0; j<listArticleMenu.size(); j++){
			col++;
			buttonArticleMenu.add(new JToggleButton(listArticleMenu.get(j)));
			if (j%((TAILLE_X_PANEL_ARTICLE-TAILLE_X_M)/TAILLE_X_M)==0 && j!=0){
				lig++;
				col=0;
			}
			buttonArticleMenu.get(j).setBounds(POS_M+col*TAILLE_X_M,POS_M+lig*TAILLE_Y_M,TAILLE_X_M,TAILLE_Y_M);
			panelMenu.add(buttonArticleMenu.get(j));
		}
		
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

		// Bouton de suppression de l'article
		this.buttonSuppression = new JButton(new ImageIcon("./ressources/supprimer_panier.png"));
		buttonSuppression.setBounds(POS_X_SUPPRESSION,POS_Y_SUPPRESSION,TAILLE_SUPPRESSION,TAILLE_SUPPRESSION);
		panelCommande.add(buttonSuppression);

		this.labelRecapCommande = new LinkedList<JLabel>();
		this.tabbedPaneArticle.setOpaque(true);
		this.panelCommande.add(tabbedPaneArticle);
	}
	
	/**
	 * Créer une nouvelle LinkedList contenant l'en-tête du récap
	 * Cette méthode est à appeler à chaque fois que l'utilisateur clique sur le bouton "Rechercher"
	 * avec un numéro de table OU de réservation correct
	 * @param nTable numéro de la table pour laquelle on souhaite créer le récap
	 * @param nResa numéro de la réservation
	 * @exception si nResa ET nTable sont vide => PROBLEME gerer ce cas en amont lors de l'appui sur "Rechercher"
	 */
	public void createNewRecap(int numResa){
		String date = controleur.getDateNow();
		String service = controleur.getServiceNow();

		if(numResa == 0){
			return;
		}
		int j=0;
		// Suppression des anciennes lignes de récap
		for(j=0;j<labelRecapCommande.size();j++){
			panelCommande.remove(labelRecapCommande.get(j));
		}
		j=0;
		this.labelRecapCommande = new LinkedList<JLabel>();

		labelRecapCommande.add(new JLabel("Réservation n°"+numResa+" au nom de "+controleur.getNom(numResa)));
		labelRecapCommande.get(j).setBounds(POS_X_RECAP,POS_Y_RECAP+j*TAILLE_Y_RECAP,TAILLE_X_RECAP,TAILLE_Y_RECAP);
		panelCommande.add(labelRecapCommande.get(j));
		j++;
		labelRecapCommande.add(new JLabel("Table n°"+controleur.getNumeroTables(numResa)));
		labelRecapCommande.get(j).setBounds(POS_X_RECAP,POS_Y_RECAP+j*TAILLE_Y_RECAP,TAILLE_X_RECAP,TAILLE_Y_RECAP);
		panelCommande.add(labelRecapCommande.get(j));
		j++;
		labelRecapCommande.add(new JLabel("Service du "+date+" au "+service));
		labelRecapCommande.get(j).setBounds(POS_X_RECAP,POS_Y_RECAP+j*TAILLE_Y_RECAP,TAILLE_X_RECAP,TAILLE_Y_RECAP);
		panelCommande.add(labelRecapCommande.get(j));
		j++;
		labelRecapCommande.add(new JLabel("---------------------------------------------"));
		labelRecapCommande.get(j).setBounds(POS_X_RECAP,POS_Y_RECAP+j*TAILLE_Y_RECAP,TAILLE_X_RECAP,TAILLE_Y_RECAP);
		panelCommande.add(labelRecapCommande.get(j));
		j++;

		updateRecap(numResa);
	}
	/**
	 * Cette méthode ne fait qu'afficher la liste des articles commandés pour un numéro de réservation donnée
	 * Il faut appeler createNewResa() avant un updateRecap()
	 * @param numResa numéro de réservation
	 */
	public void updateRecap(int numResa){
		if(numResa == 0){
			return;
		}
		int j = 4;
		int q = 0;
		float somme = 0;
		float prix = 0;
		String a = "";
		HashMap<String,Integer> articlesCommandes = this.controleur.getArticlesCommandes(numResa);
		Set<String> articles = articlesCommandes.keySet();
		Iterator<String> itArticles = articles.iterator();

		while(itArticles.hasNext()){
			a=itArticles.next();
			q=articlesCommandes.get(a);
			prix = controleur.getPrixArticle(a);
			somme += prix*q;
			labelRecapCommande.add(new JLabel(""
						+ String.format("%0$-"+(65)+"s","- (x"+q+") "+a+" ")
						+ prix*q
						+ "€"));
			labelRecapCommande.get(j).setBounds(POS_X_RECAP,POS_Y_RECAP+j*TAILLE_Y_RECAP,TAILLE_X_RECAP,TAILLE_Y_RECAP);
			panelCommande.add(labelRecapCommande.get(j));
			j++;
		}
		labelRecapCommande.add(new JLabel("---------------------------------------------"));
		labelRecapCommande.get(j).setBounds(POS_X_RECAP,POS_Y_RECAP+j*TAILLE_Y_RECAP,TAILLE_X_RECAP,TAILLE_Y_RECAP);
		panelCommande.add(labelRecapCommande.get(j));
		j++;
		labelRecapCommande.add(new JLabel(String.format("%0$-70s","TOTAL = ") + somme + "€"));
		labelRecapCommande.get(j).setBounds(POS_X_RECAP,POS_Y_RECAP+j*TAILLE_Y_RECAP,TAILLE_X_RECAP,TAILLE_Y_RECAP);
		panelCommande.add(labelRecapCommande.get(j));
	}
	
	/**
	 * Applique à tous les boutons de la liste l, le boolean b
	 * @param l liste des bouton à affecter
	 * @param b etat désiré de tous les boutons 
	 */
	public void setSelectedButtonArticle(ArrayList<JToggleButton> l, boolean b){
		Iterator<JToggleButton> it = l.iterator();
		while(it.hasNext()){
			it.next().setSelected(b);
		}
	}
	/**
	 * Applique b à tous les boutons de tous les onglets
	 */
	public void setSelectedButtonArticle(boolean b){
		setSelectedButtonArticle(buttonArticleBoisson,b);
		setSelectedButtonArticle(buttonArticleEntree,b);
		setSelectedButtonArticle(buttonArticlePlat,b);
		setSelectedButtonArticle(buttonArticleDessert,b);
		setSelectedButtonArticle(buttonArticleMenu,b);
	}
	/**
	 * Demande au controleur d'ajouter les articles selectionnés
	 */
	public void ajouterArticlesSelectionnes(ArrayList<JToggleButton> l){
		for(int j=0; j<l.size(); j++){
			if(l.get(j).isSelected()){
				controleur.ajouterArticle(l.get(j).getText(),(int)spinnerQuantite.getValue(),controleur.getNumResaCmdSelectionne());
			}
		}
	}
	public void update(Observable o, Object arg){
	}

	/**
	 * Active les Actions sur les boutons et autres composant de l'inteface
	 *
	 */
	public void activeListener(ActionListener aL){
		buttonRecherche.addActionListener(aL);
		buttonAjout.addActionListener(aL);
		buttonSuppression.addActionListener(aL);
	}

	public JPanel getPanel(){
		return this.panelCommande;
	}
	public JPanel getPanelCommande(){
		return this.panelCommande;
	}
	public JTabbedPane getTabbedPaneArticle(){
		return this.tabbedPaneArticle;
	}
	public JPanel getPanelBoisson(){
		return this.panelBoisson;
	}
	public JPanel getPanelEntree(){
		return this.panelEntree;
	}
	public JPanel getPanelPlat(){
		return this.panelPlat;
	}
	public JPanel getPanelDessert(){
		return this.panelDessert;
	}
	public JPanel getPanelMenu(){
		return this.panelMenu;
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
		return this.buttonRecherche;
	}
	public JButton getButtonAjout(){
		return this.buttonAjout;
	}
	public JButton getButtonSuppression(){
		return this.buttonSuppression;
	}
	public SpinnerModel getModelQuantite(){
		return this.modelQuantite;
	}
	public JSpinner getSpinnerQuantite(){
		return this.spinnerQuantite;
	}

	public ArrayList<JToggleButton> getButtonArticleBoisson(){
		return this.buttonArticleBoisson;
	}
	public ArrayList<JToggleButton> getButtonArticleEntree(){
		return this.buttonArticleEntree;
	}
	public ArrayList<JToggleButton> getButtonArticlePlat(){
		return this.buttonArticlePlat;
	}
	public ArrayList<JToggleButton> getButtonArticleDessert(){
		return this.buttonArticleDessert;
	}
	public ArrayList<JToggleButton> getButtonArticleMenu(){
		return this.buttonArticleMenu;
	}
}
