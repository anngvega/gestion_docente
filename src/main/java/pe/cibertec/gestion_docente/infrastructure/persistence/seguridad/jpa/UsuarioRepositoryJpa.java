package pe.cibertec.gestion_docente.infrastructure.persistence.seguridad.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.cibertec.gestion_docente.infrastructure.persistence.seguridad.entity.UsuarioEntity;

import java.util.Optional;

public interface UsuarioRepositoryJpa extends JpaRepository<UsuarioEntity, Long> {

    @Query("""
  select u
  from UsuarioEntity u
  left join fetch u.roles ur
  left join fetch ur.rol r
  left join fetch u.docente d
  where u.username = :username
""")
    Optional<UsuarioEntity> usuarioPorUsername(@Param("username") String username);
}
