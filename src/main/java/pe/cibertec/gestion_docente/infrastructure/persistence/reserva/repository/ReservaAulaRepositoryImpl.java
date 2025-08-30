package pe.cibertec.gestion_docente.infrastructure.persistence.reserva.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;
import pe.cibertec.gestion_docente.domain.reserva.model.ReservaAulaModel;
import pe.cibertec.gestion_docente.domain.reserva.repository.ReservaAulaRepository;
import pe.cibertec.gestion_docente.domain.shared.valueobject.PaginaResult;
import pe.cibertec.gestion_docente.domain.shared.valueobject.PaginacionRequest;
import pe.cibertec.gestion_docente.infrastructure.persistence.reserva.entity.ReservaAulaEntity;
import pe.cibertec.gestion_docente.infrastructure.persistence.reserva.jpa.ReservaAulaRepositoryJpa;
import pe.cibertec.gestion_docente.infrastructure.persistence.reserva.mapper.ReservaAulaMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository @RequiredArgsConstructor
public class ReservaAulaRepositoryImpl implements ReservaAulaRepository {

  private final ReservaAulaRepositoryJpa jpa;
  private final ReservaAulaMapper mapper;

  @Override public boolean existeSolapamiento(Long idAula, LocalDateTime inicio, LocalDateTime fin) {
    return jpa.existeSolapamiento(idAula, inicio, fin);
  }

  @Override public ReservaAulaModel crear(ReservaAulaModel m) {
    ReservaAulaEntity e = mapper.entity(m);
    return mapper.map(jpa.save(e));
  }

  @Override public PaginaResult<ReservaAulaModel> listar(Long idAula, Integer idDocente, LocalDateTime desde, LocalDateTime hasta, PaginacionRequest p) {
    Sort sort = p.isAscendente() ? Sort.by(p.getOrdenarPor()).ascending() : Sort.by(p.getOrdenarPor()).descending();
    Page<ReservaAulaEntity> page = jpa.buscar(idAula, idDocente, desde, hasta, PageRequest.of(p.getPagina(), p.getTamanio(), sort));
    List<ReservaAulaModel> contenido = page.getContent().stream().map(mapper::map).toList();
    return PaginaResult.of(contenido, page.getNumber(), page.getSize(), page.getTotalElements());
  }

  @Override public Optional<ReservaAulaModel> porId(Long id) {
    return jpa.findById(id).map(mapper::map);
  }

  @Override public void cancelar(Long id) {
    var e = jpa.findById(id).orElseThrow(() -> new IllegalArgumentException("Reserva no encontrada"));
    e.setEstado("CANCELADA");
    jpa.save(e);
  }
}
