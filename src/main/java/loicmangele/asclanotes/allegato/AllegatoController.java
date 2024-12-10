package loicmangele.asclanotes.allegato;

import loicmangele.asclanotes.utente.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/allegati")
public class AllegatoController {

    @Autowired
    private AllegatoService allegatoService;

    @PostMapping("/upload")
    public Allegato uploadAllegato(@RequestParam("file") MultipartFile file,
                                   @RequestParam("appuntoId") Long appuntoId
                                  ) {
        return allegatoService.uploadAllegato(file, appuntoId);
    }

    @GetMapping("/{appuntoId}")
    public List<Allegato> findAllAllegati(@PathVariable Long appuntoId) {
        return allegatoService.findAllAllegati(appuntoId);
    }

    @GetMapping("/{allegatoId}")
    public Allegato findById(@PathVariable Long allegatoId) {
        return allegatoService.findById(allegatoId);
    }

    @DeleteMapping("/{allegatoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllegato(@PathVariable Long allegatoId,
                               @AuthenticationPrincipal Utente utenteLoggato) {

        allegatoService.findById(allegatoId);
        allegatoService.findByIdAndDelete(allegatoId);
    }
}
