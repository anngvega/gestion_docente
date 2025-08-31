package pe.cibertec.gestion_docente.presentation.seguridad.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.BadCredentialsException;
import pe.cibertec.gestion_docente.application.seguridad.SeguridadServiceImpl;
import pe.cibertec.gestion_docente.domain.seguridad.model.SeguridadModel;

import java.util.Map;

@RestController
@RequestMapping("/api/public/auth")
@RequiredArgsConstructor
public class ApiAuthController {

    private final SeguridadServiceImpl seguridad;

    public record LoginReq(String username, String password) {}

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginReq body) {
        try {
            SeguridadModel s = seguridad.autenticacion(body.username(), body.password());

            // 🔑 Formamos el shape que espera Angular
            Map<String, Object> data = Map.of(
                    "accessToken",  s.getAccessToken(),
                    "refreshToken", s.getRefreshToken(),
                    "expiresIn",    s.getExpiresIn()
            );

            return ResponseEntity.ok(Map.of("data", data));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    Map.of("error", "Credenciales inválidas")
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    Map.of("error", "Error interno del servidor")
            );
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody Map<String, String> body) {
        // Implementación del refresh si es necesaria
        return ResponseEntity.ok(Map.of("message", "Refresh endpoint"));
    }
}
