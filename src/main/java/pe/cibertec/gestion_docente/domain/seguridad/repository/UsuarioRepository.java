package pe.cibertec.gestion_docente.domain.seguridad.repository;

import pe.cibertec.gestion_docente.domain.seguridad.model.UsuarioModel;

import java.util.Optional;

public interface UsuarioRepository {
    Optional<UsuarioModel> usuarioPorUserName(String username);
    void guardarToken(String token);
    String obtenerTokenCache(String username);
}
