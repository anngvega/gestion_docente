package pe.cibertec.gestion_docente.application.seguridad.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.cibertec.gestion_docente.domain.seguridad.service.TokenService;
import pe.cibertec.gestion_docente.infrastructure.seguridad.jpa.UsuarioRepositoryJpa;
import pe.cibertec.gestion_docente.presentation.dto.auth.LoginResponseDto;

@Component @RequiredArgsConstructor
public class RefrescarTokenUseCase {
    private final UsuarioRepositoryJpa usuarios;
    private final TokenService tokens;

    public LoginResponseDto ejecutar(String refreshToken){
        if(!tokens.esTokenValido(refreshToken)) throw new IllegalArgumentException("Refresh token inválido");
        var username = tokens.extraerUsuario(refreshToken);
        var u = usuarios.findByUsuario(username).orElseThrow(() -> new IllegalArgumentException("Usuario no existe"));
        return new LoginResponseDto(tokens.generarTokenAcceso(u), tokens.generarTokenRefresco(u), 900);
    }
}
