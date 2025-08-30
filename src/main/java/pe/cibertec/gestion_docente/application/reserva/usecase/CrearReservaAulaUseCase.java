package pe.cibertec.gestion_docente.application.reserva.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pe.cibertec.gestion_docente.domain.reserva.model.ReservaAulaModel;
import pe.cibertec.gestion_docente.domain.reserva.repository.ReservaAulaRepository;
import pe.cibertec.gestion_docente.domain.seguridad.repository.UsuarioRepository;

@Component @RequiredArgsConstructor
public class CrearReservaAulaUseCase {

  private final ReservaAulaRepository repo;
  private final UsuarioRepository usuarioRepository;

  public ReservaAulaModel ejecutar(ReservaAulaModel m) {
    if (m.getIdAula() == null) throw new IllegalArgumentException("Aula requerida");
    if (m.getInicio() == null || m.getFin() == null || !m.getFin().isAfter(m.getInicio()))
      throw new IllegalArgumentException("Rango de tiempo inválido");

    Authentication a = SecurityContextHolder.getContext().getAuthentication();
    String username = a != null ? a.getName() : null;
    Integer idDocente = usuarioRepository.usuarioPorUserName(username)
        .map(u -> u.getIdDocente())
        .orElseThrow(() -> new IllegalStateException("Usuario no asociado a docente"));

    if (repo.existeSolapamiento(m.getIdAula(), m.getInicio(), m.getFin()))
      throw new IllegalStateException("Existe otra reserva en ese rango de tiempo");

    m.setIdDocente(idDocente);
    m.setEstado("RESERVADA");
    return repo.crear(m);
  }
}
