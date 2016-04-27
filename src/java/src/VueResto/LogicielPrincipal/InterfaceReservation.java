package VueResto.LogicielPrincipal;
import VueResto.*;
import VueResto.LogicielPrincipal.*;
import ModeleResto.*;
import ControleurResto.*;
import java.util.*;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.*;
import java.text.SimpleDateFormat;
import javax.swing.table.*;
import java.awt.*;
import ControleurResto.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.text.SimpleDateFormat;

/** classe d'interface de reservation contenant le panel de reservation a afficher dans l'interface principale du logiciel.
 *
 *
 */
public class InterfaceReservation extends Observateur{
    private JPanel panelReservation;
    private JScrollPane jScrollPane;
    private static final int TAILLE_X_PANEL = 980;
    private static final int TAILLE_Y_PANEL = 980;
    private static final int TAILLE_Y_BOUTON = 20;
    private static final int TAILLE_X_BOUTON = 200;
    private RModel rModel;
    private String titre[] = {"Nom","n° Réservation","n° Table","Nombre de personnes","Date","Service"};
    private JTable tableau;
    private static final int POS_X_TABLE = 10;
    private static final int POS_Y_TABLE = 30;
    private static final int TAILLE_X_FIELD_TABLE = 100;
    private static final int TAILLE_Y_FIELD_TABLE = 0;
    private static final int TAILLE_X_TAB = 650;
    private static final int TAILLE_Y_TAB = 550;
    private static final int POS_X_TAB = TAILLE_X_BOUTON + 10 + 10;
    private static final int POS_Y_TAB = TAILLE_Y_FIELD_TABLE + 10;
    private static final int TAILLE_LIGNE = 20;
    private JButton boutonReservation = new JButton("Reserver");
    private JLabel labelNomReservation = new JLabel("Nom (Obligatoire)");
    private JButton boutonSupprimer= new JButton("Supprimer");
    private JTextField texteNomReservation = new JTextField(20);
    private JLabel labelTelephoneReservation = new JLabel("Telephone (Obligatoire)");
    private JTextField texteTelephoneReservation = new JTextField(20);
    private JLabel labelNombrePersonnes= new JLabel("Nombre de personnes");
    private SpinnerModel modelNombrePersonnes = new SpinnerNumberModel(1,1,100,1);     
    private JSpinner spinnerNombrePersonnes = new JSpinner(modelNombrePersonnes);
    private JLabel labelDate= new JLabel("Date");
    private SpinnerModel modelDate= new SpinnerDateModel();     
    private JSpinner spinnerDate = new JSpinner(modelDate);
    private JSpinner.DateEditor editorDate = new JSpinner.DateEditor(spinnerDate, "dd/MM/yyyy");
    private JLabel labelService = new JLabel("Service");
    private String[] service = {"MIDI","SOIR"};
    private JComboBox<String> comboBoxService= new JComboBox<String>(service);
    private JLabel labelLocalisation = new JLabel("Localisation");
    private JTextField texteLocalisation = new JTextField(20);
    private JButton boutonAcutaliser= new JButton("Actualiser");

