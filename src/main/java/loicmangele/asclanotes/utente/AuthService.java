package loicmangele.asclanotes.utente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UtenteService utenteService;
    public String checkCredentials(UtenteLoginDTO body){

    }
}
