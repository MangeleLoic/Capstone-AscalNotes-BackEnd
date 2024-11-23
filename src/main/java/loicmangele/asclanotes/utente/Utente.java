package loicmangele.asclanotes.utente;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "utente")
@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties({"password", "role", "accountNonLocked", "credentialsNonExpired", "accountNonExpired", "authorities", "enabled"})
public class Utente implements UserDetails {
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

    private Role role = Role.STUDENT;

    public Utente(String username, String fullname, String password, String email, String profileImage, Role role) {
        this.username = username;
        this.fullname = fullname;
        this.password = password;
        this.email = email;
        this.profileImage = profileImage;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
