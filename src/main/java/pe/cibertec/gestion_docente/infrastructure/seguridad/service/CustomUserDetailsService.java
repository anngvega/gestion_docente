package pe.cibertec.gestion_docente.infrastructure.seguridad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import pe.cibertec.gestion_docente.infrastructure.seguridad.jpa.UsuarioRepositoryJpa;

@Service @RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UsuarioRepositoryJpa usuarios;

    @Override public UserDetails loadUserByUsername(String username) {
        var u = usuarios.findByUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("No existe"));
        var auth = java.util.List.of(new SimpleGrantedAuthority(u.getRol().getNombreRol()));
        return new org.springframework.security.core.userdetails.User(
                u.getUsername(), u.getPasswordHash(), u.getActivo(), true, true, true, auth);
    }
}
