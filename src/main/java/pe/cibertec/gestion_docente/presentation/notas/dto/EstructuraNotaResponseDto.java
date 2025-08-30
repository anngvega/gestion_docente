package pe.cibertec.gestion_docente.presentation.notas.dto;

public record EstructuraNotaResponseDto(
  Long idEstructura,
  Long idCurso,
  String descripcion,
  Double peso
) {}
