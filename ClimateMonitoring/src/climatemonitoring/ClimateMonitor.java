package climatemonitoring;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;
import java.util.Random;
import prog.io.ConsoleInputManager;

/**
 * Questa classe rappresenta il monitoraggio climatico.
 * Estende la classe AreaInteresse e implementa le funzionalità
 * per la gestione del monitoraggio climatico, inclusa la registrazione,
 * il login, la gestione delle impostazioni home e altro.
 *
 * @author Lorenzo Fusè Matricola 753168
 * @author Alessandro Ciminella Matricola 753369
 * @author Cosmin Dragan Matricola 754427
 */


public class ClimateMonitor extends AreaInteresse {
	
	

    /** Lista dei centri di monitoraggio. */
    public static List<ClimateMonitor> monitoringCenters;

    /** Password per l'autenticazione. */
    public static String password;

    /** Scanner per l'input da console. */
    private static Scanner in = new Scanner(System.in);

    /** Generatore di numeri casuali. */
    private static Random random = new Random();

    /** Flag per verificare la validità dell'email. */
    private static boolean checkEmail;

    /** Flag per verificare il dominio dell'email. */
    private static boolean checkDomain;

    /** Codice fiscale dell'utente. */
    public static String fiscalcode;

    /** Flag per verificare il formato del codice fiscale. */
    private static boolean checkFiscalCode;

    /** Identificatore dell'utente. */
    public String userId;
    
    /** Identificatore centro di monitoraggio dell'utente loggato */
    public static String OperatorCenter;

    /**
     * Costruttore della classe ClimateMonitor.
     *
     * @param name      Nome dell'area di interesse
     * @param state     Stato dell'area di interesse
     * @param latitude  Latitudine dell'area di interesse
     * @param longitude Longitudine dell'area di interesse
     */
    public ClimateMonitor(String name, String state, String latitude, String longitude) {
        super(name, state, latitude, longitude);
    }


