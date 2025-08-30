package pe.cibertec.gestion_docente.application.alumno;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.cibertec.gestion_docente.domain.alumno.model.AlumnoModel;
import pe.cibertec.gestion_docente.domain.alumno.repository.AlumnoRepository;
import pe.cibertec.gestion_docente.domain.alumno.service.AlumnoService;
import pe.cibertec.gestion_docente.domain.shared.valueobject.PaginaResult;
import pe.cibertec.gestion_docente.domain.shared.valueobject.PaginacionRequest;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlumnoServiceImpl implements AlumnoService {
  private final AlumnoRepository repo;

  @Override public Optional<AlumnoModel> porCodigo(String codigoAlumno) {
    return repo.porCodigo(codigoAlumno);
  }

  @Override public PaginaResult<AlumnoModel> listar(PaginacionRequest pag) {
    return repo.listar(pag);
  }
}
