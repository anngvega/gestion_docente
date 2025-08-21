package pe.cibertec.gestion_docente.infrastructure.persistence.seguridad.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.cibertec.gestion_docente.infrastructure.persistence.seguridad.entity.UsuarioEntity;

import java.util.Optional;

public interface UsuarioRepositoryJpa extends JpaRepository<UsuarioEntity, Long> {

    @Query("""
           SELECT DISTINCT u FROM UsuarioEntity u
           LEFT JOIN FETCH u.docente d
           LEFT JOIN FETCH u.roles ur
           LEFT JOIN FETCH ur.rol r
           WHERE u.activo = true AND u.username = :username
           """)
    Optional<UsuarioEntity> usuarioPorUsername(@Param("username") String username);
}