    /**
     * Metodo principale per l'esecuzione dell'applicazione.
     * Gestisce il menu principale e le varie funzionalità dell'app.
     *
     * @param args Argomenti della riga di comando (non utilizzati)
     * @throws Exception Eccezione generica per gestire eventuali errori
     */
    public static void main(String[] args) throws Exception {
		
		ConsoleInputManager in = new ConsoleInputManager();
		Comune municipality = new Comune();
		String choice;


		System.out.println("\n" + "CLIMATE MONITORING" + "\n");

		do {
			System.out.println("BENVENUTO! EFFETTUA UNA SCELTA\n1) CERCA UN'AREA GEOGRAFICA PER IL NOME\n2) CERCA UN'AREA GEOGRAFICA PER LE COORDINATE\n3) REGISTRATI\n4) LOGIN\n5) IMPOSTAZIONI HOME\n6) EXIT");
			choice = in.readLine("\nSCELTA: ");
			switch(choice) {   
			
			//CERCA AREA GEOGRAFICA PER NOME -- SEARCH GEOGRAPHICAL AREA BY NAME
				
			
			case "1":
			   
			    AreaInteresse foundArea = municipality.SearchGeographicalAreaByName();
			    if (foundArea != null) {
			        System.out.println(foundArea.toString() + "\n");
			    } else {
			        System.out.println("Area geografica non trovata per il nome e il paese specificati.");
			    }
			    break;


				
			//CERCA AREA GEOGRAFICA TRAMITE COORDINATE -- SEARCH GEOGRAPHICAL AREA BY COORDINATES
			case "2":   
			AreaInteresse foundAreaByCoordinates = municipality.SearchGeographicalAreaByCoordinates();
		    if (foundAreaByCoordinates != null) {
		        System.out.println(foundAreaByCoordinates.toString() + "\n");
		    } else {
		        System.out.println("Area geografica non trovata per il nome e il paese specificati.");
		    }
		    break;
				
				
			//REGISTRAZIONE -- REGISTRATION
			case "3":   
				String name = in.readLine("INSERISCI IL NOME : ");

				while(name.isBlank()) {
					System.out.println("ATTENZIONE : CAMPO VUOTO");
					System.out.println("NOME: ");
					name = in.readLine();
				}

				String checkname = name.replaceAll(" ", "");

				for(int i = 0; i < checkname.length(); i++) {
					char c = checkname.charAt(i);
					if(!Character.isLetter(c)) {
						System.out.println("ATTENZIONE : NOME INSERITO NON AMMESSO, REINSERIRE:");

					}
				}
				String surname = in.readLine("INSERISCI IL COGNOME : ");

				while(surname.isBlank()) {
					System.out.println("ATTENZIONE : CAMPO VUOTO");
					System.out.println("COGNOME: ");
					surname = in.readLine();
				}

				String checksurname = surname.replaceAll(" ", "");

				for(int i = 0; i < checksurname.length(); i++) {
					char c = checksurname.charAt(i);
					if(!Character.isLetter(c)) {
						System.out.println("ATTENZIONE : COGNOME NON INSERITO, REINSERIRE:");

					}
				}

				String fiscalcode = in.readLine("INSERISCI IL CODICE FISCALE : ").toUpperCase();
				long checklength = fiscalcode.chars().count();
				//CONTROLLO SE IL CODICE FISCALE INSERITO HA 16 CARATTERI  -- CHECK IF THE FISCAL CODE ENTERED HAS 16 CHARACTERS
				while(checklength != 16) { 
					System.out.println("ATTENZIONE: LUNGHEZZA CODICE FISCALE NON AMMESSA, REINSERIRE: ");
					System.out.println("CODICE FISCALE : ");
					fiscalcode = in.readLine().toUpperCase();
					checklength = fiscalcode.chars().count();
				}

				checkFiscalCode(fiscalcode);

				while(checkFiscalCode == false) {
					System.out.println("ATTENZIONE: FORMATO CODICE FISCALE ERRATO, REINSERIRE: ");
					System.out.println("CODICE FISCALE : ");

					fiscalcode = in.readLine().toUpperCase();

					checklength = fiscalcode.chars().count();

					while(checklength != 16) {
						System.out.println("ATTENZIONE: LUNGHEZZA CODICE FISCALE ERRATA, REINSERIRE: ");
						System.out.println("CODICE FISCALE :");
						fiscalcode = in.readLine().toUpperCase();
						checklength = fiscalcode.chars().count();
					}

					checkFiscalCode(fiscalcode); 
				}

				String zipcode;
				while(true) {
					zipcode = in.readLine("INSERISCI IL CODICE POSTALE : ");
					int ziplength = String.valueOf(zipcode).length();
					if (ziplength == 5) {
						//ESCI DAL CICLO SE LA LUNGHEZZA È CORRETTA -- EXIT THE LOOP IF THE LENGTH IS CORRECT
						break; 
					} else {
						System.out.println("ERRORE: IL CAP DEVE ESSERE FORMATO DA ALMENO 5 INTERI.");
					}
				}		

				String email = in.readLine("INSERISCI LA MAIL : ");
				while(email.isBlank()) {
					System.out.println("ATTENZIONE : CAMPO VUOTO");
					System.out.println("\nEMAIL: ");
					email = in.readLine().toLowerCase();
				}

				checkEmail(email);

				while(checkEmail == false) {
					System.out.println("ATTENZIONE: EMAIL NON DISPONIBILE, REINSERIRE: ");
					System.out.println("\nEMAIL: ");
					email = in.readLine().toLowerCase();

					while(email.isBlank()) {
						System.out.println("ATTENZIONE : CAMPO VUOTO");
						System.out.println("\nEMAIL: ");
						email = in.readLine().toLowerCase();
					}

					checkEmail(email); 
				}

				if(email.contains("@studenti.uninsubria.it") || email.contains("@icloud.com") || email.contains("@gmail.com") || email.contains("@hotmail.com") || email.contains("@outlook.com") || email.contains("@yahoo.com")) {
					checkDomain = true;
				}else {
					checkDomain = false;
				}

				while(checkDomain == false) {
					System.out.println("ATTENZIONE : DOMINIO DELL'EMAIL INSERITO NON CONFORME O CAMPO VUOTO ");
					System.out.println("\nEMAIL: ");
					email = in.readLine().toLowerCase();

					checkEmail(email);

					while(checkEmail == false) {
						System.out.println("ATTENZIONE: EMAIL NON DISPONIBILE REINSERIRE: ");
						System.out.println("Email: ");
						email = in.readLine().toLowerCase();

						while(email.isBlank()) {
							System.out.println("ATTENZIONE : campo vuoto");
							System.out.println("Email: ");
							email = in.readLine().toLowerCase();
						}

						checkEmail(email); 
					}

					if(email.contains("@studenti.uninsubria.it") || email.contains("@gmail.com") || email.contains("@icloud.com") || email.contains("@hotmail.com") || email.contains("@outlook.com") || email.contains("@yahoo.com") && !email.isBlank()){
						checkDomain = true;
					}
				}


				String username ;
				do {    // Check for username availability
					username = in.readLine("INSERISCI L'USERNAME : ");
					if(Operatore.CheckUsername(username))
						System.out.println("USERNAME GIA' IN USO !");
				} while(Operatore.CheckUsername(username));
				MenuPassword();
				Operatore operator = new Operatore(name, surname, zipcode, email, username, password, " ");
				operator.Registration();
				System.out.println("REGISTRAZIONE AVVENUTA CON SUCCESSO !");
				break;
				
				
			case "4":   // LOGIN
				String loginUsername = in.readLine("INSERISCI USERNAME: ");
				String loginPassword = in.readLine("INSERISCI PASSWORD: ");
				
				if(Operatore.Login(loginUsername, loginPassword)) { 
					System.out.println("LOGIN EFFETTUATO !");
					// Registered operator menu ...
					String option;
					
					do {
						// Find the operator who logged in
						List<Operatore> registeredOperators = Operatore.CreateOperatorList();
						Operatore registeredOperator = null;
						for (Operatore o : registeredOperators) {
							if(o.userId.equals(loginUsername))
								registeredOperator = o;		
						} 
						
						OperatorCenter=registeredOperator.getMonitoringCenterName();
						
						option = in.readLine("\nBENVENUTO OPERATORE! EFFETTUA UNA SCELTA\n1) REGISTRA UN NUOVO CENTRO DI MONITORAGGIO\n2) AGGIUNGI UN'AREA DI INTERESSE\n3) INSERISCI PARAMETRI CLIMATICI\n4) EXIT\n");
						
						
						switch (option) {
						case "1":
							if(registeredOperator.monitoringCenterName.equals(" ")) { // If the operator does not already have an associated monitoring center
								String centerName = in.readLine("INSERISCI IL NOME DEL CENTRO DI MONITORAGGIO : ").trim();
								String centerStreet = in.readLine("INSERISCI LA VIA/PIAZZA DEL CENTRO DI MONITORAGGIO : ").trim();
								String centerCivicNumber = in.readLine("INSERISCI IL NUMERO CIVICO DEL CENTRO DI MONITORAGGIO : ").trim();
								String centerPostalCode = in.readLine("INSERISCI IL CODICE POSTALE : ").trim();
								String centerCity = in.readLine("INSERISCI LA CITTA' DEL CENTRO DI MONITORAGGIO : ").trim();
								String centerProvince = in.readLine("INSERISCI LA PROVINCIA DEL CENTRO DI MONITORAGGIO : ").trim();
								CentroMonitoraggio center = new CentroMonitoraggio(centerName, centerStreet, centerCivicNumber, centerPostalCode, centerCity, centerProvince);
								registeredOperator.RegisterCenterAreas(center);
							}
							else {
								System.out.println("L'OPERATORE " + loginUsername + " HA GIA' ASSOCIATO UN CENTRO DI MONITORAGGIO !"); 
							}
							break;
						case "2":
							if (registeredOperator.monitoringCenterName.equals(" ")) {
						        System.out.println("ATTENZIONE: PRIMA DI REGISTRARE UN'AREA DEVI REGISTRA UN CENTRO DI MONITORAGGIO !");
						    } else {
						        AreaInteresse area = AreaInteresse.inserisciAreeInteresse();
						        registeredOperator.RegisterAreaOfInterest(area);
						        System.out.println("AREA DI INTERESSE AGGIUNTA CON SUCCESSO!");
						    }
						    break;
						case "3":
							ParametroClimatico.inserisciParametriClimatici(OperatorCenter);
							break;
						default:
							break;
						}
					} while (Integer.parseInt(option) != 4);
				}  

				else
					System.out.println("USERNAME O PASSWORD SBAGLIATE");
				break;
			case "5":
				HomeSettings();
				break;
			case "6":
				confirmExit();
				break;
			default:
				System.out.println("ERRORE: scelta inserita non corretta, reinserire");
				System.out.println("\n" + "******************************************");
				break;
			}
		}while(Integer.parseInt(choice)!=5);

	}

