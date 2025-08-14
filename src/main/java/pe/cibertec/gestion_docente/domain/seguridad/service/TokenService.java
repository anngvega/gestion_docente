package pe.cibertec.gestion_docente.domain.seguridad.service;

import pe.cibertec.gestion_docente.infrastructure.seguridad.entity.UsuarioEntity;

public interface TokenService {
    String generarTokenAcceso(UsuarioEntity usuario);
    String generarTokenRefresco(UsuarioEntity usuario);
    boolean esTokenValido(String token);
    String extraerUsuario(String token);
}