package ModeleResto;
public class ReservationConcrete extends Reservation{

	private int numeroReservation;
    private Facture facture;
    private SuiviCommande suivi;

    public ReservationConcrete(int numeroReservation){
		this.suivi = new SuiviCommande();
		this.numeroReservation = numeroReservation;
    }
	public SuiviCommande getSuivi(){
		return this.suivi;
	}
	public int getNumeroReservation(){
		return this.numeroReservation;
	}
	public Facture getFacture(){
		return this.facture;
	}
	public void setFacture(Facture f){
		this.facture = f;
	}
    
    public void changed(){
      setChanged();
    }
}
