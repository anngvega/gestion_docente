package pe.cibertec.gestion_docente.presentation.notas.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.cibertec.gestion_docente.application.notas.usecase.RegistrarNotaUseCase;
import pe.cibertec.gestion_docente.domain.notas.model.NotaModel;
import pe.cibertec.gestion_docente.presentation.notas.dto.NotaRequestDto;
import pe.cibertec.gestion_docente.presentation.notas.mapper.NotaDtoMapper;

@RestController
@RequestMapping("/api/notas")
@RequiredArgsConstructor
public class NotaController {

    private final RegistrarNotaUseCase registrarNotaUseCase;
    private final NotaDtoMapper mapper;

    @PostMapping
    // @PreAuthorize("hasRole('DOCENTE')") // Temporalmente deshabilitado para testing
    public ResponseEntity<NotaModel> registrar(@Valid @RequestBody NotaRequestDto req) {
        NotaModel creada = registrarNotaUseCase.ejecutar(mapper.map(req));
        return ResponseEntity.ok(creada);
    }
}
