package it.prova.gestionetriage.security.jwt.dto;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import it.prova.gestionetriage.model.StatoUtente;
import it.prova.gestionetriage.model.Utente;

public class JwtUserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;
	private final String username;
	private final String password;
	private final Collection<? extends GrantedAuthority> authorities;
	private final StatoUtente statoUtente;

	public JwtUserDetailsImpl(String username, String password, Collection<? extends GrantedAuthority> authorities,
			StatoUtente statoUtente) {
		this.username = username;
		this.password = password;
		this.authorities = authorities;
		this.statoUtente = statoUtente;
	}
	
	public static JwtUserDetailsImpl build(Utente utente) {
		List<GrantedAuthority> authorities = utente.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
                .collect(Collectors.toList());
		
        return new JwtUserDetailsImpl(
        		utente.getUsername(),
        		utente.getPassword(),
                authorities,
                utente.getStatoUtente()
        );
    }

	@Override
	public String getUsername() {
		return username;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isEnabled() {
		return statoUtente.getDescrizione().equals("ABILITATO");
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		JwtUserDetailsImpl user = (JwtUserDetailsImpl) o;
		return Objects.equals(username, user.username);
	}

}