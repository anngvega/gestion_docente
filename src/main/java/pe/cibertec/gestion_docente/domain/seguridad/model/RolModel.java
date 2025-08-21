package pe.cibertec.gestion_docente.domain.seguridad.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RolModel {
    private String nombre;
    private String descripcion;
}
