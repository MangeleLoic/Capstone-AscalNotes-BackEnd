package loicmangele.asclanotes.corso;

import jakarta.validation.Valid;
import loicmangele.asclanotes.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/corsi")
public class CorsoController {

    @Autowired
    private CorsoService corsoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Corso creaCorso(@Valid @RequestBody CorsoDTO corsoDTO, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload: " + message);
        }
        return this.corsoService.saveCorso(corsoDTO);
    }

    //  GET http://localhost:3001/corsi
    @GetMapping
    public Page<Corso> findAllCorsi(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        return this.corsoService.findAllCorsi(page, size, sortBy);
    }

    //  GET http://localhost:3001/corsi/{corsiId}
    @GetMapping("/{corsoId}")
    public Corso findById(@PathVariable Long corsoId) {
        return this.corsoService.findById(corsoId);
    }

    //  PUT http://localhost:3001/corsi/{corsiId} (+ req.body)
    @PutMapping("/{corsoId}")
    public Corso findByIdAndUpdate(@PathVariable Long corsoId,
                                     @RequestBody @Valid CorsoDTO body,
                                     BindingResult validationResult
                                     ) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        }
        return this.corsoService.updateCorso(corsoId, body );
    }

    //  DELETE http://localhost:3001/utenti/{utenteId} --> 204
    @DeleteMapping("/{corsoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable Long corsoId) {
        this.corsoService.findByIdAndDelete(corsoId);
    }

}
