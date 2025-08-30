package pe.cibertec.gestion_docente.infrastructure.persistence.alumno.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "alumno")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AlumnoEntity {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_alumno") private Integer id;

  @Column(name = "codigo_alumno", length = 9, nullable = false, unique = true)
  private String codigoAlumno;

  @Column(length = 100, nullable = false) private String nombre;
  @Column(length = 100, nullable = false) private String apellido;
  @Column(length = 100) private String email;

  @Column(length = 1, nullable = false) private String estado; // '1' activo, '0' inactivo
}
