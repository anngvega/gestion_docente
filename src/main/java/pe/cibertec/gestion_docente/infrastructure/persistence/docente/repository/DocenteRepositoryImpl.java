package pe.cibertec.gestion_docente.infrastructure.persistence.docente.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;
import pe.cibertec.gestion_docente.domain.docente.model.DocenteModel;
import pe.cibertec.gestion_docente.domain.docente.repository.DocenteRepository;
import pe.cibertec.gestion_docente.domain.shared.valueobject.PaginaResult;
import pe.cibertec.gestion_docente.domain.shared.valueobject.PaginacionRequest;
import pe.cibertec.gestion_docente.infrastructure.persistence.docente.entity.DocenteEntity;
import pe.cibertec.gestion_docente.infrastructure.persistence.docente.jpa.DocenteRepositoryJpa;
import pe.cibertec.gestion_docente.infrastructure.persistence.docente.mapper.DocenteMapper;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DocenteRepositoryImpl implements DocenteRepository {

    private final DocenteRepositoryJpa docenteJpa;
    private final DocenteMapper mapper;

    @Override
    public boolean existeActivo(Integer idDocente) {
        return docenteJpa.existsActivoById(idDocente);
    }

    @Override
    public Optional<DocenteModel> porId(Integer idDocente) {
        return docenteJpa.findById(idDocente).map(mapper::map);
    }

    @Override
    public PaginaResult<DocenteModel> listar(PaginacionRequest req) {
        Pageable pageable = toPageable(req);
        Page<DocenteEntity> page = docenteJpa.findAll(pageable);

        List<DocenteModel> items = page.getContent()
                .stream()
                .map(mapper::map)        // <-- tu mapper tiene 'map', no 'toModel'
                .toList();

        return PaginaResult.of(items, page.getNumber(), page.getSize(), page.getTotalElements());
    }

    private Pageable toPageable(PaginacionRequest p) {
        String sortProp = (p.getOrdenarPor() == null || p.getOrdenarPor().isBlank())
                ? "id" : p.getOrdenarPor();  // asegúrate que esta propiedad existe en DocenteEntity

        Sort.Direction dir = p.isAscendente() ? Sort.Direction.ASC : Sort.Direction.DESC;
        return PageRequest.of(p.getPagina(), p.getTamanio(), Sort.by(dir, sortProp));
    }
}
