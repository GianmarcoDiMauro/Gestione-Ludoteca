import Animazione.*;
import Eventi.*;
import Menù.*;
import java.util.Scanner;
import java.time.LocalDate;
import java.util.Vector;
import java.io.*;
import java.util.InputMismatchException;

public class MenùTestuale {
	public static void main (String[] args) {
		
		// Variabile che registra il nome del file di salvataggio.
		String nomeFile;
		
		// Inizializzazioni.
		Scanner input = new Scanner (System.in);
		Calendario agenda = new Calendario ();
		GestioneMenù menù = new GestioneMenù ();
		GestioneAnimazione lista = new GestioneAnimazione ();
		String password = null;
		
		System.out.println("Digita il nome del file di salvataggio.");
		nomeFile = input.nextLine();
		try {
			ObjectInputStream fileInput = new ObjectInputStream(new BufferedInputStream(new FileInputStream(nomeFile)));
			
			// Assegna al vettore dell'oggetto "agenda" il valore del primo vettore salvato sul file.
			agenda.setAgenda((Vector <Eventi>)(fileInput.readObject()));
			
			// Assegna al vettore dell'oggetto "menù" il valore del secondo vettore salvato sul file.
			menù.setLista((Vector <Catering>)(fileInput.readObject()));
			
			// Assegna al vettore dell'oggetto "lista" il valore del terzo vettore salvato sul file.
			lista.setLista((Vector <Animazione>)(fileInput.readObject()));
			
			password = ((String)(fileInput.readObject()));
			
			fileInput.close();
		
		// Gestisce l'eventuale assenza del file.
		}	catch (FileNotFoundException e) {
			
			System.out.println("ATTENZIONE: Il file non è stato trovato.");
			System.out.println("Sarà creato al primo salvataggio");
			
			// Inizializzo la password.
			password = "0000";
			
			// Crea alcune offerte base per il Catering e li aggiunge al menù.
			CateringBuffet pizza = new CateringBuffet ("Pizza", 50.00, 75.00, 100.00);
			pizza.setDescrizioneMenù("Il menù comprende diverse teglie di pizza Margherita, Tedesca e Capricciosa.");
			pizza.setAllergeni("Latticini");
			pizza.setAllergeni("Funghi");
			menù.addMenù(pizza);
			
			CateringBuffet panini = new CateringBuffet ("Panini", 25.00, 50.00, 75.00);
			panini.setDescrizioneMenù("Il menù comprende una selezione di panini con formaggi e insaccati.");
			panini.setAllergeni("Latticini");
			menù.addMenù(panini);
			
			CateringAlPiatto noFormaggi = new CateringAlPiatto ("No Formaggi", 3.50);
			noFormaggi.setDescrizioneMenù("Il piatto prevede un sandwich con salame e maionese ed uno con solo prosciutto.");
			menù.addMenù(noFormaggi);
			
			CateringAlPiatto misto = new CateringAlPiatto ("Piatto Misto", 5.00);
			misto.setDescrizioneMenù("Il piatto misto comprende un sandwich con salame e maionese ed una fetta di pizza margherita.");
			misto.setAllergeni("Latticini");
			menù.addMenù(misto);
			
			// Crea alcune offerte base per l'animazione e le aggiunge al menù.
			Animazione giochi = new Animazione ("Animazione & Giochi", 100);
			giochi.setDescrizione("Lo spettacolo prevede la presenza di due animatori che intratterrano i bambini con divertenti giochi.");
			lista.addAnimazione(giochi);
			
			Animazione magia = new Animazione ("Spettacolo di magia", 70);
			magia.setDescrizione("Lo spettacolo prevede la presenza di un giovane mago che incanterà i vostri figli con incredibili trucchi di magia.");
			lista.addAnimazione(magia);
			
			Animazione burattini = new Animazione ("Spettacolo di burattini", 100);
			burattini.setDescrizione("Uno spettacolo di burattini per grandi e piccini!");
			lista.addAnimazione(burattini);
			
		// Gestisce l'eventualità in cui nel file non è presente la classe desiderata.
		} catch (ClassNotFoundException e) {
			System.out.println("ERRORE di lettura");
			System.out.println(e);
			System.out.println("");
			
		// Gestisce altri errori.	
		} catch (IOException e) {
			System.out.println("ERRORE di I/O");
			System.out.println(e);
		}
		
		// variabile che registra la scelta fatta dall'utente nel menù principale
		char sceltaHomePage;
		
		//Menù iniziale.
		do {
			System.out.println("Digita la lettera corrispondente all'azione che vuoi eseguire.");
			System.out.println("");
			//Area che permette di visualizzare le offerte, registrare nuovi eventi, modificarli e rimuoverli.
			System.out.println("[A]rea Clienti");
			//Area che permettere di modificare le offerte proposte all'utente.
			System.out.println("[Z]ona Staff");
			System.out.println("[*]Esci");
			sceltaHomePage = input.nextLine().charAt(0);
			
			switch (sceltaHomePage) {
				//Apre il menù "Area Clienti".
				case 'A' : case 'a' : menùAreaClienti(nomeFile, agenda, menù, lista, password); break;
				//Apre il menù "Zona Staff".
				case 'Z': case 'z': menùZonaStaff(nomeFile, agenda, menù, lista, password); break;
				case '*' : {
					salva(nomeFile, agenda.getAgenda(), menù.getVettoreMenù(), lista.getLista(), password);
					System.out.println("Chiusura in corso.");
					System.exit(0);
				}
				default : System.out.println("Input errato. Riprova."); break;
			}
		} while (sceltaHomePage != '*');
	}
	
	// Metodo per la gestione del menù "Area Clienti".
	private static void menùAreaClienti(String nomeFile, Calendario agenda, GestioneMenù menù, GestioneAnimazione lista, String password ) {
		Scanner input = new Scanner (System.in);
		
		// variabile che registra la scelta fatta dall'utente nel menù.
		char sceltaMenùClienti;
		do {
			System.out.println("Digita la lettera corrispondente all'azione che vuoi eseguire.");
			System.out.println("");
			System.out.println("[A]aggiungi un evento.");
			// Permette di visualizzare le date disponibili e le offerte proposte.
			System.out.println("[I] nostri servizi.");
			System.out.println("[M]odifica un evento.");
			System.out.println("[R]imuovi un evento.");
			System.out.println("[*]Torna indietro.");
			sceltaMenùClienti = input.nextLine().charAt(0);
			
			switch (sceltaMenùClienti) {
			//Permette di aggiungere un evento invocando l'apposito metodo.
			case 'A' : case 'a' : aggiungiEvento(nomeFile, agenda, menù, lista, password); break;
			//Apre il menù Agenda,
			case 'I' :  case 'i': menùAgenda(agenda, menù, lista); break;
			//Crea la data dell'evento necessaria al metodo ausiliare "Modifica Evento"
			case 'M' : case 'm' : {
				//Controllo che l'utente non inserisca un input sbagliato.
				try {
					System.out.println("Scrivi l'anno dell'evento che vuoi modificare.");
					int anno = input.nextInt();
					System.out.println("Scrivi il mese dell'evento che vuoi modificare.");
					int mese = input.nextInt();
					System.out.println("Scrivi il giorno dell'evento che vuoi modificare.");
					int giorno = input.nextInt();
				
					// Questa variabile serve per impedire al programma di leggere il tasto "a capo" come input per il menù successivo.
					String pulizia = input.nextLine();
					
					if (controlloCoerenzaData(anno, mese, giorno)) {
						menùModificaEvento(agenda.getPerData(LocalDate.of(anno, mese, giorno)), nomeFile, agenda, menù, lista, password);
					} else {
						menùAreaClienti(nomeFile, agenda, menù, lista, password);
					}
				}
				catch (InputMismatchException e) {
					//Cancella l'input inserito.
					String controlloErrori = input.nextLine();
					System.out.println("Input errato.Riprovare");
				}
				break;
			}
			//Apre il menù "Rimuovi evento".
			case 'R' : case 'r' : menùRimuoviEvento(nomeFile, agenda, menù, lista, password); break;
			case '*' : break;
			default : System.out.println("Input errato. Riprovare."); break;
		}
			
		} while (sceltaMenùClienti != '*');
	}
	
