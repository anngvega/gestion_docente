package pe.cibertec.gestion_docente.application.seguridad;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.cibertec.gestion_docente.application.seguridad.usecase.AutenticarUsuarioUseCase;
import pe.cibertec.gestion_docente.application.seguridad.usecase.RefrescarTokenUseCase;
import pe.cibertec.gestion_docente.domain.seguridad.model.SeguridadModel;
import pe.cibertec.gestion_docente.domain.seguridad.service.SeguridadService;
import pe.cibertec.gestion_docente.domain.seguridad.service.TokenService;

@Service
@RequiredArgsConstructor
public class SeguridadServiceImpl implements SeguridadService {

    private final AutenticarUsuarioUseCase login;
    private final RefrescarTokenUseCase refresh;
    private final TokenService tokens;

    @Override
    public SeguridadModel autenticacion(String username, String password) {
        SeguridadModel s = login.ejecutar(username, password);

        s.setExpiresIn(tokens.segundosHastaExpirar(s.getAccessToken()));

        return s;
    }

    @Override
    public SeguridadModel refrescar(String refreshToken) {
        SeguridadModel s = refresh.ejecutar(refreshToken);
        s.setExpiresIn(tokens.segundosHastaExpirar(s.getAccessToken())); // <—

        return s;
    }
}
