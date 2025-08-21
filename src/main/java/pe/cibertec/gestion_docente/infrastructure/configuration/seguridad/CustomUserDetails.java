package pe.cibertec.gestion_docente.infrastructure.configuration.seguridad;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pe.cibertec.gestion_docente.domain.seguridad.model.UsuarioModel;

import java.util.Collection;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final transient UsuarioModel usuario;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return usuario.getRoles().stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getNombre()))
                .collect(Collectors.toSet());
    }

    @Override public String getPassword() { return usuario.getPassword(); }
    @Override public String getUsername() { return usuario.getUsername(); }
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return usuario.isActivo(); }
}
