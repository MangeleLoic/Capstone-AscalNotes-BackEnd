package loicmangele.asclanotes.exceptions;

public class UtenteNotFoundException extends RuntimeException {
    public UtenteNotFoundException(Long id) {
        super("L'utente con id: " + id + " non è stato trovato.");
    }
}