	//Metodo per la gestione del menù "Zona Staff".
	private static void menùZonaStaff(String nomeFile, Calendario agenda, GestioneMenù menù, GestioneAnimazione lista, String password) {
		Scanner input = new Scanner (System.in);
		
		//Stringa che registra l'input dell'utente.
		String tentativo;
		
		System.out.println("Digita la password di accesso.");
		tentativo = input.nextLine();
		
		//Se la password è errata, il programma ritona al main. Se è corretta, si apre il menù.
		if (!tentativo.equals(password)) {
			System.out.println("Password errata.");
		} else {
			
			//Questa variabile registra la scelta dell'utente.
			char scelta;
			do {
				// Permette di aggiungere, rimuovere, modificare i menù e i tipi di animazione.
				System.out.println("[A]ggiorna servizi;");
				// Modifica la password.
				System.out.println("[M]odifica Password");
				//Elimina tutti gli eventi registrati precedenti ad una data (essa compresa):
				System.out.println("[P]ulisci agenda");
				System.out.println("[*]Torna indietro");
				
				scelta = input.nextLine().charAt(0);
				
				switch (scelta) {
				//Genera un menù secondario che permette la selezione delle informazioni da modificare.
				case 'a' : case 'A' : {
					char sceltaAggiorna;
					do {
						System.out.println("[A]nimazione");
						System.out.println("[C]osto affitto");
						//Ovvero le offerte del Catering.
						System.out.println("[M]enù");
						System.out.println("[*]Torna indietro");
						
						sceltaAggiorna = input.nextLine().charAt(0);
						
						switch (sceltaAggiorna) {
						//Apre il menù per la modifica degli spettacoli disponibili.
						case 'a' : case 'A': menùAnimazione(nomeFile, agenda, menù, lista, password); break;
						//Permette di modificare il costo di affitto della sala.
						case 'c' : case 'C' : {
							//Controllo che non sia stati inseriti input erronei.
							try {
								System.out.println("Digita il nuovo costo di affitto della sala");
								double costo = input.nextDouble();
								
								//Questa variabile impedisce allo Scanner di leggere l'input return come input per il prossimo menù di selezione.
								String pulizia = input.nextLine();
								
								Eventi evento = null;
								evento.setTariffaAffitto(costo);
								salva (nomeFile, agenda.getAgenda(), menù.getVettoreMenù(), lista.getLista(), password);
							}
							catch (InputMismatchException e) {
								//Cancella l'input inserito.
								String controlloErrori = input.nextLine();
								System.out.println("Input errato.Riprovare");
							}
							break;
						}
						//Apre il menù per la modifica delle offerte del catering.
						case 'm' : case 'M': menùCatering(nomeFile, agenda, menù, lista, password); break;
						case '*' : break;
						default : System.out.println("Input errato. Riprovare.");
						}
					} while (sceltaAggiorna != '*');
					break;
				}
				//Modifica la password.
				case 'm' : case 'M' : {
					
					System.out.println("Digita la password di accesso.");
					tentativo = input.nextLine();
					
					if (!tentativo.equals(password)) {
						System.out.println("Password errata.");
					} else {
						System.out.println("Digita la nuova password");
						password = input.nextLine();
						salva(nomeFile, agenda.getAgenda(), menù.getVettoreMenù(), lista.getLista(), password);
					}
					break;
				}
				
				//Permette di eliminare un grosso blocco di eventi registrati.
				case 'P' : case 'p' : {
					System.out.println("Attenzione: verranno rimossi dall'agenda tutti gli eventi memorizzati precedenti ad una data. ");
					System.out.println("Procedere solo se sicuri.");
					System.out.println("");
					
					char sceltaPulizia;
					do {
						System.out.println("Da quando vuoi eliminare gli eventi?");
						System.out.println("[1]Elimina gli eventi registrati per oggi e tutti i precedenti");
						System.out.println("[2]Altra data");
						System.out.println("[*]Torna indietro");
						
						sceltaPulizia = input.nextLine().charAt(0);
						
						switch (sceltaPulizia) {
						case '1' : {
							agenda.eliminaDaData(LocalDate.now()); 
							salva (nomeFile, agenda.getAgenda(), menù.getVettoreMenù(), lista.getLista(), password);
							break;
						}
						case '2' :{
							try {
								LocalDate data = null;
								do {
									System.out.println("Scrivi l'anno dell'evento.");
									int anno = input.nextInt();
									System.out.println("Scrivi il mese dell'evento.");
									int mese = input.nextInt();
									System.out.println("Scrivi il giorno dell'evento.");
									int giorno = input.nextInt();
									
									// Questa variabile serve per impedire al programma di leggere il return come input per il menù successivo.
									String pulizia = input.nextLine();
									
									//Controllo se i valori inseriti sono coerenti.
									if(controlloCoerenzaData(anno, mese, giorno)) {
										data = LocalDate.of(anno, mese, giorno);
										
									} else {
										System.out.println("Questa data non è corretta.");
										System.out.println("");
									}
								}while (data == null);
								
								agenda.eliminaDaData(data);
								salva(nomeFile, agenda.getAgenda(), menù.getVettoreMenù(), lista.getLista(), password);
							}
							catch (InputMismatchException e) {
								//Cancella l'input inserito.
								String controlloErrori = input.nextLine();
								System.out.println("Input errato.Riprovare");
							}
						}
						case '*' : break;
						default : System.out.println("Input errato, riprovare."); break;
						}
					} while (sceltaPulizia != '*');
				};
				case '*' : break;
				default : System.out.println("Input errato, riprovare."); break;
				}
			} while (scelta != '*');
		}
		
	}
	
	// Metodo che gestisce le registrazioni di nuovi eventi sull'agenda.
	private static void aggiungiEvento(String nomeFile, Calendario agenda, GestioneMenù menù, GestioneAnimazione lista, String password) {
		Scanner input = new Scanner (System.in);
		
		// Creo la data dell'evento.
		LocalDate data = null;
		do {
			//Controllo che l'utente non inserisca input erronei.
			try {
				System.out.println("Scrivi l'anno dell'evento.");
				int anno = input.nextInt();
				System.out.println("Scrivi il mese dell'evento.");
				int mese = input.nextInt();
				System.out.println("Scrivi il giorno dell'evento.");
				int giorno = input.nextInt();
				
				// Questa variabile serve per impedire al programma di leggere il return come input per il menù successivo.
				String pulizia = input.nextLine();
				
				//Controllo se i valori inseriti sono coerenti.
				if(controlloCoerenzaData(anno, mese, giorno)) {
					data = LocalDate.of(anno, mese, giorno);
					
				} else {
					System.out.println("Questa data non è corretta.");
					System.out.println("");
				}
			}
			catch (InputMismatchException e) {
				//Cancella l'input inserito.
				String controlloErrori = input.nextLine();
				System.out.println("Input errato.Riprovare");
			}
		}while (data == null);
		
		//Chiedo i valori comuni a tutti i tipi di evento.
		
		System.out.println("Digita il nome del referente.");
		String nomeReferente = input.nextLine();
		System.out.println("Digita il numero del referente.");
		String numeroReferente = input.nextLine();
		
		//Questa variabile registra la scelta dell'utente.
		char scelta;
		// Chiedo se l'evento deve essere dotato di Catering.
		do {
			System.out.println("Si desidera avere il servizio di catering?");
			System.out.println("[S][N]");
			scelta = input.nextLine().charAt(0);
			
			if (scelta != 's' && scelta != 'S' && scelta != 'n' && scelta != 'N') {
				System.out.println("Input errato. Riprovare.");
				System.out.println("");
			}
		} while (scelta != 's' && scelta != 'S' && scelta != 'n' && scelta != 'N');
		
		/* Se la scelta è "No",  il metodo "addEvento" controlla se la data è disponibile. Se sì, crea l'evento e lo aggiunge
		al calendario */
		if (scelta == 'n' || scelta == 'N') {
			if (agenda.addEvento(data, nomeReferente, numeroReferente)) {
				System.out.println("Evento registrato correttamente");
				salva(nomeFile,agenda.getAgenda(), menù.getVettoreMenù(),lista.getLista(), password);
				
				//Aggiunge, se richiesto, informazioni non essenziali per la creazione dell'evento.
				aggiungiAltreInformazioni(agenda.getPerData(data), nomeFile, agenda, menù, lista, password);
			} else {
				System.out.println("La data selezionata non è disponibile.");
			}
		
			/* Se la scelta è "Sì", chiedo di aggiungere alle informazioni il numero egli invitati, assicurandomi che sia 
			 * maggiore di zero.
			 */
		} else {
			int numeroInvitati = 0;
			do {
					try {
					System.out.println("Indicare il numero di invitati");
					numeroInvitati = input.nextInt();
					
					// Questa variabile serve per impedire al programma di leggere il return come input per il menù successivo.
					String pulizia = input.nextLine();
					
					if (numeroInvitati <= 0) {
						System.out.println("Attenzione: indicare un numero di invitati maggiore di zero.");
					}
				}
					catch (InputMismatchException e) {
						//Cancella l'input inserito.
						String controlloErrori = input.nextLine();
						System.out.println("Input errato.Riprovare");
					}
			} while (numeroInvitati <= 0);
			
			//Questa variabile registra la scelta effettuata dall'utente.
			char scelta2;
			//Chiedo se l'evento deve prevedere un servizio di animazione.
			do {
				System.out.println("Si desidera avere il servizio di animazione?");
				System.out.println("[S][N]");
				scelta2 = input.nextLine().charAt(0);
				
				if (scelta2 != 's' && scelta2 != 'S' && scelta2 != 'n' && scelta2 != 'N') {
					System.out.println("Input errato. Riprovare.");
				}
			} while (scelta2 != 's' && scelta2 != 'S' && scelta2 != 'n' && scelta2 != 'N');
			
			// Se la scelta è no, il metodo "addEvento" controlla la data e, se libera, crea l'evento e l'aggiunge al Calendario.
			if (scelta2 == 'n' || scelta2 == 'N') {
				if (agenda.addEvento(data, nomeReferente, numeroReferente, numeroInvitati)) {
					System.out.println("Evento registrato correttamente");
					salva(nomeFile,agenda.getAgenda(), menù.getVettoreMenù(),lista.getLista(), password);
					
					//Aggiunge, se richiesto, informazioni secondarie non indispensabili per la creazione dell'evento.
					aggiungiAltreInformazioni(agenda.getPerData(data), nomeFile, agenda, menù, lista, password);
				} else {
					System.out.println("La data selezionata non è disponibile.");
				}
			// Se la scelta è sì, creo un evento con animazione.
			} else {
				System.out.println("Si prega di selezionare il tipo di spettacolo richiesto.");
				System.out.println("Ecco le nostre offerte.");
				lista.seeAll();
				
				String animazione;
				do {
					System.out.println("Digitare il nome dello spettacolo che si desidera.");
					animazione = input.nextLine();
					
					if (lista.getAnimazione(animazione) == null) {
						System.out.println("Lo spettacolo selezionato non esiste.");
					}
					
				} while (lista.getAnimazione(animazione) == null);
				if (agenda.addEvento(data, nomeReferente, numeroReferente, numeroInvitati, lista.getAnimazione(animazione))) {
					System.out.println("Evento registrato correttamente");
					salva(nomeFile,agenda.getAgenda(), menù.getVettoreMenù(),lista.getLista(), password);
					
					//Aggiunge, se richiesto, informazioni secondarie non indispensabili per la creazione dell'evento.
					aggiungiAltreInformazioni(agenda.getPerData(data), nomeFile, agenda, menù, lista, password);
				} else {
					System.out.println("La data selezionata non è disponibile.");
				}
			}
		}
	}

