package climatemonitoring;

import java.io.FileWriter;

import prog.io.ConsoleInputManager;

/**
 * Questa classe rappresenta un'area di interesse per il monitoraggio del clima.
 * Ogni area di interesse ha un nome, uno stato, una latitudine e una longitudine.
 * Gli operatori possono inserire le informazioni relative all'area di interesse tramite input da console.
 * Le informazioni inserite vengono visualizzate e restituite come un'istanza di AreaInteresse.
 * 
 * Gli attributi di un'AreaInteresse sono:
 * - name: il nome dell'area di interesse
 * - state: lo stato in cui si trova l'area di interesse
 * - latitude: la latitudine dell'area di interesse
 * - longitude: la longitudine dell'area di interesse
 * 
 * Gli operatori possono inserire il nome, lo stato, la latitudine e la longitudine dell'area di interesse,
 * e i dettagli inseriti verranno restituiti come un'istanza di AreaInteresse.
 * 
 * @author Lorenzo Fusè Matricola 753168
 * @author Alessandro Ciminella Matricola 753369
 * @author Cosmin Dragan Matricola 754427
 */
public class AreaInteresse {

    // Attributi dell'area di interesse
    public String name, state, latitude, longitude;

    /**
     * Costruttore della classe AreaInteresse.
     * Crea un'istanza di AreaInteresse con i parametri specificati.
     * 
     * @param name il nome dell'area di interesse
     * @param state lo stato in cui si trova l'area di interesse
     * @param latitude la latitudine dell'area di interesse
     * @param longitude la longitudine dell'area di interesse
     */
    public AreaInteresse(String name, String state, String latitude, String longitude) {
        this.name = name;
        this.state = state;
        this.latitude = latitude;
        this.longitude = longitude;
    }


	/**
     * Metodo statico per inserire le informazioni relative all'area di interesse da console.
     * 
     * @return un'istanza di AreaInteresse con le informazioni inserite dall'operatore
     */
    public static AreaInteresse inserisciAreeInteresse() {

        ConsoleInputManager inputManager = new ConsoleInputManager();

        // L'operatore inserisce le informazioni relative all'area di interesse
        String areaName = inputManager.readLine("INSERISCI IL NOME DELL'AREA DI INTERESSE : ");
        String areaState = inputManager.readLine("INSERISCI LO STATO DELL'AREA DI INTERESSE : ");
        String areaLatitude = inputManager.readLine("INSERISCI LA LATITUDINE DELL'AREA DI INTERESSE : ");
        String areaLongitude = inputManager.readLine("INSERISCI LA LONGITUDINE DELL'AREA DI INTERESSE : ");

        // Viene creata un'istanza di AreaInteresse con le informazioni inserite
        AreaInteresse area = new AreaInteresse(areaName, areaState, areaLatitude, areaLongitude);
        
        try{
            FileWriter fileWritercsv = new FileWriter("./CoordinateMonitoraggio.csv", true);
            fileWritercsv.append("\n"+areaName + ";" + areaState + ";" + areaLatitude + ";" + areaLongitude+";");
            fileWritercsv.close();
        }catch(Exception e){
            System.out.println(e);
        }
        
        // Le informazioni inserite vengono visualizzate a video
        System.out.println("\n");
        System.out.println("NOME DELL'AREA DI INTERESSE : " + areaName);
        System.out.println("\n");
        System.out.println("STATO DELL'AREA DI INTERESSE : " + areaState);
        System.out.println("\n");
        System.out.println("LA LATITUDINE DELL'AREA DI INTERESSE : "+ areaLatitude);
        System.out.println("\n");
        System.out.println("LA LONGITUDINE DELL'AREA DI INTERESSE : " + areaLongitude);

        return area;
    }

    /**
     * Metodo toString per ottenere una rappresentazione testuale dell'area di interesse.
     * 
     * @return una stringa che rappresenta l'area di interesse
     */
    @Override
    public String toString() {
        return "NomeArea : "+ name + " Stato : " + state + " Latitudine : " + latitude + " Longitudine : " + longitude;
    }

}
