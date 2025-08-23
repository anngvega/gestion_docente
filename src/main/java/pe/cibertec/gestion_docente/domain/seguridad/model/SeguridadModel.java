package pe.cibertec.gestion_docente.domain.seguridad.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeguridadModel {

    // si quieres seguir devolviendo "token" y "refresh" en el JSON:
    @JsonProperty("token")
    private String accessToken;

    @JsonProperty("refresh")
    private String refreshToken;

    private long   expiresIn;  // en segundos

}
