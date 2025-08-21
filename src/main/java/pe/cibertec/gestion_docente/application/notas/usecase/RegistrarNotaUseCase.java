package pe.cibertec.gestion_docente.application.notas.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pe.cibertec.gestion_docente.domain.notas.model.NotaModel;
import pe.cibertec.gestion_docente.domain.notas.repository.NotaRepository;
import pe.cibertec.gestion_docente.domain.seguridad.repository.UsuarioRepository;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class RegistrarNotaUseCase {

    private final NotaRepository notaRepository;
    private final UsuarioRepository usuarioRepository;

    public NotaModel ejecutar(NotaModel n) {
        if (n.getCalificacion() == null || n.getCalificacion() < 0 || n.getCalificacion() > 20) {
            throw new IllegalArgumentException("Calificación fuera de rango");
        }
        if (n.getCodigoAlumno() == null || n.getCodigoAlumno().isBlank()) {
            throw new IllegalArgumentException("Código de alumno requerido");
        }
        if (n.getIdCurso() == null || n.getIdEstructura() == null) {
            throw new IllegalArgumentException("Curso y estructura requeridos");
        }

        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        String username = a != null ? a.getName() : null;

        Integer idDocente = usuarioRepository.usuarioPorUserName(username)
                .map(u -> u.getIdDocente())
                .orElseThrow(() -> new IllegalStateException("Usuario no asociado a docente"));

        if (notaRepository.existsNotaUnica(n.getCodigoAlumno(), n.getIdCurso(), n.getIdEstructura())) {
            throw new IllegalStateException("Nota duplicada para alumno/curso/estructura");
        }

        n.setIdDocente(idDocente);
        n.setFechaRegistro(LocalDateTime.now());
        return notaRepository.saveNota(n);
    }
}
