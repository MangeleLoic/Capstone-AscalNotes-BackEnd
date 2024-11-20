package loicmangele.asclanotes.allegato;


import loicmangele.asclanotes.appunto.Appunto;
import loicmangele.asclanotes.appunto.AppuntoRepository;
import loicmangele.asclanotes.exceptions.AllegatoNotFoundByPathException;
import loicmangele.asclanotes.exceptions.AllegatoNotFoundException;
import loicmangele.asclanotes.exceptions.AppuntoNotFoundException;
import loicmangele.asclanotes.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AllegatoService {
    @Autowired
    private AllegatoRepository allegatoRepository;
    @Autowired
    private AppuntoRepository appuntoRepository;

    public Allegato save(AllegatoDTO body){
        this.allegatoRepository.findById(body.id()).ifPresent(existingAllegato -> {
            throw new BadRequestException("Il percorso " + body.path() + " è già presente");
        });
        Allegato allegato = new Allegato();
        allegato.setPath(body.path());
        allegato.setAppunto(appuntoRepository.findById(body.appuntoId()).orElseThrow(()->new AppuntoNotFoundException(body.appuntoId())));
        return allegatoRepository.save(allegato);
    }

    public List<Allegato> findAllAllegati(Long appuntoId) {
        return this.allegatoRepository.findAll();
    }


    public Allegato findById(long id){
        return this.allegatoRepository.findById(id).orElseThrow(()->new AppuntoNotFoundException(id));
    }
    public void findByIdAndDelete(long id) {
        Allegato allegato = allegatoRepository.findById(id)
                .orElseThrow(() -> new AllegatoNotFoundException(id));
        allegatoRepository.delete(allegato);
    }

    public Allegato findByPath(String path) {
        return this.allegatoRepository.findByPath(path).orElseThrow(()-> new AllegatoNotFoundByPathException(path));
    }
}