    /** constructeur du panel de l'interface de reservation
     * 
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

        labelTelephoneReservation.setBounds(5,nbBoutons*TAILLE_Y_BOUTON,TAILLE_X_BOUTON,TAILLE_Y_BOUTON);
        nbBoutons++;
        this.panelReservation.add(labelTelephoneReservation);

        texteTelephoneReservation.setBounds(5,nbBoutons*TAILLE_Y_BOUTON,TAILLE_X_BOUTON,TAILLE_Y_BOUTON);
        nbBoutons++;
        this.panelReservation.add(texteTelephoneReservation);

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

        // Tableau contenant les commandes :
        Object[][] data = {};

        this.rModel = new RModel(data,titre);
        //this.tableau = new JTable(sModel);
        this.tableau = new JTable(new DefaultTableModel(data,titre));
        this.tableau.setRowHeight(TAILLE_LIGNE);
        this.tableau.setBounds(POS_X_TAB,POS_Y_TAB,TAILLE_X_TAB,TAILLE_Y_TAB);
        this.jScrollPane = new JScrollPane(tableau);
        jScrollPane.setBounds(POS_X_TAB,POS_Y_TAB,TAILLE_X_TAB,TAILLE_Y_TAB);
        panelReservation.add( jScrollPane);
        // mise à jour du tableau 

        boutonAcutaliser.setBounds(POS_X_TAB,POS_Y_TAB + TAILLE_Y_TAB+10,TAILLE_X_BOUTON,TAILLE_Y_BOUTON);
        this.panelReservation.add(boutonAcutaliser);

        boutonSupprimer.setBounds(POS_X_TAB + TAILLE_X_BOUTON + 5 ,POS_Y_TAB + TAILLE_Y_TAB+10,TAILLE_X_BOUTON,TAILLE_Y_BOUTON);
        this.panelReservation.add(boutonSupprimer);

        this.miseAjourTableauReservation();

    }


    public void effetBoutonSupprimer(){
        System.out.println("effetBoutonSupprimer");
        int selectedRow = this.tableau.getSelectedRow();
        if(selectedRow >= 0){
            System.out.println("Selected row : " + selectedRow );
            int numeroReservation = (int) (this.tableau.getModel()).getValueAt(selectedRow,1);
            String date = (String) (this.tableau.getModel()).getValueAt(selectedRow,4);
            int table= Controleur.get().getNumeroTables(numeroReservation).get(0);
            String service = (String) (this.tableau.getModel()).getValueAt(selectedRow,5);
            System.out.println("Numero Reservation: " + numeroReservation);
            System.out.println("Date : " + date);
            System.out.println("table: " + table);
            System.out.println("service: " + service);
            if (numeroReservation > 0){
                System.out.println("suppression reservation");
                Controleur.get().supprimerReservation(table,date,service);
            }
            this.miseAjourTableauReservation();
        }
    }

    public void effetBoutonActualiser(){
        System.out.println("effetBoutonActualiser");
        this.miseAjourTableauReservation();
    }

    public void miseAjourTableauReservation(){
        LinkedList<Integer> listeReservations = new LinkedList<Integer>();
        ((DefaultTableModel)this.tableau.getModel()).getDataVector().removeAllElements();
        ((DefaultTableModel)this.tableau.getModel()).fireTableDataChanged();

        listeReservations = Controleur.get().getListeReservations();
        if(!listeReservations.isEmpty()){
            System.out.println("Une ou  plusieurs réservations ont été trouvées ");
        }
        else{
            System.out.println("liste vide");
        }

        for(int numeroReservationCourant : listeReservations){
            // On affiche les reservations trouvées
            String etatCommande = Controleur.get().getEtatCommande(numeroReservationCourant);
            String nomCommande = Controleur.get().getNom(numeroReservationCourant);
            String date = Controleur.get().getDateNow();
            String tempsEtat = Controleur.get().getDateNow();
            System.out.println(etatCommande);
            String table = Controleur.get().getNumeroTables(numeroReservationCourant).toString();
            String serviceCourant = Controleur.get().getService(numeroReservationCourant);
            String nbPersonnes = Integer.toString(Controleur.get().getNombrePersonnes(numeroReservationCourant));

            Object[] o = {nomCommande,numeroReservationCourant,table,nbPersonnes,date,serviceCourant};
            ((DefaultTableModel)this.tableau.getModel()).addRow(o);
            ((DefaultTableModel)this.tableau.getModel()).fireTableDataChanged();
        }

    }

    public void activeListener(ActionListener aL){
        boutonReservation.addActionListener(aL);
        boutonSupprimer.addActionListener(aL);
        boutonAcutaliser.addActionListener(aL);
    }

    /** permet d'acceder au bouton de reservation de l'interface de reservation
     * 
     * @return bouton de reservation
     */
    public JButton getBoutonReservation(){
        return this.boutonReservation;
    } 

    public JButton getBoutonActualiser(){
        return this.boutonAcutaliser;
    } 

    public JButton getBoutonSupprimer(){
        return this.boutonSupprimer;
    }

    /** permet d'acceder à l'étiquette du nom de l'interface de reservation
     * 
     * @return etiquette du nom de reservation
     */
    public JLabel getLabelNomReservation(){
        return this.labelNomReservation;
    } 

    /** permet d'acceder au contenu du champ textuel du nom de l'interface de reservation
     * 
     * @return champ de texte du nom de reservation
     */
    public JTextField getTexteNomReservation(){
        return this.texteNomReservation;
    } 

