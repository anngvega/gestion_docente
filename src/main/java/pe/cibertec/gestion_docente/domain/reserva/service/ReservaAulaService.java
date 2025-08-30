package pe.cibertec.gestion_docente.domain.reserva.service;

import pe.cibertec.gestion_docente.domain.reserva.model.ReservaAulaModel;
import pe.cibertec.gestion_docente.domain.shared.valueobject.PaginaResult;
import pe.cibertec.gestion_docente.domain.shared.valueobject.PaginacionRequest;

import java.time.LocalDateTime;

public interface ReservaAulaService {
  ReservaAulaModel crear(ReservaAulaModel m);
  PaginaResult<ReservaAulaModel> listar(Long idAula, Integer idDocente, LocalDateTime desde, LocalDateTime hasta, PaginacionRequest pag);
  void cancelar(Long id);
}
