package pe.cibertec.gestion_docente.domain.docente.service;

import pe.cibertec.gestion_docente.domain.docente.model.DocenteModel;
import pe.cibertec.gestion_docente.domain.shared.valueobject.PaginaResult;
import pe.cibertec.gestion_docente.domain.shared.valueobject.PaginacionRequest;

public interface DocenteService {
  PaginaResult<DocenteModel> listar(PaginacionRequest paginacion);
}