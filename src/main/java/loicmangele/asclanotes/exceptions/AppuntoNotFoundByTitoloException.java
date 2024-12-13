package loicmangele.asclanotes.exceptions;

public class AppuntoNotFoundByTitoloException extends RuntimeException {
    public AppuntoNotFoundByTitoloException(String titolo) {
        super(" Appunto con titolo: " + titolo + " non trovato!");
    }
}