	//Metodo che mostra gli eventi prenotati, la prima data disponibile e i servizi di Catering e Animazione.
	private static void menùAgenda(Calendario agenda, GestioneMenù menù, GestioneAnimazione lista) {
		Scanner input = new Scanner (System.in);
		
		//Variabile che registra la scelta dell'utente
		char scelta;
		do {
			//Visualizza tutti gli eventi e permette di cercarli.
			System.out.println("[L]a nostra agenda");
			//Permette di cercare la prima data libera.
			System.out.println("[P]rima data disponibile");
			//Visualizza tutte le offerte disponibili.
			System.out.println("[V]isualizza i nostri servizi");
			System.out.println("[*]Torna indietro");
			
			scelta = input.nextLine().charAt(0);
			switch (scelta) {
			//Crea un menù per la visualizzazione degli eventi.
			case 'L' : case 'l' : {
				
				//Variabile che registra la scelta dell'utente
				char sceltaAgenda;
				do {
					System.out.println("Cosa vuoi visualizzare?");
					//Visualizza solo eventi che prevedono il catering.
					System.out.println("[1] Eventi con Catering.");
					//Visualizza solo eventi che prevedono il servizio animazione.
					System.out.println("[2] Eventi con Animazione.");
					//Visualizza tutti gli eventi.
					System.out.println("[3] Tutti gli eventi.");
					//Cerca un evento conoscendo la data.
					System.out.println("[4] Eventi per data.");
					//Cerca un evento conoscendo il referente.
					System.out.println("[5] Eventi per nome.");
					System.out.println("[*] Torna indietro.");
					
					sceltaAgenda = input.nextLine().charAt(0);
					
					switch (sceltaAgenda) {
					case '1' : agenda.seeCatering(); break;
					case '2' : agenda.seeAnimazione(); break;
					case '3' : agenda.seeAll(); break;
					//Crea un oggetto Local Date e controlla se ne esiste già uno uguale associato ad un evento.
					case '4' : {
						LocalDate data = null;
						do {
							//Controllo che l'utente non inserisca input erronei.
							try {
								System.out.println("Scrivi l'anno.");
								int anno = input.nextInt();
								System.out.println("Scrivi il mese.");
								int mese = input.nextInt();
								System.out.println("Scrivi il giorno.");
								int giorno = input.nextInt();
								
								// Questa variabile serve per impedire al programma di leggere il return come input per il menù successivo.
								String pulizia = input.nextLine();
								
								//Controllo se i valori inseriti sono coerenti.
								if(controlloCoerenzaData(anno, mese, giorno)) {
									data = LocalDate.of(anno, mese, giorno);
									
								} else {
									System.out.println("Questa data non è corretta.");
									System.out.println("");
								}
							}
							catch (InputMismatchException e) {
								//Cancella l'input inserito.
								String controlloErrori = input.nextLine();
								System.out.println("Input errato.Riprovare");
							}
						}while (data == null);
						
						//Il metodo visualizzaData controlla se una data è libera o meno e, se no, ne stampa l'evento.
						if (!agenda.visualizzaData(data)) {
							System.out.println("Questa data è libera.");
						}
						break;
					}
					case '5' : {
						System.out.println("Digita nome.");
						String nome = input.nextLine();
						
						//Il metodo visualizzaData controlla se una data è libera o meno e, se no, ne stampa l'evento.
						if (!agenda.visualizzaPerNome(nome)) {
							System.out.println("Nessuna corrispondenza trovata.");
						}
						break;
					}
					case '*' : break;
					default : System.out.println("Input errato, riprovare.");
					}
				} while (sceltaAgenda != '*');
				break;
			}
			//Crea un piccolo menù per la ricerca della prima data disponibile.
			case 'P' : case 'p' : {
				
				//Variabile che registra la scelta dell'utente
				char sceltaData;
				do {
					System.out.println("Vuoi vedere la prima data disponibile da quando?");
					//Controlla da una qualsiasi data.
					System.out.println("[A]ltra Data.");
					//Controlla da oggi.
					System.out.println("[O]ggi.");
					System.out.println("[*]Torna indietro");
					
					sceltaData = input.nextLine().charAt(0);
					switch (sceltaData) {
					//Crea un oggetto LocalDate ed invoca un metodo per il controllo della disponibilità.
					case 'A' : case 'a' : {
						LocalDate data = null;
						do {
							//Controllo che l'utente non inserisca input erronei.
							try {
								System.out.println("Scrivi l'anno.");
								System.out.println("");
								int anno = input.nextInt();
								System.out.println("Scrivi il mese.");
								System.out.println("");
								int mese = input.nextInt();
								System.out.println("Scrivi il giorno.");
								System.out.println("");
								int giorno = input.nextInt();
								
								// Questa variabile serve per impedire al programma di leggere il return come input per il menù successivo.
								String pulizia = input.nextLine();
								
								//Controllo se i valori inseriti sono coerenti.
								if(controlloCoerenzaData(anno, mese, giorno)) {
									data = LocalDate.of(anno, mese, giorno);
									
								} else {
									System.out.println("Questa data non è corretta.");
									System.out.println("");
								}
							}
							catch (InputMismatchException e) {
								//Cancella l'input inserito.
								String controlloErrori = input.nextLine();
								System.out.println("Input errato.Riprovare");
							}
						}while (data == null);
						
						System.out.println("La prima data disponibile è " + agenda.primaData(data));
						break;
					}
					
					case 'o' : case 'O' : {
						System.out.println("La prima data disponibile è " + agenda.primaData(LocalDate.now()));
						break;
					}
					
					case '*' : break;
					default : System.out.println("Input errato!"); break;
					}
				} while (sceltaData != '*');
				break;
			}
			//Crea un menù per la visualizzazione dei servizi offerti.
			case 'V' : case 'v' : {
				char sceltaServizi;
				
				do {
					System.out.println("Cosa vuoi visualizzare?");
					//Mostra i vari spettacoli selezionabili.
					System.out.println("[A]nimazioni.");
					//Mostra i vari menù a buffet selezionabili.
					System.out.println("[B]uffet.");
					//Mostra i vari menù al piatto selezionabili.
					System.out.println("[P]iatti.");
					//Mostra tutti i menù selezionabili.
					System.out.println("[T]utti i menù.");
					System.out.println("[*]Torna indietro");
					
					sceltaServizi = input.nextLine().charAt(0);
					
					switch (sceltaServizi) {
					case 'a' : case 'A' : lista.seeAll(); break;
					case 'B' : case 'b' : menù.seeBuffet(); break;
					case 'P' : case 'p' : menù.seePiatto(); break;
					case 'T' : case 't' : menù.seeAll();break;
					case '*' : break;
					default : System.out.println("Input errato!"); break;
					}
					
				} while (sceltaServizi != '*');
				
			} 
			case '*' : break;
			default : System.out.println("Input errato, riprovare!");
			}
		} while (scelta != '*');
	}
	
