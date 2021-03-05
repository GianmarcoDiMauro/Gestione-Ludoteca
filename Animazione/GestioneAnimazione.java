package Animazione;
import java.io.Serializable;
import java.util.Vector;
import java.util.ConcurrentModificationException;

import Men�.Catering;
/**
 *  Questa classe permette la gestione dei vari tipi di animazione. Ne crea una lista e permette di visualizzarli in ordine, 
 *  aggiungerne altri o rimuoverne alcuni.
 */
public class GestioneAnimazione {
	static final long serialVersionUID = 1;
	private  Vector <Animazione> lista = new Vector <Animazione> ();
	
	/**
	 *  Costruttore.
	 */
	public GestioneAnimazione () {}
	
	/**
	 * Questo metodo permette di modificare il vettore lista.
	 */
	public void setLista (Vector <Animazione> lista) {
		this.lista = lista;
	}
	
	/**
	 * Questo metodo ritorna il vettore lista.
	 */
	public Vector getLista() {return lista;}
	
	/**
	 *  Questo metodo permette di aggiungere un nuovo tipo di animazione alla lista. Ritorna "true" se l'elemento � stato aggiunto 
	 *  correttamente, false altrimenti.
	 */
	public boolean addAnimazione (Animazione festa) {
		// Controllo che non esista un'altro servizio di animazione memorizzato con lo stesso nome.
		if (!controlloNome(lista, festa)) {
			lista.add(festa);
			return true;
		} else return false;
	}
	
	/**
	 *  Questo metodo permette di rimuovere dei tipi di animazione dalla lista attraverso l'indicazione del nome identificativo.
	 */
	public void removeAnimazione (String nome) {
		
		//La classe Vector pu� generare un errore se viene modificata durante una iterazione. 
		try {
			for (int i = 0; i < lista.size(); i++) {
				if (lista.elementAt(i).getNome().equals(nome)) {
					lista.remove(i);		
				}		
			}
		}
		catch (ConcurrentModificationException e) {
				System.out.println("ATTENZIONE: � stato riscontrato un errore.");
				System.out.println(e);
		}
	}

	/**
	 *  Questo metodo permette di visualizzare tutti i men� disponibili.
	 */
	public void seeAll () {
		for (Animazione x : lista) {
			System.out.println(x.toString());
		}		
	}
	
	/**
	 * Questo metodo ritorna l' oggetto la cui variabile "nome" � uguale alla stringa data come parametro. Se assente,
	 * torner� un oggetto "null".
	 */
	public Animazione getAnimazione (String nome) {
		Animazione oggetto = null;
		for (Animazione x : lista) {
			if (x.getNome().equalsIgnoreCase(nome)) {
				oggetto = x;
			}
		}
		return oggetto;
	}
	
	/* Questo metodo di supporto controlla se esiste gi� un'offerta che possiede lo stesso nome. Ritorna "false" se non esiste un caso
	di omonimia, true altrimenti.
	*/
	private static boolean controlloNome (Vector <Animazione> lista, Animazione oggetto) {
		boolean flag = false;
		for (Animazione x : lista) {
			if (x.getNome().equalsIgnoreCase(oggetto.getNome())) {
				flag = true;
			}
		}
		return flag;
	}
	
}
