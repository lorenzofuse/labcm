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
     */
	public static void inserisciParametriClimatici(String operatoreLoggato) {
		Scanner s1 = new Scanner(System.in);
		Scanner s2 = new Scanner(System.in);
		
													
		System.out.println("Inserisci l'area di interesse: ");
		String areaInterest = s1.nextLine();
													
		System.out.println("Inserisci la data di rilevazione dato: ");
		String detectionDate = s1.nextLine();
		
		
		
		int scorevento = richiediScore(s1);
		
		System.out.println("Inserisci una nota (Max 256 characters): ");
		String notavento = richiediNota(s2);
		
		
		int scoreumidita = richiediScore(s1);
		
		System.out.println("Inserisci una nota (Max 256 characters): ");
		String notaumidita = richiediNota(s2);
		
		
		int scorepressione = richiediScore(s1);
		
		System.out.println("Inserisci una nota (Max 256 characters): ");
		String notapressione = richiediNota(s2);
		
		
		int scoretemp = richiediScore(s1);
		
		System.out.println("Inserisci una nota (Max 256 characters): ");
		String notatemp = richiediNota(s2);
		
		
		int scoreprec = richiediScore(s1);
		
		System.out.println("Inserisci una nota (Max 256 characters): ");
		String notaprec = richiediNota(s2);
		
				
		int scorealtit = richiediScore(s1);
		
		System.out.println("Inserisci una nota (Max 256 characters): ");
		String notaaltit = richiediNota(s2);
		
		
		int scoreghiacc = richiediScore(s1);
		
		System.out.println("Inserisci una nota (Max 256 characters): ");
		String notaghiacc = richiediNota(s2);
		
//		System.out.println(operatoreLoggato+ " diocan");
		
		try{
			FileWriter fileWritercsv = new FileWriter("./ParametriClimatici.csv", true);
			fileWritercsv.append(operatoreLoggato + ";" + areaInterest + ";" + detectionDate + "\n");
			fileWritercsv.append("vento" + ";" + scorevento + ";" + notavento + "\n");
			fileWritercsv.append("umidità" + ";" + scoreumidita + ";" + notaumidita + "\n");
			fileWritercsv.append("pressione" + ";" + scorepressione + ";" + notapressione + "\n");
			fileWritercsv.append("temperatura" + ";" + scoretemp + ";" + notatemp + "\n");
			fileWritercsv.append("precipitazioni" + ";" + scoreprec + ";" + notaprec + "\n");
			fileWritercsv.append("attitudine dei ghiacciai" + ";" + + scorealtit + ";" + notaaltit + "\n");
			fileWritercsv.append("massa dei ghiacciai" + ";" + scoreghiacc + ";" + notaghiacc + "\n");
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
	public static int richiediScore(Scanner scanner) {
        int score;
        do {
            System.out.println("Inserisci lo score (1-5): ");
            score = scanner.nextInt();
            if (score < 1 || score > 5) {
                System.out.println("Lo score deve essere compreso tra 1 e 5. Riprova.");
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
	public static String richiediNota(Scanner scanner) {
	    String nota = "";
	    do {
	        if (!nota.isEmpty()) {
	            System.out.println("Inserisci una nota (Max 256 characters): ");
	        }
	        nota = scanner.nextLine();
	        if (nota.length() > 256) {
	            System.out.println("La nota non può superare i 256 caratteri. Riprova.");
	        }
	    } while (nota.length() > 256 && nota.length() != 0);
	    return nota;
	}


		
	}
