package climatemonitoring;

import java.util.*;
import java.io.*;

/**
 * Questa classe gestisce l'inserimento dei parametri climatici e la loro scrittura su un file CSV.
 * 
 * @author Lorenzo Fuse Matricola 753168
 * @author Alessandro Ciminella Matricola 753369
 * @author Cosmin Dragan Matricola 754427
 */
public class ParametroClimatico{

    /**
     * Costruttore vuoto per la classe ParametroClimatico.
     */
	public ParametroClimatico(){

	}
	
	/**
	 * Metodo per l'inserimento dei parametri climatici da parte dell'utente.
	 * I parametri inseriti verranno scritti su un file CSV.
	 * 
	 * @param operatoreLoggato  L'operatore che sta effettuando l'inserimento dei parametri
	 * @throws FileNotFoundException se il file "ParametriClimatici.csv" non viene trovato
	 * @throws IOException se si verifica un errore di I/O durante la scrittura sul file
	 * @throws SecurityException se l'accesso al file non è consentito per motivi di sicurezza
	 * @throws Exception per altri errori generici che possono verificarsi durante l'esecuzione del metodo
	 */
	public static void inserisciParametriClimatici(String operatoreLoggato) {
		Scanner s1 = new Scanner(System.in);
		Scanner s2 = new Scanner(System.in);
		
													
		System.out.println("Inserisci l'area di interesse: ");
		String areaInterest = s1.nextLine();
													
		System.out.println("Inserisci la data di rilevazione dato: ");
		String detectionDate = s1.nextLine();
		
		
		System.out.println("VENTO: ");
		int swind = reqScore(s1);
		
		System.out.println("Inserisci una nota (Max 256 characters): ");
		String nwind = reqNote(s2);
		
		System.out.println("UMIDITA': ");
		int shum = reqScore(s1);
		
		System.out.println("Inserisci una nota (Max 256 characters): ");
		String nhum = reqNote(s2);
		
		System.out.println("PRESSIONE: ");
		int spress = reqScore(s1);
		
		System.out.println("Inserisci una nota (Max 256 characters): ");
		String npress = reqNote(s2);
		
		System.out.println("TEMPERATURA: ");
		int stemp = reqScore(s1);
		
		System.out.println("Inserisci una nota (Max 256 characters): ");
		String ntemp = reqNote(s2);
		
		System.out.println("PRECIPITAZIONE: ");
		int sprec = reqScore(s1);
		
		System.out.println("Inserisci una nota (Max 256 characters): ");
		String nprec = reqNote(s2);
		
		System.out.println("ALTITUDINE GHIACCIAIO: ");		
		int sglalt = reqScore(s1);
		
		System.out.println("Inserisci una nota (Max 256 characters): ");
		String nglaalt = reqNote(s2);
		
		System.out.println("MASSA GHIACCIAIO: ");
		int smg = reqScore(s1);
		
		System.out.println("Inserisci una nota (Max 256 characters): ");
		String nmg = reqNote(s2);
		
//		System.out.println(operatoreLoggato+ " diocan");
		
		try{
			FileWriter fileWritercsv = new FileWriter("./ParametriClimatici.csv", true);
			fileWritercsv.append(operatoreLoggato + ";" + areaInterest + ";" + detectionDate + "\n");
			fileWritercsv.append("VENTO" + ";" + swind + ";" + nwind + "\n");
			fileWritercsv.append("UMIDITA'" + ";" + shum + ";" + nhum + "\n");
			fileWritercsv.append("PRESSIONE" + ";" + spress + ";" + npress + "\n");
			fileWritercsv.append("TEMPERATURA" + ";" + stemp + ";" + ntemp + "\n");
			fileWritercsv.append("PRECIPITAZIONE" + ";" + sprec + ";" + nprec + "\n");
			fileWritercsv.append("ALTITUDINE DEI GHIACCIAI" + ";" + + sglalt + ";" + nglaalt + "\n");
			fileWritercsv.append("MASSA DEI GHIACCIAI" + ";" + smg + ";" + nmg + "\n");
			fileWritercsv.close();
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
    /**
     * Metodo per richiedere uno score valido all'utente, compreso tra 1 e 5.
     * 
     * @param scanner   Scanner per l'input da parte dell'utente
     * @return          Lo score inserito dall'utente
     */
	public static int reqScore(Scanner scanner) {
        int score;
        do {
            System.out.println("INSERISCI LO SCORE (1-5): ");
            score = scanner.nextInt();
            if (score < 1 || score > 5) {
                System.out.println("LO SCORE DEVE ESSERE COMPRESO TRA 1 E 5.");
            }
        } while (score < 1 || score > 5);
        return score;
    }
	
    /**
     * Metodo per richiedere una nota all'utente, con una lunghezza massima di 256 caratteri.
     * 
     * @param scanner   Scanner per l'input da parte dell'utente
     * @return          La nota inserita dall'utente
     */
	public static String reqNote(Scanner scanner) {
	    String nota = "";
	    do {
	        if (!nota.isEmpty()) {
	            System.out.println("INSERISCI DELLE NOTE (MAX 256 CARATTERI) : ");
	        }
	        nota = scanner.nextLine();
	        if (nota.length() > 256) {
	            System.out.println("LA NOTA HA PIU' DI 256 CARATTERI, RIPROVA.");
	        }
	    } while (nota.length() > 256 && nota.length() != 0);
	    return nota;
	}


		
	}
