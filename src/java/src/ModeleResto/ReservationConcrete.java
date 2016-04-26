package ModeleResto;
public class ReservationConcrete extends Reservation{

	private int numeroReservation;
    private Facture fractureDuTibia;
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
}
