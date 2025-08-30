package pe.cibertec.gestion_docente.infrastructure.persistence.notas.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.cibertec.gestion_docente.infrastructure.persistence.notas.entity.EstructuraNotaEntity;

public interface EstructuraNotaRepositoryJpa extends JpaRepository<EstructuraNotaEntity, Long> {
    boolean existsByIdCursoAndDescripcion(Long idCurso, String descripcion);
    Page<EstructuraNotaEntity> findByIdCurso(Long idCurso, Pageable pageable);
}
