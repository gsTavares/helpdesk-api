package com.gustavo.helpdesk.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.gustavo.helpdesk.domain.enums.Perfil;

// Objeto com as regras de contrato do SpringSecurity
public class UserSS implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String email;
    private String senha;
    private Collection<? extends GrantedAuthority> authorities;

    public UserSS(Integer id, String email, String senha, Set<Perfil> perfis) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.authorities = perfis.stream().map(perfil -> new SimpleGrantedAuthority(perfil.getDescricao()))
                .collect(Collectors.toSet());
    }

    public Integer getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    // Retorna senha do usuário
    @Override
    public String getPassword() {
        return senha;
    }

    // Retorna o nome de usuário
    @Override
    public String getUsername() {
        return email;
    }

    // A conta não está expirada?
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // A conta não está bloqueada?
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // As crendenciais da conta não estão expiradas?
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // A conta está habilitada
    @Override
    public boolean isEnabled() {
        return true;
    }

}
