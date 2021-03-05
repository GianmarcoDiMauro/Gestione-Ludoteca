package Menù;
import java.io.Serializable;
import java.util.Vector;
import java.util.ConcurrentModificationException;

/**
 * Questa classe permettere di creare vari menù per il catering. Al suo interno è indicato il nome del menù, una breve descrizione
 * degli alimenti offerti e una lista di allergeni presenti.
 */
public class Catering implements Serializable {
	
	static final long serialVersionUID = 2;
	private String nomeMenù;
	private String descrizioneMenù;
	private Vector <String> allergeni = new Vector <String> ();
	
	/**
	 * Costruttore
	 */
	public Catering (String nomeMenù) {
		this.nomeMenù = nomeMenù;
	}
	
	/**
	 * Vari metodi set() utili per modificare le informazioni di uno specifico oggetto.
	 */
	public void setNomeMenù (String nomeMenù) {this.nomeMenù = nomeMenù;}
	public void setDescrizioneMenù (String descrizioneMenù) {this.descrizioneMenù = descrizioneMenù;}
	public void setAllergeni (String allergene) {
		allergeni.add(allergene);
	}
	
	/**
	 *  Questo metodo serve a rimuovere un allergene dalla lista, nel caso il menù venisse modificato. Ritorna il valore "true" se 
	 *  l'allergene è stato rimosso, "false" se non è stato trovato.
	 */
	public boolean removeAllergene (String allergene) {
		boolean flag = false;
		
		//La classe Vector può generare un errore se modificata durante una iterazione.
		try {
			for (String x : allergeni) {
				if (x.equalsIgnoreCase(allergene)) {
					allergeni.remove(x);
					flag = true;
				}
			}
		}
		catch (ConcurrentModificationException e) {
			System.out.println("ATTENZIONE: è stato riscontrato un errore.");
			System.out.println(e);
		}
		return flag;
	}
	
	/**
	 * Vari metodi get() che restituiscono le informazioni dell'oggetto.
	 */
	public String getNomeMenù () {return nomeMenù;}
	public Vector getAllergeni () {return allergeni;}
	
	/**
	 * Questo metodo ritorna una stringa con le principali informazioni sul menù.
	 */
	public String toString () {
		return nomeMenù + ": " + descrizioneMenù + " Attenzione: il menù contiene i seguenti allergeni: " + this.getAllergeni();
	}
}
