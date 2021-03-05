package Menù;
import java.util.ConcurrentModificationException;
import java.util.Vector;

/**
 * Questa classe permette la gestione dei vari menù. Ne crea una lista e permette di visualizzarli in ordine, aggiungerne altri 
 * o rimuoverne alcuni.
 */
public class GestioneMenù {
	private  Vector <Catering> lista = new Vector <Catering> ();
	
	/**
	 *  Costruttore.
	 */
	public GestioneMenù () {}
	
	/**
	 * Questo metodo permette di modificare il vettore "lista".
	 */
	public void setLista (Vector <Catering> lista) {
		this.lista = lista;
	}
	
	/**
	 *  Questo metodo permette di aggiungere un nuovo menù alla lista e lo ordina seguendo una schema lessicografico. 
	 *  Ritorna "true" se l'oggetto è stato aggiunto correttamente, "false" altrimenti.
	 */
	public boolean addMenù (Catering menù) {
		
		//Controllo se esiste già un menù con lo stesso nome identificativo.
		if (!controlloNome(lista, menù)) {
			lista.add(menù);
			riordina(lista);
			return true;
	} else return false;
}
	
	/**
	 *  Questo metodo permette di rimuovere dei menù dalla lista.
	 */
	public void removeMenù (String nomeMenù) {
		
		//La classe Vector può generare un errore se modificata durante una iterazione.
		try {
			for (int i = 0; i < lista.size(); i++) {
				if (lista.elementAt(i).getNomeMenù().equals(nomeMenù)) {
					lista.remove(i);		
				}		
			}
		} 
		catch (ConcurrentModificationException e) {
			System.out.println("ATTENZIONE: è stato riscontrato un errore.");
			System.out.println(e);
		}
	}
	
	/**
	 *  Questo metodo permette di visualizzare tutti i menù disponibili.
	 */
	public void seeAll () {
		for (Catering x : lista) {
			System.out.println(x.toString());
		}		
	}

	/**
	 *  Questo metodo permette di visualizzare tutti i menù a buffet.
	 */
	public void seeBuffet () {
		for (Catering x : lista) {
			if (x instanceof CateringBuffet) {
				System.out.println(x.toString());
			}
		}		
	}
	
	/**
	 *  Questo metodo permette di visualizzare tutti i menù al piatto.
	 */
	public void seePiatto () {
		for (Catering x : lista) {
			if (x instanceof CateringAlPiatto) {
					System.out.println(x.toString());
			}
		}		
	}
	
	/**
	 * Questo metodo ritorna un oggetto la cui variabile "nome" è uguale alla stringa data come parametro. Se assente,
	 * tornerà un oggetto "null".
	 */
	public Catering getMenù (String nomeMenù) {
		Catering oggetto = null;
		for (Catering x : lista) {
			if (x.getNomeMenù().equalsIgnoreCase(nomeMenù)) {
				oggetto = x;
			}
		}
		return oggetto;
	}
	
	/**
	 * Questo metodo ritorna il vettore lista.
	 */
	public Vector getVettoreMenù() {return lista;}
		
	// Questo metodo di supporto riordina secondo un ordine lessicografico i vari menù.
	private static void riordina (Vector <Catering> lista) {
		for (int i=0; i < lista.size()-1; i++) {
			for (int j=i; j< lista.size(); j++) {
				if (lista.elementAt(i).getNomeMenù().compareToIgnoreCase(lista.elementAt(j).getNomeMenù()) >0) {
					lista.add(i, lista.get(j));
					lista.remove(j+1);
				}
			}
		}
	}
	
	/* Questo metodo di supporto controlla se esiste già un menù che possiede lo stesso nome. Ritorna "false" se non esiste un menù
	con lo stesso nome, true altrimenti.
	*/
	private static boolean controlloNome (Vector <Catering> lista, Catering menù) {
		boolean flag = false;
		for (Catering x : lista) {
			if (x.getNomeMenù().equalsIgnoreCase(menù.getNomeMenù())) {
				flag = true;
			}
		}
		return flag;
	}
}
