package pe.cibertec.gestion_docente.application.notas.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.cibertec.gestion_docente.domain.notas.model.EstructuraNotaModel;
import pe.cibertec.gestion_docente.domain.notas.repository.NotaRepository;
import pe.cibertec.gestion_docente.domain.shared.valueobject.PaginaResult;
import pe.cibertec.gestion_docente.domain.shared.valueobject.PaginacionRequest;

@Component @RequiredArgsConstructor
public class ListarEstructurasUseCase {
  private final NotaRepository repo;
  public PaginaResult<EstructuraNotaModel> ejecutar(Long idCurso, PaginacionRequest pag) {
    return repo.listarEstructuras(idCurso, pag);
  }
}
