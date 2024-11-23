package loicmangele.asclanotes.utente;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UtenteLoginDTO(
        @NotNull(message = "Lo username è obbligatorio")
                @NotEmpty(message = "Lo username non può essere vuoto")
        String username,

        @NotNull(message = "La Password è obbligatoria")
                @NotEmpty(message = "La password non può essere vuota")
        String password
) {
}
