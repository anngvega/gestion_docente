package pe.cibertec.gestion_docente.infrastructure.persistence.alumno.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;
import pe.cibertec.gestion_docente.domain.alumno.model.AlumnoModel;
import pe.cibertec.gestion_docente.domain.alumno.repository.AlumnoRepository;
import pe.cibertec.gestion_docente.domain.shared.valueobject.PaginaResult;
import pe.cibertec.gestion_docente.domain.shared.valueobject.PaginacionRequest;
import pe.cibertec.gestion_docente.infrastructure.persistence.alumno.entity.AlumnoEntity;
import pe.cibertec.gestion_docente.infrastructure.persistence.alumno.jpa.AlumnoRepositoryJpa;
import pe.cibertec.gestion_docente.infrastructure.persistence.alumno.mapper.AlumnoMapper;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AlumnoRepositoryImpl implements AlumnoRepository {

  private final AlumnoRepositoryJpa jpa;
  private final AlumnoMapper mapper;

  @Override public Optional<AlumnoModel> porCodigo(String codigoAlumno) {
    return jpa.findByCodigoAlumno(codigoAlumno).map(mapper::map);
  }

  @Override public PaginaResult<AlumnoModel> listar(PaginacionRequest p) {
    Sort.Direction dir = p.isAscendente() ? Sort.Direction.ASC : Sort.Direction.DESC;
    Pageable pageable = PageRequest.of(p.getPagina(), p.getTamanio(), Sort.by(dir, p.getOrdenarPor()));
    Page<AlumnoEntity> page = jpa.findAll(pageable);

    List<AlumnoModel> contenido = page.getContent().stream().map(mapper::map).toList();
    return PaginaResult.of(contenido, page.getNumber(), page.getSize(), page.getTotalElements());
  }
}
