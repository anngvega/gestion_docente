package pe.cibertec.gestion_docente.presentation.docente.dto;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class DocenteResponseDto {
  private Long id;
  private String codigo;
  private String nombres;
  private String apellidos;
  private String email;
  private Boolean activo;
}