	// Metodo che permette di modificare gli eventi già registrati.
	private static void menùModificaEvento (Eventi evento, String nomeFile, Calendario agenda, GestioneMenù menù, GestioneAnimazione lista, String password) {
		Scanner input = new Scanner (System.in);
		
		// Se l'evento è nullo, torna indietro all'area clienti.
		if (evento.equals(null)) {
			System.out.println("Non è stato trovato alcuno evento in questa data.");
			System.out.println("");
			menùAreaClienti(nomeFile, agenda, menù, lista, password);
		}
		
		//Questa variabile registra la scelta effettuata
		char sceltaMenù;
		//Creo un menù per la selezione delle macroaree da modificare.
		do {
			// Stampo sullo schermo l'evento che si sta per modificare.
			System.out.println("Stai modificando questo evento:");
			System.out.println(evento.toString());
			
			System.out.println("");
			System.out.println("Quali informazioni desideri modificare?");
			//I dettagli uguali a tutte le tipologie di eventi.
			System.out.println("[E]vento base");
			//I dettagli esclusivi degli eventi catering e animazione.
			System.out.println("[S]pettacolo e cibarie");
			System.out.println("[*]Torna Indietro");
			sceltaMenù = input.nextLine().charAt(0);
			
			switch (sceltaMenù) {
			
			// Crea un menù per la selezione dei dettagli da modificare.
			case 'E' : case 'e' : {
				char scelta;
				do {
					System.out.println("[A]cconto");
					System.out.println("[D]ata");
					System.out.println("[I]nformazioni Referente");
					System.out.println("[M]odifica descrizione");
					System.out.println("[P]agamento");
					System.out.println("[*]Torna indietro");
					scelta = input.nextLine().charAt(0);
					
					switch (scelta) {
					//Invoca il metodo per la modifica dell'evento.
					case 'A' : case 'a' : setAcconto(evento, nomeFile, agenda, menù, lista, password); break;
					//Genera un oggetto LocalDate e controlla se ne esiste già uno uguale a cui associato un evento.
					case 'D': case 'd': {
						//Controllo che l'utente non abbia inserito degli input erronei.
						try {
							System.out.println("Scrivi il nuovo anno.");
							int anno = input.nextInt();
							System.out.println("Scrivi il nuovo mese.");
							int mese = input.nextInt();
							System.out.println("Scrivi il nuovo giorno.");
							int giorno = input.nextInt();
							
							// Questa variabile serve per impedire al programma di leggere il return come input per il menù successivo.
							String pulizia = input.nextLine();
							
							// Controllo la coerenza della data.
							if (!controlloCoerenzaData(anno, mese, giorno)) {
								System.out.println("Data inesistente.");
								menùModificaEvento(evento, nomeFile, agenda, menù, lista, password);
							} else {
								if (agenda.visualizzaData(LocalDate.of(anno, mese, giorno))) {
									System.out.println("Impossibile spostare l'evento per quella data.");
								} else {
									evento.setData(LocalDate.of(anno, mese, giorno));
									salva(nomeFile, agenda.getAgenda(), menù.getVettoreMenù(), lista.getLista(), password);
								}
							}
						} 
						catch (InputMismatchException e) {
							//Cancella l'input inserito.
							String controlloErrori = input.nextLine();
							System.out.println("Input errato.Riprovare");
						}
					} break;
					//Genera un menù per la selezione dei dettagli del referente da modificare.
					case 'I' : case 'i' : {
						
						//Questa variabile registra la selezione effettuata dall'utente.
						char sceltaModificaReferente;
						do {
							System.out.println("Che informazioni vuoi modificare?");
							System.out.println("[N]ome referente");
							System.out.println("[R]ecapito referente");
							System.out.println("[*]Torna Indietro");
							sceltaModificaReferente = input.nextLine().charAt(0);
							
							switch (sceltaModificaReferente) {
							case 'N' : case 'n' : {
								System.out.println("Digita il nuovo nome");
								String nome = input.nextLine();
								evento.setNomeReferente(nome);
								break;
							} 
							case 'R' : case 'r' : {
								System.out.println("Digita il nuovo numero");
								String numero = input.nextLine();
								evento.setNumeroReferente(numero);
								break;
							}
							case '*' : break;
							default : System.out.println("Input errato. Riprovare."); break;
							}
						}while (sceltaModificaReferente != '*');
						break;
					}
					//Genera un menù per la metodologia di modifica della descrizione.
					case 'M' : case 'm' : {
						System.out.println("Vuoi scrivere una nuova descrizione o aggiungere informazioni alla precedente?");
						//Aggiunge informazioni alla descrizione precedente.
						System.out.println("[A]ggiungere informazioni alla precedente.");
						//Sostituisce la vecchia descrizione.
						System.out.println("[S]crivere una nuova descrizione.");
						char selezione = input.nextLine().charAt(0);
						if (selezione == 'a') {
							System.out.println("Scrivi la nuova informazione");
							String info = input.nextLine();
							evento.aggiungiAltreInformazioni(info);
							salva(nomeFile, agenda.getAgenda(), menù.getVettoreMenù(), lista.getLista(), password);
						} else if (selezione == 's') {
							setDescrizione(evento, nomeFile, agenda, menù, lista, password);
						} else {
							System.out.println("ERRORE!");
						}
						break;
					}
					//Modifica la variabile booleana dell'evento.
					case 'P' : case 'p' : {
						
						// Se l'evento risulta pagato, il programma chiede conferma per eseguire l'operazione.
						if (evento.getPagato()) {
							System.out.println("ATTENZIONE: L'evento risulta saldato! Vuoi modificare questo dato?");
							System.out.println("[S] [N]");
							char selezione = input.nextLine().charAt(0);
							if (selezione == 's') {
								evento.setPagato();
								salva(nomeFile, agenda.getAgenda(), menù.getVettoreMenù(), lista.getLista(), password);
							} else if (selezione == 'n') {
								 menùModificaEvento(evento, nomeFile, agenda, menù, lista, password);
							} else {
								System.out.println("ERRORE!");
							}
					
						} else {
							evento.setPagato();
							salva(nomeFile, agenda.getAgenda(), menù.getVettoreMenù(), lista.getLista(), password);
						} break;
					}
					case '*' : break;
					default : System.out.println("Input errato. Riprova."); break;
					}
				} while (scelta != '*');
				break;
			}
			
			// Le seguenti opzioni sono disponibili solo per eventi comprendenti Catering e Animazione.
			case 'S': case 's': {
				
				// Questa variabile memorizza la scelta relativa al menù.
				char scelta;
				do {
					System.out.println("[B]uffet");
					System.out.println("[M]enù al piatto");
					System.out.println("[N]umero invitati");
					System.out.println("[S]pettacolo");
					System.out.println("[T]orta");
					System.out.println("[*]Torna indietro");
					scelta = input.nextLine().charAt(0);
					
					switch (scelta) {
					case 'B' : case 'b' : {
						aggiungiBuffet(evento, nomeFile, agenda, menù, lista, password);
						break;
					} 
					case 'M' : case 'm' :  {
						aggiungiPiatto(evento, nomeFile, agenda, menù, lista, password);
						break;
					} 
					case 'N' : case 'n' : {
						if (evento instanceof EventiCatering) {
							//Controllo l'input inserito dall'utente.
							try {
								System.out.println("Qual è il nuovo numero di invitati?");
								int numeroInvitati = input.nextInt();
								
								//Questo vettore evita che il programma legga il return come input per il prossimo menù di selezione.
								String pulizia = input.nextLine();
								((EventiCatering)(evento)).setNumeroInvitati(numeroInvitati);
								
								System.out.println("Attenzione, il prezzo finale dell'evento potrebbe essere cambiato.");
								System.out.println("Il costo finale dell'evento è" + evento.getCosto());
							} 
							catch (InputMismatchException e) {
								//Cancella l'input inserito.
								String controlloErrori = input.nextLine();
								System.out.println("Input errato.Riprovare");
							}
						}else { 
							System.out.println("ERRORE: questo evento non prevede il servizio catering.");
						}
						
						break;
					}
					case 'S' : case 's' : {
						aggiungiAnimazione(evento, nomeFile, agenda, menù, lista, password);
						break;
					}
					case 'T' : case 't' : {
						aggiungiTorta(evento, nomeFile, agenda, menù, lista, password);
						break;
					}
					case '*' : break;
					default : System.out.println("Input errato!"); break;
					}
				} while (scelta != '*');
			}
			case '*' : break;
			default : System.out.println("Input errato. Riprova."); break;
			}
		} while (sceltaMenù != '*');
	}
	
