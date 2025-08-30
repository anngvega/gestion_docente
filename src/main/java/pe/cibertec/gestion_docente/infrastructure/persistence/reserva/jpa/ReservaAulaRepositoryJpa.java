package pe.cibertec.gestion_docente.infrastructure.persistence.reserva.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import pe.cibertec.gestion_docente.infrastructure.persistence.reserva.entity.ReservaAulaEntity;

import java.time.LocalDateTime;

public interface ReservaAulaRepositoryJpa extends JpaRepository<ReservaAulaEntity, Long> {

  // ¿Existe solapamiento para el mismo aula en estado RESERVADA?
  @Query("""
    select case when count(r)>0 then true else false end
    from ReservaAulaEntity r
    where r.idAula = :idAula and r.estado = 'RESERVADA'
      and ( (r.inicio < :fin) and (r.fin > :inicio) )
  """)
  boolean existeSolapamiento(@Param("idAula") Long idAula,
                             @Param("inicio") LocalDateTime inicio,
                             @Param("fin") LocalDateTime fin);

  @Query("""
     select r from ReservaAulaEntity r
     where (:idAula is null or r.idAula = :idAula)
       and (:idDocente is null or r.idDocente = :idDocente)
       and (:desde is null or r.inicio >= :desde)
       and (:hasta is null or r.fin <= :hasta)
  """)
  Page<ReservaAulaEntity> buscar(@Param("idAula") Long idAula,
                                 @Param("idDocente") Integer idDocente,
                                 @Param("desde") LocalDateTime desde,
                                 @Param("hasta") LocalDateTime hasta,
                                 Pageable pageable);
}
