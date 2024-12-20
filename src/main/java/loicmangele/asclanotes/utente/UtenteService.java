package loicmangele.asclanotes.utente;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import jakarta.transaction.Transactional;
import loicmangele.asclanotes.exceptions.BadRequestException;
import loicmangele.asclanotes.exceptions.UtenteNotFindByUsernameException;
import loicmangele.asclanotes.exceptions.UtenteNotFoundByEmailException;
import loicmangele.asclanotes.exceptions.UtenteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@Transactional
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    private Cloudinary cloudinaryUploader;

    public Utente saveUtente(UtenteDto body){
        this.utenteRepository.findByUsername(body.username()).ifPresent(existingUtente -> {
            throw new BadRequestException("Lo username " + body.username() + " è già in uso");
        });

        this.utenteRepository.findByEmail(body.email()).ifPresent(
                existingUser -> {
                    throw new BadRequestException("L'email " + body.email() + " è già in uso!");
                }
        );
        Utente utente = new Utente();
        utente.setUsername(body.username());
        utente.setEmail(body.email());
        utente.setPassword(bcrypt.encode(body.password()));
        utente.setFullname(body.fullname());
        utente.setProfileImage(body.profileImage());
        utente.setRole(Role.STUDENT);


        return this.utenteRepository.save(utente);
    }

    public Page<Utente> findAllUtenti(int page, int size, String sortBy) {
        if (size > 50) size = 50;
        Pageable pageable = PageRequest.of(page -1, size, Sort.by(sortBy));
        return utenteRepository.findAll(pageable);
    }

    public Optional<Utente> findById(Long id){
        return this.utenteRepository.findById(id);
    }

    public Utente updateUtente(long id, UtenteDto body) {
        return utenteRepository.findById(id).map(utente -> {
            if (body.username() != null) utente.setUsername(body.username());
            if (body.email() != null) utente.setEmail(body.email());
            if (body.password() != null && !body.password().isBlank()) {
                utente.setPassword(bcrypt.encode(body.password()));
            }
            if (body.fullname() != null) utente.setFullname(body.fullname());
            if (body.profileImage() != null) utente.setProfileImage(body.profileImage());

            return utenteRepository.save(utente);
        }).orElseThrow(() -> new UtenteNotFoundException(id));
    }

    public void findByIdAndDelete(long id) {
        Utente utente = utenteRepository.findById(id)
                .orElseThrow(() -> new UtenteNotFoundException(id));
        utenteRepository.delete(utente);
    }

    public Utente findByEmail(String email) {
        return this.utenteRepository.findByEmail(email).orElseThrow(() -> new UtenteNotFoundByEmailException(email));
    }

    public Utente findByUsername(String username) {
        return this.utenteRepository.findByUsername(username).orElseThrow(() -> new UtenteNotFindByUsernameException(username));
    }

    public String uploadImg(MultipartFile file, long id) {

        String url = null;
        try {
            url = (String) cloudinaryUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        } catch (IOException e) {
            throw new BadRequestException("Ci sono stati problemi con l'upload del file!");
        }
        Utente utente = utenteRepository.findById(id).orElseThrow(() -> new UtenteNotFoundException(id));
        utente.setProfileImage(url);
        this.utenteRepository.save(utente);

        return url;

    }

}
