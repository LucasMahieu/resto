package VueResto.LogicielPrincipal;
import VueResto.*;
import VueResto.LogicielPrincipal.*;
import ModeleResto.*;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.*;

public class InterfaceReservation extends ObservateurReservation{
  private JPanel panelReservation;
  private static final int TAILLE_X_PANEL = 980;
  private static final int TAILLE_Y_PANEL = 980;
  private static final int TAILLE_Y_BOUTON = 20;
  private static final int TAILLE_X_BOUTON = 200;


  public InterfaceReservation(){
    int nbBoutons = 0;
    this.panelReservation = new JPanel();
    this.panelReservation.setLayout(null);
    this.panelReservation.setPreferredSize(new Dimension(TAILLE_X_PANEL,TAILLE_Y_PANEL));

    JLabel labelNomReservation = new JLabel("Nom");
    labelNomReservation.setBounds(5,nbBoutons * TAILLE_Y_BOUTON,TAILLE_X_BOUTON,TAILLE_Y_BOUTON);
    nbBoutons++;
    this.panelReservation.add(labelNomReservation);

    JTextField texteNomReservation = new JTextField(20);
    texteNomReservation.setBounds(5,nbBoutons * TAILLE_Y_BOUTON,TAILLE_X_BOUTON,TAILLE_Y_BOUTON);
    nbBoutons++;
    this.panelReservation.add(texteNomReservation);

    JLabel labelPrenomReservation = new JLabel("Prenom");
    labelPrenomReservation.setBounds(5,nbBoutons*TAILLE_Y_BOUTON,TAILLE_X_BOUTON,TAILLE_Y_BOUTON);
    nbBoutons++;
    this.panelReservation.add(labelPrenomReservation);

    JTextField textePrenomReservation= new JTextField(20);
    textePrenomReservation.setBounds(5,nbBoutons*TAILLE_Y_BOUTON,TAILLE_X_BOUTON,TAILLE_Y_BOUTON);
    nbBoutons++;
    this.panelReservation.add(textePrenomReservation);

    JLabel labelNombrePersonnes= new JLabel("Nombre de personnes");
    labelNombrePersonnes.setBounds(5,nbBoutons*TAILLE_Y_BOUTON,TAILLE_X_BOUTON,TAILLE_Y_BOUTON);
    nbBoutons++;
    this.panelReservation.add(labelNombrePersonnes);

    SpinnerModel modelNombrePersonnes= new SpinnerNumberModel(1,1,100,1);     
    JSpinner spinnerNombrePersonnes = new JSpinner(modelNombrePersonnes);
    spinnerNombrePersonnes.setBounds(5,nbBoutons*TAILLE_Y_BOUTON,TAILLE_X_BOUTON,TAILLE_Y_BOUTON);
    nbBoutons++;
    this.panelReservation.add(spinnerNombrePersonnes);

    JLabel labelDate= new JLabel("Date");
    labelDate.setBounds(5,nbBoutons*TAILLE_Y_BOUTON,TAILLE_X_BOUTON,TAILLE_Y_BOUTON);
    nbBoutons++;
    this.panelReservation.add(labelDate);

    SpinnerModel modelDate= new SpinnerDateModel();     
    JSpinner spinnerDate = new JSpinner(modelDate);
    JSpinner.DateEditor editorDate = new JSpinner.DateEditor(spinnerDate, "dd-MM-yyyy");
    spinnerDate.setEditor(editorDate);
    spinnerDate.setBounds(5,nbBoutons*TAILLE_Y_BOUTON,TAILLE_X_BOUTON,TAILLE_Y_BOUTON);
    nbBoutons++;
    this.panelReservation.add(spinnerDate);

    JLabel labelService= new JLabel("Service");
    labelService.setBounds(5,nbBoutons*TAILLE_Y_BOUTON,TAILLE_X_BOUTON,TAILLE_Y_BOUTON);
    nbBoutons++;
    this.panelReservation.add(labelService);

    String[] service = {"Midi","Soir"};
    JComboBox<String> comboBoxService= new JComboBox<String>(service);
    comboBoxService.setBounds(5,nbBoutons*TAILLE_Y_BOUTON,TAILLE_X_BOUTON,TAILLE_Y_BOUTON);
    nbBoutons++;
    this.panelReservation.add(comboBoxService);

    JButton boutonReservation = new JButton("Reserver");
    boutonReservation.setBounds(5,nbBoutons*TAILLE_Y_BOUTON,TAILLE_X_BOUTON,TAILLE_Y_BOUTON);
    nbBoutons++;
    this.panelReservation.add(boutonReservation);

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
