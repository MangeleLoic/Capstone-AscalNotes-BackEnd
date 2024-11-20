package loicmangele.asclanotes.corso;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "corso")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Corso {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;
    @Column( nullable = false)
    private String nome;
    @Column( nullable = false, unique = true)
    private String codice;
    @Column
    private String descrizione;
    @Column
    private String facolta;
}
