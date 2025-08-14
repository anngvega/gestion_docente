package pe.cibertec.gestion_docente.infrastructure.seguridad.entity;

import jakarta.persistence.*;
import lombok.Getter; import lombok.Setter;

@Entity @Table(name="usuario") @Getter @Setter
public class UsuarioEntity {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Integer idUsuario;

    @Column(nullable=false, unique=true, length=50) private String usuario;
    @Column(name="contrasena", nullable=false, length=255) private String passwordHash;

    @ManyToOne @JoinColumn(name="id_rol", nullable=false)
    private RolEntity rol;

    @Column(name="id_docente") private Integer idDocente; // null si es administrativo

    public String getUsername(){ return usuario; }
    public Boolean getActivo(){ return true; }
}
