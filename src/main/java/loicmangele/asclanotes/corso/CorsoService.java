package loicmangele.asclanotes.corso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CorsoService {
    @Autowired
    private CorsoRepository corsoRepository;
}
