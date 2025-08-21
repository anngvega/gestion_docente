package pe.cibertec.gestion_docente.application.notas.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.cibertec.gestion_docente.domain.notas.model.EstructuraNotaModel;
import pe.cibertec.gestion_docente.domain.notas.repository.NotaRepository;

@Component
@RequiredArgsConstructor
public class CrearEstructuraNotaUseCase {
    private final NotaRepository notaRepository;

    public EstructuraNotaModel ejecutar(EstructuraNotaModel m) {
        if (m.getPeso() == null || m.getPeso() < 0 || m.getPeso() > 100) {
            throw new IllegalArgumentException("Peso inválido");
        }
        if (m.getDescripcion() == null || m.getDescripcion().isBlank()) {
            throw new IllegalArgumentException("Descripción requerida");
        }
        if (m.getIdCurso() == null) {
            throw new IllegalArgumentException("Curso requerido");
        }
        return notaRepository.crearEstructura(m);
    }
}
