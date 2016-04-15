package VueResto.LogicielPrincipal;
import VueResto.*;
import VueResto.LogicielPrincipal.*;
import ModeleResto.*;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;

/** classe d'interface de reservation contenant le panel de reservation a afficher dans l'interface principale du logiciel.
 *
 *
 */
public class InterfaceReservation extends ObservateurReservation{
  private JPanel panelReservation;
  private static final int TAILLE_X_PANEL = 980;
  private static final int TAILLE_Y_PANEL = 980;
  private static final int TAILLE_Y_BOUTON = 20;
  private static final int TAILLE_X_BOUTON = 200;
  private JButton boutonReservation = new JButton("Reserver");
  private JLabel labelNomReservation = new JLabel("Nom (Obligatoire)");
  private JTextField texteNomReservation = new JTextField(20);
  private JLabel labelPrenomReservation = new JLabel("Prenom (Obligatoire)");
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

  /** constructeur du panel de l'interface de reservation
   * 
   * @returns interface de reservation
   */
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


  /** permet d'acceder au bouton de reservation de l'interface de reservation
   * 
   * @returns bouton de reservation
   */
  public JButton getBoutonReservation(){
    return this.boutonReservation;
  } 

  /** permet d'acceder à l'étiquette du nom de l'interface de reservation
   * 
   * @returns etiquette du nom de reservation
   */
  public JLabel getLabelNomReservation(){
    return this.labelNomReservation;
  } 

  /** permet d'acceder au contenu du champ textuel du nom de l'interface de reservation
   * 
   * @returns champ de texte du nom de reservation
   */
  public JTextField getTexteNomReservation(){
    return this.texteNomReservation;
  } 

  /** permet d'acceder a l'etiquette du prenom de l'interface de reservation
   * 
   * @returns etiquette du prenom de reservation
   */
  public JLabel getLabelPrenomReservation(){
    return this.labelPrenomReservation;
  } 

  /** permet d'acceder au contenu du champ textuel du prenom de l'interface de reservation
   * 
   * @returns champ du texte du prenom de reservation
   */
  public JTextField getTextePrenomReservation(){
    return this.textePrenomReservation;
  } 

  /** permet d'acceder a l'etiquette du nombre de personnes de l'interface de reservation
   * 
   * @returns etiquette du nombre de personnes
   */
  public JLabel getLabelNombrePersonnes(){
    return this.labelNombrePersonnes;
  }

  /** permet d'acceder au modele du spinner du nombre de personnes de l'interface de reservation
   * 
   * @returns modele du spinner du nombre de personnes
   */
  public SpinnerModel getModelNombrePersonnes(){
    return this.modelNombrePersonnes;
  } 

  /** permet d'acceder au spinner du nombre de personnes de l'interface de reservation
   * 
   * @returns spinner du nombre de personnes
   */
  public JSpinner getSpinnerNombrePersonnes(){
    return this.spinnerNombrePersonnes;
  } 

  /** permet d'acceder a l'etiquette de la date de l'interface de reservation
   * 
   * @returns etiquette de la date
   */
  public JLabel getLabelDate(){
    return this.labelDate;
  }

  /** permet d'acceder au modele de la date de l'interface de reservation
   * 
   * @returns modele du spinner de la date
   */
  public SpinnerModel getModelDate(){
    return this.modelDate;
  }


  /** permet d'acceder au spinner de la date
   * 
   * @returns spinner de la date
   */
  public JSpinner getSpinnerDate(){
    return this.spinnerDate;
  } 

  /** permet d'acceder a l'editeur du spinner de la date
   * 
   * @returns editeur du spinner de la date
   */
  public JSpinner.DateEditor editorDate(){
    return this.editorDate;
  }

  /** permet d'acceder a l'etiquette du service de l'interface de reservation
   * 
   * @returns etiquette du service
   */
  public JLabel getLabelService(){
    return this.labelService;
  } 


  /** permet d'acceder au differents services
   * 
   * @returns tableau de chaines des services
   */
  public String[] getService(){
    return this.service;
  }

  /** permet d'acceder a la combobox du service de l'interface de reservation
   * 
   * @returns combobox du service
   */
  public JComboBox<String> getComboBoxService(){
    return this.comboBoxService;
  }

  /** permet d'acceder a l'etiquette de la localisation de l'interface de reservation
   * 
   * @returns etiquette de localisation
   */
  public JLabel getLabelLocalisation(){
    return this.labelLocalisation;
  } 

  /** permet d'acceder au champ de texte de la localisation de l'interface de reservation
   * 
   * @returns champ textuel de la localisation 
   */
  public JTextField getTexteLocalisation(){
    return this.texteLocalisation;
  }

  /** permet d'acceder au panel de l'interface de reservation
   * 
   * @returns panel de l'interface de reservation
   */
  public JPanel getPanel(){
    return this.panelReservation;
  }

  /** permet de modifier les dimensions du panel de l'interface de reservation
   * 
   */
  public void setDimension(int x, int y){
    this.panelReservation.setPreferredSize(new Dimension(x,y));
  }

  /** !!!NON IMPLEMENTEE!! permet de mettre a jour les donnees de l'interface de reservation
   * 
   */
  public void miseAJour(){
  }
}
