package loicmangele.asclanotes.utente;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AuthController {

    @PostMapping("/login")
    public String login(@RequestBody UtenteLoginDTO body) {
        return "token";
    }
}
