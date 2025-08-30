package pe.cibertec.gestion_docente.presentation.reserva.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record ReservaAulaRequestDto(
  @NotNull Long idAula,
  Long idCurso,
  @NotNull LocalDateTime inicio,
  @NotNull LocalDateTime fin
) {}
