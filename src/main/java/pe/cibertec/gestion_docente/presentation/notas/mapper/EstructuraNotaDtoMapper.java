package pe.cibertec.gestion_docente.presentation.notas.mapper;

import org.springframework.stereotype.Component;
import pe.cibertec.gestion_docente.domain.notas.model.EstructuraNotaModel;
import pe.cibertec.gestion_docente.presentation.notas.dto.EstructuraNotaRequestDto;
import pe.cibertec.gestion_docente.presentation.notas.dto.EstructuraNotaResponseDto;

@Component
public class EstructuraNotaDtoMapper {
  public EstructuraNotaModel toModel(EstructuraNotaRequestDto r) {
    return EstructuraNotaModel.builder()
        .idCurso(r.idCurso())
        .descripcion(r.descripcion())
        .peso(r.peso())
        .build();
  }
  public EstructuraNotaModel toModel(Long id, EstructuraNotaRequestDto r) {
    return EstructuraNotaModel.builder()
        .idEstructura(id)
        .idCurso(r.idCurso())
        .descripcion(r.descripcion())
        .peso(r.peso())
        .build();
  }
  public EstructuraNotaResponseDto toDto(EstructuraNotaModel m) {
    return new EstructuraNotaResponseDto(m.getIdEstructura(), m.getIdCurso(), m.getDescripcion(), m.getPeso());
  }
}
