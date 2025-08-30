package pe.cibertec.gestion_docente.domain.alumno.service;

import pe.cibertec.gestion_docente.domain.alumno.model.AlumnoModel;
import pe.cibertec.gestion_docente.domain.shared.valueobject.PaginaResult;
import pe.cibertec.gestion_docente.domain.shared.valueobject.PaginacionRequest;

import java.util.Optional;

public interface AlumnoService {
  Optional<AlumnoModel> porCodigo(String codigoAlumno);
  PaginaResult<AlumnoModel> listar(PaginacionRequest pag);
}
