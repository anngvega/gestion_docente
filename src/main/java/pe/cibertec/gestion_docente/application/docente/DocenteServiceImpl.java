package pe.cibertec.gestion_docente.application.docente;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.cibertec.gestion_docente.domain.docente.model.DocenteModel;
import pe.cibertec.gestion_docente.domain.docente.repository.DocenteRepository;
import pe.cibertec.gestion_docente.domain.docente.service.DocenteService;
import pe.cibertec.gestion_docente.domain.shared.valueobject.PaginaResult;
import pe.cibertec.gestion_docente.domain.shared.valueobject.PaginacionRequest;

@Service
@RequiredArgsConstructor
public class DocenteServiceImpl implements DocenteService {
  private final DocenteRepository repo;

  @Override
  public PaginaResult<DocenteModel> listar(PaginacionRequest pag) {
    return repo.listar(pag);
  }
}
