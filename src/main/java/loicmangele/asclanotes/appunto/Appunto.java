package loicmangele.asclanotes.appunto;

import jakarta.persistence.*;
import loicmangele.asclanotes.allegato.Allegato;
import loicmangele.asclanotes.corso.Corso;
import loicmangele.asclanotes.utente.Utente;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

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
    @Column(nullable = false)
    private String titolo;
    @Column(nullable = false)
    private String contenuto;
    @Column(nullable = false, updatable = false)
    private LocalDateTime dataCreazione = LocalDateTime.now();
    @Column(nullable = false)
    private LocalDateTime dataUltimaModifica = LocalDateTime.now();
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "utente_id")
    private Utente utente;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "corso_id")
    private Corso corso;

    @OneToMany(mappedBy = "appunto")
    private List<Allegato> allegati;
}
