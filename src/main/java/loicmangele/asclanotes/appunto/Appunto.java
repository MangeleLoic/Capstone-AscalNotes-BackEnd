package loicmangele.asclanotes.appunto;

import jakarta.persistence.*;
import loicmangele.asclanotes.corso.Corso;
import loicmangele.asclanotes.utente.Utente;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "appunto")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Appunto {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;
    private String titolo;
    private String contenuto;
    private LocalDateTime dataCreazione = LocalDateTime.now();
    private LocalDateTime dataUltimaModifica = LocalDateTime.now();
    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;
    @ManyToOne
    @JoinColumn(name = "corso_id")
    private Corso corso;
}
