package Animazione;
import java.io.Serializable;
/**
 *Questa classe implementa diversi tipi di animazione per gli eventi.
 */
public class Animazione implements Serializable {
	static final long serialVersionUID = 3;
	private String nome;
	private double costoAnimazione;
	private String descrizione;
	
	/**
	 * Costruttore
	 */
	public Animazione (String nome, double costo) {
		this.nome = nome;
		this.costoAnimazione = costo;
	}
	
	/**
	 * Vari metodi get() che restituiscono le informazioni sull'animazione.
	 */
	public String getNome() {return nome;}
	public double getCostoAnimazione() {return costoAnimazione;}
	
	/**
	 * Vari metodi set() utili per modificare le informazioni di uno specifico oggetto.
	 */
	public void setNome(String nome) {this.nome = nome;}
	public void setCostoAnimazione(double costo) {this.costoAnimazione = costo;}
	public void setDescrizione(String descrizione) {this.descrizione = descrizione;}
	
	/**
	 * Questo metodo ritorna una stringa con le informazioni principali sull'animazione.
	 */
	public String toString() {
		return nome + ": " + descrizione + " Costo di: " + costoAnimazione +"€.";
	}
}