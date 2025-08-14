package pe.cibertec.gestion_docente.application.seguridad;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.cibertec.gestion_docente.application.seguridad.usecase.AutenticarUsuarioUseCase;
import pe.cibertec.gestion_docente.application.seguridad.usecase.RefrescarTokenUseCase;
import pe.cibertec.gestion_docente.presentation.dto.auth.LoginResponseDto;

@Service @RequiredArgsConstructor
public class SeguridadServiceImpl {
    private final AutenticarUsuarioUseCase login;
    private final RefrescarTokenUseCase refresh;
    public LoginResponseDto autenticacion(String u, String p){ return login.ejecutar(u,p); }
    public LoginResponseDto refrescar(String rt){ return refresh.ejecutar(rt); }
}

