package loicmangele.asclanotes.allegato;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import loicmangele.asclanotes.appunto.Appunto;
import loicmangele.asclanotes.appunto.AppuntoRepository;
import loicmangele.asclanotes.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Service
public class AllegatoService {
    @Autowired
    private AllegatoRepository allegatoRepository;
    @Autowired
    private AppuntoRepository appuntoRepository;
    @Autowired
    private Cloudinary cloudinaryUploader;



    public Allegato uploadAllegato(MultipartFile file, Long appuntoId) {
        String url;
        try {
            url = (String) cloudinaryUploader.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.asMap("folder", "attachments")
            ).get("url");
        } catch (IOException e) {
            throw new BadRequestException("Ci sono stati problemi con l'upload del file!");
        }

        Appunto appunto = appuntoRepository.findById(appuntoId)
                .orElseThrow(() -> new AppuntoNotFoundException(appuntoId));
        Allegato allegato = new Allegato(appunto, url);

        return allegatoRepository.save(allegato);
    }



    public List<Allegato> findAllAllegati(Long appuntoId) {
        return allegatoRepository.findByAppuntoId(appuntoId);
    }



    public Allegato findById(long id){
        return this.allegatoRepository.findById(id).orElseThrow(()->new AllegatoNotFoundException(id));
    }
    public void findByIdAndDelete(long id) {
        Allegato allegato = allegatoRepository.findById(id)
                .orElseThrow(() -> new AllegatoNotFoundException(id));
        allegatoRepository.delete(allegato);
    }


}