    /**
     * Metodo per gestire il menu di selezione della password.
     * L'utente può scegliere di generare automaticamente la password
     * o inserirla manualmente.
     */
	public static void MenuPassword(){ 
		
		try {
			System.out.println("INSERISCI PASSWORD");
			System.out.println("1- GENERA AUTOMATICAMENTE" + "\n" + "2- INSERISCI MANUALMENTE");
			System.out.println("SCELTA: ");
			int scelta = in.nextInt();

			switch(scelta) {
			case 1:
				PasswordGenerator();
				System.out.println("PASSWORD : \n" + password);
				in.nextLine();
				break;
			case 2:
				Password();
				break;
			default: 
				System.out.println("ERRORE: SCELTA INSERITA NON CORRETTA, REINSERIRE");
				MenuPassword();
			}
		}catch(Exception e) {
			in.nextLine();
			System.out.println("ERRORE: SCELTA INSERITA NON CORRETTA, REINSERIRE");
			MenuPassword();
		}
	}

	 /**
     * Metodo per inserire manualmente la password.
     *
     * @return La password inserita
     */
	public static String Password(){ 

		System.out.println("PASSWORD: ");
		in.nextLine();
		password = in.nextLine();
		long LunghezzaPassword = password.chars().count();

		while(LunghezzaPassword < 8) {
			System.out.println("ATTENZIONE : LUNGHEZZA PASSWORD NON AMMESSA, LA PASSWORD DEVE AVERE ALMENO 8 CARATTERI, REINSERIRE: ");
			System.out.println("PASSWORD: ");
			password = in.nextLine();
			LunghezzaPassword = password.chars().count();
		}

		return password;

	}

