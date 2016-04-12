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
  private static final int TAILLE_X_PANEL = 900;
  private static final int TAILLE_Y_PANEL = 900;

  public InterfaceReservation(){
    this.panelReservation = new JPanel();
    this.panelReservation.setLayout(null);
    this.panelReservation.setPreferredSize(new Dimension(TAILLE_X_PANEL,TAILLE_Y_PANEL));

    JLabel labelNomReservation = new JLabel("Nom");
    labelNomReservation.setBounds(0,280,210,20);
    this.panelReservation.add(labelNomReservation);

    JTextField texteNomReservation = new JTextField(20);
    texteNomReservation.setBounds(0,300,210,20);
    this.panelReservation.add(texteNomReservation);

    JTextField textePrenomReservation= new JTextField(20);
    textePrenomReservation.setBounds(0,400,210,20);
    this.panelReservation.add(textePrenomReservation);

    JButton boutonReservation = new JButton("Reserver");
    boutonReservation.setBounds(0,500,210,20);
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
