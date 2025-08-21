package pe.cibertec.gestion_docente.presentation.notas.dto;

import java.time.LocalDateTime;

public record NotaResponseDto(
        Long idNota,
        String codigoAlumno,
        Long idCurso,
        Long idEstructura,
        Integer idDocente,
        Double calificacion,
        LocalDateTime fechaRegistro
) {}
