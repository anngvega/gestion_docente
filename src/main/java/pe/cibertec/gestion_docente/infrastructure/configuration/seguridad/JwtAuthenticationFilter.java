package pe.cibertec.gestion_docente.infrastructure.configuration.seguridad;

import jakarta.servlet.*; import jakarta.servlet.http.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component; import org.springframework.web.filter.OncePerRequestFilter;
import pe.cibertec.gestion_docente.domain.seguridad.service.TokenService;
import pe.cibertec.gestion_docente.infrastructure.seguridad.service.CustomUserDetailsService;
import java.io.IOException;

@Component @RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final TokenService tokens; private final CustomUserDetailsService uds;

    @Override protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws ServletException, IOException {
        var h=req.getHeader("Authorization");
        if(h!=null && h.startsWith("Bearer ")){
            var t=h.substring(7);
            if(tokens.esTokenValido(t) && SecurityContextHolder.getContext().getAuthentication()==null){
                var user=tokens.extraerUsuario(t);
                var ud=uds.loadUserByUsername(user);
                var auth=new UsernamePasswordAuthenticationToken(ud,null,ud.getAuthorities());
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        chain.doFilter(req,res);
    }
}
