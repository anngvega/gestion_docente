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

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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
    @Override
    public PaginaResult<EstructuraNotaModel> listarEstructuras(Long idCurso, PaginacionRequest p) {
        var sort = p.isAscendente() ? Sort.by(p.getOrdenarPor()).ascending() : Sort.by(p.getOrdenarPor()).descending();
        var pageable = PageRequest.of(p.getPagina(), p.getTamanio(), sort);

        Page<EstructuraNotaEntity> page = (idCurso == null)
                ? estructuraJpa.findAll(pageable)
                : estructuraJpa.findByIdCurso(idCurso, pageable);

        var contenido = page.getContent().stream().map(mapper::map).toList();
        return PaginaResult.of(contenido, page.getNumber(), page.getSize(), page.getTotalElements());
    }

    @Override
    public Optional<EstructuraNotaModel> obtenerEstructura(Long idEstructura) {
        return estructuraJpa.findById(idEstructura).map(mapper::map);
    }

    @Override
    public EstructuraNotaModel actualizarEstructura(EstructuraNotaModel model) {
        var existente = estructuraJpa.findById(model.getIdEstructura())
                .orElseThrow(() -> new IllegalArgumentException("Estructura no encontrada"));
        existente.setIdCurso(model.getIdCurso());
        existente.setDescripcion(model.getDescripcion());
        existente.setPeso(model.getPeso() == null ? null : BigDecimal.valueOf(model.getPeso()));
        return mapper.map(estructuraJpa.save(existente));
    }

    @Override
    public void eliminarEstructura(Long idEstructura) {
        estructuraJpa.deleteById(idEstructura);
    }
}
