package pe.cibertec.gestion_docente.presentation.reserva.dto;

import java.time.LocalDateTime;

public record ReservaAulaResponseDto(
  Long idReserva,
  Long idAula,
  Long idCurso,
  Integer idDocente,
  LocalDateTime inicio,
  LocalDateTime fin,
  String estado
) {}
