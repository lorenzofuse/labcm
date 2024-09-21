package climatemonitoring;

/**
 * Questa classe rappresenta un indirizzo e fornisce metodi per la sua creazione
 * e rappresentazione come stringa.
 * 
 * Gli attributi di un indirizzo includono via, numero civico, CAP, città e provincia.
 * 
 * Gli autori di questa classe sono:
 * @author Lorenzo Fuse Matricola 753168
 * @author Alessandro Ciminella Matricola 753369
 * @author Cosmin Dragan Matricola 754427
 */
public class Indirizzo {

    /** Via dell'indirizzo. */
    public String street;

    /** Numero civico dell'indirizzo. */
    public String civicNumber;

    /** CAP dell'indirizzo. */
    public String postalCode;

    /** Città dell'indirizzo. */
    public String city;

    /** Provincia dell'indirizzo. */
    public String province;

    /**
     * Costruttore per creare un nuovo oggetto Indirizzo.
     * 
     * @param street Via dell'indirizzo
     * @param civicNumber Numero civico dell'indirizzo
     * @param postalCode CAP dell'indirizzo
     * @param city Città dell'indirizzo
     * @param province Provincia dell'indirizzo
     */
    public Indirizzo(String street, String civicNumber, String postalCode, String city, String province) {
        this.street = street;
        this.civicNumber = civicNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.province = province;
    }

    /**
     * Metodo per ottenere una rappresentazione in stringa dell'indirizzo.
     * 
     * @return Una stringa che rappresenta l'indirizzo nel formato "via numeroCivico CAP città provincia"
     */
    @Override
    public String toString() {
        return street + " " + civicNumber + " " + postalCode + " " + city + " " + province;
    }

    /**
     * Metodo statico per creare un oggetto Indirizzo da una stringa di indirizzo.
     * 
     * @param addressString La stringa contenente l'indirizzo nel formato "via numeroCivico CAP città provincia"
     * @return Un oggetto Indirizzo corrispondente alla stringa, null se la stringa è nel formato errato
     */
    public static Indirizzo fromString(String addressString) {
        String[] parts = addressString.split(" ");
        if (parts.length >= 5) {  // Assuming the address has street, civicNumber, postalCode, city, province
            String street = parts[0];
            String civicNumber = parts[1];
            String postalCode = parts[2];
            String city = parts[3];
            String province = parts[4];
            return new Indirizzo(street, civicNumber, postalCode, city, province);
        } else {
            return null;
        }
    }
}
