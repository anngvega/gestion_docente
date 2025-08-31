package pe.cibertec.gestion_docente.application.docente.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.cibertec.gestion_docente.domain.docente.service.DocenteService;
import pe.cibertec.gestion_docente.presentation.docente.dto.DocenteResponseDto;
import pe.cibertec.gestion_docente.presentation.docente.mapper.DocenteDtoMapper;
import pe.cibertec.gestion_docente.domain.shared.valueobject.PaginaResult;
import pe.cibertec.gestion_docente.domain.shared.valueobject.PaginacionRequest;

@Component
@RequiredArgsConstructor
public class ListarDocentesUseCase {
  private final DocenteService service;
  private final DocenteDtoMapper mapper;

    public PaginaResult<DocenteResponseDto> ejecutar(PaginacionRequest pag) {
        var pageModel = service.listar(pag);
        return PaginaResult.of(
                pageModel.getContenido().stream().map(mapper::toDto).toList(),
                pageModel.getPaginaActual(),
                pageModel.getTamanio(),
                pageModel.getTotalElementos()
        );
  }
}
