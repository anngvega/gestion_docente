package pe.cibertec.gestion_docente.infrastructure.seguridad.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.cibertec.gestion_docente.infrastructure.seguridad.entity.UsuarioEntity;
import java.util.Optional;

public interface UsuarioRepositoryJpa extends JpaRepository<UsuarioEntity, Integer> {
    Optional<UsuarioEntity> findByUsuario(String usuario);
}