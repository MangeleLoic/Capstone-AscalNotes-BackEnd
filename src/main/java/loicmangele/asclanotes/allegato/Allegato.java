package loicmangele.asclanotes.allegato;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import loicmangele.asclanotes.appunto.Appunto;
import lombok.*;

@Entity
@Table(name = "allegato")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Allegato {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "appunto_id")
    @JsonIgnore
    private Appunto appunto;
    private String path;

    public Allegato(Appunto appunto, String path) {
        this.appunto = appunto;
        this.path = path;
    }
}
