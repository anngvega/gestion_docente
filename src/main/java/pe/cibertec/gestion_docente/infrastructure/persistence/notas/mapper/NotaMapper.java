package pe.cibertec.gestion_docente.infrastructure.persistence.notas.mapper;

import org.springframework.stereotype.Component;
import pe.cibertec.gestion_docente.domain.notas.model.EstructuraNotaModel;
import pe.cibertec.gestion_docente.domain.notas.model.NotaModel;
import pe.cibertec.gestion_docente.infrastructure.persistence.notas.entity.EstructuraNotaEntity;
import pe.cibertec.gestion_docente.infrastructure.persistence.notas.entity.NotaEntity;

import java.math.BigDecimal;

@Component
public class NotaMapper {

    // ---- Estructura ----
    public EstructuraNotaModel map(EstructuraNotaEntity entity) {
        if (entity == null) return null;
        return EstructuraNotaModel.builder()
                .idEstructura(entity.getId())
                .idCurso(entity.getIdCurso())
                .descripcion(entity.getDescripcion())
                .peso(entity.getPeso() != null ? entity.getPeso().doubleValue() : null)
                .build();
    }

    public EstructuraNotaEntity entity(EstructuraNotaModel model) {
        if (model == null) return null;
        EstructuraNotaEntity e = new EstructuraNotaEntity();
        e.setId(model.getIdEstructura());
        e.setIdCurso(model.getIdCurso());
        e.setDescripcion(model.getDescripcion());
        e.setPeso(model.getPeso() != null ? BigDecimal.valueOf(model.getPeso()) : null);
        return e;
    }

    // ---- Nota ----
    public NotaModel map(NotaEntity entity) {
        if (entity == null) return null;
        return NotaModel.builder()
                .idNota(entity.getId())
                .codigoAlumno(entity.getCodigoAlumno())
                .idCurso(entity.getIdCurso())
                .idEstructura(entity.getIdEstructura())
                .idDocente(entity.getIdDocente())
                .calificacion(entity.getCalificacion() != null ? entity.getCalificacion().doubleValue() : null)
                .fechaRegistro(entity.getFechaRegistro())
                .build();
    }

    public NotaEntity entity(NotaModel model) {
        if (model == null) return null;
        NotaEntity e = new NotaEntity();
        e.setId(model.getIdNota());
        e.setCodigoAlumno(model.getCodigoAlumno());
        e.setIdCurso(model.getIdCurso());
        e.setIdEstructura(model.getIdEstructura());
        e.setIdDocente(model.getIdDocente());
        e.setCalificacion(model.getCalificacion() != null ? BigDecimal.valueOf(model.getCalificacion()) : null);
        e.setFechaRegistro(model.getFechaRegistro());
        return e;
    }
}
