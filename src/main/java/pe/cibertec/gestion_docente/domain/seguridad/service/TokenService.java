package pe.cibertec.gestion_docente.domain.seguridad.service;

import org.springframework.security.core.userdetails.UserDetails;
import pe.cibertec.gestion_docente.domain.seguridad.model.UsuarioModel;

public interface TokenService {
    String generarTokenAcceso(UsuarioModel usuario);
    String generarTokenRefresco(UsuarioModel usuario);
    String extraerUsuario(String token);
    boolean esTokenValido(String token);
    UserDetails crearUserDetailsDesdeToken(String token);
    long segundosHastaExpirar(String token);
}
