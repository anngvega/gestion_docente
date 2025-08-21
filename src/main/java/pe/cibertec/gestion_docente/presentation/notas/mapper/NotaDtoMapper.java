package pe.cibertec.gestion_docente.presentation.notas.mapper;

import org.springframework.stereotype.Component;
import pe.cibertec.gestion_docente.domain.notas.model.NotaModel;
import pe.cibertec.gestion_docente.presentation.notas.dto.NotaRequestDto;

@Component
public class NotaDtoMapper {
    public NotaModel map(NotaRequestDto r) {
        return NotaModel.builder()
                .codigoAlumno(r.codigoAlumno())
                .idCurso(r.idCurso())
                .idEstructura(r.idEstructura())
                .calificacion(r.calificacion())
                .build();
    }
}
