package com.example.MinorProject.DigitalLibrary.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class UserRecord implements UserDetails {


    private final static String AUTHORITIES_DELIMITER = "::";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;

    @Column(unique = true,nullable = false)
    private String username;
    private String password;
    private String authorities;

    @JoinColumn
    @OneToOne(mappedBy = "userRecord")
    @JsonIgnoreProperties({"userRecord"})
    @Getter
    private Admin admin;

    @JoinColumn
    @OneToOne(mappedBy = "userRecord")
    @JsonIgnoreProperties({"userRecord"})
    @Getter
    private Student student;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String[] authoritiesList = this.authorities.split(AUTHORITIES_DELIMITER);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for(int i = 0 ; i < authoritiesList.length ; i++){
            GrantedAuthority user = new SimpleGrantedAuthority(authoritiesList[i]);
            grantedAuthorities.add(user);
        }
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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