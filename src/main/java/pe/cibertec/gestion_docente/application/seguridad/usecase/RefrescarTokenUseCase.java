package pe.cibertec.gestion_docente.application.seguridad.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pe.cibertec.gestion_docente.domain.seguridad.model.SeguridadModel;
import pe.cibertec.gestion_docente.domain.seguridad.model.UsuarioModel;
import pe.cibertec.gestion_docente.domain.seguridad.service.TokenService;
import pe.cibertec.gestion_docente.infrastructure.configuration.seguridad.CustomUserDetails;
import pe.cibertec.gestion_docente.infrastructure.persistence.seguridad.service.CustomUserDetailsService;

@Slf4j
@Component
@RequiredArgsConstructor
public class RefrescarTokenUseCase {

    private final TokenService tokenService;
    private final CustomUserDetailsService uds;

    public SeguridadModel ejecutar(String refreshToken) {
        if (!tokenService.esTokenValido(refreshToken)) {
            log.warn("Refresh inválido");
            throw new RuntimeException("Refresh token inválido o expirado");
        }
        String username = tokenService.extraerUsuario(refreshToken);
        CustomUserDetails cud = (CustomUserDetails) uds.loadUserByUsername(username);
        UsuarioModel u = cud.getUsuario();
        return SeguridadModel.builder()
                .token(tokenService.generarTokenAcceso(u))
                .refresh(tokenService.generarTokenRefresco(u))
                .build();
    }
}
