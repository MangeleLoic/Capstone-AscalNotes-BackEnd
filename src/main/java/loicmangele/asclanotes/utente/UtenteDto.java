package loicmangele.asclanotes.utente;

import jakarta.validation.constraints.*;



public record UtenteDto (
        @NotNull(message = "lo username è obbligatorio!")
        @Size( min=3, max=15, message = "il numero di caratteri dello username dev'essere tra i 3 e i 15 caratteri ")
        @Pattern(
                regexp = "^[A-Za-z0-9_]+$",
                message = "lo username deve contenere caratteri validi")
        String username,

        @NotNull(message = "l'email è obbligatoria!")
        @Email(message = "l'email inserita non è valida!!")
         String email,

        @NotNull(message = "La password è obbligatoria!")
        @Size(min = 8, message = "La password deve avere almeno 8 caratteri!")
        @Pattern(
                regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
                message = "La password deve contenere almeno una lettera maiuscola, una lettera minuscola, un numero e un carattere speciale"
        )
        String password,


                @NotNull(message = "il nome completo è obbligatorio!")
        @Size( min=5, message = "il numero di caratteri dell'username dev'essere di minimo 5 caratteri ")
        @Pattern(
                regexp = "^[a-zA-Zà-úÀ-Ú\\s]+$",
                message = "Il nome completo può contenere solo lettere e spazi.")
        String fullname,
        String profileImage) {

}