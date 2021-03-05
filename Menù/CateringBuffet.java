package Menù;
/**
 * Questa classe estende la classe Catering, introducendo i menù a buffet.
 *
 */
public class CateringBuffet extends Catering {
	
	// Le seguenti variabili indicano il prezzo a seconda del numero di invitati.
	private double prezzo20Persone;
	private double prezzo50Persone;
	private double prezzo100Persone;
	
	/**
	 * Costruttore
	 */
	public CateringBuffet (String nomeMenù, double prezzo20Persone, double prezzo50Persone, double prezzo100Persone) {
		super (nomeMenù);
		this.prezzo20Persone = prezzo20Persone;
		this.prezzo50Persone = prezzo50Persone;
		this.prezzo100Persone = prezzo100Persone;
		}
	
	/**
	 * Vari metodi set() utili per modificare le informazioni di uno specifico oggetto.
	 */
	public void setPrezzo20(double prezzo20Persone) {this.prezzo20Persone = prezzo20Persone;}
	public void setPrezzo50(double prezzo50Persone) {this.prezzo50Persone = prezzo50Persone;}
	public void setPrezzo100(double prezzo100Persone) {this.prezzo100Persone = prezzo100Persone;}

	
	
	/**
	 * Vari metodi get() utili per ottenere le informazioni di uno specifico oggetto.
	 */
	public double getPrezzo20() {return prezzo20Persone;}
	public double getPrezzo50() {return prezzo50Persone;}
	public double getPrezzo100() {return prezzo100Persone;}
	
	/**
	 * Questo metodo ritorna una stringa con le principali informazioni sul menù.
	 */
	public String toString () {
		return super.toString() + " Prezzo: " + prezzo20Persone + "€, " + prezzo50Persone + "€, " + prezzo100Persone + "€.";
	}
}
