package pe.cibertec.gestion_docente.presentation.seguridad.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pe.cibertec.gestion_docente.infrastructure.persistence.seguridad.jpa.UsuarioRepositoryJpa;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;

@RestController
@RequestMapping("/public/debug")
@RequiredArgsConstructor
public class DebugAuthController {

    private final UsuarioRepositoryJpa usuarioRepo;
    private final PasswordEncoder passwordEncoder;

    // ¿La app encuentra al usuario y está activo?
    @GetMapping("/user")
    public Object user(@RequestParam String username) {
        return usuarioRepo.usuarioPorUsername(username)
                .map(u -> Map.of(
                        "found", true,
                        "id", u.getId(),
                        "username", u.getUsername(),
                        "activo", u.getActivo(),
                        "roles", u.getRoles().stream().map(r -> r.getRol().getNombre()).toList()
                ))
                .orElse(Map.of("found", false));
    }

    // Genera un bcrypt para comparar/actualizar fácilmente
    @GetMapping("/hash")
    public Map<String, String> hash(@RequestParam String raw) {
        return Map.of("raw", raw, "bcrypt", passwordEncoder.encode(raw));
    }

    @GetMapping("/check")
    public Object check(@RequestParam String username, @RequestParam String raw) {
        return usuarioRepo.usuarioPorUsername(username)
                .map(u -> {
                    String hash = u.getPasswordHash();
                    boolean matches = passwordEncoder.matches(raw, hash);
                    String prefix = (hash != null && hash.length() >= 4) ? hash.substring(0, 4) : "";
                    return Map.of(
                            "found", true,
                            "username", u.getUsername(),
                            "hashPrefix", prefix,   // por inspección, suele ser $2a$ o $2b$
                            "matches", matches
                    );
                })
                .orElse(Map.of("found", false));
    }

    // ⚠️ TEMPORAL: Actualizar contraseña para debug
    @PostMapping("/update-password")
    public Object updatePassword(@RequestParam String username, @RequestParam String newPassword) {
        return usuarioRepo.usuarioPorUsername(username)
                .map(u -> {
                    String newHash = passwordEncoder.encode(newPassword);
                    u.setPasswordHash(newHash);
                    usuarioRepo.save(u);
                    return Map.of(
                            "success", true,
                            "username", username,
                            "message", "Contraseña actualizada correctamente",
                            "newHashPrefix", newHash.substring(0, 4)
                    );
                })
                .orElse(Map.of(
                        "success", false,
                        "message", "Usuario no encontrado"
                ));
    }
}
