package loicmangele.asclanotes.appunto;

import jakarta.validation.Valid;
import loicmangele.asclanotes.exceptions.AppuntoNotFoundException;
import loicmangele.asclanotes.exceptions.BadRequestException;
import loicmangele.asclanotes.exceptions.UnauthorizedException;
import loicmangele.asclanotes.utente.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.stream.Collectors;

@RestController
@RequestMapping("/appunti")
public class AppuntoController {

    @Autowired
    private AppuntoService appuntoService;

    @PostMapping
    public Appunto creaAppunto(@Valid @RequestBody AppuntoDTO appuntoDTO,
                                 @AuthenticationPrincipal Utente utenteLoggato) {
        return appuntoService.saveAppunto(appuntoDTO, utenteLoggato);
    }

    //  GET http://localhost:3001/appunti
    @GetMapping
    public Page<Appunto> findAllAppunti(@RequestParam(defaultValue = "1") int page,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(defaultValue = "id") String sortBy) {
        return this.appuntoService.findAllAppunti( page -1, size, sortBy);
    }

    //  GET http://localhost:3001/appunti/{appuntiId}
    @GetMapping("/{appuntiId}")
    public Appunto findById(@PathVariable Long appuntiId) {
        return this.appuntoService.findById(appuntiId)
                .orElseThrow(() -> new AppuntoNotFoundException(appuntiId));
    }

    //  GET http://localhost:3001/titolo/{appuntiTitle}
    @GetMapping("/titolo/{appuntiTitle}")
    public Appunto findByTitolo(@PathVariable String appuntiTitle) {
        return this.appuntoService.findByTitolo(appuntiTitle);
    }


    //  PUT http://localhost:3001/appunti/{appuntoId} (+ req.body)
    @PutMapping("/{appuntoId}")
    public Appunto findByIdAndUpdate(@PathVariable Long appuntoId,
                                    @RequestBody @Validated AppuntoDTO body,
                                    BindingResult validationResult,
                                     @AuthenticationPrincipal Utente utenteLoggato) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        }
        return this.appuntoService.updateAppunto(appuntoId, body, utenteLoggato);
    }

    //  DELETE http://localhost:3001/appunti/{appuntoId} --> 204
    @DeleteMapping("/{appuntoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable Long appuntoId,
                                  @AuthenticationPrincipal Utente utenteLoggato) {
        this.appuntoService.findByIdAndDelete(appuntoId, utenteLoggato);
    }

}


