package pe.cibertec.gestion_docente.infrastructure.persistence.reserva.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.cibertec.gestion_docente.infrastructure.persistence.reserva.entity.AulaEntity;

public interface AulaRepositoryJpa extends JpaRepository<AulaEntity, Long> {}
