package pe.cibertec.gestion_docente.presentation.docente.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.cibertec.gestion_docente.domain.docente.model.DocenteModel;
import pe.cibertec.gestion_docente.domain.docente.repository.DocenteRepository;

@RestController
@RequestMapping("/api/docentes")
@RequiredArgsConstructor
public class DocenteController {

    private final DocenteRepository repo;

    @GetMapping("/{id}")
    public ResponseEntity<DocenteModel> porId(@PathVariable Integer id) {
        return repo.porId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
