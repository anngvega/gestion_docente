package pe.cibertec.gestion_docente.infrastructure.persistence.notas.entity;

import pe.cibertec.gestion_docente.infrastructure.persistence.shared.Auditoria;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "notas",
       uniqueConstraints = @UniqueConstraint(name = "uk_nota_alumno_curso_estructura",
                                             columnNames = {"codigo_alumno","id_curso","id_estructura"}),
       indexes = {
           @Index(name = "ix_notas_curso", columnList = "id_curso"),
           @Index(name = "ix_notas_docente", columnList = "id_docente")
       })
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class NotaEntity extends Auditoria<String> {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nota") private Long id;
    @Column(name = "codigo_alumno", length = 9, nullable = false) private String codigoAlumno;
    @Column(name = "id_curso", nullable = false) private Long idCurso;
    @Column(name = "id_estructura", nullable = false) private Long idEstructura;
    @Column(name = "id_docente", nullable = false) private Integer idDocente;
    @Column(name = "calificacion", precision = 5, scale = 2, nullable = false) private BigDecimal calificacion;
    @Column(name = "fecha_registro") private LocalDateTime fechaRegistro;
}
