package pe.cibertec.gestion_docente.infrastructure.persistence.alumno.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.cibertec.gestion_docente.infrastructure.persistence.alumno.entity.AlumnoEntity;

import java.util.Optional;

public interface AlumnoRepositoryJpa extends JpaRepository<AlumnoEntity, Integer> {
  Optional<AlumnoEntity> findByCodigoAlumno(String codigoAlumno);
}
