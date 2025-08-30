package pe.cibertec.gestion_docente.domain.reserva.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data @Builder
public class ReservaAulaModel {
  private Long idReserva;
  private Long idAula;
  private Long idCurso;    // opcional si quieres asociar al curso
  private Integer idDocente; // se obtiene del usuario logueado
  private LocalDateTime inicio;
  private LocalDateTime fin;
  private String estado;   // RESERVADA / CANCELADA
}
