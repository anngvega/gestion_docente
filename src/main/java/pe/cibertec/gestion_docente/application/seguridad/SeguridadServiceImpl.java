package pe.cibertec.gestion_docente.application.seguridad;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.cibertec.gestion_docente.application.seguridad.usecase.AutenticarUsuarioUseCase;
import pe.cibertec.gestion_docente.application.seguridad.usecase.RefrescarTokenUseCase;
import pe.cibertec.gestion_docente.domain.seguridad.model.SeguridadModel;
import pe.cibertec.gestion_docente.domain.seguridad.service.SeguridadService;

@Service
@RequiredArgsConstructor
public class SeguridadServiceImpl implements SeguridadService {

    private final AutenticarUsuarioUseCase login;
    private final RefrescarTokenUseCase refresh;

    @Override
    public SeguridadModel autenticacion(String username, String password) {
        return login.ejecutar(username, password);
    }

    @Override
    public SeguridadModel refrescar(String token) {
        return refresh.ejecutar(token);
    }
}
