package pe.cibertec.gestion_docente.presentation.docente.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pe.cibertec.gestion_docente.infrastructure.persistence.docente.entity.DocenteEntity;
import pe.cibertec.gestion_docente.infrastructure.persistence.docente.jpa.DocenteRepositoryJpa;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/public/debug")
@RequiredArgsConstructor
public class DocenteDebugController {
    
    private final DocenteRepositoryJpa docenteRepo;
    
    @GetMapping("/docentes/count")
    public Map<String, Object> contarDocentes() {
        long count = docenteRepo.count();
        List<DocenteEntity> todos = docenteRepo.findAll();
        return Map.of(
            "total", count,
            "docentes", todos
        );
    }
    
    @GetMapping("/docentes/lista-publica")
    public Map<String, Object> listaPublica() {
        List<DocenteEntity> todos = docenteRepo.findAll();
        return Map.of(
            "total", todos.size(),
            "docentes", todos.stream().map(d -> Map.of(
                "id", d.getId(),
                "codigoDocente", d.getCodigoDocente(),
                "nombre", d.getNombre(),
                "apellido", d.getApellido(),
                "email", d.getEmail(),
                "telefono", d.getTelefono(),
                "estado", d.getEstado()
            )).toList()
        );
    }
    
    @PostMapping("/docentes/crear-ejemplos")
    public Map<String, Object> crearEjemplos() {
        // Crear algunos docentes de ejemplo
        DocenteEntity docente1 = new DocenteEntity();
        docente1.setCodigoDocente("DOC001");
        docente1.setNombre("Carlos");
        docente1.setApellido("Rodriguez");
        docente1.setEmail("carlos.rodriguez@cibertec.edu.pe");
        docente1.setTelefono("123456789");
        docente1.setEstado("1");
        
        DocenteEntity docente2 = new DocenteEntity();
        docente2.setCodigoDocente("DOC002");
        docente2.setNombre("Maria");
        docente2.setApellido("Gonzalez");
        docente2.setEmail("maria.gonzalez@cibertec.edu.pe");
        docente2.setTelefono("987654321");
        docente2.setEstado("1");
        
        DocenteEntity docente3 = new DocenteEntity();
        docente3.setCodigoDocente("DOC003");
        docente3.setNombre("Juan");
        docente3.setApellido("Perez");
        docente3.setEmail("juan.perez@cibertec.edu.pe");
        docente3.setTelefono("555777888");
        docente3.setEstado("1");
        
        docenteRepo.save(docente1);
        docenteRepo.save(docente2);
        docenteRepo.save(docente3);
        
        return Map.of("mensaje", "3 docentes de ejemplo creados");
    }
}
