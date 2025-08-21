package pe.cibertec.gestion_docente.presentation.notas.dto;

import jakarta.validation.constraints.*;

public record EstructuraNotaRequestDto(
        @NotNull Long idCurso,
        @NotBlank @Size(max = 70) String descripcion,
        @NotNull @DecimalMin("0.0") @DecimalMax("100.0") Double peso
) {}
