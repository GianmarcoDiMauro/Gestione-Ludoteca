package Men�;
import java.util.ConcurrentModificationException;
import java.util.Vector;

/**
 * Questa classe permette la gestione dei vari men�. Ne crea una lista e permette di visualizzarli in ordine, aggiungerne altri 
 * o rimuoverne alcuni.
 */
public class GestioneMen� {
	private  Vector <Catering> lista = new Vector <Catering> ();
	
	/**
	 *  Costruttore.
	 */
	public GestioneMen� () {}
	
	/**
	 * Questo metodo permette di modificare il vettore "lista".
	 */
	public void setLista (Vector <Catering> lista) {
		this.lista = lista;
	}
	
	/**
	 *  Questo metodo permette di aggiungere un nuovo men� alla lista e lo ordina seguendo una schema lessicografico. 
	 *  Ritorna "true" se l'oggetto � stato aggiunto correttamente, "false" altrimenti.
	 */
	public boolean addMen� (Catering men�) {
		
		//Controllo se esiste gi� un men� con lo stesso nome identificativo.
		if (!controlloNome(lista, men�)) {
			lista.add(men�);
			riordina(lista);
			return true;
	} else return false;
}
	
	/**
	 *  Questo metodo permette di rimuovere dei men� dalla lista.
	 */
	public void removeMen� (String nomeMen�) {
		
		//La classe Vector pu� generare un errore se modificata durante una iterazione.
		try {
			for (int i = 0; i < lista.size(); i++) {
				if (lista.elementAt(i).getNomeMen�().equals(nomeMen�)) {
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
		for (Catering x : lista) {
			System.out.println(x.toString());
		}		
	}

	/**
	 *  Questo metodo permette di visualizzare tutti i men� a buffet.
	 */
	public void seeBuffet () {
		for (Catering x : lista) {
			if (x instanceof CateringBuffet) {
				System.out.println(x.toString());
			}
		}		
	}
	
	/**
	 *  Questo metodo permette di visualizzare tutti i men� al piatto.
	 */
	public void seePiatto () {
		for (Catering x : lista) {
			if (x instanceof CateringAlPiatto) {
					System.out.println(x.toString());
			}
		}		
	}
	
	/**
	 * Questo metodo ritorna un oggetto la cui variabile "nome" � uguale alla stringa data come parametro. Se assente,
	 * torner� un oggetto "null".
	 */
	public Catering getMen� (String nomeMen�) {
		Catering oggetto = null;
		for (Catering x : lista) {
			if (x.getNomeMen�().equalsIgnoreCase(nomeMen�)) {
				oggetto = x;
			}
		}
		return oggetto;
	}
	
	/**
	 * Questo metodo ritorna il vettore lista.
	 */
	public Vector getVettoreMen�() {return lista;}
		
	// Questo metodo di supporto riordina secondo un ordine lessicografico i vari men�.
	private static void riordina (Vector <Catering> lista) {
		for (int i=0; i < lista.size()-1; i++) {
			for (int j=i; j< lista.size(); j++) {
				if (lista.elementAt(i).getNomeMen�().compareToIgnoreCase(lista.elementAt(j).getNomeMen�()) >0) {
					lista.add(i, lista.get(j));
					lista.remove(j+1);
				}
			}
		}
	}
	
	/* Questo metodo di supporto controlla se esiste gi� un men� che possiede lo stesso nome. Ritorna "false" se non esiste un men�
	con lo stesso nome, true altrimenti.
	*/
	private static boolean controlloNome (Vector <Catering> lista, Catering men�) {
		boolean flag = false;
		for (Catering x : lista) {
			if (x.getNomeMen�().equalsIgnoreCase(men�.getNomeMen�())) {
				flag = true;
			}
		}
		return flag;
	}
}
