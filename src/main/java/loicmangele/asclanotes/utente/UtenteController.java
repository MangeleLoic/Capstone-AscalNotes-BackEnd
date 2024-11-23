package loicmangele.asclanotes.utente;

import jakarta.validation.Valid;
import loicmangele.asclanotes.exceptions.UtenteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/utenti")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping
    public Page<Utente> findAll(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(defaultValue = "id") String sortBy) {
        if (page < 0) page = 0;
        return utenteService.findAllUtenti(page, size, sortBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Utente saveUtente(@RequestBody @Valid UtenteDto body) {
        return utenteService.saveUtente(body);
    }

    @GetMapping("/{utenteId}")
    public Utente findById(@PathVariable Long utenteId) {
        return utenteService.findById(utenteId)
                .orElseThrow(() -> new UtenteNotFoundException(utenteId));
    }

    @PutMapping("/{utenteId}")
    public Utente findByIdAndUpdate(@PathVariable Long utenteId,
                                    @RequestBody @Valid UtenteDto body) {
        return utenteService.updateUtente(utenteId, body);
    }

    @DeleteMapping("/{utenteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyRole(ADMIN)")
    public void findByIdAndDelete(@PathVariable Long utenteId) {
        utenteService.findByIdAndDelete(utenteId);
    }
}
