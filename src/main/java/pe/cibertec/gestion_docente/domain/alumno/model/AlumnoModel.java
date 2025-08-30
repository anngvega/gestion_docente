package pe.cibertec.gestion_docente.domain.alumno.model;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class AlumnoModel {
  private Integer idAlumno;
  private String codigoAlumno; // p.ej. A20231234 (9)
  private String nombre;
  private String apellido;
  private String email;
  private boolean activo;
}