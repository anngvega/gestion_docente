package pe.cibertec.gestion_docente.presentation.seguridad.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.BadCredentialsException;
import pe.cibertec.gestion_docente.application.seguridad.SeguridadServiceImpl;
import pe.cibertec.gestion_docente.domain.seguridad.model.SeguridadModel;

import java.util.Map;

@RestController
@RequestMapping("/public/auth")
@RequiredArgsConstructor
public class AuthController {

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
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Credenciales inválidas"));
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody Map<String, String> body) {
        String refreshToken = body.get("refreshToken");

        SeguridadModel s = seguridad.refrescar(refreshToken);

        Map<String, Object> data = Map.of(
                "accessToken",  s.getAccessToken(),
                "refreshToken", s.getRefreshToken(),
                "expiresIn",    s.getExpiresIn()
        );

        return ResponseEntity.ok(Map.of("data", data));
    }
}
