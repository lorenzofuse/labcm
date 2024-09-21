package climatemonitoring;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Questa classe rappresenta un centro di monitoraggio per il monitoraggio del clima.
 * Ogni centro di monitoraggio ha un nome, un indirizzo e una lista di aree di interesse monitorate.
 * Gli operatori possono registrare nuove aree di interesse presso il centro di monitoraggio e visualizzare le aree di interesse monitorate.
 * Inoltre, è possibile creare una lista di centri di monitoraggio leggendo i dati da un file CSV.
 * 
 * Gli attributi di un CentroMonitoraggio sono:
 * - name: il nome del centro di monitoraggio
 * - address: l'indirizzo del centro di monitoraggio (di tipo Indirizzo)
 * - monitoredAreas: la lista di aree di interesse monitorate
 * 
 * I metodi principali di questa classe sono:
 * - CentroMonitoraggio(String name, Indirizzo address): costruttore per creare un centro di monitoraggio con il nome e l'indirizzo specificati.
 * - CentroMonitoraggio(String name, String street, String civicNumber, String postalCode, String city, String province): costruttore alternativo per creare un centro di monitoraggio con il nome e i dettagli dell'indirizzo separati.
 * - registerAreaOfInterest(AreaInteresse area): metodo per registrare un'area di interesse presso il centro di monitoraggio.
 * - printMonitoredAreas(): metodo per stampare a schermo le aree di interesse monitorate.
 * - createCentersList(): metodo statico per creare una lista di centri di monitoraggio leggendo i dati da un file CSV.
 * 
 * La classe utilizza anche la classe Indirizzo per rappresentare gli indirizzi e la classe AreaInteresse per rappresentare le aree di interesse.
 * 
 * @author Lorenzo Fusè Matricola 753168
 * @author Alessandro Ciminella Matricola 753369
 * @author Cosmin Dragan Matricola 754427
 */
public class CentroMonitoraggio {

    public String name;
    public Indirizzo address;
    public List<AreaInteresse> monitoredAreas;

    /**
     * Costruttore della classe CentroMonitoraggio.
     * Crea un'istanza di CentroMonitoraggio con il nome e l'indirizzo specificati.
     * Inizializza la lista delle aree di interesse monitorate.
     * 
     * @param name il nome del centro di monitoraggio
     * @param address l'indirizzo del centro di monitoraggio
     */
    public CentroMonitoraggio(String name, Indirizzo address) {
        this.name = name;
        this.address = address;
        this.monitoredAreas = new ArrayList<>();
    }

    /**
     * Costruttore alternativo della classe CentroMonitoraggio.
     * Crea un'istanza di CentroMonitoraggio con il nome e i dettagli dell'indirizzo separati.
     * Inizializza la lista delle aree di interesse monitorate.
     * 
     * @param name il nome del centro di monitoraggio
     * @param street la via del centro di monitoraggio
     * @param civicNumber il numero civico del centro di monitoraggio
     * @param postalCode il codice postale del centro di monitoraggio
     * @param city la città del centro di monitoraggio
     * @param province la provincia del centro di monitoraggio
     */
    public CentroMonitoraggio(String name, String street, String civicNumber, String postalCode, String city, String province) {
        this.name = name;
        this.address = new Indirizzo(street, civicNumber, postalCode, city, province);
        this.monitoredAreas = new ArrayList<>();
    }

    /**
     * Metodo per registrare un'area di interesse presso il centro di monitoraggio.
     * 
     * @param area l'area di interesse da registrare
     */
    public void registerAreaOfInterest(AreaInteresse area) {
        this.monitoredAreas.add(area);
    }

    /**
     * Metodo per stampare a schermo le aree di interesse monitorate dal centro di monitoraggio.
     */
    public void printMonitoredAreas() {
        for (AreaInteresse area : monitoredAreas) {
            System.out.println(area);
        }
    }

    
    
    
    
    /**
     * Metodo statico per creare una lista di centri di monitoraggio leggendo i dati da un file CSV.
     * 
     * @return una lista di centri di monitoraggio
     */
    
    
    
    public static List<CentroMonitoraggio> createCentersList() {
        List<CentroMonitoraggio> centers = new ArrayList<>();
        String line = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader("./CentroMonitoraggio.csv"));

            while ((line = br.readLine()) != null) {
                String[] lineParts = line.split(";");
                if (lineParts.length >= 3) {
                    String name = lineParts[0];
                    String addressAsString = lineParts[1];
                    String[] areaStrings = lineParts[2].split(",");

                    // Assuming you have a method in Indirizzo class to parse the address string
                    Indirizzo address = Indirizzo.fromString(addressAsString);

                    CentroMonitoraggio center = new CentroMonitoraggio(name, address);

                    centers.add(center);
                }
            }
            br.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        return centers;
    }
    


    
    
    
    
    
    
}
