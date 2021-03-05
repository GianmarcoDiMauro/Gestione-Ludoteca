package Eventi;
import java.time.LocalDate;
import Animazione.Animazione;

/**
 * Questa classe aggiunge la possibilità di avere un servizio di animazione agli eventi con Catering.
 */
public class EventiAnimazione extends EventiCatering{
	private Animazione divertimento;
	
	/**
	 * Costruttore.
	 */
	public EventiAnimazione (LocalDate data, String nomeReferente, String numeroReferente, int numeroInvitati, Animazione divertimento) {
		super(data, nomeReferente, numeroReferente, numeroInvitati);
		this.divertimento = divertimento;
	}
	
	public EventiAnimazione (LocalDate data, String nomeReferente, String numeroReferente, int numeroInvitati) {
		super(data, nomeReferente, numeroReferente, numeroInvitati);
	}
	
	/**
	 * Metodo set() per modificare il tipo di animazione selezionata.
	 */
	public void setDivertimento (Animazione divertimento) {this.divertimento = divertimento;}
	
	/**
	 * Questo metodo ritorna il costo finale dell'evento. Ritornerà un numero negativo se non è stato selezionato nessun
	 *  tipo di animazione o nessun tipo di Catering.
	 */
	public double getCosto() {
		if (divertimento == null) return -1;
		else {
			//Controllo che sia stato selezionato almeno una tipologia di Catering.
			if (super.getCosto() == -1) {
				return -1;
			} else return super.getCosto() + divertimento.getCostoAnimazione();
		}
	}
	
	/**
	 * Questo metodo ritorna una stringa con le informazioni principali sull'evento.
	 */
	public String toString () {
		return super.toString() + " Animazione: " + divertimento.getNome() + ".";
		}
	

}