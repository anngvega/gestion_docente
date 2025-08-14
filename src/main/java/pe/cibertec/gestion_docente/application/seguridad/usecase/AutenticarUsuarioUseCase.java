package pe.cibertec.gestion_docente.application.seguridad.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import pe.cibertec.gestion_docente.domain.seguridad.exception.CredencialesInvalidasException;
import pe.cibertec.gestion_docente.domain.seguridad.service.TokenService;
import pe.cibertec.gestion_docente.infrastructure.seguridad.jpa.UsuarioRepositoryJpa;
import pe.cibertec.gestion_docente.presentation.dto.auth.LoginResponseDto;

@Component @RequiredArgsConstructor
public class AutenticarUsuarioUseCase {
    private final AuthenticationManager authManager;
    private final UsuarioRepositoryJpa usuarios;
    private final TokenService tokens;

    public LoginResponseDto ejecutar(String username, String password){
        try {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (AuthenticationException e){
            throw new CredencialesInvalidasException();
        }
        var u = usuarios.findByUsuario(username).orElseThrow(CredencialesInvalidasException::new);
        return new LoginResponseDto(tokens.generarTokenAcceso(u), tokens.generarTokenRefresco(u), 900);
    }
}
