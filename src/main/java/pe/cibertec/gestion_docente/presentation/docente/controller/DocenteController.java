package pe.cibertec.gestion_docente.presentation.docente.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.cibertec.gestion_docente.domain.docente.repository.DocenteRepository;
import pe.cibertec.gestion_docente.domain.docente.service.DocenteService;
import pe.cibertec.gestion_docente.domain.shared.valueobject.PaginaResult;
import pe.cibertec.gestion_docente.domain.shared.valueobject.PaginacionRequest;
import pe.cibertec.gestion_docente.presentation.docente.dto.DocenteResponseDto;
import pe.cibertec.gestion_docente.presentation.docente.mapper.DocenteDtoMapper;

@RestController
@RequestMapping("/api/docentes")
@RequiredArgsConstructor
public class DocenteController {

    private final DocenteRepository repo;          // para /{id} si aún no tienes método en el service
    private final DocenteService docenteService;   // application/docente/DocenteServiceImpl
    private final DocenteDtoMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<DocenteResponseDto> porId(@PathVariable Integer id) {
        return repo.porId(id)
                .map(mapper::toDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMINISTRATIVO')") // o hasAnyRole('ADMIN','COORDINADOR')
    public PaginaResult<DocenteResponseDto> listar(
            @RequestParam(defaultValue = "0")  int pagina,
            @RequestParam(defaultValue = "10") int tamanio,
            @RequestParam(defaultValue = "id,asc") String sort) {

        // sort viene como "campo,direccion"
        String[] s = sort.split(",");
        String ordenarPor = s[0];
        String direccion  = (s.length > 1 ? s[1] : "asc");

        var req = PaginacionRequest.builder()
                .pagina(pagina)
                .tamanio(tamanio)
                .ordenarPor(ordenarPor)
                .direccion(direccion) // "asc" / "desc"
                .build();

        var pageModel = docenteService.listar(req);

        // Mapeo a DTO conservando la paginación de tu VO
        return PaginaResult.of(
                pageModel.getContenido().stream().map(mapper::toDto).toList(),
                pageModel.getPaginaActual(),
                pageModel.getTamanio(),
                pageModel.getTotalElementos()
        );
    }
}
