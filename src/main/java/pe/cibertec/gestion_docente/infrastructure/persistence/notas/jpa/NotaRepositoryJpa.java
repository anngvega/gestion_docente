package pe.cibertec.gestion_docente.infrastructure.persistence.notas.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import pe.cibertec.gestion_docente.infrastructure.persistence.notas.entity.NotaEntity;

public interface NotaRepositoryJpa extends JpaRepository<NotaEntity, Long> {

    boolean existsByCodigoAlumnoAndIdCursoAndIdEstructura(String codigoAlumno, Long idCurso, Long idEstructura);

    @Query("""
       SELECT n FROM NotaEntity n
       WHERE (:idCurso IS NULL OR n.idCurso = :idCurso)
         AND (:idDocente IS NULL OR n.idDocente = :idDocente)
         AND (:codigoAlumno IS NULL OR n.codigoAlumno = :codigoAlumno)
    """)
    Page<NotaEntity> buscar(@Param("idCurso") Long idCurso,
                            @Param("idDocente") Integer idDocente,
                            @Param("codigoAlumno") String codigoAlumno,
                            Pageable pageable);
}
