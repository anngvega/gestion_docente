package pe.cibertec.gestion_docente.presentation.reserva.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.cibertec.gestion_docente.domain.reserva.model.ReservaAulaModel;
import pe.cibertec.gestion_docente.domain.reserva.service.ReservaAulaService;
import pe.cibertec.gestion_docente.domain.shared.valueobject.PaginaResult;
import pe.cibertec.gestion_docente.domain.shared.valueobject.PaginacionRequest;
import pe.cibertec.gestion_docente.presentation.reserva.dto.ReservaAulaRequestDto;
import pe.cibertec.gestion_docente.presentation.reserva.dto.ReservaAulaResponseDto;
import pe.cibertec.gestion_docente.presentation.reserva.mapper.ReservaAulaDtoMapper;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/reservas")
@RequiredArgsConstructor
public class ReservaAulaController {

  private final ReservaAulaService service;
  private final ReservaAulaDtoMapper mapper;

  @PostMapping
  @PreAuthorize("hasRole('DOCENTE')")
  public ReservaAulaResponseDto crear(@Valid @RequestBody ReservaAulaRequestDto req) {
    ReservaAulaModel m = service.crear(mapper.toModel(req));
    return mapper.toDto(m);
  }

  @GetMapping
  @PreAuthorize("hasAnyRole('DOCENTE','ADMINISTRATIVO')")
  public PaginaResult<ReservaAulaResponseDto> listar(
      @RequestParam(required = false) Long idAula,
      @RequestParam(required = false) Integer idDocente,
      @RequestParam(required = false) LocalDateTime desde,
      @RequestParam(required = false) LocalDateTime hasta,
      @RequestParam(defaultValue = "0") int pagina,
      @RequestParam(defaultValue = "10") int tamanio,
      @RequestParam(defaultValue = "id,asc") String sort
  ) {
    String[] s = sort.split(",");
    var reqPag = PaginacionRequest.builder()
        .pagina(pagina).tamanio(tamanio)
        .ordenarPor(s[0]).direccion(s.length>1? s[1] : "asc")
        .build();

    var page = service.listar(idAula, idDocente, desde, hasta, reqPag);
    return PaginaResult.of(
        page.getContenido().stream().map(mapper::toDto).toList(),
        page.getPaginaActual(), page.getTamanio(), page.getTotalElementos()
    );
  }

  @PostMapping("/{id}/cancelar")
  @PreAuthorize("hasAnyRole('DOCENTE','ADMINISTRATIVO')")
  public ResponseEntity<Void> cancelar(@PathVariable Long id) {
    service.cancelar(id);
    return ResponseEntity.noContent().build();
  }
}
