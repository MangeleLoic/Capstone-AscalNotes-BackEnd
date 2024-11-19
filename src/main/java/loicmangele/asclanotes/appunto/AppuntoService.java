package loicmangele.asclanotes.appunto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppuntoService {
     @Autowired
    private AppuntoRepository appuntoRepository;
}