    /** permet d'acceder a l'etiquette du telephone  de l'interface de reservation
     * 
     * @return etiquette du prenom de reservation
     */
    public JLabel getLabelTelephoneReservation(){
        return this.labelTelephoneReservation;
    } 

    /** permet d'acceder au contenu du champ textuel du telephone de l'interface de reservation
     * 
     * @return champ du texte du prenom de reservation
     */
    public JTextField getTexteTelephoneReservation(){
        return this.texteTelephoneReservation;
    } 

    /** permet d'acceder a l'etiquette du nombre de personnes de l'interface de reservation
     * 
     * @return etiquette du nombre de personnes
     */
    public JLabel getLabelNombrePersonnes(){
        return this.labelNombrePersonnes;
    }

    /** permet d'acceder au modele du spinner du nombre de personnes de l'interface de reservation
     * 
     * @return modele du spinner du nombre de personnes
     */
    public SpinnerModel getModelNombrePersonnes(){
        return this.modelNombrePersonnes;
    } 

    /** permet d'acceder au spinner du nombre de personnes de l'interface de reservation
     * 
     * @return spinner du nombre de personnes
     */
    public JSpinner getSpinnerNombrePersonnes(){
        return this.spinnerNombrePersonnes;
    } 

    /** permet d'acceder a l'etiquette de la date de l'interface de reservation
     * 
     * @return etiquette de la date
     */
    public JLabel getLabelDate(){
        return this.labelDate;
    }

    /** permet d'acceder au modele de la date de l'interface de reservation
     * 
     * @return modele du spinner de la date
     */
    public SpinnerModel getModelDate(){
        return this.modelDate;
    }


    /** permet d'acceder au spinner de la date
     * 
     * @return spinner de la date
     */
    public JSpinner getSpinnerDate(){
        return this.spinnerDate;
    } 

    /** permet d'acceder a l'editeur du spinner de la date
     * 
     * @return editeur du spinner de la date
     */
    public JSpinner.DateEditor editorDate(){
        return this.editorDate;
    }

    /** permet d'acceder a l'etiquette du service de l'interface de reservation
     * 
     * @return etiquette du service
     */
    public JLabel getLabelService(){
        return this.labelService;
    } 


    /** permet d'acceder au differents services
     * 
     * @return tableau de chaines des services
     */
    public String[] getService(){
        return this.service;
    }

    /** permet d'acceder a la combobox du service de l'interface de reservation
     * 
     * @return combobox du service
     */
    public JComboBox<String> getComboBoxService(){
        return this.comboBoxService;
    }

    /** permet d'acceder a l'etiquette de la localisation de l'interface de reservation
     * 
     * @return etiquette de localisation
     */
    public JLabel getLabelLocalisation(){
        return this.labelLocalisation;
    } 

    /** permet d'acceder au champ de texte de la localisation de l'interface de reservation
     * 
     * @return champ textuel de la localisation 
     */
    public JTextField getTexteLocalisation(){
        return this.texteLocalisation;
    }

    /** permet d'acceder au panel de l'interface de reservation
     * 
     * @return panel de l'interface de reservation
     */
    public JPanel getPanel(){
        return this.panelReservation;
    }

    /** permet de modifier les dimensions du panel de l'interface de reservation
     * @param x abscisse
     * @param y ordonnee
     * 
     */
    public void setDimension(int x, int y){
        this.panelReservation.setPreferredSize(new Dimension(x,y));
    }

    /** !!!NON IMPLEMENTEE!! permet de mettre a jour les donnees de l'interface de reservation
     * @param o observable
     * @param arg argument
     * 
     */
    public void update(Observable o, Object arg){
      System.out.println("update Reservation");
      if ( o instanceof ModeleResto.ReservationConcrete){
        System.out.println("update Reservation maj");
        miseAjourTableauReservation();
      }
    }
    /**
     * Cette classe est un TableModel Pour le tableau de suivi des commande
     *
     */
    public class RModel extends AbstractTableModel {
        private Object[][] data;
        private String[] titre;

        public void setData(Object[][] data){
            this.data = data;
        }

        /** accesseur du tableau
         *
         * @return tableau de RModel
         */
        public Object[][] getData(){
            return this.data;
        }

        //Constructeur
        public RModel(Object[][] data, String[] titre){
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
         * @param col numero de colonne
         * @return chaine de caractere dans la colonne demandee
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
