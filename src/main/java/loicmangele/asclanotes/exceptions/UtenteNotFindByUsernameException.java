package loicmangele.asclanotes.exceptions;

public class UtenteNotFindByUsernameException extends RuntimeException {
    public UtenteNotFindByUsernameException(String username) {
        super("L'username fornito non è associato a nessun utente! ");
    }
}
