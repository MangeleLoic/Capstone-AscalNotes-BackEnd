package loicmangele.asclanotes.appunto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record AppuntoDTO(
        @NotNull(message = "il titolo è obbligatorio!")
                @Size(min = 5, max = 30, message = "il titolo dev'essere tra 5 e 30 caratteri!")
        String titolo,
        @NotNull(message = "il contenuto è obbligatorio!")
        String contenuto,
        @NotNull(message = "l'utente che carica l'qppunto è obbligatorio!")
        Long utenteId,
        @NotNull(message = "Il corso associato all'appunto è obbligatorio!")
        Long corsoId,

        List<Long> allegati

        //DA RIVEDERE QUANDO SVIUPPO LE ALTRE ENTITIES!!!
       /*
        List<Long> commenti*/ ) {
}
