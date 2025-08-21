package pe.cibertec.gestion_docente.infrastructure.configuration;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public class AuditorAwareConfig implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) return Optional.empty();
        Object principal = auth.getPrincipal();
        String username = (principal instanceof UserDetails u) ? u.getUsername()
                : (principal instanceof String s ? s : null);
        return Optional.ofNullable(username);
    }
}
