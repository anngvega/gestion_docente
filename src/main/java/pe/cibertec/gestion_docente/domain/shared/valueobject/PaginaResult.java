package pe.cibertec.gestion_docente.domain.shared.valueobject;

import lombok.Builder;
import lombok.Value;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Value
@Builder
public class PaginaResult<T> {
    List<T> contenido;
    int paginaActual;
    int tamanio;
    long totalElementos;
    int totalPaginas;
    boolean primera;
    boolean ultima;
    boolean vacia;

    public static <T> PaginaResult<T> of(List<T> contenido, int pagina, int tamanio, long total) {
        int totalPaginas = (int) Math.ceil((double) total / tamanio);
        return PaginaResult.<T>builder()
                .contenido(contenido)
                .paginaActual(pagina)
                .tamanio(tamanio)
                .totalElementos(total)
                .totalPaginas(totalPaginas)
                .primera(pagina == 0)
                .ultima(pagina >= totalPaginas - 1)
                .vacia(contenido.isEmpty())
                .build();
    }
    public <U> PaginaResult<U> map(Function<? super T, ? extends U> fn) {
        List<U> nuevoContenido = (contenido == null)
                ? Collections.emptyList()
                : contenido.stream().map(fn).collect(Collectors.toList());

        return PaginaResult.<U>builder()
                .contenido(nuevoContenido)
                .paginaActual(paginaActual)
                .tamanio(tamanio)
                .totalElementos(totalElementos)
                .totalPaginas(totalPaginas)
                .primera(primera)
                .ultima(ultima)
                .vacia(nuevoContenido.isEmpty())
                .build();
    }
}
