package pe.cibertec.gestion_docente.infrastructure.persistence.docente.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pe.cibertec.gestion_docente.domain.docente.model.DocenteModel;
import pe.cibertec.gestion_docente.domain.docente.repository.DocenteRepository;
import pe.cibertec.gestion_docente.infrastructure.persistence.docente.jpa.DocenteRepositoryJpa;
import pe.cibertec.gestion_docente.infrastructure.persistence.docente.mapper.DocenteMapper;

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
}
