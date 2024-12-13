package loicmangele.asclanotes.appunto;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppuntoRepository extends JpaRepository<Appunto, Long> {
    Optional<Appunto> findByTitolo(String titolo);
}
