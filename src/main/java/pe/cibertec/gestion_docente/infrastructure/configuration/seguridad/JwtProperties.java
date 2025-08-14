package pe.cibertec.gestion_docente.infrastructure.configuration.seguridad;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Getter; import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "security.jwt")
@Getter @Setter
public class JwtProperties {
    private String secret;
    private long accessTokenExpiration;
    private long refreshTokenExpiration;
}
