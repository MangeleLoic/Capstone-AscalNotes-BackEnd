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
    @Column(nullable = false)
    private Role role;

    public Utente(String username, String fullname, String password, String email, String profileImage, Role role) {
        this.username = username;
        this.fullname = fullname;
        this.password = password;
        this.email = email;
        this.profileImage = profileImage;
        this.role = Role.STUDENT;
    }
}