	// Metodo che permette di rimuovere un evento.
	private static void menùRimuoviEvento(String nomeFile, Calendario agenda, GestioneMenù menù, GestioneAnimazione lista, String password) {
		Scanner input = new Scanner (System.in);
		
		//Questa variabile registra la selezione dell'utente.
		char scelta;
		do {
			System.out.println("Vuoi eliminare un evento?");
			System.out.println("[1] Elimina l'evento registrato in una data");
			System.out.println("[2] Elimina gli eventi registrati sotto un nome.");
			System.out.println("[*] Torna indietro.");
			
			scelta = input.nextLine().charAt(0);
			
			switch (scelta) {
			case '1' : {
				LocalDate data = null;
				do {
					
					//Controllo che l'utente non inserisca un input erroneo.
					try {
						System.out.println("Scrivi l'anno.");
						System.out.println("");
						int anno = input.nextInt();
						System.out.println("Scrivi il mese.");
						System.out.println("");
						int mese = input.nextInt();
						System.out.println("Scrivi il giorno.");
						System.out.println("");
						int giorno = input.nextInt();
						
						// Questa variabile serve per impedire al programma di leggere il return come input per il menù successivo.
						String pulizia = input.nextLine();
						
						//Controllo se i valori inseriti sono coerenti.
						if(controlloCoerenzaData(anno, mese, giorno)) {
							data = LocalDate.of(anno, mese, giorno);
							
						} else {
							System.out.println("Questa data non è corretta.");
							System.out.println("");
						}
					}
					catch (InputMismatchException e) {
						//Cancella l'input inserito.
						String controlloErrori = input.nextLine();
						System.out.println("Input errato.Riprovare");
					}
				}while (data == null);
				
				//Controllo se la data è effettivamente occupata da un evento.
				if (agenda.visualizzaData(data)) {
					if (agenda.removeEvento(data)) {
						System.out.println("L'evento è stato rimosso correttamente.");
						salva (nomeFile, agenda.getAgenda(), menù.getVettoreMenù(), lista.getLista(), password);
					} else System.out.println("ERRORE: L'evento non è stato rimosso");
				} else 	System.out.println("Questa data è libera.");
			}
			case '2' : {
				// Variabile che registra il nome.
				String nome;
				
				//Variabile necessaria per il ciclo do-while.
				Boolean flag = false;
				
				do {
					System.out.println("Digita il nome del referente di cui vuoi eliminare gli eventi.");
					nome = input.nextLine();
					
					/* Se vengono trovati eventi corrispondenti all'imput inserito, viene richiesto se eliminarli o no. Altrimenti
					viene inviato un messaggio di errore. */
					if (agenda.visualizzaPerNome(nome)) {
						char sceltaElimina;
						do {
							System.out.println("Verranno eliminati questi eventi. Procedere?");
							System.out.println("[S][N]");
							
							sceltaElimina = input.nextLine().charAt(0);
							
							switch (sceltaElimina) {
							case 'S' : case 's' : {
								if (agenda.removeEvento(nome)) {
									System.out.println("Eliminato");
									salva(nomeFile, agenda.getAgenda(), menù.getVettoreMenù(), lista.getLista(), password);
								} else System.out.println("ERRORE: evento non eliminato");
								break;
							}
							case 'N' : case 'n': break;
							default: System.out.println("Input errato, riprovare!");
							}
							
						} while (sceltaElimina != 's' && sceltaElimina != 'S' && sceltaElimina != 'n' && sceltaElimina != 'N');
					} else {
						System.out.println("Non sono state trovate corrispondenze.");
					}	
				} while (!flag);
				break;
			}
			case '*' : break;
			default : System.out.println("Input errato, riprovare.");
			}
			
		} while (scelta != '*');
	}
	
	//Questo metodo aggiunge la stringa "torta" all'evento Catering.
	private static void aggiungiTorta (Eventi evento, String nomeFile, Calendario agenda, GestioneMenù menù, GestioneAnimazione lista, String password) {
		Scanner input = new Scanner (System.in);
		//Controllo che l'evento preveda il servizio catering.
		if (evento instanceof EventiCatering) {
			System.out.println("Scrivere il tipo di torta che si desidera.");
			String torta = input.nextLine();
			((EventiCatering) evento).setTorta(torta);
			salva(nomeFile, agenda.getAgenda(), menù.getVettoreMenù(), lista.getLista(), password);	
		} else System.out.println("ERRORE: questo evento non prevede servizio catering.");
	}
	
	//Questo metodo aggiunge un'animazione ad un dato evento Animazione
 	private static void aggiungiAnimazione (Eventi evento, String nomeFile, Calendario agenda, GestioneMenù menù, GestioneAnimazione lista, String password) {
		Scanner input = new Scanner (System.in);
		
		// Controllo che l'evento sia realmente un evento Animazione.
		if (evento instanceof EventiAnimazione) {
			System.out.println("Ecco i nostri servizi di animazione");
			System.out.println("");
			lista.seeAll();
			System.out.println("Quale bisogna registrare?");
			String nomeSpettacolo = input.nextLine();
			
			//controllo che lo spettacolo selezionato esista.
			if (lista.getAnimazione(nomeSpettacolo) != null) {
				((EventiAnimazione)(evento)).setDivertimento(lista.getAnimazione(nomeSpettacolo));
				System.out.println("ATTENZIONE: il costo finale potrebbe essere cambiato.");
				System.out.println("Il nuovo costo totale è di:" + evento.getCosto() + "€.");
				System.out.println("");
				salva(nomeFile, agenda.getAgenda(), menù.getVettoreMenù(), lista.getLista(), password);
				
			} else System.out.println("Questo tipo di animazione non esiste più.");
		} else {
			System.out.println("ERRORE: questo evento non prevede il servizio animazione.");
		}
	}
		
	// Questo metodo aggiunge un menù al piatto ad un dato evento Catering.
	private static void aggiungiPiatto (Eventi evento, String nomeFile, Calendario agenda, GestioneMenù menù, GestioneAnimazione lista, String password) {
		Scanner input = new Scanner (System.in);
		
		// Controllo che l'evento sia realmente un evento con catering.
		if (evento instanceof EventiCatering) {
			System.out.println("Ecco i nostri menù al Piatto");
			System.out.println("");
			menù.seePiatto();
			System.out.println("Quale bisogna registrare?");
			String nomePiatto = input.nextLine();
			
			// Controllo che il menù selezionato esista.
			if (menù.getMenù(nomePiatto) != null) {
				
				System.out.println("Questo menù è per tutti gli invitati?");
				System.out.println("[S][N]");
				char sceltaPiatto = input.nextLine().charAt(0);
				if (sceltaPiatto == 'N' || sceltaPiatto == 'n') {
					System.out.println("Quanti piatti bisogna preparare?");
					int numeroPiatti = input.nextInt();
					
					//Questo vettore evita che il programma legga il return come input per il prossimo menù di selezione.
					String pulizia = input.nextLine();
					((EventiCatering)(evento)).setPiatto((CateringAlPiatto)(menù.getMenù(nomePiatto)), numeroPiatti);
					salva(nomeFile, agenda.getAgenda(), menù.getVettoreMenù(), lista.getLista(), password);
				} else if (sceltaPiatto == 'S' || sceltaPiatto == 's') {
					((EventiCatering)(evento)).setPiatto((CateringAlPiatto)(menù.getMenù(nomePiatto)));
					salva(nomeFile, agenda.getAgenda(), menù.getVettoreMenù(), lista.getLista(), password);
				} else {
					System.out.println("Input errato!");
					menùModificaEvento(evento, nomeFile, agenda, menù, lista, password);
				}
				System.out.println("ATTENZIONE: il costo finale potrebbe essere cambiato.");
				System.out.println("Il nuovo costo totale è di:" + evento.getCosto() + "€.");
				System.out.println("");
				salva(nomeFile, agenda.getAgenda(), menù.getVettoreMenù(), lista.getLista(), password);
			} else System.out.println("Questo menù non esiste.");
		} else { 
			System.out.println("ERRORE: questo evento non prevede il servizio catering.");
		}
	}
	
	//Questo metodo aggiunge un acconto ad un dato evento.
	private static void setAcconto(Eventi evento, String nomeFile, Calendario agenda, GestioneMenù menù, GestioneAnimazione lista, String password) {
		Scanner input = new Scanner (System.in);
		System.out.println("Quanto viene versato come acconto?");
		System.out.println("Attenzione: il valore inserito verà sommato agli altri versamenti effettuati, se presenti.");
		//Controllo che l'utente non inserisca dati erronei.
		try {
			double acconto = input.nextDouble();
			
			//Questo vettore evita che il programma legga il return come input per il prossimo menù di selezione.
			String pulizia = input.nextLine();
			
			if (acconto > 0) {
				evento.setAcconto(acconto);
				salva(nomeFile, agenda.getAgenda(),menù.getVettoreMenù(), lista.getLista(), password );
			} else System.out.println("L'acconto devve essere maggiore di 0");
		}
		catch (InputMismatchException e) {
			//Cancella l'input inserito.
			String controlloErrori = input.nextLine();
			System.out.println("Input errato.Riprovare");
		}	
	}

	// Questo metodo aggiunge un menù Buffet ad un dato evento Catering.
	private static void aggiungiBuffet (Eventi evento, String nomeFile, Calendario agenda, GestioneMenù menù, GestioneAnimazione lista, String password) {
		Scanner input = new Scanner (System.in);
		
		// Controllo che l'evento sia realmente un evento con catering.
		if (evento instanceof EventiCatering) {
			System.out.println("Ecco i nostri menù a Buffet");
			System.out.println("");
			menù.seeBuffet();
			System.out.println("Quale bisogna registrare?");
			String nomeBuffet = input.nextLine();
			
			// Controllo che il menù selezionato esista.
			if (menù.getMenù(nomeBuffet) != null) {
				((EventiCatering)(evento)).setBuffet((CateringBuffet)(menù.getMenù(nomeBuffet)));
				System.out.println("ATTENZIONE: il costo finale potrebbe essere cambiato.");
				System.out.println("Il nuovo costo totale è di:" + evento.getCosto() + "€.");
				System.out.println("");
				salva(nomeFile, agenda.getAgenda(), menù.getVettoreMenù(), lista.getLista(), password);
			} else System.out.println("Questo menù non esiste.");
		} else { 
			System.out.println("ERRORE: questo evento non prevede il servizio catering.");
		}
	}
	
	//Questo metodo modifica la descrizione di un evento
	private static void setDescrizione(Eventi evento, String nomeFile, Calendario agenda, GestioneMenù menù, GestioneAnimazione lista,String password) {
		Scanner input = new Scanner (System.in);
		System.out.println("Scrivi una descrizione dell'evento.");
		String descrizione = input.nextLine();
		evento.setDescrizioneEvento(descrizione);
		salva(nomeFile, agenda.getAgenda(), menù.getVettoreMenù(), lista.getLista(), password);
		
	}
	
