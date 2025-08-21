package pe.cibertec.gestion_docente.application.notas;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.cibertec.gestion_docente.application.notas.usecase.CrearEstructuraNotaUseCase;
import pe.cibertec.gestion_docente.application.notas.usecase.ListarNotasUseCase;
import pe.cibertec.gestion_docente.application.notas.usecase.RegistrarNotaUseCase;
import pe.cibertec.gestion_docente.domain.notas.model.EstructuraNotaModel;
import pe.cibertec.gestion_docente.domain.notas.model.NotaModel;
import pe.cibertec.gestion_docente.domain.notas.service.NotaService;
import pe.cibertec.gestion_docente.domain.shared.valueobject.PaginaResult;
import pe.cibertec.gestion_docente.domain.shared.valueobject.PaginacionRequest;

@Service
@RequiredArgsConstructor
public class NotaServiceImpl implements NotaService {

    private final CrearEstructuraNotaUseCase crearEstructura;
    private final RegistrarNotaUseCase registrarNota;
    private final ListarNotasUseCase listarNotas;

    @Override
    public EstructuraNotaModel crearEstructura(EstructuraNotaModel model) {
        return crearEstructura.ejecutar(model);
    }

    @Override
    public NotaModel registrarNota(NotaModel model) {
        return registrarNota.ejecutar(model);
    }

    @Override
    public PaginaResult<NotaModel> listarNotas(Long idCurso, Integer idDocente, String codigoAlumno, PaginacionRequest pag) {
        return listarNotas.ejecutar(idCurso, idDocente, codigoAlumno, pag);
    }
}
