package pe.cibertec.gestion_docente.infrastructure.persistence.seguridad.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pe.cibertec.gestion_docente.domain.seguridad.model.UsuarioModel;
import pe.cibertec.gestion_docente.domain.seguridad.repository.UsuarioRepository;
import pe.cibertec.gestion_docente.infrastructure.persistence.seguridad.jpa.UsuarioRepositoryJpa;
import pe.cibertec.gestion_docente.infrastructure.persistence.seguridad.mapper.UsuarioMapper;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SeguridadRepositoryImpl implements UsuarioRepository {

  private final UsuarioRepositoryJpa usuarioJpa;
  private final UsuarioMapper mapper;

  @Override
  public Optional<UsuarioModel> usuarioPorUserName(String username) {
    return usuarioJpa.usuarioPorUsername(username).map(mapper::toModel);
  }

  // si manejas cache de tokens, deja la implementación aquí
  @Override public void guardarToken(String token) { /* ... */ }
  @Override public String obtenerTokenCache(String username) { /* ... */
      return username;
  }
}
