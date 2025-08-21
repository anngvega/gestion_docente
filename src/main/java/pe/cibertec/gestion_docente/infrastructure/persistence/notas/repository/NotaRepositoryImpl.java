package pe.cibertec.gestion_docente.infrastructure.persistence.notas.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import pe.cibertec.gestion_docente.domain.notas.model.EstructuraNotaModel;
import pe.cibertec.gestion_docente.domain.notas.model.NotaModel;
import pe.cibertec.gestion_docente.domain.notas.repository.NotaRepository;
import pe.cibertec.gestion_docente.domain.shared.valueobject.PaginaResult;
import pe.cibertec.gestion_docente.domain.shared.valueobject.PaginacionRequest;
import pe.cibertec.gestion_docente.infrastructure.persistence.notas.entity.EstructuraNotaEntity;
import pe.cibertec.gestion_docente.infrastructure.persistence.notas.entity.NotaEntity;
import pe.cibertec.gestion_docente.infrastructure.persistence.notas.jpa.EstructuraNotaRepositoryJpa;
import pe.cibertec.gestion_docente.infrastructure.persistence.notas.jpa.NotaRepositoryJpa;
import pe.cibertec.gestion_docente.infrastructure.persistence.notas.mapper.NotaMapper;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class NotaRepositoryImpl implements NotaRepository {

    private final EstructuraNotaRepositoryJpa estructuraJpa;
    private final NotaRepositoryJpa notaJpa;
    private final NotaMapper mapper;

    @Override
    public EstructuraNotaModel crearEstructura(EstructuraNotaModel model) {
        // opcional: evita duplicados por (idCurso, descripcion)
        if (estructuraJpa.existsByIdCursoAndDescripcion(model.getIdCurso(), model.getDescripcion())) {
            throw new IllegalStateException("La estructura ya existe para ese curso y descripción");
        }
        EstructuraNotaEntity entity = mapper.entity(model);
        return mapper.map(estructuraJpa.save(entity));
    }

    @Override
    public NotaModel saveNota(NotaModel model) {
        NotaEntity entity = mapper.entity(model);
        return mapper.map(notaJpa.save(entity));
    }

    @Override
    public boolean existsNotaUnica(String codigoAlumno, Long idCurso, Long idEstructura) {
        return notaJpa.existsByCodigoAlumnoAndIdCursoAndIdEstructura(codigoAlumno, idCurso, idEstructura);
    }

    @Override
    public PaginaResult<NotaModel> listarNotas(Long idCurso, Integer idDocente, String codigoAlumno, PaginacionRequest p) {
        Sort sort = p.isAscendente()
                ? Sort.by(p.getOrdenarPor()).ascending()
                : Sort.by(p.getOrdenarPor()).descending();

        Page<NotaEntity> page = notaJpa.buscar(idCurso, idDocente, codigoAlumno,
                PageRequest.of(p.getPagina(), p.getTamanio(), sort));

        List<NotaModel> contenido = page.getContent()
                .stream()
                .map(mapper::map)
                .toList();

        return PaginaResult.of(contenido, page.getNumber(), page.getSize(), page.getTotalElements());
    }
}
