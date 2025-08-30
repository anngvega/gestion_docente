package pe.cibertec.gestion_docente.infrastructure.persistence.reserva.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "aula")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AulaEntity {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_aula") private Long id;

  @Column(length = 20, nullable = false, unique = true) private String codigo; // p.ej. A-301
  @Column(length = 100, nullable = false) private String nombre;
  private Integer capacidad;
  @Column(length = 1, nullable = false) private String estado; // '1' activo
}
