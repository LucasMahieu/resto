package VueResto.LogicielPrincipal;
import VueResto.*;
import VueResto.LogicielPrincipal.*;
import ModeleResto.*;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;

public class InterfaceReservation extends ObservateurReservation{
	private JPanel panelReservation;
	private static final int TAILLE_X_PANEL = 980;
	private static final int TAILLE_Y_PANEL = 980;
	private static final int TAILLE_Y_BOUTON = 20;
	private static final int TAILLE_X_BOUTON = 200;
	private JButton boutonReservation = new JButton("Reserver");
	private JLabel labelTest = new JLabel("Localisation");
	private JLabel labelNomReservation = new JLabel("Nom");
	private JTextField texteNomReservation = new JTextField(20);
	private JLabel labelPrenomReservation = new JLabel("Prenom");
	private JTextField textePrenomReservation = new JTextField(20);
	private JLabel labelNombrePersonnes= new JLabel("Nombre de personnes");
	private SpinnerModel modelNombrePersonnes = new SpinnerNumberModel(1,1,100,1);     
	private JSpinner spinnerNombrePersonnes = new JSpinner(modelNombrePersonnes);
	private JLabel labelDate= new JLabel("Date");
	private SpinnerModel modelDate= new SpinnerDateModel();     
	private JSpinner spinnerDate = new JSpinner(modelDate);
	private JSpinner.DateEditor editorDate = new JSpinner.DateEditor(spinnerDate, "dd-MM-yyyy");
	private JLabel labelService = new JLabel("Service");
	private String[] service = {"Midi","Soir"};
	private JComboBox<String> comboBoxService= new JComboBox<String>(service);
	private JLabel labelLocalisation = new JLabel("Localisation");
	private JTextField texteLocalisation = new JTextField(20);

	public InterfaceReservation(){
		int nbBoutons = 0;
		this.panelReservation = new JPanel();
		this.panelReservation.setLayout(null);
		this.panelReservation.setPreferredSize(new Dimension(TAILLE_X_PANEL,TAILLE_Y_PANEL));

		labelNomReservation.setBounds(5,nbBoutons * TAILLE_Y_BOUTON,TAILLE_X_BOUTON,TAILLE_Y_BOUTON);
		nbBoutons++;
		this.panelReservation.add(labelNomReservation);

		texteNomReservation.setBounds(5,nbBoutons * TAILLE_Y_BOUTON,TAILLE_X_BOUTON,TAILLE_Y_BOUTON);
		nbBoutons++;
		this.panelReservation.add(texteNomReservation);

		labelPrenomReservation.setBounds(5,nbBoutons*TAILLE_Y_BOUTON,TAILLE_X_BOUTON,TAILLE_Y_BOUTON);
		nbBoutons++;
		this.panelReservation.add(labelPrenomReservation);

		textePrenomReservation.setBounds(5,nbBoutons*TAILLE_Y_BOUTON,TAILLE_X_BOUTON,TAILLE_Y_BOUTON);
		nbBoutons++;
		this.panelReservation.add(textePrenomReservation);

		labelNombrePersonnes.setBounds(5,nbBoutons*TAILLE_Y_BOUTON,TAILLE_X_BOUTON,TAILLE_Y_BOUTON);
		nbBoutons++;
		this.panelReservation.add(labelNombrePersonnes);

		spinnerNombrePersonnes.setBounds(5,nbBoutons*TAILLE_Y_BOUTON,TAILLE_X_BOUTON,TAILLE_Y_BOUTON);
		nbBoutons++;
		this.panelReservation.add(spinnerNombrePersonnes);

		labelDate.setBounds(5,nbBoutons*TAILLE_Y_BOUTON,TAILLE_X_BOUTON,TAILLE_Y_BOUTON);
		nbBoutons++;
		this.panelReservation.add(labelDate);

		spinnerDate.setEditor(editorDate);
		spinnerDate.setBounds(5,nbBoutons*TAILLE_Y_BOUTON,TAILLE_X_BOUTON,TAILLE_Y_BOUTON);
		nbBoutons++;
		this.panelReservation.add(spinnerDate);

		labelService.setBounds(5,nbBoutons*TAILLE_Y_BOUTON,TAILLE_X_BOUTON,TAILLE_Y_BOUTON);
		nbBoutons++;
		this.panelReservation.add(labelService);

		comboBoxService.setBounds(5,nbBoutons*TAILLE_Y_BOUTON,TAILLE_X_BOUTON,TAILLE_Y_BOUTON);
		nbBoutons++;
		this.panelReservation.add(comboBoxService);

		labelLocalisation.setBounds(5,nbBoutons*TAILLE_Y_BOUTON,TAILLE_X_BOUTON,TAILLE_Y_BOUTON);
		nbBoutons++;
		this.panelReservation.add(labelLocalisation);

		texteLocalisation.setBounds(5,nbBoutons*TAILLE_Y_BOUTON,TAILLE_X_BOUTON,TAILLE_Y_BOUTON);
		nbBoutons++;
		this.panelReservation.add(texteLocalisation);

		boutonReservation.setBounds(5,nbBoutons*TAILLE_Y_BOUTON,TAILLE_X_BOUTON,TAILLE_Y_BOUTON);
		nbBoutons++;
		this.panelReservation.add(boutonReservation);
	}

	public void activeListener(ActionListener aL){
		boutonReservation.addActionListener(aL);
	}
	public JButton getBoutonReservation(){
		return this.boutonReservation;
	} 
	public JLabel getLabelTest(){
		return this.labelTest;
	} 
	public JLabel getLabelNomReservation(){
		return this.labelNomReservation;
	} 
	public JTextField getTexteNomReservation(){
		return this.texteNomReservation;
	} 
	public JLabel getLabelPrenomReservation(){
		return this.labelPrenomReservation;
	} 
	public JTextField getTextePrenomReservation(){
		return this.textePrenomReservation;
	} 
	public JLabel getLabelNombrePersonnes(){
		return this.labelNombrePersonnes;
	}
	public SpinnerModel getModelNombrePersonnes(){
		return this.modelNombrePersonnes;
	} 
	public JSpinner getSpinnerNombrePersonnes(){
		return this.spinnerNombrePersonnes;
	} 
	public JLabel getLabelDate(){
		return this.labelDate;
	}
	public SpinnerModel getModelDate(){
		return this.modelDate;
	}
	public JSpinner getSpinnerDate(){
		return this.spinnerDate;
	} 
	public JSpinner.DateEditor editorDate(){
		return this.editorDate;
	}
	public JLabel getLabelService(){
		return this.labelService;
	} 
	public String[] getService(){
		return this.service;
	}
	public JComboBox<String> getComboBoxService(){
		return this.comboBoxService;
	}
	public JLabel getLabelLocalisation(){
		return this.labelLocalisation;
	} 
	public JTextField getTexteLocalisation(){
		return this.texteLocalisation;
	}

	public JPanel getPanel(){
		return this.panelReservation;
	}

	public void setDimension(int x, int y){
		this.panelReservation.setPreferredSize(new Dimension(x,y));
	}

	public void miseAJour(){
	}
}
