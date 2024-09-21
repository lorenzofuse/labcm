package climatemonitoring;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import prog.io.ConsoleInputManager;

/**
 * Questa classe fornisce i metodi per l'utente non registrato di ricercare
 * aree geografiche per nome e coordinate, nonché per visualizzare le
 * informazioni sull'area geografica.
 * Estende la classe Object.
 * 
 * @author Lorenzo Fusè Matricola 753168
 * @author Alessandro Ciminella Matricola 753369
 * @author Cosmin Dragan Matricola 754427
 */

public class Comune  {
	
    /** Oggetto ConsoleInputManager per la gestione dell'input da console. */
    ConsoleInputManager in = new ConsoleInputManager();

    /** Lista delle aree di interesse. */
    public static List<AreaInteresse> areeInteresse = new ArrayList<>();

    /** Stringa per memorizzare la riga letta dal BufferedReader. */
    static String line = "";

    /**
     * Metodo per cercare un'area geografica per nome.
     * Restituisce l'area di interesse corrispondente se trovata.
     * 
     * @return L'oggetto AreaInteresse corrispondente, null se non trovato
     * @throws IOException Eccezione lanciata in caso di errore di input/output
     */
	public AreaInteresse SearchGeographicalAreaByName() throws IOException {
		String city = in.readLine("INSERISCI LA CITTA' : ").trim();
	    String country = in.readLine("INSERISCI IL PAESE : ").trim();
		
	    BufferReader();
	    
		if (!areeInteresse.isEmpty()) {  
			for (AreaInteresse  a : areeInteresse) {
				if (a != null && a.name != null && a.state!= null) {
					if ((a.name.toUpperCase().equals(city.toUpperCase())) && (a.state.toUpperCase().equals(country.toUpperCase()))) {  
						return a;
					}
				}
			}
		}
		return null;    
	}

	
	 /**
     * Metodo per cercare un'area geografica per coordinate.
     * Restituisce l'area di interesse corrispondente se trovata.
     * Se non viene trovata un'area con coordinate esatte, restituisce l'area più vicina.
     * 
     * @return L'oggetto AreaInteresse corrispondente, null se non trovato
     * @throws IOException Eccezione lanciata in caso di errore di input/output
     */
	public AreaInteresse SearchGeographicalAreaByCoordinates() throws IOException {
		String lat = in.readLine("INSERISCI LA LATITUDINE : ").trim();
		String lon = in.readLine("INSERISCI LA LONGITUDINE : ").trim();
		
		BufferReader();
		
		// Search in the list for the area with the same latitude and longitude as the ones entered
		if (!areeInteresse.isEmpty()) {  // Check if the list is not empty
			for (AreaInteresse  a : areeInteresse) {
				if (a != null && a.latitude != null && a.longitude != null) {
					if ((a.latitude.equals(lat)) && (a.longitude.equals(lon))) {   // Found the desired area
						return a;
					}
				}
			}
	
			AreaInteresse closestArea = areeInteresse.get(0);  // Take the first one from the list

			for (AreaInteresse  a : areeInteresse) { // Then search for the closest coordinates
				double latDouble = Double.parseDouble(lat), lonDouble = Double.parseDouble(lon);    // Entered by the user
				double aLat = Double.parseDouble(a.latitude), aLon = Double.parseDouble(a.longitude);   // From the list
				double closestLat = Double.parseDouble(closestArea.latitude), closestLon = Double.parseDouble(closestArea.longitude);
				if (Math.abs(latDouble - aLat) + Math.abs(lonDouble - aLon) < Math.abs(latDouble - closestLat) + Math.abs(lonDouble - closestLon)) {
					closestArea = a;  // If "a" is closer, update "closestArea"
				}
			}
			return closestArea;
		}
		return null;    
	}

	
    /**
     * Metodo per visualizzare le informazioni sull'area geografica.
     * 
     * @param a L'area di interesse da visualizzare
     */
	public void DisplayGeographicalArea(AreaInteresse a) {
		a.toString();
	}
	
    /**
     * Metodo privato per leggere le informazioni sulle aree geografiche da un file.
     * 
     * @throws IOException Eccezione lanciata in caso di errore di input/output
     */
	private void BufferReader() throws IOException{
		try {
	        BufferedReader br1 = new BufferedReader(new FileReader("./CoordinateMonitoraggio.csv"));
	        areeInteresse = new ArrayList<>();

	        String[] locations = new String[4];

	        while ((line = br1.readLine()) != null) {
	            locations = line.split(";");
	            areeInteresse.add(new AreaInteresse(locations[0], locations[1], locations[2], locations[3])); // Fill the list
	        }
	        br1.close();
	    } catch (FileNotFoundException e) {
	        System.err.println("Errore: Il file non è stato trovato.");
	    } catch (IOException e) {
	        System.err.println("Errore di I/O durante la lettura del file.");
	    }
	}
}
