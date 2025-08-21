package pe.cibertec.gestion_docente.domain.seguridad.service;

import pe.cibertec.gestion_docente.domain.seguridad.model.SeguridadModel;

public interface SeguridadService {
    SeguridadModel autenticacion(String username, String password);
    SeguridadModel refrescar(String token);
}
