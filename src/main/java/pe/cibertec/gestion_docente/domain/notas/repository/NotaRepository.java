package pe.cibertec.gestion_docente.domain.notas.repository;

import pe.cibertec.gestion_docente.domain.notas.model.EstructuraNotaModel;
import pe.cibertec.gestion_docente.domain.notas.model.NotaModel;
import pe.cibertec.gestion_docente.domain.shared.valueobject.PaginaResult;
import pe.cibertec.gestion_docente.domain.shared.valueobject.PaginacionRequest;

public interface NotaRepository {
    EstructuraNotaModel crearEstructura(EstructuraNotaModel model);
    NotaModel saveNota(NotaModel model);
    boolean existsNotaUnica(String codigoAlumno, Long idCurso, Long idEstructura);
    PaginaResult<NotaModel> listarNotas(Long idCurso, Integer idDocente, String codigoAlumno, PaginacionRequest pag);
}
