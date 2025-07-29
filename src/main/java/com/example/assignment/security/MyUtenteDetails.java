package com.example.assignment.security;

import com.example.assignment.entity.Utente;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

public class MyUtenteDetails implements UserDetails {
    private final Utente utente;

    public MyUtenteDetails(Utente utente) {
        this.utente = utente;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(utente.getRole());
        return List.of(authority);
    }

    @Override
    public String getPassword() {
        return utente.getPassword();
    }

    @Override
    public String getUsername() {
        return utente.getNome_utente();
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
