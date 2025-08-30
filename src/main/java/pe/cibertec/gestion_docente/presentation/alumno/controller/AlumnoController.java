// pe/cibertec/gestion_docente/presentation/alumno/controller/AlumnoController.java
package pe.cibertec.gestion_docente.presentation.alumno.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.cibertec.gestion_docente.domain.alumno.service.AlumnoService;
import pe.cibertec.gestion_docente.domain.shared.valueobject.PaginaResult;
import pe.cibertec.gestion_docente.domain.shared.valueobject.PaginacionRequest;
import pe.cibertec.gestion_docente.presentation.alumno.dto.AlumnoResponseDto;
import pe.cibertec.gestion_docente.presentation.alumno.mapper.AlumnoDtoMapper;

@RestController
@RequestMapping("/api/alumnos")
@RequiredArgsConstructor
public class AlumnoController {

  private final AlumnoService service;
  private final AlumnoDtoMapper mapper;

  @GetMapping("/{codigo}")
  @PreAuthorize("hasAnyRole('DOCENTE','ADMINISTRATIVO')")
  public ResponseEntity<AlumnoResponseDto> porCodigo(@PathVariable String codigo) {
    return service.porCodigo(codigo)
        .map(mapper::toDto)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping
  @PreAuthorize("hasAnyRole('DOCENTE','ADMINISTRATIVO')")
  public PaginaResult<AlumnoResponseDto> listar(
      @RequestParam(defaultValue = "0") int pagina,
      @RequestParam(defaultValue = "10") int tamanio,
      @RequestParam(defaultValue = "id,asc") String sort) {

    String[] s = sort.split(",");
    var req = PaginacionRequest.builder()
        .pagina(pagina).tamanio(tamanio)
        .ordenarPor(s[0]).direccion(s.length>1? s[1] : "asc") // Nota: si tu Lombok genera setDireccion con mayúscula, usa .direccion(...)
        .build();

    var pageModel = service.listar(req);
    return PaginaResult.of(
        pageModel.getContenido().stream().map(mapper::toDto).toList(),
        pageModel.getPaginaActual(), pageModel.getTamanio(), pageModel.getTotalElementos()
    );
  }
}
