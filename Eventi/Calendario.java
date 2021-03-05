package Eventi;
import java.time.LocalDate;
import java.util.Vector;
import java.util.ConcurrentModificationException;
import Menù.*;
import Animazione.*;


/**
 * 	Questa classe organizza i vari eventi, creando una vera e propria agenda. Permette dunque di aggiungere e rimuovere eventi,
 *	visualizzarli in ordine temporale o, anche, visualizzarli a seconda della tipologia. Consente di fare ricerche per nome e per
 * data, e permette di trovare la prima data disponibile per prenotare un nuovo evento.
 */
public class Calendario {
	Vector <Eventi> agenda = new Vector <Eventi>();
	
	/**
	 * Costruttore
	 */
	public Calendario() {}
	
	/**
	 *  Questo metodo permette di modificare il vettore "agenda".
	 */
	public void setAgenda (Vector <Eventi> agenda) {
		this.agenda = agenda;
	}
	
	/**
	 * Questo metodo ritorna il vettore "agenda".
	 */
	public Vector getAgenda() {return agenda;}
	
	/**
	 * Questi tre metodi permettono di creare e aggiungere un evento all'agenda. Ritornano un valore true se l'aggiunta è stata effettuata
	 * correttamente, false viceversa.
	 */
	public boolean addEvento (LocalDate data, String nomeReferente, String numeroReferente) {
		//Controllo che la data non sia già occupata.
		if (visualizzaData(data)) return false;
		else {
			Eventi compleanno = new Eventi (data, nomeReferente, numeroReferente);
			agenda.add(compleanno);
			return true;
		}
	}
	
	/**
	 * Costruttore per eventi Catering.
	 */
	public boolean addEvento (LocalDate data, String nomeReferente, String numeroReferente, int numeroInvitati) {
		//Controllo che la data non sia già occupata.
		if (visualizzaData(data)) return false;
		else {
			EventiCatering compleanno = new EventiCatering (data, nomeReferente, numeroReferente, numeroInvitati);
			agenda.add(compleanno);
			return true;
		}
	}
	
	/**
	 * Costruttore per eventi con animazione.
	 */
	public boolean addEvento (LocalDate data, String nomeReferente, String numeroReferente, int numeroInvitati, Animazione animazione) {
		//Controllo che la data non sia già occupata.
		if (visualizzaData(data)) return false;
		else {
			EventiAnimazione compleanno = new EventiAnimazione (data, nomeReferente, numeroReferente, numeroInvitati, animazione);
			agenda.add(compleanno);
			return true;
		}
	}
	
