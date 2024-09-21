package climatemonitoring;

import java.awt.Desktop;
import java.io.File;

/**
 * Questa classe si occupa di aprire il file PDF del Manuale Utente.
 * Il file PDF deve essere nominato "MU.pdf" e posizionato nella stessa directory del programma.
 * Se il sistema supporta il Desktop AWT, il file PDF verrà aperto utilizzando il programma predefinito per la visualizzazione dei file PDF.
 * Se il file PDF non esiste o non è presente nella directory specificata, viene stampato un messaggio di errore.
 * In caso di eccezioni durante l'apertura del file PDF, vengono stampati i dettagli dell'eccezione.
 * 
 * @author Lorenzo Fuse Matricola 753168
 * @author Alessandro Ciminella Matricola 753369
 * @author Cosmin Dragan Matricola 754427
 */
public class ApriManualeUtente {

    /**
     * Costruttore della classe ApriManualeUtente.
     * Apre il file PDF del Manuale Utente.
     */
    public ApriManualeUtente() {

        try {

            File manualeUtente = new File("./MU.pdf"); // CAMBIARE NOME FILE
            if (manualeUtente.exists()) {

                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(manualeUtente);
                } else {
                    System.out.println("AWT NON SUPPORTATO");
                }

            } else {
                System.out.println("FILE INESISTENTE");
            }

            System.out.println("\n" + "MANUALE APERTO CON SUCCESSO");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
