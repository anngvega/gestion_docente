package pe.cibertec.gestion_docente.presentation.dto.auth;

import jakarta.validation.constraints.NotBlank; import lombok.Getter; import lombok.Setter;
@Getter @Setter public class RefreshTokenRequestDto { @NotBlank private String refreshToken; }
