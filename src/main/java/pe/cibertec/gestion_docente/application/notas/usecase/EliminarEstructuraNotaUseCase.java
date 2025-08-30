package pe.cibertec.gestion_docente.application.notas.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.cibertec.gestion_docente.domain.notas.repository.NotaRepository;

@Component @RequiredArgsConstructor
public class EliminarEstructuraNotaUseCase {
  private final NotaRepository repo;
  public void ejecutar(Long idEstructura) { repo.eliminarEstructura(idEstructura); }
}
