package pe.cibertec.gestion_docente.infrastructure.persistence.alumno.mapper;

import org.springframework.stereotype.Component;
import pe.cibertec.gestion_docente.domain.alumno.model.AlumnoModel;
import pe.cibertec.gestion_docente.infrastructure.persistence.alumno.entity.AlumnoEntity;

@Component
public class AlumnoMapper {
  public AlumnoModel map(AlumnoEntity e) {
    if (e == null) return null;
    return AlumnoModel.builder()
        .idAlumno(e.getId())
        .codigoAlumno(e.getCodigoAlumno())
        .nombre(e.getNombre())
        .apellido(e.getApellido())
        .email(e.getEmail())
        .activo("1".equals(e.getEstado()))
        .build();
  }
}
