package loicmangele.asclanotes.utente;

import loicmangele.asclanotes.exceptions.UnauthorizedException;
import loicmangele.asclanotes.tools.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UtenteService utenteService;

    @Autowired
    private JWT jwt;
    public String checkCredentials(UtenteLoginDTO body) {

        Utente found = this.utenteService.findByUsername(body.username());
        if (found.getPassword().equals(body.password())) {
            String accessToken = jwt.createToken(found);
            return  accessToken;
        } else {
            throw new UnauthorizedException(" Credenziali errate!");
        }
    }
}