	//Questo metodo chiede se registrare alcune informazioni secondarie per un dato evento.
		private static void aggiungiAltreInformazioni (Eventi evento, String nomeFile, Calendario agenda, GestioneMenù menù, GestioneAnimazione lista, String password ) {
			Scanner input = new Scanner (System.in);
			
			//Variabile che registra la scelta fatta dall'utente.
			char scelta;
			do {
				System.out.println("Vuoi aggiungere un acconto per questo evento?");
				System.out.println("[S][N]");
				scelta = input.nextLine().charAt(0);
				
				
				if (scelta == 's' || scelta == 'S') {
					setAcconto(evento, nomeFile, agenda, menù, lista, password);
					
				//Se l'input non è corretto, chiedo di riprovare.
				} else if (scelta != 's' && scelta != 'S' && scelta != 'n' && scelta != 'N') {
					System.out.println("Input errato. Riprovare!");
				}
			} while (scelta != 's' && scelta != 'S' && scelta != 'n' && scelta != 'N');
			
			
			do {
				System.out.println("Vuoi aggiungere una descrizione per questo evento?");
				System.out.println("[S][N]");
				scelta = input.nextLine().charAt(0);
				
				if(scelta == 's' || scelta == 'S') {
					setDescrizione(evento, nomeFile, agenda, menù, lista, password);
					
				//Se l'input non è corretto, chiedo di riprovare.
				}else if (scelta != 's' && scelta != 'S' && scelta != 'n' && scelta != 'N') {
					System.out.println("Input errato. Riprovare!");
				}
			} while (scelta != 'S' && scelta != 's' && scelta != 'N' && scelta != 'n');
			
			if (evento instanceof EventiCatering) {
				
				do {
					System.out.println("Vuoi aggiungere un menù per questo evento?");
					System.out.println("[S][N]");
					scelta = input.nextLine().charAt(0);
					
					if(scelta == 's' || scelta == 'S') {
						char scelta2;
						do {
							System.out.println("Vuoi aggiungere un menù Buffet?");
							System.out.println("[S][N]");
							scelta2 = input.nextLine().charAt(0);
							
							if(scelta2 == 's' || scelta == 'S') {
								aggiungiBuffet(evento, nomeFile, agenda, menù, lista, password);
							
							//Se l'input non è corretto, chiedo di riprovare.
							} else if (scelta != 's' && scelta != 'S' && scelta != 'n' && scelta != 'N') {
								System.out.println("Input errato. Riprovare!");
							}
						} while (scelta2 != 'S' && scelta2 != 's' && scelta2 != 'N' && scelta2 != 'n');
						
						do {
							System.out.println("Vuoi aggiungere un menù al Piatto?");
							System.out.println("[S][N]");
							scelta2 = input.nextLine().charAt(0);
							
							if(scelta2 == 's' || scelta == 'S') {
								aggiungiPiatto(evento, nomeFile, agenda, menù, lista, password);
								
							//Se l'input non è corretto, chiedo di riprovare.
							} else if (scelta != 's' && scelta != 'S' && scelta != 'n' && scelta != 'N') {
								System.out.println("Input errato. Riprovare!");
							}
						
						} while (scelta2 != 'S' && scelta2 != 's' && scelta2 != 'N' && scelta2 != 'n');
						
					//Se l'input non è corretto, chiedo di riprovare.
					} else if (scelta != 's' && scelta != 'S' && scelta != 'n' && scelta != 'N') {
						System.out.println("Input errato. Riprovare!");
					}
					
				}  while (scelta != 's' && scelta != 'S' && scelta != 'n' && scelta != 'N');
				
				do {
					System.out.println("Vuoi aggiungere una torta?");
					System.out.println("[S][N]");
					scelta = input.nextLine().charAt(0);
					
					if(scelta == 's' || scelta == 'S') {
						aggiungiTorta(evento, nomeFile, agenda, menù, lista, password);
						
					//Se l'input non è corretto, chiedo di riprovare.
					} else if (scelta != 's' && scelta != 'S' && scelta != 'n' && scelta != 'N') {
						System.out.println("Input errato. Riprovare!");
					}
				} while (scelta != 'S' && scelta != 's' && scelta != 'N' && scelta != 'n');
			}
		}

	//Questo metodo permette di modificare i servizi di animazione disponibili
		public static void menùAnimazione (String nomeFile, Calendario agenda, GestioneMenù menù, GestioneAnimazione lista, String password) {
			Scanner input = new Scanner (System.in);
			char scelta;
			
			//Mostro tutte le offerte.
			lista.seeAll();
			do {
				System.out.println("Cosa vuoi fare?");
				System.out.println("[A]ggiungi nuovo servizio");
				System.out.println("[M]odifica servizio esistente");
				System.out.println("[*]Torna indietro");
				
				scelta = input.nextLine().charAt(0);
				
				switch (scelta) {
				//Aggiunge un nuovo oggetto.
				case 'a' : case 'A' : {
					try {
						System.out.println("Come deve chiamarsi il nuovo evento?");
						String nome = input.nextLine();
						System.out.println("Quanto deve costare il servizio?");
						double costo = input.nextDouble();
						
						//Questa variabile impedisce allo Scanner di leggere l'input return come input per il prossimo menù di selezione.
						String pulizia = input.nextLine();
						
						Animazione nuova = new Animazione (nome, costo);
						if (lista.addAnimazione(nuova)) {
							System.out.println("Aggiungi una descrizione del tipo di animazione");
							String descrizione = input.nextLine();
							lista.getAnimazione(nome).setDescrizione(descrizione);
							salva(nomeFile, agenda.getAgenda(), menù.getVettoreMenù(), lista.getLista(), password);
						} else {
							System.out.println("ERRORE: esiste già un evento con questo nome");
						}
					}
					catch (InputMismatchException e) {
						//Cancella l'input inserito.
						String controlloErrori = input.nextLine();
						System.out.println("Input errato.Riprovare");
					}	
					break;
				} 
				//Modifica un oggetto esistente.
				case 'm' : case 'M' : {
					System.out.println("Digita il nome del servizio che vuoi modificare.");
					String daModificare = input.nextLine();
					
					if (lista.getAnimazione(daModificare) != null) {
						
						// Chiedo all'utente se vuole modificare il nome dell'evento.
						char sceltaNome;
						do {
							System.out.println("Vuoi modificare il nome di questo servizio?");
							System.out.println("[S][N]");
							
							sceltaNome = input.nextLine().charAt(0);
							
							if (sceltaNome == 's' || sceltaNome == 'S') {
								System.out.println("Come deve chiamarsi?");
								String nome = input.nextLine();
								lista.getAnimazione(daModificare).setNome(nome);
								salva(nomeFile, agenda.getAgenda(), menù.getVettoreMenù(), lista.getLista(), password);
							} else if (sceltaNome != 's' && sceltaNome != 'S' && sceltaNome != 'n' && sceltaNome != 'N') {
								System.out.println("Input errato, riprovare.");
							}
						
						} while (sceltaNome != 's' && sceltaNome != 'S' && sceltaNome != 'n' && sceltaNome != 'N');
						
						// Chiedo all'utente se vuole modificare il costo dell'evento.
						char sceltaCosto;
						do {
							System.out.println("Vuoi modificare il costo di questo evento?");
							System.out.println("[S][N]");
							
							sceltaCosto = input.nextLine().charAt(0);
							
							if (sceltaCosto == 's' || sceltaCosto == 's') {
								try {
									System.out.println("Quanto deve costare?");
									double costo = input.nextDouble();
									
									//Questa variabile inpedisce che lo scanner legga il return come un input.
									String pulizia = input.nextLine();
									
									lista.getAnimazione(daModificare).setCostoAnimazione(costo);
									salva(nomeFile, agenda.getAgenda(), menù.getVettoreMenù(), lista.getLista(), password);
								}
								catch (InputMismatchException e) {
									//Cancella l'input inserito.
									String controlloErrori = input.nextLine();
									System.out.println("Input errato.Riprovare");
								}	
							} else if (sceltaCosto != 's' && sceltaCosto != 'S' && sceltaCosto != 'n' && sceltaCosto != 'N') {
								System.out.println("Input errato, riprovare.");
							}
						
						} while (sceltaCosto != 's' && sceltaCosto != 'S' && sceltaCosto != 'n' && sceltaCosto != 'N');
						
						// Chiedo all'utente se vuole modificare la descrizione dell'evento.
						char sceltaDescrizione;
						do {
							System.out.println("Vuoi modificare la descrizione di questo evento?");
							System.out.println("[S][N]");
							
							sceltaDescrizione = input.nextLine().charAt(0);
							
							if (sceltaDescrizione == 's' || sceltaDescrizione == 's') {
								System.out.println("Scrivi la nuova descrizione");
								String descrizione = input.nextLine();
								lista.getAnimazione(daModificare).setDescrizione(descrizione);
								salva(nomeFile, agenda.getAgenda(), menù.getVettoreMenù(), lista.getLista(), password);
							} else if (sceltaDescrizione != 's' && sceltaDescrizione != 'S' && sceltaDescrizione != 'n' && sceltaDescrizione != 'N') {
								System.out.println("Input errato, riprovare.");
							}
						
						} while (sceltaDescrizione != 's' && sceltaDescrizione != 'S' && sceltaDescrizione != 'n' && sceltaDescrizione != 'N');
						
					} else {
						System.out.println("Il tipi di animazione scelto non esiste.");
					}
					break;
				}
				case '*' : break;
				default: System.out.println("Input errato. Riprovare."); break;
				}
			} while (scelta != '*');
		}
		
