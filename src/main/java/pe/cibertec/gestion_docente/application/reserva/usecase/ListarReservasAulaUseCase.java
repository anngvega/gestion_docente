// pe/cibertec/gestion_docente/application/reserva/usecase/ListarReservasAulaUseCase.java
package pe.cibertec.gestion_docente.application.reserva.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.cibertec.gestion_docente.domain.reserva.model.ReservaAulaModel;
import pe.cibertec.gestion_docente.domain.reserva.repository.ReservaAulaRepository;
import pe.cibertec.gestion_docente.domain.shared.valueobject.PaginaResult;
import pe.cibertec.gestion_docente.domain.shared.valueobject.PaginacionRequest;

import java.time.LocalDateTime;

@Component @RequiredArgsConstructor
public class ListarReservasAulaUseCase {
  private final ReservaAulaRepository repo;
  public PaginaResult<ReservaAulaModel> ejecutar(Long idAula, Integer idDocente, LocalDateTime desde, LocalDateTime hasta, PaginacionRequest pag) {
    return repo.listar(idAula, idDocente, desde, hasta, pag);
  }
}
