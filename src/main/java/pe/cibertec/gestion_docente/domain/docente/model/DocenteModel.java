package pe.cibertec.gestion_docente.domain.docente.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DocenteModel {
    private Integer idDocente;
    private String codigoDocente;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private boolean activo;
}
