package pe.cibertec.gestion_docente.infrastructure.persistence.notas.entity;

import pe.cibertec.gestion_docente.infrastructure.persistence.shared.Auditoria;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "estructuraNota",
       uniqueConstraints = @UniqueConstraint(name="uk_estructura_curso_desc", columnNames = {"id_curso","descripcion"}))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class EstructuraNotaEntity extends Auditoria<String> {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estructura") private Long id;
    @Column(name = "id_curso", nullable = false) private Long idCurso;
    @Column(name = "descripcion", length = 70, nullable = false) private String descripcion;
    @Column(name = "peso", precision = 5, scale = 2, nullable = false) private BigDecimal peso;
}
