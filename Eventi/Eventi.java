package Eventi;

import java.io.Serializable;
import java.time.LocalDate;
/**
 * Questa classe gestisce le informazioni base sugli eventi organizzati nella struttura in questione. 
 * Contiene le informazioni sulle tariffe, acconti e pagamenti. Inoltre registra i dettagli dell'evento organizzato, 
 * quali data, recapito telefonico ed una breve descrizione dell'evento.
 */
public class Eventi implements Serializable {
	
	static final long serialVersionUID = 1;
	private LocalDate data;
	
	// Utile per registrare informazioni secondarie: utilizzo di addobbi particolari, segnalazioni di disabilità, ecc.
	private String descrizioneEvento = null;
	
	private String nomeReferente;
	private String numeroReferente;
	
	private static double tariffaAffitto = 150.00;

	// Variabile booleana che registra se è già stata versata la somma per l'organizzazione dell'evento.
	private boolean pagato = false;
	
	// Variabile che registra se è stato versato un acconto o meno.
	private double acconto = 0.00;
	
	/**
	 * Costruttore.
	 */
	public Eventi (LocalDate data, String nomeReferente, String numeroReferente) {
		this.data = data;
		this.nomeReferente = nomeReferente;
		this.numeroReferente = numeroReferente;
	}
	
	/**
	 * Vari metodi get() che restituiscono le informazioni sulla data e sui contatti.
	 */
	
	public LocalDate getData () {return data;}
	public String getNomeReferente() {return nomeReferente;}
	public String getNumeroReferente () {return numeroReferente;}
	public boolean getPagato () {return pagato;}
	public double getAcconto () {return acconto;}
	
	/**
	 * Vari metodi set() utili per modificare le informazioni di uno specifico oggetto.
	 */
	public void setData (LocalDate data) {this.data = data;}
	public void setNumeroReferente (String numeroReferente) {this.numeroReferente = numeroReferente;}
	public void setNomeReferente (String nomeReferente) {this.nomeReferente = nomeReferente;}
	public void setTariffaAffitto (double tariffaAffitto) {this.tariffaAffitto = tariffaAffitto;}
	/**
	 *  Il metodo setAcconto() non sostituisce, ma addiziona i valori di acconto inseriti.
	 */
	public void setAcconto (double acconto) {this.acconto += acconto;}
	/**
	 * Il metodo setPagato() cambia il valore del boolean da "false" a "true" e viceversa. Si dovrà dunque fare attenzione 
	 * al suo utilizzo.
	 */
	public void setPagato () {
		if (!pagato) {
			pagato = true;
		} else {
			pagato = false;
		}
	}
	
	/**
	 * Questi metodi permettono di aggiungere e modificare informazioni secondarie. 
	 */
	public void setDescrizioneEvento (String descrizioneEvento) {this.descrizioneEvento = descrizioneEvento;}
	public void aggiungiAltreInformazioni (String informazioni) {descrizioneEvento = descrizioneEvento + " " + informazioni;}
	
	/** 
	 * Questo metodo restituisce il costo finale dell'evento. 
	 */
	public double getCosto () {return tariffaAffitto;}
	
	/**
	 * Questo metodo restituisce i soldi ancora da versare per saldare il pagamento.
	 */
	public double daPagare () {
		//Se l'evento è già stato saldato (quindi pagato = true), il metodo tornerà il valore 0.
		if (pagato) {
			return 0;
		} else {
			// Sottraggo al costo complessivo il totale degli acconti già versati.
			return this.getCosto() - acconto;
		}
	}
	/**
	 * Questo metodo ritorna una stringa con le informazioni principali sull'evento.
	 */
	public String toString () {return data.toString() + ". Cliente : " + nomeReferente + " (" + numeroReferente + "). Da pagare: " +
			daPagare() + "€. "+ descrizioneEvento;
	}
	
	
	
	

}
