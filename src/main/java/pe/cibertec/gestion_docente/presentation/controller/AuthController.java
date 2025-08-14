package pe.cibertec.gestion_docente.presentation.controller;

import jakarta.validation.Valid; import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pe.cibertec.gestion_docente.application.seguridad.SeguridadServiceImpl;
import pe.cibertec.gestion_docente.presentation.dto.auth.*;

@RestController @RequestMapping("/public/api/auth") @RequiredArgsConstructor
public class AuthController {
    private final SeguridadServiceImpl auth;
    @PostMapping("/login")   public LoginResponseDto login(@Valid @RequestBody LoginRequestDto r){ return auth.autenticacion(r.getUsername(), r.getPassword()); }
    @PostMapping("/refresh") public LoginResponseDto refresh(@Valid @RequestBody RefreshTokenRequestDto r){ return auth.refrescar(r.getRefreshToken()); }
}
