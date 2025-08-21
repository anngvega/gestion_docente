package pe.cibertec.gestion_docente.infrastructure.persistence.docente.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "docente")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class DocenteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_docente")
    private Integer id;

    @Column(name = "codigo_docente", length = 6, nullable = false)
    private String codigoDocente;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(length = 100, nullable = false)
    private String apellido;

    @Column(length = 100)
    private String email;

    @Column(length = 9)
    private String telefono;

    @Column(length = 1, nullable = false)
    private String estado;
}
