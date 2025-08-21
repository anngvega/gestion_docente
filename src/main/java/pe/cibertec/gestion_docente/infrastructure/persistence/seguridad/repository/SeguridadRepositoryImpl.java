package pe.cibertec.gestion_docente.infrastructure.persistence.seguridad.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import pe.cibertec.gestion_docente.domain.seguridad.model.RolModel;
import pe.cibertec.gestion_docente.domain.seguridad.model.UsuarioModel;
import pe.cibertec.gestion_docente.domain.seguridad.repository.UsuarioRepository;
import pe.cibertec.gestion_docente.infrastructure.persistence.seguridad.jpa.UsuarioRepositoryJpa;

import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor
public class SeguridadRepositoryImpl implements UsuarioRepository {

    private final UsuarioRepositoryJpa usuarioJpa;

    @Override
    public Optional<UsuarioModel> usuarioPorUserName(String username) {
        return usuarioJpa.usuarioPorUsername(username).map(u ->
                UsuarioModel.builder()
                        .id(u.getId())
                        .username(u.getUsername())
                        .email(u.getEmail())
                        .password(u.getPasswordHash())
                        .nombre(u.getNombre())
                        .apellido(u.getApellido())
                        .activo(Boolean.TRUE.equals(u.getActivo()))
                        .idDocente(u.getDocente() != null ? u.getDocente().getId() : null)
                        .roles(u.getRoles().stream()
                                .map(ur -> RolModel.builder()
                                        .nombre(ur.getRol().getNombre())
                                        .descripcion(ur.getRol().getDescripcion())
                                        .build())
                                .collect(Collectors.toSet()))
                        .build()
        );
    }

    @Override
    public void guardarToken(String token) {
        log.info("Token emitido: {}", token);
    }

    @Override
    public String obtenerTokenCache(String username) {
        return "";
    }
}
