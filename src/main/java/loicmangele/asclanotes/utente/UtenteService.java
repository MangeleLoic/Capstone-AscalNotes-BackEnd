package loicmangele.asclanotes.utente;

import loicmangele.asclanotes.exceptions.BadRequestException;
import loicmangele.asclanotes.exceptions.UtenteNotFindByUsernameException;
import loicmangele.asclanotes.exceptions.UtenteNotFoundByEmailException;
import loicmangele.asclanotes.exceptions.UtenteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;

   /* @Autowired
    private PasswordEncoder bcrypt;*/

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
        //utente.setPassword(bcrypt.encode(body.password()));
        utente.setFullname(body.fullname());
        utente.setProfileImage(body.profileImage());

        if (utente.getRole() == null) {
            utente.setRole(Role.STUDENT);
        }

        return this.utenteRepository.save(utente);
    }

    public Page<Utente> findAllUtenti(int page, int size, String sortBy) {
        if (size > 50) size = 50;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return utenteRepository.findAll(pageable);
    }

    public Optional<Utente> findById(Long id){
        return this.utenteRepository.findById(id);
    }

    public Utente updateUtente(long id, UtenteDto body) {
        Utente found = this.utenteRepository.findById(id).orElse(null);


        if (!found.getEmail().equals(body.email())) {
            this.utenteRepository.findByUsername(body.username()).ifPresent(
                    utente -> {
                        throw new BadRequestException("Username " + body.username() + " già in uso!");
                    }
            );
        }


        found.setFullname(body.fullname());
        found.setUsername(body.username());
        found.setEmail(body.email());
        found.setPassword(body.password());
        found.setProfileImage(body.profileImage());

        return this.utenteRepository.save(found);
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
}