	 /**
     * Metodo per generare automaticamente una password casuale.
     *
     * @return La password generata
     */
	public static String PasswordGenerator(){ // metodo che genera automaticamente la password

		String minuscole = "qwertyuiopasdfghjklzxcvbnm";
		String maiuscole = "QWERTYUIOPASDFGHJKLZXCVBNM";
		String num = "123456789";
		String car_speciali = "!%&/)(=?^@#*}{][-><";

		String finale = minuscole + maiuscole + num + car_speciali;

		char[] passwordGenerator = new char[9];
		for(int i = 0; i < 9; i++) {
			passwordGenerator[i] = finale.charAt(random.nextInt(finale.length()));
		}

		password = new String(passwordGenerator);

		return password;

	}

    /**
     * Metodo per controllare la validità dell'email.
     *
     * @param email Email da controllare
     */
	public static void checkEmail(String email) { // metodo che contolla se la email inserita � gi� stata inserita da un altro utente, quindi diventa non disponibile.

		checkEmail = true;

		String controllo = "Email : " + email;

		try {
			BufferedReader br = new BufferedReader(new FileReader("./OperatoriRegistrati.csv"));
			String s;
			while((s = br.readLine()) != null) {
				if(s.contains(controllo)){
					checkEmail = false;
				}
			}
			br.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}


    /**
     * Metodo per controllare il formato del codice fiscale.
     *
     * @param fiscalcode Codice fiscale da controllare
     */
	public static void checkFiscalCode(String fiscalcode) {

		if(Character.isLetter(fiscalcode.charAt(0)) && Character.isLetter(fiscalcode.charAt(1)) && Character.isLetter(fiscalcode.charAt(2)) && Character.isLetter(fiscalcode.charAt(3)) && Character.isLetter(fiscalcode.charAt(4)) && Character.isLetter(fiscalcode.charAt(5)) && Character.isDigit(fiscalcode.charAt(6)) && Character.isDigit(fiscalcode.charAt(7)) && Character.isLetter(fiscalcode.charAt(8)) && Character.isDigit(fiscalcode.charAt(9)) && Character.isDigit(fiscalcode.charAt(10)) && Character.isLetter(fiscalcode.charAt(11)) && Character.isDigit(fiscalcode.charAt(12)) && Character.isDigit(fiscalcode.charAt(13)) && Character.isDigit(fiscalcode.charAt(14)) && Character.isLetter(fiscalcode.charAt(15))) {
			checkFiscalCode = true;
		}else {
			checkFiscalCode = false;
		}

	}

	 /**
     * Metodo per ottenere l'utente dato il suo username.
     *
     * @param username Username dell'utente da cercare
     * @return L'oggetto Operatore corrispondente all'utente
     */
	public Operatore getUserByUsername(String username) {
		try (BufferedReader br = new BufferedReader(new FileReader("./OperatoriRegistrati.csv"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(";");
				if (parts.length >= 6) { // Assuming each line has at least 6 fields
					String userId = parts[5].trim(); // Assuming username is stored at index 4
					if (userId.equals(username)) {
						// Found the user, create and return the Operator object
						Operatore operator = new Operatore();
						operator.name = parts[0].trim();
						operator.surname = parts[1].trim();
						operator.fiscalCode = parts[2].trim();
						operator.email = parts[3].trim();
						operator.userId = userId;
						operator.password = parts[5].trim();
						operator.monitoringCenterName = parts[6].trim();
						return operator;
					}
				}
			}

			System.out.println("Utente non trovato nel file OperatoriRegistrati.csv, devi prima registrare un centro.");

		} catch (Exception e) {
			System.err.println("Errore durante la lettura del file: " + e.getMessage());
		}

		return null; // Return null if no operator with the given username is found
	}
	
	 /**
     * Metodo per gestire le impostazioni della home.
     * Permette di visualizzare informazioni sull'applicazione,
     * consultare il manuale utente o tornare alla schermata principale.
     */
	public static void HomeSettings() {

		System.out.println("\n" + "******************************************");
		System.out.println("\n" + "IMPOSTAZIONI" + "\n");

		try {
			System.out.println("1- INFO APPLICAZIONE " + "\n" + "2- MANUALE UTENTE " + "\n" + "3- HOME ");
			System.out.println("\nINSERIRE LA SCELTA: ");
			int scelta = in.nextInt();

			switch(scelta) {
			case 1:
				InfoApp info = new InfoApp();
				HomeSettings();
				break;
			case 2:
				ApriManualeUtente manualeUtente = new ApriManualeUtente();
				in.nextLine();
				HomeSettings();
				break;
			case 3:
				System.out.println("\n" + "******************************************");
				main(null);
				break;
			default:
				System.out.println("ERRORE: SCELTA INSERITA NON CORRETTA, REINSERIRE");
				HomeSettings();
				break;
			}
		}catch(Exception e) {
			in.nextLine();
			System.out.println("ERRORE: SCELTA INSERITA NON CORRETTA, REINSERIRE");
			HomeSettings();
		}
		
	}
	
    /**
     * Metodo per confermare l'uscita dall'applicazione.
     * Chiede all'utente se è sicuro di voler uscire e gestisce la risposta.
     */
	public static void confirmExit(){ 
		
		System.out.println("\n" + "SEI SICURO DI CHIUDERE L'APPLICAZIONE CLIMATE MONITORING ?" + "\n");
		System.out.println("PER CONFERMARE DIGITARE (SI) PER ANNULARE DIGITARE (NO)");
		System.out.println("\nDIGITARE LA SCELTA : ");
		String exit = in.nextLine().toUpperCase();
		
		switch(exit){
		case "SI": 
			System.out.println("\n" + "USCITA EFFETTUATA! ");
			System.exit(0);
			break;
		case "NO":
			System.out.println("\n" + "******************************************");
			HomeSettings();
			break;
		default:
			System.out.println("\nERRORE: SCELTA INSERITA NON CORRETTA, REINSERIRE");
			confirmExit();
		}
		
	}


}

