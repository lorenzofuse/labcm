package climatemonitoring;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Questa classe rappresenta un operatore del sistema di monitoraggio climatico.
 * Gli operatori sono responsabili della registrazione, accesso e gestione delle aree di interesse e dei centri di monitoraggio.
 * 
 * 
 * @author Lorenzo Fuse Matricola 753168
 * @author Alessandro Ciminella Matricola 753369
 * @author Cosmin Dragan Matricola 754427
 */
public class Operatore extends Comune {

	/** Nome dell'operatore */
	public String name;
	/** Cognome dell'operatore */
	public String surname;
	/** Codice fiscale dell'operatore */
	public String fiscalCode;
	/** Email dell'operatore */
	public String email;
	/** ID utente dell'operatore */
	public String userId;
	/** Password dell'operatore */
	public String password;
	/** Nome del centro di monitoraggio gestito dall'operatore */
	public String monitoringCenterName;
	/** Operatore */
	public Operatore operator;

	/**
	 * Costruttore per la classe Operatore.
	 * 
	 * @param name                  Nome dell'operatore
	 * @param surname               Cognome dell'operatore
	 * @param fiscalCode            Codice fiscale dell'operatore
	 * @param email                 Email dell'operatore
	 * @param userId                ID utente dell'operatore
	 * @param password              Password dell'operatore
	 * @param monitoringCenterName  Nome del centro di monitoraggio gestito dall'operatore
	 */
	public Operatore(String name, String surname, String fiscalCode, String email, String userId, String password, String monitoringCenterName) {
		this.name = name;
		this.surname = surname;
		this.fiscalCode = fiscalCode;
		this.email = email;
		this.userId = userId;
		this.password = password;
		this.monitoringCenterName = monitoringCenterName;
	}

	/**
	 * Costruttore vuoto per la classe Operatore.
	 */
	public Operatore() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Crea una lista di operatori leggendoli dal file "OperatoriRegistrati.csv".
	 * 
	 * @return Una lista di operatori
	 * @throws FileNotFoundException se il file "OperatoriRegistrati.csv" non viene trovato
	 * @throws IOException se si verifica un errore di I/O durante la lettura del file
	 * @throws ArrayIndexOutOfBoundsException se si verifica un errore di indice di array non valido
	 * @throws Exception se si verifica un errore generico durante l'esecuzione
	 */
	public static List<Operatore> CreateOperatorList() { 
	    List<Operatore> operators = new ArrayList<Operatore>(); 
	    String line = ""; 
	    
	    try {
	        BufferedReader br = new BufferedReader(new FileReader("./OperatoriRegistrati.csv")); 
	        String[] operatorData = new String[7]; 
	        
	        while ((line = br.readLine()) != null) { 
	            operatorData = line.split(";"); // Divide la riga in base al carattere ';' e memorizza i dati in operatorData
	            operators.add(new Operatore(operatorData[0], operatorData[1], operatorData[2], operatorData[3], operatorData[4], operatorData[5], operatorData[6]));
	        }
	        br.close(); 
	        
	    } catch (FileNotFoundException e) {
	        System.err.println("Errore: Il file non è stato trovato."); 
	    } catch (IOException e) {
	        System.err.println("Errore di I/O durante la lettura del file.");
	    } catch (ArrayIndexOutOfBoundsException e) {
	        System.err.println("Errore: Indice di array non valido."); 
	    } catch (Exception e) {
	        System.err.println("Errore generico: " + e.getMessage()); 
	    }
	    return operators; // Restituisce la lista di operatori
	}


	/**
	 * Verifica se esiste già un utente con lo stesso username.
	 * 
	 * @param user  Username da verificare
	 * @return      True se esiste già un utente con lo stesso username, altrimenti False
	 */
	public static boolean CheckUsername(String user) { // Checks if there are multiple users with the same username

		List<Operatore> operators = CreateOperatorList();
		if (!operators.isEmpty())
			for (Operatore op : operators)
				if (user.equals(op.userId))
					return true;  // userId già preso
		return false; //userId libero
	}

