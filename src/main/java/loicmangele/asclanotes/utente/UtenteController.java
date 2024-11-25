package loicmangele.asclanotes.utente;

import loicmangele.asclanotes.exceptions.BadRequestException;
import loicmangele.asclanotes.exceptions.UtenteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/utenti")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    //  GET http://localhost:3001/utenti
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
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
    @PreAuthorize("hasAuthority('ADMIN')")
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
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable Long utenteId) {
        this.utenteService.findByIdAndDelete(utenteId);
    }

    //  Upload Immagine
    @PatchMapping("/{utenteId}/img")
    public String uploadAvatar (@PathVariable long utenteId, @RequestParam("img") MultipartFile file) {
        return this.utenteService.uploadImg(file, utenteId);
    }

    @GetMapping("/me")
    public Utente getProfile(@AuthenticationPrincipal Utente currentAuthenticatedUser) {
        return currentAuthenticatedUser;
    }

    @PutMapping("/me")
    public Utente updateProfile(@AuthenticationPrincipal Utente currentAuthenticatedUser, @RequestBody @Validated UtenteDto body) {
        return this.utenteService.updateUtente(currentAuthenticatedUser.getId(), body);
    }

    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfile(@AuthenticationPrincipal Utente currentAuthenticatedUser) {
        this.utenteService.findByIdAndDelete(currentAuthenticatedUser.getId());
    }


}
