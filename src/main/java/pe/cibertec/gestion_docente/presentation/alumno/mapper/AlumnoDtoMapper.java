package pe.cibertec.gestion_docente.presentation.alumno.mapper;

import org.springframework.stereotype.Component;
import pe.cibertec.gestion_docente.domain.alumno.model.AlumnoModel;
import pe.cibertec.gestion_docente.presentation.alumno.dto.AlumnoResponseDto;

@Component
public class AlumnoDtoMapper {
  public AlumnoResponseDto toDto(AlumnoModel m) {
    if (m == null) return null;
    return AlumnoResponseDto.builder()
        .id(m.getIdAlumno() == null ? null : m.getIdAlumno().longValue())
        .codigo(m.getCodigoAlumno())
        .nombres(m.getNombre())
        .apellidos(m.getApellido())
        .email(m.getEmail())
        .activo(m.isActivo())
        .build();
  }
}
