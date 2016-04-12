package VueResto.LogicielPrincipal;
import VueResto.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class InterfaceCommande extends ObservateurCommande{

	private JPanel panelCommande;
	private JTabbedPane tabbedPaneArticle;
	private JPanel panelBoisson;
	private JTextField textFieldNbTable;
	private JTextField textFieldNom;
	private static final int TAILLE_X_PANEL = 900;
	private static final int TAILLE_Y_PANEL = 900;
	private static final int TAILLE_X_FIELD_TABLE = 100;
	private static final int TAILLE_X_FIELD_NOM = 100;
	private static final int TAILLE_X_PANEL_ARTICLE = 500;
	private static final int TAILLE_Y_PANEL_ARTICLE = 400;
	private static final int PLACEMENT_TAB_ARTICLE = SwingConstants.TOP;
	
	public InterfaceCommande(){
		// PANEL PRINCIPALE
		this.panelCommande = new JPanel();
		panelCommande.setPreferredSize(new Dimension(TAILLE_X_PANEL,TAILLE_Y_PANEL));

		//CHAMP TEXT POUR CHOISIR LE NB DE TABLE
		this.textFieldNbTable = new JTextField ("n TABLE", TAILLE_X_FIELD_TABLE);
		panelCommande.add(textFieldNbTable);
		//CHAMP TEXT POUR CHOISIR LE NOM DE LA RESA
		this.textFieldNom = new JTextField ("NOM", TAILLE_X_FIELD_NOM);
		panelCommande.add(textFieldNom);
		// PANEL QUI VA CONTENIR LES ARTICLES
		this.tabbedPaneArticle = new JTabbedPane(PLACEMENT_TAB_ARTICLE);
		
		
		this.panelBoisson = new JPanel();
        panelBoisson.setPreferredSize(new Dimension(TAILLE_X_PANEL_ARTICLE,TAILLE_Y_PANEL));
		this.tabbedPaneArticle.addTab("BOISSON",panelBoisson);

		this.tabbedPaneArticle.setOpaque(true);
		this.panelCommande.add(tabbedPaneArticle);

	}
	
	public JPanel getPanel(){
		return this.panelCommande;
	}

	public void miseAJour(){
	}
}
