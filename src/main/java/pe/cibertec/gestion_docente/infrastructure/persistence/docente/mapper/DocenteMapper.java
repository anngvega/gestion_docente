package pe.cibertec.gestion_docente.infrastructure.persistence.docente.mapper;

import org.springframework.stereotype.Component;
import pe.cibertec.gestion_docente.domain.docente.model.DocenteModel;
import pe.cibertec.gestion_docente.infrastructure.persistence.docente.entity.DocenteEntity;

@Component
public class DocenteMapper {
    public DocenteModel toModel(DocenteEntity e) {
        if (e == null) return null;
        return DocenteModel.builder()
                .idDocente(e.getId())
                .codigoDocente(e.getCodigoDocente())
                .nombre(e.getNombre())
                .apellido(e.getApellido())
                .email(e.getEmail())
                .telefono(e.getTelefono())
                .activo("1".equals(e.getEstado()))
                .build();
    }
}
