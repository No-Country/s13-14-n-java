package com.latam.companerosDeViajeAPI.persistence.entities.user;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import com.latam.companerosDeViajeAPI.persistence.entities.country.Country;
import com.latam.companerosDeViajeAPI.persistence.entities.Interest.Interest;
import com.latam.companerosDeViajeAPI.utils.Gender;
import com.latam.companerosDeViajeAPI.utils.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
@Table(name ="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
@Getter
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    @Column(unique = true)
    private String email;
    private String name;
    private LocalDate birthDate;
    private String address;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
    @Enumerated(EnumType.STRING)
    @ManyToMany
    @JoinTable(
            name = "users_interest", //
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "interest_id")
    )
    private List<Interest>interest;
    @Column(unique = true)
    private String phoneNumber;
    private String profilePicture;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}