	//Questo metodo permette di aggiungere e modificare i menù per il catering.
	private static void menùCatering (String nomeFile, Calendario agenda, GestioneMenù menù, GestioneAnimazione lista, String password) {
		Scanner input = new Scanner (System.in);
		char scelta;
		
		menù.seeAll();
		do {
			System.out.println("Cosa vuoi fare?");
			System.out.println("[A]ggiungi nuovo menù");
			System.out.println("[M]odifica menù esistente");
			System.out.println("[*]Torna indietro");
			
			scelta = input.nextLine().charAt(0);
			
			switch (scelta) {
			//Crea un nuovo oggetto.
			case 'a' : case 'A' : {
				char sceltaTipologia;
				do {
					System.out.println("Che genere di menù vuoi creare?");
					System.out.println("[A]l piatto.");
					System.out.println("[B]uffet.");
					System.out.println("[*]Torna indietro");
					
					sceltaTipologia = input.nextLine().charAt(0);
					
					switch (sceltaTipologia) {
					//Crea un oggetto CateringAlPiatto.
					case 'a' : case 'A' : {
						System.out.println("Come deve chiamarsi il nuovo menù?");
						String nome = input.nextLine();
						System.out.println("Quanto deve costare il singolo piatto?");
						//Controllo che l'utente non inserisca un input erroneo.
						try {
							double costo = input.nextDouble();
							
							//Questa variabile inpedisce allo Scanner di leggere il return come input.
							String pulizia = input.nextLine();
							
							CateringAlPiatto nuovo = new CateringAlPiatto (nome, costo);
							if (menù.addMenù(nuovo)) {
								System.out.println("Aggiungi una descrizione del piatto");
								String descrizione = input.nextLine();
								menù.getMenù(nome).setDescrizioneMenù(descrizione);
								
								char sceltaAllergeni;
								do {
									System.out.println("Vuoi segnalare la presenza di allergeni?");
									System.out.println("[S][N]");
									sceltaAllergeni = input.nextLine().charAt(0);
									
									//Chiedo all'utente se vuole indicare gli allergeni presenti nel menù.
									if (sceltaAllergeni == 's' || sceltaAllergeni == 'S') {
										String allergene;
										do {
											System.out.println("Inserire un allergene per volta. Per terminare digitare spazio ed invio.");
											allergene = input.nextLine();
											if (allergene != " " ) {
												menù.getMenù(nome).setAllergeni(allergene);
											}
										} while (allergene != " ");
									} else if (sceltaAllergeni != 's' && sceltaAllergeni != 'S' && sceltaAllergeni != 'n' && sceltaAllergeni != 'N') {
										System.out.println("Input errato. Riprovare.");
									}
								}while (sceltaAllergeni != 's' && sceltaAllergeni != 'S' && sceltaAllergeni != 'n' && sceltaAllergeni != 'N');
								
								salva(nomeFile, agenda.getAgenda(), menù.getVettoreMenù(), lista.getLista(), password);
							} else {
								System.out.println("ERRORE: esiste già un menù con questo nome");
							}
						}
						catch (InputMismatchException e) {
							//Cancella l'input inserito.
							String controlloErrori = input.nextLine();
							System.out.println("Input errato.Riprovare");
						}	
						break;
					}
					//Crea un oggetto CateringBuffet.
					case 'b' : case 'B':{
						System.out.println("Come deve chiamarsi il nuovo menù?");
						String nome = input.nextLine();
						//Controllo che l'utente non inserisca input erronei.
						try {
							System.out.println("Quanto deve costare il buffet per 20 persone?");
							double costo20 = input.nextDouble();
							System.out.println("Quanto deve costare il buffet per 50 persone?");
							double costo50 = input.nextDouble();
							System.out.println("Quanto deve costare il buffet per 100 persone?");
							double costo100 = input.nextDouble();
							
							//Questa variabile inpedisce allo Scanner di leggere il return come input.
							String pulizia = input.nextLine();
							
							CateringBuffet nuovo = new CateringBuffet (nome, costo20, costo50, costo100);
							
							if (menù.addMenù(nuovo)) {
								System.out.println("Aggiungi una descrizione del piatto");
								String descrizione = input.nextLine();
								menù.getMenù(nome).setDescrizioneMenù(descrizione);
								
								//Chiedo all'utente se vuole segnalare gli allergeni presenti nel menù.
								char sceltaAllergeni;
								do {
									System.out.println("Vuoi segnalare la presenza di allergeni?");
									System.out.println("[S][N]");
									sceltaAllergeni = input.nextLine().charAt(0);
									
									if (sceltaAllergeni == 's' || sceltaAllergeni == 'S') {
										String allergene;
										do {
											System.out.println("Inserire un allergene per volta. Per terminare digitare spazio ed invio.");
											allergene = input.nextLine();
											if (allergene != " " ) {
												menù.getMenù(nome).setAllergeni(allergene);
											}
										} while (allergene != " ");
									} else if (sceltaAllergeni != 's' && sceltaAllergeni != 'S' && sceltaAllergeni != 'n' && sceltaAllergeni != 'N') {
										System.out.println("Input errato. Riprovare.");
									}
								}while (sceltaAllergeni != 's' && sceltaAllergeni != 'S' && sceltaAllergeni != 'n' && sceltaAllergeni != 'N');
								
								salva(nomeFile, agenda.getAgenda(), menù.getVettoreMenù(), lista.getLista(), password);
							} else {
								System.out.println("ERRORE: esiste già un menù con questo nome");
							}
						}
						catch (InputMismatchException e) {
							//Cancella l'input inserito.
							String controlloErrori = input.nextLine();
							System.out.println("Input errato.Riprovare");
						}	
						break;
					}
					case '*' : break;
					default: System.out.println("Input errato. Riprovare."); break;
					}
				} while (sceltaTipologia != '*');
				break;
			}
			//Modifica un servizio esistente.
			case 'b' : case 'B' : {
				System.out.println("Digita il nome del servizio che vuoi modificare.");
				String daModificare = input.nextLine();
				
				if (menù.getMenù(daModificare) != null) {
					// Chiedo all'utente se vuole modificare il nome del menù.
					char sceltaNome;
					do {
						System.out.println("Vuoi modificare il nome di questo menù?");
						System.out.println("[S][N]");
						
						sceltaNome = input.nextLine().charAt(0);
						
						if (sceltaNome == 's' || sceltaNome == 's') {
							System.out.println("Come deve chiamarsi?");
							String nome = input.nextLine();
							menù.getMenù(daModificare).setNomeMenù(nome);
							salva(nomeFile, agenda.getAgenda(), menù.getVettoreMenù(), lista.getLista(), password);
						} else if (sceltaNome != 's' && sceltaNome != 'S' && sceltaNome != 'n' && sceltaNome != 'N') {
							System.out.println("Input errato, riprovare.");
						}
					
					} while (sceltaNome != 's' && sceltaNome != 'S' && sceltaNome != 'n' && sceltaNome != 'N');
				
					// Chiedo all'utente se vuole modificare la descrizione dell'evento.
					char sceltaDescrizione;
					do {
						System.out.println("Vuoi modificare la descrizione di questo menù?");
						System.out.println("[S][N]");
						
						sceltaDescrizione = input.nextLine().charAt(0);
						
						if (sceltaDescrizione == 's' || sceltaDescrizione == 's') {
							System.out.println("Scrivi la nuova descrizione");
							String descrizione = input.nextLine();
							menù.getMenù(daModificare).setDescrizioneMenù(descrizione);
							salva(nomeFile, agenda.getAgenda(), menù.getVettoreMenù(), lista.getLista(), password);
						} else if (sceltaDescrizione != 's' && sceltaDescrizione != 'S' && sceltaDescrizione != 'n' && sceltaDescrizione != 'N') {
							System.out.println("Input errato, riprovare.");
						}
					
					} while (sceltaDescrizione != 's' && sceltaDescrizione != 'S' && sceltaDescrizione != 'n' && sceltaDescrizione != 'N');
					
					//Chiedo all'utente se vuole aggiungere allergeni al menù.
					char sceltaAggiungiAllergene;
					do {
						System.out.println("Vuoi segnalare altri allergeni per questo menù?");
						System.out.println("[S][N]");
						sceltaAggiungiAllergene = input.nextLine().charAt(0);
						
						if (sceltaAggiungiAllergene == 's' || sceltaAggiungiAllergene == 'S') {
							String allergene;
							do {
								System.out.println("Inserire un allergene per volta. Per terminare digitare spazio ed invio.");
								allergene = input.nextLine();
								if (allergene != " " ) {
									menù.getMenù(daModificare).setAllergeni(allergene);
								}
							} while (allergene != " ");
						} else if (sceltaAggiungiAllergene != 's' && sceltaAggiungiAllergene != 'S' && sceltaAggiungiAllergene != 'n' && sceltaAggiungiAllergene != 'N') {
							System.out.println("Input errato. Riprovare.");
						}
					}while (sceltaAggiungiAllergene != 's' && sceltaAggiungiAllergene != 'S' && sceltaAggiungiAllergene != 'n' && sceltaAggiungiAllergene != 'N');
					
					//Chiedo all'utente se vuole rimuovere degli allergeni dalle segnalazioni.
					char sceltaRimuoviAllergene;
					do {
						System.out.println("Vuoi eliminare degli allergeni segnalati per questo menù?");
						System.out.println("[S][N]");
						sceltaRimuoviAllergene = input.nextLine().charAt(0);
						
						if (sceltaRimuoviAllergene == 's' || sceltaRimuoviAllergene == 'S') {
							String allergene;
							do {
								System.out.println("Inserire un allergene per volta. Per terminare digitare spazio ed invio.");
								allergene = input.nextLine();
								if (allergene != " " ) {
									if (!menù.getMenù(daModificare).removeAllergene(allergene)) {
										System.out.println("Allergene non trovato");
									}
								}
							} while (allergene != " ");
						} else if (sceltaRimuoviAllergene != 's' && sceltaRimuoviAllergene != 'S' && sceltaRimuoviAllergene != 'n' && sceltaRimuoviAllergene != 'N') {
							System.out.println("Input errato. Riprovare.");
						}
					}while (sceltaAggiungiAllergene != 's' && sceltaAggiungiAllergene != 'S' && sceltaAggiungiAllergene != 'n' && sceltaAggiungiAllergene != 'N');
					
					// Se il menù da modificare è un menù al Piatto, chiedo se si vuole modificare il costo.
					if (menù.getMenù(daModificare) instanceof CateringAlPiatto) {
						char sceltaCosto;
						do {
							System.out.println("Vuoi modificare il costo del menù?");
							System.out.println("[S][N]");
							sceltaCosto = input.nextLine().charAt(0);
							
							if (sceltaCosto == 's' || sceltaCosto == 'S') {
								//Controllo che l'utente non inserisca input erronei.
								try {
									System.out.println("Digita il nuovo costo.");
									double costo = input.nextDouble();
									
									//Variabile che impedisce allo Scanner di utilizzare l'input return come valore.
									String pulizia = input.nextLine();
									
									((CateringAlPiatto)(menù.getMenù(daModificare))).setPrezzoPiatto(costo);
									salva (nomeFile, agenda.getAgenda(), menù.getVettoreMenù(), lista.getLista(), password);
									}
								catch (InputMismatchException e) {
									//Cancella l'input inserito.
									String controlloErrori = input.nextLine();
									System.out.println("Input errato.Riprovare");
								}	
							} else if (sceltaCosto != 's' && sceltaCosto != 'S' && sceltaCosto != 'n' && sceltaCosto != 'N') {
								System.out.println("Input errato. Riprovare.");
							}
							
						} while (sceltaCosto != 's' && sceltaCosto != 'S' && sceltaCosto != 'n' && sceltaCosto != 'N');
					}
					
					// Se il menù da modificare è un menù Buffet, chiedo se si vuole modificare il costo.
					if (menù.getMenù(daModificare) instanceof CateringBuffet) {
						
						//Quello per 20 persone
						char sceltaCosto20;
						do {
							System.out.println("Vuoi modificare il costo del menù per 20 persone?");
							System.out.println("[S][N]");
							sceltaCosto20 = input.nextLine().charAt(0);
							
							if (sceltaCosto20 == 's' || sceltaCosto20 == 'S') {
								System.out.println("Digita il nuovo costo.");
								//Controllo che l'utente non inserisca input erronei.
								try {
									double costo20 = input.nextDouble();
									
									//Variabile che impedisce allo Scanner di utilizzare l'input return come valore.
									String pulizia = input.nextLine();
									
									((CateringAlPiatto)(menù.getMenù(daModificare))).setPrezzoPiatto(costo20);
									salva (nomeFile, agenda.getAgenda(), menù.getVettoreMenù(), lista.getLista(), password);
								}
								catch (InputMismatchException e) {
									//Cancella l'input inserito.
									String controlloErrori = input.nextLine();
									System.out.println("Input errato.Riprovare");
								}	
							} else if (sceltaCosto20 != 's' && sceltaCosto20 != 'S' && sceltaCosto20 != 'n' && sceltaCosto20 != 'N') {
								System.out.println("Input errato. Riprovare.");
							}
							
						} while (sceltaCosto20 != 's' && sceltaCosto20 != 'S' && sceltaCosto20 != 'n' && sceltaCosto20 != 'N');
						
						//Quello per 50 persone
						char sceltaCosto50;
						do {
							System.out.println("Vuoi modificare il costo del menù per 50 persone?");
							System.out.println("[S][N]");
							sceltaCosto50 = input.nextLine().charAt(0);
							
							if (sceltaCosto50 == 's' || sceltaCosto50 == 'S') {
								//Controllo che l'utente non inserisca input erronei.
								try {
									System.out.println("Digita il nuovo costo.");
									double costo50 = input.nextDouble();
									
									//Variabile che impedisce allo Scanner di utilizzare l'input return come valore.
									String pulizia = input.nextLine();
									
									((CateringAlPiatto)(menù.getMenù(daModificare))).setPrezzoPiatto(costo50);
									salva (nomeFile, agenda.getAgenda(), menù.getVettoreMenù(), lista.getLista(), password);
								}
								catch (InputMismatchException e) {
									//Cancella l'input inserito.
									String controlloErrori = input.nextLine();
									System.out.println("Input errato.Riprovare");
								}	
							} else if (sceltaCosto50 != 's' && sceltaCosto50 != 'S' && sceltaCosto50 != 'n' && sceltaCosto50 != 'N') {
								System.out.println("Input errato. Riprovare.");
							}
							
						} while (sceltaCosto50 != 's' && sceltaCosto50 != 'S' && sceltaCosto50 != 'n' && sceltaCosto50 != 'N');
						
						//Quello per 100 persone
						char sceltaCosto100;
						do {
							System.out.println("Vuoi modificare il costo del menù per 100 persone?");
							System.out.println("[S][N]");
							sceltaCosto100 = input.nextLine().charAt(0);
							
							if (sceltaCosto100 == 's' || sceltaCosto100 == 'S') {
								//Controllo che l'utente non inserisca input erronei.
								try {
									System.out.println("Digita il nuovo costo.");
									double costo100 = input.nextDouble();
									
									//Variabile che impedisce allo Scanner di utilizzare l'input return come valore.
									String pulizia = input.nextLine();
									
									((CateringAlPiatto)(menù.getMenù(daModificare))).setPrezzoPiatto(costo100);
									salva (nomeFile, agenda.getAgenda(), menù.getVettoreMenù(), lista.getLista(), password);
								}
								catch (InputMismatchException e) {
									//Cancella l'input inserito.
									String controlloErrori = input.nextLine();
									System.out.println("Input errato.Riprovare");
								}	
							} else if (sceltaCosto100 != 's' && sceltaCosto100 != 'S' && sceltaCosto100 != 'n' && sceltaCosto100 != 'N') {
								System.out.println("Input errato. Riprovare.");
							}
							
						} while (sceltaCosto100 != 's' && sceltaCosto100 != 'S' && sceltaCosto100 != 'n' && sceltaCosto100 != 'N');
						
					}
				} else {
					System.out.println("Il menù scelto non esiste.");
				}
				break;
			}
			case '*' : break;
			default: System.out.println("Input errato. Riprovare."); break;
			}
		} while (scelta != '*');
	}
		
