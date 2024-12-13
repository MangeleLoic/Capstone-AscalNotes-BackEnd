package loicmangele.asclanotes.exceptions;

public class CorsoNotFoundByCodiceException extends RuntimeException {
    public CorsoNotFoundByCodiceException(String codice) {
        super("Corso non trovato con codice: " + codice + " !");
    }
}
