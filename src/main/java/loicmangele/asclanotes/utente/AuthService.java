/*package loicmangele.asclanotes.utente;

import loicmangele.asclanotes.exceptions.UnauthorizedException;
import loicmangele.asclanotes.tools.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private JWT jwt;

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private PasswordEncoder bcrypt;

    public String checkCredentials(UtenteLoginDTO body) {

        Utente found = this.utenteService.findByUsername(body.username());

        if (bcrypt.matches(body.password(), found.getPassword())) {

            String accessToken = jwt.createToken(found);
            return accessToken;
        } else {
            throw new UnauthorizedException("Credenziali errate!");
        }
    }
}*/
