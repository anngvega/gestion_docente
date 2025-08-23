package pe.cibertec.gestion_docente.infrastructure.configuration.seguridad;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import pe.cibertec.gestion_docente.domain.seguridad.service.TokenService;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // 1) Deja pasar preflight y rutas públicas (ahorra trabajo y evita ruido)
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            filterChain.doFilter(request, response);
            return;
        }
        String path = request.getServletPath();
        if (path.startsWith("/public/")) {        // ajusta si usas otro prefijo público
            filterChain.doFilter(request, response);
            return;
        }

        // 2) Lee el Authorization (case-insensitive) y valida formato Bearer
        String auth = request.getHeader("Authorization");
        if (auth == null || !auth.regionMatches(true, 0, "Bearer ", 0, 7)) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = auth.substring(7).trim();
        if (jwt.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }

        // 3) Evita re-autenticar si ya hay Authentication en el contexto
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                if (tokenService.esTokenValido(jwt)) {
                    var ud = tokenService.crearUserDetailsDesdeToken(jwt);
                    var authToken = new UsernamePasswordAuthenticationToken(
                            ud, null, ud.getAuthorities()
                    );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            } catch (Exception ex) {
                // No “rompas” la cadena si el token falla; deja que el request siga y termine en 401/403
                logger.warn("JWT inválido o expirado", ex);
            }
        }

        filterChain.doFilter(request, response);
    }
}
