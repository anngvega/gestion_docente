package pe.cibertec.gestion_docente.infrastructure.persistence.reserva.mapper;

import org.springframework.stereotype.Component;
import pe.cibertec.gestion_docente.domain.reserva.model.ReservaAulaModel;
import pe.cibertec.gestion_docente.infrastructure.persistence.reserva.entity.ReservaAulaEntity;

@Component
public class ReservaAulaMapper {
  public ReservaAulaModel map(ReservaAulaEntity e) {
    if (e == null) return null;
    return ReservaAulaModel.builder()
        .idReserva(e.getId())
        .idAula(e.getIdAula())
        .idCurso(e.getIdCurso())
        .idDocente(e.getIdDocente())
        .inicio(e.getInicio())
        .fin(e.getFin())
        .estado(e.getEstado())
        .build();
  }
  public ReservaAulaEntity entity(ReservaAulaModel m) {
    if (m == null) return null;
    return ReservaAulaEntity.builder()
        .id(m.getIdReserva())
        .idAula(m.getIdAula())
        .idCurso(m.getIdCurso())
        .idDocente(m.getIdDocente())
        .inicio(m.getInicio())
        .fin(m.getFin())
        .estado(m.getEstado())
        .build();
  }
}
