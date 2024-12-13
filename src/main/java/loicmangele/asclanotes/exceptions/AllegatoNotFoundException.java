package loicmangele.asclanotes.exceptions;

public class AllegatoNotFoundException extends RuntimeException {
    public AllegatoNotFoundException(Long id) {
        super("Allegato non trovato");
    }
}
