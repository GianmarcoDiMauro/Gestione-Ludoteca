package Men�;
/**
 * Questa classe estende la classe Catering, introducendo i men� al piatto.
 *
 */
public class CateringAlPiatto extends Catering {
	private double prezzoPiatto;
	
	/**
	 * Costruttore
	 */
	public CateringAlPiatto (String nomeMen�, double prezzoPiatto) {
		super (nomeMen�);
		this.prezzoPiatto = prezzoPiatto;
	}
	
	/**
	 * Metodo per modificare il prezzo al piatto.
	 */
	public void setPrezzoPiatto (double prezzoPiatto) {this.prezzoPiatto = prezzoPiatto;}
	
	/**
	 * Metodo per ottenere il prezzo al piatto.
	 */
	
	public double getPrezzoPiatto () {return prezzoPiatto;}
	
	/**
	 * Questo metodo ritorna una stringa con le principali informazioni sul men�.
	 */
	public String toString () {
		return super.toString() + " Prezzo: " + prezzoPiatto + "�.";
	}
}
