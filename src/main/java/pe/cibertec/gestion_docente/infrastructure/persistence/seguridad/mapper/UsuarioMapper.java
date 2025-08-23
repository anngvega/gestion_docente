package pe.cibertec.gestion_docente.infrastructure.persistence.seguridad.mapper;

import org.springframework.stereotype.Component;
import pe.cibertec.gestion_docente.domain.seguridad.model.RolModel;
import pe.cibertec.gestion_docente.domain.seguridad.model.UsuarioModel;
import pe.cibertec.gestion_docente.infrastructure.persistence.seguridad.entity.UsuarioEntity;
import pe.cibertec.gestion_docente.infrastructure.persistence.seguridad.entity.UsuarioRolEntity;

import java.util.Set;
import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class UsuarioMapper {

    public UsuarioModel toModel(UsuarioEntity e) {
        if (e == null) return null;

        Integer idDocente = (e.getDocente() == null) ? null : e.getDocente().getId();

        // Vacío tipado para evitar el error de 'bad type in conditional expression'
        Set<RolModel> roles = (e.getRoles() == null)
                ? Collections.<RolModel>emptySet()
                : e.getRoles().stream()
                .filter(ur -> ur != null
                        && Boolean.TRUE.equals(ur.getActivo())
                        && ur.getRol() != null
                        && Boolean.TRUE.equals(ur.getRol().getActivo()))
                .map(UsuarioRolEntity::getRol)
                .map(r -> RolModel.builder()
                        .nombre(r.getNombre())
                        .descripcion(r.getDescripcion())
                        .build())
                .collect(Collectors.toSet());

        return UsuarioModel.builder()
                .id(e.getId())
                .username(e.getUsername())
                .password(e.getPasswordHash()) // pasa el hash BCRYPT tal cual
                .nombre(e.getNombre())
                .apellido(e.getApellido())
                .activo(Boolean.TRUE.equals(e.getActivo()))
                .idDocente(idDocente)
                .roles(roles)
                .build();
    }
}