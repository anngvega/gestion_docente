package pe.cibertec.gestion_docente.infrastructure.persistence.reserva.entity;

import jakarta.persistence.*;
import lombok.*;
import pe.cibertec.gestion_docente.infrastructure.persistence.shared.Auditoria;

import java.time.LocalDateTime;

@Entity
@Table(name = "reserva_aula",
       indexes = {
         @Index(name="ix_reserva_aula_idaula", columnList="id_aula"),
         @Index(name="ix_reserva_aula_docente", columnList="id_docente")
       })
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ReservaAulaEntity extends Auditoria<String> {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_reserva") private Long id;

  @Column(name = "id_aula", nullable = false) private Long idAula;
  @Column(name = "id_curso") private Long idCurso;
  @Column(name = "id_docente", nullable = false) private Integer idDocente;

  @Column(name = "fecha_inicio", nullable = false) private LocalDateTime inicio;
  @Column(name = "fecha_fin", nullable = false) private LocalDateTime fin;

  @Column(length = 15, nullable = false) private String estado; // RESERVADA / CANCELADA
}
