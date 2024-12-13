package loicmangele.asclanotes.allegato;

import jakarta.validation.constraints.NotNull;

public record AllegatoDTO(
        @NotNull(message = "L'id dell'allegato è obbligatorio!")
        Long id,
        @NotNull(message = "Il percorso dell'allegato è obbligatorio!")
        String path,
        @NotNull(message = "L'id dell'appunto a cui l'allegato è associato è obbligatorio!")
        Long appuntoId
        ) {
}