	/**
	 * Questo metodo rimuove un evento in una specifica data.
	 */
	public boolean removeEvento (LocalDate data) {
		boolean flag = false;
		
		// La classe Vector può generare un errore se modificata durante una iterazione.
		try {
			for (Eventi x : agenda) {
				if (x.getData().isEqual(data)) {
					agenda.remove(x);
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
	 * Questo metodo rimuove gli eventi prenotati da un referente.
	 */
	public boolean removeEvento (String nomeReferente) {
		boolean flag = false;
		
		//La classe Vector può generare un errore se modificata durante una iterazione.
		try {
			for (Eventi x : agenda) {
				if (x.getNomeReferente().equals(nomeReferente)) {
					agenda.remove(x);
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
	 * Metodo che permette di visulizzare l'elenco completo degli eventi.
	 */
	public void seeAll () {
		
		/*Essendo possibile modificare le date degli eventi, preferisco eseguire una operazione di ordinamento ogni volta che la
		la lista viene visualizzata */
		riordina(agenda);
		
		for(Eventi x : agenda) {
			System.out.println(x.toString());
		}
	}
	
	/**
	 * Metodo che permette di visualizzare l'elenco di tutti gli eventi comprendenti Catering.
	 */
	public void seeCatering () {
		
		/*Essendo possibile modificare le date degli eventi, preferisco eseguire una operazione di ordinamento ogni volta che la
		la lista viene visualizzata */
		riordina(agenda);
		
		for(Eventi x : agenda) {
			if (x instanceof EventiCatering) System.out.println(x.toString());
		}
	}
	
	/**
	 * Metodo che permette di visualizzare l'elenco di tutti gli eventi comprendenti Animazione.
	 */
	public void seeAnimazione () {
		
		/*Essendo possibile modificare le date degli eventi, preferisco eseguire una operazione di ordinamento ogni volta che la
		la lista viene visualizzata */
		riordina(agenda);
		
		for(Eventi x : agenda) {
			if (x instanceof EventiAnimazione) System.out.println(x.toString());
		}
	}
	
	/**
	 * Metodo che permette di cercare un evento registrato per nome o porzione di nome del referente. Ritorna un valore false
	 * se non è stata trovata alcuna corrispondenza.
	 */
	public boolean visualizzaPerNome (String porzione) {
		boolean flag = false;
		for (Eventi x : agenda) {
			if (x.getNomeReferente().contains(porzione)) {
				System.out.println(x.toString());
				flag = true;
			}
		}
		return flag;
	}
	
	/**
	 * Metodo che permette di controllare se una data è libera o meno. Ritorna un valore false se libera, se occupata stampa
	 * l'evento prenotato e ritorna valore true;
	 */
	public boolean visualizzaData (LocalDate data) {
		boolean flag = false;
		for (Eventi x : agenda) {
			if (x.getData().isEqual(data)) {
				System.out.println(x.toString());
				flag = true;
			}
		}
		return flag;
	}
	/**
	 * Metodo che ritorna l'evento organizzato per una data data. Se la data è libera, l'evento sarà "null".
	 */
	public Eventi getPerData (LocalDate data) {
		Eventi evento = null;
		for(Eventi x : agenda) {
			if (x.getData().isEqual(data)) {
				evento = x;
			}
		}
		return evento;
	}
	
	/**
	 * Questo metodo ritorna la prima data disponibile per le prenotazioni a partire da un giorno prestabilito.
	 */
	public LocalDate primaData (LocalDate data) {
		// Questa variabile rappresenta la data che si sta controllando.
		LocalDate esaminata = data;
		// Questa variabile è funzionale al ciclo.
		boolean flag;
		do {
			flag = true;
			for (Eventi x : agenda) {
				if (esaminata.isEqual(x.getData())) {
					flag = false;
				}
			}
			if (!flag) {
				esaminata = esaminata.plusDays(1);
			}
		} while (!flag);
		return esaminata;
	}
	
	/**
	 * Questo metodo permette di eliminare tutti gli eventi registrati nell'agenda precedenti ad una data. E compressa essa stessa.
	 */
	public void eliminaDaData (LocalDate data) {
		//La classe Vector può generare un errore se modificata durante una iterazione.
		try {
			do {
				if (agenda.get(0).getData().isBefore(data)) {
					agenda.remove(0);
				}
			} while (!agenda.get(0).getData().isAfter(data) && !agenda.get(0).getData().isEqual(data));
		}
		catch (ConcurrentModificationException e) {
			System.out.println("ATTENZIONE: è stato riscontrato un errore.");
			System.out.println(e);
		}
	}
	
	// Questo metodo di supporto riordina secondo un ordine temporale gli eventi dell'agenda.
	private static void riordina (Vector <Eventi> agenda) {
			for (int i=0; i < agenda.size()-1; i++) {
				for (int j=i; j< agenda.size(); j++) {
					if (agenda.elementAt(i).getData().isAfter(agenda.elementAt(j).getData())) {
						agenda.add(i, agenda.get(j));
						agenda.remove(j+1);
					}
				}
			}
		}
	
	// Questo metodo controlla se la data inserita è già registrata nell'agenda per un altro evento.
	private static boolean controlloData(LocalDate data, Vector <Eventi> agenda) {
		boolean flag = true;
		for (Eventi x : agenda) {
			if (data.isEqual(x.getData())) {
				flag = false;
			}
		}
		return flag;
	}
}
