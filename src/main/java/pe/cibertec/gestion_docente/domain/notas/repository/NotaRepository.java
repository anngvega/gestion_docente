package pe.cibertec.gestion_docente.domain.notas.repository;

import pe.cibertec.gestion_docente.domain.notas.model.EstructuraNotaModel;
import pe.cibertec.gestion_docente.domain.notas.model.NotaModel;
import pe.cibertec.gestion_docente.domain.shared.valueobject.PaginaResult;
import pe.cibertec.gestion_docente.domain.shared.valueobject.PaginacionRequest;

import java.util.Optional;

public interface NotaRepository {
    // Estructura
    EstructuraNotaModel crearEstructura(EstructuraNotaModel model);
    PaginaResult<EstructuraNotaModel> listarEstructuras(Long idCurso, PaginacionRequest pag);
    Optional<EstructuraNotaModel> obtenerEstructura(Long idEstructura);
    EstructuraNotaModel actualizarEstructura(EstructuraNotaModel model);
    void eliminarEstructura(Long idEstructura);

    // Notas
    NotaModel saveNota(NotaModel model);
    boolean existsNotaUnica(String codigoAlumno, Long idCurso, Long idEstructura);
    PaginaResult<NotaModel> listarNotas(Long idCurso, Integer idDocente, String codigoAlumno, PaginacionRequest pag);
}