	/**
	 * Registra un nuovo operatore nel sistema.
	 * 
	 * @throws FileNotFoundException se il file "OperatoriRegistrati.csv" non viene trovato
	 * @throws IOException se si verifica un errore di I/O durante la scrittura sul file
	 * @throws SecurityException se l'accesso al file non è consentito per motivi di sicurezza
	 */
	public void Registration() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("./OperatoriRegistrati.csv", true));
			bw.write(name + ";" + surname + ";" + fiscalCode + ";" + email + ";" + userId + ";" + password + ";"
					+ monitoringCenterName + ";" + "\n");
			bw.close();
		} catch (FileNotFoundException e) {
			System.err.println("Errore: Il file non è stato trovato.");
		} catch (IOException e) {
			System.err.println("Errore di I/O durante la scrittura sul file.");
		} catch (SecurityException e) {
			System.err.println("Errore di sicurezza: Accesso al file non consentito.");
		} catch (Exception e) {
			System.err.println("Errore generico: " + e.getMessage());
		}
	}

	/**
	 * Esegue l'accesso di un operatore al sistema.
	 * 
	 * @param username  Username dell'operatore
	 * @param passwd    Password dell'operatore
	 * @return          True se l'accesso ha successo, altrimenti False
	 */
	public static boolean Login(String username, String psw) {
		List<Operatore> operators = CreateOperatorList();
		if (!operators.isEmpty()) {
			for (Operatore op : operators) {
				if (username.equals(op.userId) && psw.equals(op.password))
					return true; // Returns true if the password is correct
			}
		}
		return false; // Returns false if the username or password is incorrect
	}

	/**
	 * Registra le aree di interesse associate a un centro di monitoraggio.
	 * 
	 * @param cm Centro di monitoraggio
	 * @throws FileNotFoundException se il file "CentroMonitoraggio.csv" non viene trovato
	 * @throws IOException se si verifica un errore di I/O durante la scrittura sul file
	 * @throws SecurityException se l'accesso al file non è consentito per motivi di sicurezza
	 */
	public void RegisterCenterAreas(CentroMonitoraggio cm) {

        
	      try{
	    	FileWriter fileWritercsv = new FileWriter("./CentroMonitoraggio.csv", true);
	    	fileWritercsv.append("\n"+cm.name + ";" + cm.address.toString() + ";" + ":" + "\n");
	        fileWritercsv.close();
			} catch (FileNotFoundException e) {
				System.err.println("Errore: Il file non è stato trovato.");
			} catch (IOException e) {
				System.err.println("Errore di I/O durante la scrittura sul file.");
			} catch (SecurityException e) {
				System.err.println("Errore di sicurezza: Accesso al file non consentito.");
			} catch (Exception e) {
				System.err.println("Errore generico: " + e.getMessage());
			}
	    	
	    	
	    	
	        
	        List<Operatore> registeredOperators = CreateOperatorList();
	        
	        
	        for (Operatore o : registeredOperators) {
	            if (o.userId.equals(userId)) {
	                o.monitoringCenterName = cm.name;
	            } 
	        }
	        
	        

	        try (BufferedWriter bw = new BufferedWriter(new FileWriter("./OperatoriRegistrati.csv"))) {
	            for (Operatore o : registeredOperators) {
	                bw.write(o.name + ";" + o.surname + ";" + o.fiscalCode + ";" + o.email + ";" + o.userId + ";" + o.password + ";" + o.monitoringCenterName + ";" + "\n");
	            }
	            bw.close();
	    		} catch (FileNotFoundException e) {
				System.err.println("Errore: Il file non è stato trovato.");
			} catch (IOException e) {
				System.err.println("Errore di I/O durante la scrittura sul file.");
			} catch (SecurityException e) {
				System.err.println("Errore di sicurezza: Accesso al file non consentito.");
			} catch (Exception e) {
				System.err.println("Errore generico: " + e.getMessage());
			}
		}


	/**
	 * Registra un'area di interesse associata a un operatore.
	 * 
	 * @param area Area di interesse da registrare
	 * @throws FileNotFoundException se il file "CentroMonitoraggio.csv" non viene trovato
	 * @throws IOException se si verifica un errore di I/O durante la scrittura sul file
	 * @throws SecurityException se l'accesso al file non è consentito per motivi di sicurezza
	 */  
	public void RegisterAreaOfInterest(AreaInteresse area) {

		List<CentroMonitoraggio> centers = CentroMonitoraggio.createCentersList();

		// Find the center with the matching name
		for (CentroMonitoraggio center : centers) {
			if (center.name.equals(monitoringCenterName)) {
				// Add the area of interest to this center's monitoredAreas list
				center.registerAreaOfInterest(area);
				System.out.println("AREA DI INTERESSE AGGIUNTA AL CENTRO DI MONITORAGGIO : " + monitoringCenterName);
				// Optionally, save the updated list of centers back to the file here
				//saveCentersList(centers); // Implement this method if you want to persist the changes





				try{
					FileWriter fileWritercsv = new FileWriter("./CentroMonitoraggio.csv", true);
					fileWritercsv.append(center.monitoredAreas.get(0).name+ ", ");
					fileWritercsv.close();
				} catch (FileNotFoundException e) {
					System.err.println("Errore: Il file non è stato trovato.");
				} catch (IOException e) {
					System.err.println("Errore di I/O durante la scrittura sul file.");
				} catch (SecurityException e) {
					System.err.println("Errore di sicurezza: Accesso al file non consentito.");
				} catch (Exception e) {
					System.err.println("Errore generico: " + e.getMessage());
				}



			}
		}
	}


	/**
	 * Ottiene un operatore dal file "OperatoriRegistrati.csv" tramite username.
	 * 
	 * @param username Username dell'operatore
	 * @return L'operatore corrispondente all'username, se trovato, altrimenti null
	 * @throws FileNotFoundException se il file "OperatoriRegistrati.csv" non viene trovato
	 * @throws IOException se si verifica un errore di I/O durante la lettura del file
	 * @throws SecurityException se l'accesso al file non è consentito per motivi di sicurezza
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

		} catch (FileNotFoundException e) {
		    System.err.println("Errore: Il file non è stato trovato.");
		} catch (IOException e) {
		    System.err.println("Errore di I/O durante la lettura del file.");
		} catch (SecurityException e) {
		    System.err.println("Errore di sicurezza: Accesso al file non consentito.");
		} catch (Exception e) {
		    System.err.println("Errore generico: " + e.getMessage());
		}

		return null; // Return null if no operator with the given username is found
	}

	/**
	 * Restituisce il nome del centro di monitoraggio.
	 *
	 * @return il nome del centro di monitoraggio
	 */
	public String getMonitoringCenterName() {
		return this.monitoringCenterName;
	}


}
