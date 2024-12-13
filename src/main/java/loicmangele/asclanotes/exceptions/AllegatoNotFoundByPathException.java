package loicmangele.asclanotes.exceptions;

public class AllegatoNotFoundByPathException extends RuntimeException {
    public AllegatoNotFoundByPathException(String path) {
        super(" Allegato con percorso file: " + path + " non trovato!");
    }
}
