package loicmangele.asclanotes.corso;

import loicmangele.asclanotes.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CorsoService {
    @Autowired
    private CorsoRepository corsoRepository;

    public Corso saveCorso(CorsoDTO body){
        this.corsoRepository.findByNome(body.nome()).ifPresent(existingCorso -> {
            throw new BadRequestException("Il Corso " + body.nome() + " è già presente");
        });

        this.corsoRepository.findByCodice(body.codice()).ifPresent(existingCorso -> {
            throw new BadRequestException("Il Corso con codice " + body.codice() + " è già presente");
        });

        Corso corso = new Corso();
        corso.setNome(body.nome());
        corso.setCodice(body.codice());
        corso.setDescrizione(body.descrizione());
        corso.setFacolta(body.facolta());

        return corsoRepository.save(corso);
    }

    public Page<Corso> findAllCorsi(int page, int size, String sortBy) {
        if (size > 50) size = 50;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return corsoRepository.findAll(pageable);
    }

    public Optional<Corso> findById(Long id){
        return this.corsoRepository.findById(id);
    }

    public Corso updateCorso(long id, CorsoDTO body) {
        return corsoRepository.findById(id).map(corso -> {
            corso.setNome(body.nome());
            corso.setCodice(body.codice());
            corso.setDescrizione(body.descrizione());
            corso.setFacolta(body.facolta());
            return corsoRepository.save(corso);
        }).orElseThrow(() -> new CorsoNotFoundException(id));
    }

    public void findByIdAndDelete(long id) {
        Corso corso = corsoRepository.findById(id)
                .orElseThrow(() -> new CorsoNotFoundException(id));
        corsoRepository.delete(corso);
    }

    public Corso findByCodice(String codice) {
        return this.corsoRepository.findByCodice(codice).orElseThrow(()-> new CorsoNotFoundByCodiceException(codice));
    }

    public Corso findByNome(String nome) {
        return this.corsoRepository.findByNome(nome).orElseThrow(()-> new CorsoNotFoundByNameException(nome));
    }
}
