// pe/cibertec/gestion_docente/application/reserva/usecase/CancelarReservaAulaUseCase.java
package pe.cibertec.gestion_docente.application.reserva.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.cibertec.gestion_docente.domain.reserva.repository.ReservaAulaRepository;

@Component @RequiredArgsConstructor
public class CancelarReservaAulaUseCase {
  private final ReservaAulaRepository repo;
  public void ejecutar(Long id) { repo.cancelar(id); }
}
