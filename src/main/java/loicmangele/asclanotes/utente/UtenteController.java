package loicmangele.asclanotes.utente;

import loicmangele.asclanotes.exceptions.BadRequestException;
import loicmangele.asclanotes.exceptions.UtenteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/utenti")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    //  GET http://localhost:3001/utenti
    @GetMapping
    public Page<Utente> findAll(@RequestParam(defaultValue = "1") int page,
                              @RequestParam(defaultValue = "10") int size,
                              @RequestParam(defaultValue = "id") String sortBy) {
        return this.utenteService.findAllUtenti( page, size, sortBy);
    }



    //  GET http://localhost:3001/utenti/{utentiId}
    @GetMapping("/{utenteId}")
    public Utente findById(@PathVariable Long utenteId) {
        return this.utenteService.findById(utenteId)
                .orElseThrow(() -> new UtenteNotFoundException(utenteId));
    }

    //  PUT http://localhost:3001/utenti/{utenteId} (+ req.body)
    @PutMapping("/{utenteId}")
    public Utente findByIdAndUpdate(@PathVariable Long utenteId,
                                  @RequestBody @Validated UtenteDto body,
                                  BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        }
        return this.utenteService.updateUtente(utenteId, body);
    }

    //  DELETE http://localhost:3001/utenti/{utenteId} --> 204
    @DeleteMapping("/{utenteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable Long utenteId) {
        this.utenteService.findByIdAndDelete(utenteId);
    }
}
