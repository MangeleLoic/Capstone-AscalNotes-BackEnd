package loicmangele.asclanotes.exceptions;

public class AppuntoNotFoundException extends RuntimeException {
    public AppuntoNotFoundException(Long id) {
        super("L'appunto con id: " + id + " non è stato trovato.");
    }
}
