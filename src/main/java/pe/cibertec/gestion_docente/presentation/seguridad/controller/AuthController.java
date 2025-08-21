package pe.cibertec.gestion_docente.presentation.seguridad.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.cibertec.gestion_docente.application.seguridad.SeguridadServiceImpl;
import pe.cibertec.gestion_docente.domain.seguridad.model.SeguridadModel;
import org.springframework.security.authentication.BadCredentialsException;

import java.util.Map;

@RestController
@RequestMapping("/public/auth")
@RequiredArgsConstructor
public class AuthController {

    private final SeguridadServiceImpl seguridad;

    // DTO simple para el body
    public record LoginReq(String username, String password) {}

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginReq body) {
        try {
            SeguridadModel s = seguridad.autenticacion(body.username(), body.password());
            return ResponseEntity.ok(s);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Credenciales inválidas"));
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<SeguridadModel> refresh(@RequestBody Map<String, String> body) {
        return ResponseEntity.ok(seguridad.refrescar(body.get("refresh")));
    }
}
