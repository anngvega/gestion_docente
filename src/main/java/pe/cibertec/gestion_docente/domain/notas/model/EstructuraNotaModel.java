package pe.cibertec.gestion_docente.domain.notas.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EstructuraNotaModel {
    private Long idEstructura;
    private Long idCurso;
    private String descripcion;
    private Double peso;
}
