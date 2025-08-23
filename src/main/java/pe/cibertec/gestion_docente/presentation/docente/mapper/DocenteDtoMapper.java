package pe.cibertec.gestion_docente.presentation.docente.mapper;

import org.springframework.stereotype.Component;
import pe.cibertec.gestion_docente.domain.docente.model.DocenteModel;
import pe.cibertec.gestion_docente.presentation.docente.dto.DocenteResponseDto;

@Component
public class DocenteDtoMapper {

  public DocenteResponseDto toDto(DocenteModel m) {
    if (m == null) return null;

    return DocenteResponseDto.builder()
            .id(m.getIdDocente() == null ? null : m.getIdDocente().longValue())
            .codigo(m.getCodigoDocente())
            .nombres(m.getNombre())
            .apellidos(m.getApellido())
            .email(m.getEmail())
            .activo(m.isActivo())
            .build();
  }
}
