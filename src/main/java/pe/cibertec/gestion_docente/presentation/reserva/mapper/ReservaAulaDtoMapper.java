package pe.cibertec.gestion_docente.presentation.reserva.mapper;

import org.springframework.stereotype.Component;
import pe.cibertec.gestion_docente.domain.reserva.model.ReservaAulaModel;
import pe.cibertec.gestion_docente.presentation.reserva.dto.ReservaAulaRequestDto;
import pe.cibertec.gestion_docente.presentation.reserva.dto.ReservaAulaResponseDto;

@Component
public class ReservaAulaDtoMapper {
  public ReservaAulaModel toModel(ReservaAulaRequestDto r) {
    return ReservaAulaModel.builder()
        .idAula(r.idAula())
        .idCurso(r.idCurso())
        .inicio(r.inicio())
        .fin(r.fin())
        .build();
  }
  public ReservaAulaResponseDto toDto(ReservaAulaModel m) {
    return new ReservaAulaResponseDto(
        m.getIdReserva(), m.getIdAula(), m.getIdCurso(), m.getIdDocente(),
        m.getInicio(), m.getFin(), m.getEstado()
    );
  }
}
