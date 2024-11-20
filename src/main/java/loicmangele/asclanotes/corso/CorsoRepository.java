package loicmangele.asclanotes.corso;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CorsoRepository extends JpaRepository<Corso, Long> {
    Optional<Corso> findByCodice(String codice);
    Optional<Corso> findByNome(String nome);
}
