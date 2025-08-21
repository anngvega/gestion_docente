package pe.cibertec.gestion_docente.application.notas.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.cibertec.gestion_docente.domain.notas.model.NotaModel;
import pe.cibertec.gestion_docente.domain.notas.repository.NotaRepository;
import pe.cibertec.gestion_docente.domain.shared.valueobject.PaginaResult;
import pe.cibertec.gestion_docente.domain.shared.valueobject.PaginacionRequest;

@Component
@RequiredArgsConstructor
public class ListarNotasUseCase {
    private final NotaRepository notaRepository;

    public PaginaResult<NotaModel> ejecutar(Long idCurso, Integer idDocente, String codigoAlumno, PaginacionRequest pag) {
        return notaRepository.listarNotas(idCurso, idDocente, codigoAlumno, pag);
    }
}
