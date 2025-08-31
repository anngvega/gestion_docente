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

    private final DocenteRepositoryJpa docenteRepositoryJpa;
    private final DocenteMapper docenteMapper;

    @Override
    public PaginaResult<DocenteModel> listar(PaginacionRequest pag) {
        Pageable pageable = PageRequest.of(
                pag.getPagina(),
                pag.getTamanio(),
                pag.getDireccion().equalsIgnoreCase("desc") ?
                        Sort.by(pag.getOrdenarPor()).descending() :
                        Sort.by(pag.getOrdenarPor()).ascending()
        );

        Page<DocenteEntity> page = docenteRepositoryJpa.findAll(pageable);

        List<DocenteModel> docentes = page.getContent().stream()
                .map(docenteMapper::toModel)
                .toList();

        return PaginaResult.of(docentes, page.getNumber(), page.getSize(), page.getTotalElements());
    }

    @Override
    public boolean existeActivo(Integer idDocente) {
        return docenteRepositoryJpa.existsActivoById(idDocente);
    }

    @Override
    public Optional<DocenteModel> porId(Integer idDocente) {
        return docenteRepositoryJpa.findById(idDocente).map(docenteMapper::toModel);
    }


    private Pageable toPageable(PaginacionRequest p) {
        String sortProp = (p.getOrdenarPor() == null || p.getOrdenarPor().isBlank())
                ? "id" : p.getOrdenarPor();  // asegúrate que esta propiedad existe en DocenteEntity

        Sort.Direction dir = p.isAscendente() ? Sort.Direction.ASC : Sort.Direction.DESC;
        return PageRequest.of(p.getPagina(), p.getTamanio(), Sort.by(dir, sortProp));
    }
}