	// Questo metodo controlla la coerenza delle date inserite. Ritorna true se la data è coerente.
	private static boolean controlloCoerenzaData(int anno, int mese, int giorno) {
		//Controllo che il mese inserito sia valido.
		if (mese <= 0 || mese >= 13) {
			System.out.println("Data non esistente!");
			return false;
		} else {
			switch (mese) {
			//Mesi con 30 giorni.
			case 11 : case 4 : case 6 : case 9 :  {
				if (giorno < 1 || giorno > 30) {
					System.out.println("Data non esistente!");
					return false;
				} else return true;			
			}
			//Febbraio.
			case 2 : {
				//Controllo se l'anno è bisestile.
				if (anno % 4 == 0) {
					if ((anno % 100 == 0) && (anno % 400 != 0) ) {
						if (giorno <0 || giorno >28) {
							System.out.println("Data non esistente!");
							return false;

						} else return true;
					} else {
						if (giorno <0 || giorno >29) {
							System.out.println("Data non esistente!");
							return false;
						} else return true;
					}
				} else {
					if (giorno <0 || giorno >28) {
						System.out.println("Data non esistente!");
						return false;
					}return true;
				}
			}
			//Mesi con 31 giorni.
			default : 
					if (giorno <0 || giorno >31) {
						System.out.println("Data non esistente!");
						return false;
					} else return true;
			}
			}
		
	}

	// Metodo che salva le modifiche sul file.
		private static void salva (String nomeFile, Vector <Eventi> agenda, Vector <Catering> menù, Vector <Animazione> lista, String password ) {
			try {
				ObjectOutputStream fileOutput = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(nomeFile)));
				fileOutput.writeObject(agenda);
				fileOutput.writeObject(menù);
				fileOutput.writeObject(lista);
				fileOutput.writeObject(password);
				fileOutput.close();
			} catch (IOException e) {
				System.out.println("ATTENZIONE: ERRORE DI I/O!");
				System.out.println("LE MODIFICHE NON SONO STATE SALVATE!");
				System.out.println(e);
				System.out.println("");
			}
		}
		
}
