package loicmangele.asclanotes.exceptions;

public class CorsoNotFoundByNameException extends RuntimeException {
    public CorsoNotFoundByNameException(String nome) {
        super("Nessun corso con nome: " + nome + " trovato!");
    }
}
