package loicmangele.asclanotes.allegato;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AllegatoRepository extends JpaRepository<Allegato, Long> {

    List<Allegato> findByAppuntoId(Long appuntoId);

}
