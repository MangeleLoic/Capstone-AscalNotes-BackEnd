package loicmangele.asclanotes.exceptions;

public class CorsoNotFoundException extends RuntimeException {
  public CorsoNotFoundException(Long id) {
    super(" Il corso con  " + id + " not è stato trovato!");
  }
}
