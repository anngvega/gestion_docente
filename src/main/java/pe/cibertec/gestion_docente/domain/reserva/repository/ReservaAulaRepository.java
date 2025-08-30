package pe.cibertec.gestion_docente.domain.reserva.repository;

import pe.cibertec.gestion_docente.domain.reserva.model.ReservaAulaModel;
import pe.cibertec.gestion_docente.domain.shared.valueobject.PaginaResult;
import pe.cibertec.gestion_docente.domain.shared.valueobject.PaginacionRequest;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ReservaAulaRepository {
  boolean existeSolapamiento(Long idAula, LocalDateTime inicio, LocalDateTime fin);
  ReservaAulaModel crear(ReservaAulaModel m);
  PaginaResult<ReservaAulaModel> listar(Long idAula, Integer idDocente, LocalDateTime desde, LocalDateTime hasta, PaginacionRequest pag);
  Optional<ReservaAulaModel> porId(Long id);
  void cancelar(Long id);
}
