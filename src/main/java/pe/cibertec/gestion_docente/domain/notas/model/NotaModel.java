package pe.cibertec.gestion_docente.domain.notas.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class NotaModel {
    private Long idNota;
    private String codigoAlumno;
    private Long idCurso;
    private Long idEstructura;
    private Integer idDocente;
    private Double calificacion;
    private LocalDateTime fechaRegistro;
}
