package pe.cibertec.gestion_docente.domain.alumno.repository;

import pe.cibertec.gestion_docente.domain.alumno.model.AlumnoModel;
import pe.cibertec.gestion_docente.domain.shared.valueobject.PaginaResult;
import pe.cibertec.gestion_docente.domain.shared.valueobject.PaginacionRequest;

import java.util.Optional;

public interface AlumnoRepository {
  Optional<AlumnoModel> porCodigo(String codigoAlumno);
  PaginaResult<AlumnoModel> listar(PaginacionRequest pag);
}
