package pe.cibertec.gestion_docente.presentation.notas.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.cibertec.gestion_docente.application.notas.usecase.CrearEstructuraNotaUseCase;
import pe.cibertec.gestion_docente.application.notas.usecase.ListarEstructurasUseCase;
import pe.cibertec.gestion_docente.application.notas.usecase.ActualizarEstructuraNotaUseCase;
import pe.cibertec.gestion_docente.application.notas.usecase.EliminarEstructuraNotaUseCase;
import pe.cibertec.gestion_docente.domain.notas.model.EstructuraNotaModel;
import pe.cibertec.gestion_docente.domain.shared.valueobject.PaginaResult;
import pe.cibertec.gestion_docente.domain.shared.valueobject.PaginacionRequest;
import pe.cibertec.gestion_docente.presentation.notas.dto.EstructuraNotaRequestDto;
import pe.cibertec.gestion_docente.presentation.notas.dto.EstructuraNotaResponseDto;
import pe.cibertec.gestion_docente.presentation.notas.mapper.EstructuraNotaDtoMapper;

@RestController
@RequestMapping("/api/estructuras")
@RequiredArgsConstructor
public class EstructuraNotaController {

  private final CrearEstructuraNotaUseCase crear;
  private final ListarEstructurasUseCase listar;
  private final ActualizarEstructuraNotaUseCase actualizar;
  private final EliminarEstructuraNotaUseCase eliminar;
  private final EstructuraNotaDtoMapper mapper;

  @GetMapping
  @PreAuthorize("hasAnyRole('DOCENTE','ADMINISTRATIVO')")
  public PaginaResult<EstructuraNotaResponseDto> listar(
      @RequestParam(required = false) Long idCurso,
      @RequestParam(defaultValue = "0") int pagina,
      @RequestParam(defaultValue = "10") int tamanio,
      @RequestParam(defaultValue = "id,asc") String sort
  ) {
    String[] s = sort.split(",");
    var req = PaginacionRequest.builder()
        .pagina(pagina).tamanio(tamanio)
        .ordenarPor(s[0]).direccion(s.length>1? s[1] : "asc")
        .build();
    var page = listar.ejecutar(idCurso, req);
    return PaginaResult.of(
        page.getContenido().stream().map(mapper::toDto).toList(),
        page.getPaginaActual(), page.getTamanio(), page.getTotalElementos()
    );
  }

  @PostMapping
  @PreAuthorize("hasRole('ADMINISTRATIVO')")
  public EstructuraNotaResponseDto crear(@Valid @RequestBody EstructuraNotaRequestDto req) {
    EstructuraNotaModel m = crear.ejecutar(mapper.toModel(req));
    return mapper.toDto(m);
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasRole('ADMINISTRATIVO')")
  public EstructuraNotaResponseDto actualizar(@PathVariable Long id, @Valid @RequestBody EstructuraNotaRequestDto req) {
    EstructuraNotaModel m = actualizar.ejecutar(mapper.toModel(id, req));
    return mapper.toDto(m);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('ADMINISTRATIVO')")
  public ResponseEntity<Void> eliminar(@PathVariable Long id) {
    eliminar.ejecutar(id);
    return ResponseEntity.noContent().build();
  }
}
