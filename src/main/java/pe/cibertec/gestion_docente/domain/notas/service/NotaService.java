package pe.cibertec.gestion_docente.domain.notas.service;

import pe.cibertec.gestion_docente.domain.notas.model.EstructuraNotaModel;
import pe.cibertec.gestion_docente.domain.notas.model.NotaModel;
import pe.cibertec.gestion_docente.domain.shared.valueobject.PaginaResult;
import pe.cibertec.gestion_docente.domain.shared.valueobject.PaginacionRequest;

public interface NotaService {
    EstructuraNotaModel crearEstructura(EstructuraNotaModel model);
    NotaModel registrarNota(NotaModel model);
    PaginaResult<NotaModel> listarNotas(Long idCurso, Integer idDocente, String codigoAlumno, PaginacionRequest pag);
}
