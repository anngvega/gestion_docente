package pe.cibertec.gestion_docente.presentation.alumno.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pe.cibertec.gestion_docente.infrastructure.persistence.alumno.entity.AlumnoEntity;
import pe.cibertec.gestion_docente.infrastructure.persistence.alumno.jpa.AlumnoRepositoryJpa;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/public/debug/alumnos")
@RequiredArgsConstructor
public class AlumnoDebugController {
    
    private final AlumnoRepositoryJpa alumnoRepo;
    
    @GetMapping("/lista")
    public Map<String, Object> listarAlumnos() {
        List<AlumnoEntity> alumnos = alumnoRepo.findAll();
        return Map.of(
            "total", alumnos.size(),
            "alumnos", alumnos.stream().map(a -> Map.of(
                "id", a.getId(),
                "codigoAlumno", a.getCodigoAlumno(),
                "nombre", a.getNombre(),
                "apellido", a.getApellido(),
                "email", a.getEmail(),
                "estado", a.getEstado(),
                "nombreCompleto", a.getNombre() + " " + a.getApellido()
            )).toList()
        );
    }
    
    @PostMapping("/crear-ejemplos")
    public Map<String, Object> crearEjemplosAlumnos() {
        // Crear algunos alumnos de ejemplo
        AlumnoEntity alumno1 = new AlumnoEntity();
        alumno1.setCodigoAlumno("A12345678");
        alumno1.setNombre("Juan Carlos");
        alumno1.setApellido("Pérez García");
        alumno1.setEmail("juan.perez@alumno.cibertec.edu.pe");
        alumno1.setEstado("1");
        
        AlumnoEntity alumno2 = new AlumnoEntity();
        alumno2.setCodigoAlumno("A98765432");
        alumno2.setNombre("María Elena");
        alumno2.setApellido("Rodríguez López");
        alumno2.setEmail("maria.rodriguez@alumno.cibertec.edu.pe");
        alumno2.setEstado("1");
        
        AlumnoEntity alumno3 = new AlumnoEntity();
        alumno3.setCodigoAlumno("A11223344");
        alumno3.setNombre("Carlos Alberto");
        alumno3.setApellido("Mendoza Silva");
        alumno3.setEmail("carlos.mendoza@alumno.cibertec.edu.pe");
        alumno3.setEstado("1");
        
        AlumnoEntity alumno4 = new AlumnoEntity();
        alumno4.setCodigoAlumno("A55667788");
        alumno4.setNombre("Ana Sofía");
        alumno4.setApellido("Vargas Cruz");
        alumno4.setEmail("ana.vargas@alumno.cibertec.edu.pe");
        alumno4.setEstado("1");
        
        AlumnoEntity alumno5 = new AlumnoEntity();
        alumno5.setCodigoAlumno("A99001122");
        alumno5.setNombre("Pedro Luis");
        alumno5.setApellido("Torres Ramos");
        alumno5.setEmail("pedro.torres@alumno.cibertec.edu.pe");
        alumno5.setEstado("1");
        
        alumnoRepo.save(alumno1);
        alumnoRepo.save(alumno2);
        alumnoRepo.save(alumno3);
        alumnoRepo.save(alumno4);
        alumnoRepo.save(alumno5);
        
        return Map.of("mensaje", "5 alumnos de ejemplo creados exitosamente");
    }
}
