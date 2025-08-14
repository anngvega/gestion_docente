package pe.cibertec.gestion_docente.infrastructure.seguridad.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity @Table(name="rol") @Getter
@Setter
public class RolEntity {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Integer idRol;
    @Column(nullable=false, unique=true, length=50) private String nombreRol;
}