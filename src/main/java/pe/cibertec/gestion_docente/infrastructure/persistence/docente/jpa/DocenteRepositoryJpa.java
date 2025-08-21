package pe.cibertec.gestion_docente.infrastructure.persistence.docente.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.cibertec.gestion_docente.infrastructure.persistence.docente.entity.DocenteEntity;

import java.util.Optional;

public interface DocenteRepositoryJpa extends JpaRepository<DocenteEntity, Integer> {

    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN true ELSE false END " +
           "FROM DocenteEntity d WHERE d.id = :id AND d.estado = '1'")
    boolean existsActivoById(Integer id);

    Optional<DocenteEntity> findById(Integer id);
}
