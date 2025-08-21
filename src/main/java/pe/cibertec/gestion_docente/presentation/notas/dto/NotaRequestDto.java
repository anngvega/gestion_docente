package pe.cibertec.gestion_docente.presentation.notas.dto;

import jakarta.validation.constraints.*;

public record NotaRequestDto(
        @NotBlank String codigoAlumno,
        @NotNull Long idCurso,
        @NotNull Long idEstructura,
        @NotNull @DecimalMin("0.0") @DecimalMax("20.0") Double calificacion
) {}
