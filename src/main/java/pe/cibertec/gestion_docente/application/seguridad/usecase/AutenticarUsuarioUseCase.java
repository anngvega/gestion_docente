package pe.cibertec.gestion_docente.application.seguridad.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pe.cibertec.gestion_docente.domain.seguridad.model.SeguridadModel;
import pe.cibertec.gestion_docente.domain.seguridad.model.UsuarioModel;
import pe.cibertec.gestion_docente.domain.seguridad.repository.UsuarioRepository;
import pe.cibertec.gestion_docente.domain.seguridad.service.TokenService;

@Slf4j
@Component
@RequiredArgsConstructor
public class AutenticarUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public SeguridadModel ejecutar(String username, String rawPassword) {
        log.debug("[LOGIN] username={}", username);

        UsuarioModel u = usuarioRepository.usuarioPorUserName(username)
                .orElseThrow(() -> new BadCredentialsException("Credenciales inválidas"));

        log.debug("[LOGIN] encontrado id={}, activo={}, hashPrefix={}",
                u.getId(), u.isActivo(),
                u.getPassword() != null && u.getPassword().length() >= 4 ? u.getPassword().substring(0, 4) : "null");

        if (!u.isActivo()) {
            throw new BadCredentialsException("Usuario inactivo");
        }

        boolean matches = passwordEncoder.matches(rawPassword, u.getPassword());
        log.debug("[LOGIN] password matches? {}", matches);
        if (!matches) {
            throw new BadCredentialsException("Credenciales inválidas");
        }

        String access  = tokenService.generarTokenAcceso(u);
        String refresh = tokenService.generarTokenRefresco(u);

        log.debug("[LOGIN] OK -> tokens generados (access len={}, refresh len={})",
                access.length(), refresh.length());

        return SeguridadModel.builder()
                .accessToken(access)
                .refreshToken(refresh)
                .build();
    }
}
