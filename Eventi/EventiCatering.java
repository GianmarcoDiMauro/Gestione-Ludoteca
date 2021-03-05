package Eventi;
import java.util.Vector;
import java.time.LocalDate;
import Men�.CateringAlPiatto;
import Men�.CateringBuffet;

/**
 * Questa classe implementa all'evento base la possibilit� di usufruire del Catering. 
 * Registra i vari tipi di men� selezionati per l'evento, permettendo non solo di scegliere tra men� Buffet e men� al Piatto, ma anche
 * di combinare le due proposte. Permette inolte di inserire informazioni sulla torta.
 */
public class EventiCatering extends Eventi{
	
	// Sar� necessario indicare il numero di invitati per calcolare il prezzo.
	private int numeroInvitati;
	// Variabili di istanza che registrano il men� selezionato.
	private CateringBuffet Buffet;
	private CateringAlPiatto Piatto;
	// Variabile di istanza necessaria per registrare, se voluto, il numero di piatti unici da aggiungere ad un servizio Buffet.
	private int quantit�Piatti;
	// Variabile che registra la tipologia di torta desiderata per l'evento.
	private String torta;
	
	/**
	 * Costruttore
	 */
	public EventiCatering(LocalDate data, String nomeReferente, String numeroReferente, int numeroInvitati) {
		super (data, nomeReferente, numeroReferente);
		this.numeroInvitati = numeroInvitati;
	}
	
	/**
	 * Metodo get() che restituisce il numero di invitati.
	 */
	public int getNumeroInvitati() {return numeroInvitati;}
	
	/**
	 * Vari metodi set() utili per modificare le informazioni di uno specifico oggetto.
	 */
	public void setNumeroInvitati(int numeroInvitati) {
		this.numeroInvitati = numeroInvitati;
	}
	public void setBuffet (CateringBuffet Buffet) { this.Buffet = Buffet; }
	public void setPiatto (CateringAlPiatto Piatto) {this.Piatto = Piatto;}
	public void setPiatto (CateringAlPiatto Piatto, int quantit�Piatti) {
		this.Piatto = Piatto;
		this.quantit�Piatti = quantit�Piatti;
	}
	public void setTorta (String torta) {this.torta = torta;}
	
	
	/**
	 * Questo metodo ritorna il costo finale dell'evento. Ritorner� un numero negativo se non � stato selezionato nessun men�.
	 */
	public double getCosto() {
		double sommaBuffet = 0;
		double sommaPiatti = 0;
		//Se non � stato selezionato nessun tipo di Catering, ritorna un valore negativo.
		if (Buffet == null && Piatto == null) {
			return -1;
		} else {
			//Se � stato selezionato un tipo di Buffet, calcola il costo e lo assegna alla variabile "sommaBuffet"
			if (Buffet != null) {
				if (numeroInvitati <= 20) sommaBuffet += Buffet.getPrezzo20();
				else if (numeroInvitati <= 50) sommaBuffet += Buffet.getPrezzo50();
				else sommaBuffet += Buffet.getPrezzo100();
			}
			//Se � stato selezionato un men� al Piatto, calcola il costo e lo assegna alla variabile "sommaPiatti"
			if (Piatto != null) {
				//Controllo se il men� al piatto � previsto per tutti gli invitati o solo per alcuni.
				if (quantit�Piatti == 0) sommaPiatti += Piatto.getPrezzoPiatto() * numeroInvitati;
				else sommaPiatti += Piatto.getPrezzoPiatto() * quantit�Piatti;
			}
			//Aggiunge al prezzo dell'evento base, i prezzi del buffet e dei men� al piatto.
		return super.getCosto() + sommaPiatti + sommaBuffet;
		}
	}
	
	/**
	 * Questo metodo ritorna una stringa con le informazioni principali sull'evento.
	 */
	public String toString () {
		//Se non � stato ancora deciso nessun tipo di catering, stampa le informazioni base. 
		if (Piatto == null && Buffet == null) {
			return super.toString();
		} else {
			//Se � stato scelto il men� Buffet, verr� stampato solo questo.
			if (Piatto == null) {
				return super.toString() + ". Men� buffet: " + Buffet.getNomeMen�() + ", per " + numeroInvitati + " ospiti. Torta: " + torta + ".";
			}
			//Se � stato scelto il men� al piatto, verr� stampato solo questo.
			else if (Buffet == null) {
				return super.toString() + ". Men� al piatto: " + Piatto.getNomeMen�() + ", per " + numeroInvitati + " ospiti. Torta: \" + torta + \".";
			}
			//Se � stata scelta una soluzione mista, verranno stampate entrambe le soluzioni.
			else {
				return super.toString() + ". Men� buffet: " + Buffet.getNomeMen�() + ", per " + numeroInvitati + " ospiti. In aggiunta " + quantit�Piatti + " del men� " + Piatto.getNomeMen�() + ". Torta: \" + torta + \".";
			}
		}
	}
			
}

