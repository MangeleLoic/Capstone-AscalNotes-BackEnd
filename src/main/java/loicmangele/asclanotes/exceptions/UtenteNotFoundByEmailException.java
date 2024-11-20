package loicmangele.asclanotes.exceptions;

public class UtenteNotFoundByEmailException extends RuntimeException {
    public UtenteNotFoundByEmailException(String email) {
        super("L'email fornita non è associata a nessun utente! ");
    }
    }

