package pe.cibertec.gestion_docente.domain.docente.repository;

import pe.cibertec.gestion_docente.domain.docente.model.DocenteModel;
import pe.cibertec.gestion_docente.domain.shared.valueobject.PaginaResult;
import pe.cibertec.gestion_docente.domain.shared.valueobject.PaginacionRequest;

import java.util.Optional;

public interface DocenteRepository {
    boolean existeActivo(Integer idDocente);
    Optional<DocenteModel> porId(Integer idDocente);
    PaginaResult<DocenteModel> listar(PaginacionRequest pag);
}
