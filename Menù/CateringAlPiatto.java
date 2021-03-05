package Menù;
/**
 * Questa classe estende la classe Catering, introducendo i menù al piatto.
 *
 */
public class CateringAlPiatto extends Catering {
	private double prezzoPiatto;
	
	/**
	 * Costruttore
	 */
	public CateringAlPiatto (String nomeMenù, double prezzoPiatto) {
		super (nomeMenù);
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
	 * Questo metodo ritorna una stringa con le principali informazioni sul menù.
	 */
	public String toString () {
		return super.toString() + " Prezzo: " + prezzoPiatto + "€.";
	}
}
