/*package loicmangele.asclanotes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)//400
    public ErrorsResponseDTO handleBadrequest(BadRequestException ex) {
        return new ErrorsResponseDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED) // 401
    public ErrorsResponseDTO handleUnauthorized(UnauthorizedException ex) {
        return new ErrorsResponseDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN) // 403
    public ErrorsResponseDTO handleForbidden(AuthorizationDeniedException ex) {
        return new ErrorsResponseDTO("Non hai i permessi per accedere", LocalDateTime.now());
    }

    @ExceptionHandler(UtenteNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)//400
    public ErrorsResponseDTO handleUtenteNotFound(UtenteNotFoundException ex) {
        System.out.println("BadRequestException: " + ex.getMessage());
        return new ErrorsResponseDTO(ex.getMessage(), LocalDateTime.now());
    }


    @ExceptionHandler(AllegatoNotFoundByPathException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)//400
    public ErrorsResponseDTO handleAllegatoNotFoundByPath(AllegatoNotFoundByPathException ex) {
        return new ErrorsResponseDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(AllegatoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)//400
    public ErrorsResponseDTO handleAllegatoNotFound(AllegatoNotFoundException ex) {
        return new ErrorsResponseDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(AppuntoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)//400
    public ErrorsResponseDTO handleAppuntoNotFound(AppuntoNotFoundException ex) {
        return new ErrorsResponseDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(AppuntoNotFoundByTitoloException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)//400
    public ErrorsResponseDTO handleAppuntoNotFoundByTitolo(AppuntoNotFoundByTitoloException ex) {
        return new ErrorsResponseDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(CorsoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)//400
    public ErrorsResponseDTO handleCorsoNotFound(CorsoNotFoundException ex) {
        return new ErrorsResponseDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(CorsoNotFoundByCodiceException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)//400
    public ErrorsResponseDTO handleCorsoNotFoundByCodice(CorsoNotFoundByCodiceException ex) {
        return new ErrorsResponseDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(CorsoNotFoundByNameException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)//400
    public ErrorsResponseDTO handleCorsoNotFoundByName(CorsoNotFoundByNameException ex) {
        return new ErrorsResponseDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(UtenteNotFindByUsernameException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)//400
    public ErrorsResponseDTO handleUtenteNotFindByUsername(UtenteNotFindByUsernameException ex) {
        return new ErrorsResponseDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(UtenteNotFoundByEmailException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)//400
    public ErrorsResponseDTO handleUtenteNotFoundByEmail(UtenteNotFoundByEmailException ex) {
        return new ErrorsResponseDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)//500
    public ErrorsResponseDTO handleGeneric(Exception ex) {
        ex.printStackTrace();
        return new ErrorsResponseDTO("Problema lato server! Giuro che risolveremo presto!", LocalDateTime.now());
    }
}*/

