package loicmangele.asclanotes.utente;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "utente")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Utente {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column
    private String fullname;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private String profileImage;
    @Enumerated(EnumType.STRING)
    private Role role;
}
