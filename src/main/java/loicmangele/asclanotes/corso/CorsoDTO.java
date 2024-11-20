package loicmangele.asclanotes.corso;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CorsoDTO(
        @NotNull(message = "il nome del corso è obbligatorio!")
                @Size(min = 3, max = 20, message = "il nome del corso dev'essere compreso tra 3 e 20 caratteri")
        String nome,
        @NotNull(message = "il codice del corso è obbligatorio!")
                @Size(min = 3, max = 7, message = "il codice dev'essere compreso tra 3 e 7 caratteri")
        String codice,
        @Size(max = 150, message = "la descrizione del corso può avere un massimo di 150 caratteri!")
        String descrizione,
        @Size(max = 25, message = "il nome della facoltà può avere un massimo di 25 caratteri")
        String facolta)
{
}
