package pe.cibertec.gestion_docente.application.reserva;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.cibertec.gestion_docente.application.reserva.usecase.CancelarReservaAulaUseCase;
import pe.cibertec.gestion_docente.application.reserva.usecase.CrearReservaAulaUseCase;
import pe.cibertec.gestion_docente.application.reserva.usecase.ListarReservasAulaUseCase;
import pe.cibertec.gestion_docente.domain.reserva.model.ReservaAulaModel;
import pe.cibertec.gestion_docente.domain.reserva.service.ReservaAulaService;
import pe.cibertec.gestion_docente.domain.shared.valueobject.PaginaResult;
import pe.cibertec.gestion_docente.domain.shared.valueobject.PaginacionRequest;

import java.time.LocalDateTime;

@Service @RequiredArgsConstructor
public class ReservaAulaServiceImpl implements ReservaAulaService {
  private final CrearReservaAulaUseCase crear;
  private final ListarReservasAulaUseCase listar;
  private final CancelarReservaAulaUseCase cancelar;

  @Override public ReservaAulaModel crear(ReservaAulaModel m) { return crear.ejecutar(m); }
  @Override public PaginaResult<ReservaAulaModel> listar(Long idAula, Integer idDocente, LocalDateTime desde, LocalDateTime hasta, PaginacionRequest pag) {
    return listar.ejecutar(idAula, idDocente, desde, hasta, pag);
  }
  @Override public void cancelar(Long id) { cancelar.ejecutar(id); }
}
