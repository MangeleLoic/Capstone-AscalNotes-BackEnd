package loicmangele.asclanotes.appunto;


import loicmangele.asclanotes.allegato.Allegato;
import loicmangele.asclanotes.allegato.AllegatoRepository;
import loicmangele.asclanotes.corso.CorsoRepository;
import loicmangele.asclanotes.exceptions.*;
import loicmangele.asclanotes.utente.Utente;
import loicmangele.asclanotes.utente.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppuntoService {
     @Autowired
    private AppuntoRepository appuntoRepository;
    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private CorsoRepository corsoRepository;
    @Autowired
    private AllegatoRepository allegatoRepository;

    public Appunto saveAppunto(AppuntoDTO body, Utente utenteLoggato){
        this.appuntoRepository.findByTitolo(body.titolo()).ifPresent(existingAppunto -> {
            throw new BadRequestException("L'appunto con titolo " + body.titolo() + " è già presente");
        });

        Appunto appunto = new Appunto();
        appunto.setTitolo(body.titolo());
        appunto.setContenuto(body.contenuto());
        appunto.setDataCreazione(LocalDateTime.now());
        appunto.setDataUltimaModifica(LocalDateTime.now());//??RAGIONACI CON CALMA!!
        appunto.setUtente(utenteLoggato);
        appunto.setCorso(corsoRepository.findById(body.corsoId()).orElseThrow(()-> new CorsoNotFoundException(body.corsoId())));
        return appuntoRepository.save(appunto);
    }

    public Page<Appunto> findAllAppunti(int page, int size, String sortBy) {
        if (size > 50) size = 50;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return appuntoRepository.findAll(pageable);
    }

    public Optional<Appunto> findById(Long id){
        return this.appuntoRepository.findById(id);
    }

    public Appunto updateAppunto(long id, AppuntoDTO body, Utente utenteLoggato) {
        return appuntoRepository.findById(id).map(appunto -> {
            if (!appunto.getUtente().getId().equals(utenteLoggato.getId())) {
                throw new UnauthorizedException("Non puoi modificare un appunto che non hai creato.");
            }
            if (!appunto.getTitolo().equals(body.titolo()) && appuntoRepository.findByTitolo(body.titolo()).isPresent()) {
                throw new BadRequestException("Un altro appunto con il titolo " + body.titolo() + " esiste già.");
            }
            appunto.setTitolo(body.titolo());
            appunto.setContenuto(body.contenuto());
            appunto.setDataUltimaModifica(LocalDateTime.now());
            appunto.setCorso(corsoRepository.findById(body.corsoId()).orElseThrow(()-> new CorsoNotFoundException(body.corsoId())));

            return appuntoRepository.save(appunto);
        }).orElseThrow(() -> new AppuntoNotFoundException(id));
    }

    public void findByIdAndDelete(long id, Utente utenteLoggato) {
        Appunto appunto = appuntoRepository.findById(id)
                .orElseThrow(() -> new AppuntoNotFoundException(id));
        if (!appunto.getUtente().getId().equals(utenteLoggato.getId())) {
            throw new UnauthorizedException("Non puoi eliminare un appunto che non hai creato.");
        }
        List<Allegato> allegati = allegatoRepository.findByAppuntoId(id);
        allegatoRepository.deleteAll(allegati);

        appuntoRepository.delete(appunto);
    }


    public Appunto findByTitolo(String titolo) {
        return this.appuntoRepository.findByTitolo(titolo).orElseThrow(()-> new AppuntoNotFoundByTitoloException(titolo));
    }
}
