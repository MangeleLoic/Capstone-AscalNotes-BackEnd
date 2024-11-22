package loicmangele.asclanotes.utente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UtenteLoginDTO(
        @NotNull(message = "Lo username è obbligatorio")
        String username,

        @NotNull(message = "La Password è obbligatoria")
        String password
) {
}
