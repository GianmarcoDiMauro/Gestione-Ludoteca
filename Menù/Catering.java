package Men�;
import java.io.Serializable;
import java.util.Vector;
import java.util.ConcurrentModificationException;

/**
 * Questa classe permettere di creare vari men� per il catering. Al suo interno � indicato il nome del men�, una breve descrizione
 * degli alimenti offerti e una lista di allergeni presenti.
 */
public class Catering implements Serializable {
	
	static final long serialVersionUID = 2;
	private String nomeMen�;
	private String descrizioneMen�;
	private Vector <String> allergeni = new Vector <String> ();
	
	/**
	 * Costruttore
	 */
	public Catering (String nomeMen�) {
		this.nomeMen� = nomeMen�;
	}
	
	/**
	 * Vari metodi set() utili per modificare le informazioni di uno specifico oggetto.
	 */
	public void setNomeMen� (String nomeMen�) {this.nomeMen� = nomeMen�;}
	public void setDescrizioneMen� (String descrizioneMen�) {this.descrizioneMen� = descrizioneMen�;}
	public void setAllergeni (String allergene) {
		allergeni.add(allergene);
	}
	
	/**
	 *  Questo metodo serve a rimuovere un allergene dalla lista, nel caso il men� venisse modificato. Ritorna il valore "true" se 
	 *  l'allergene � stato rimosso, "false" se non � stato trovato.
	 */
	public boolean removeAllergene (String allergene) {
		boolean flag = false;
		
		//La classe Vector pu� generare un errore se modificata durante una iterazione.
		try {
			for (String x : allergeni) {
				if (x.equalsIgnoreCase(allergene)) {
					allergeni.remove(x);
					flag = true;
				}
			}
		}
		catch (ConcurrentModificationException e) {
			System.out.println("ATTENZIONE: � stato riscontrato un errore.");
			System.out.println(e);
		}
		return flag;
	}
	
	/**
	 * Vari metodi get() che restituiscono le informazioni dell'oggetto.
	 */
	public String getNomeMen� () {return nomeMen�;}
	public Vector getAllergeni () {return allergeni;}
	
	/**
	 * Questo metodo ritorna una stringa con le principali informazioni sul men�.
	 */
	public String toString () {
		return nomeMen� + ": " + descrizioneMen� + " Attenzione: il men� contiene i seguenti allergeni: " + this.getAllergeni();
	}
}
