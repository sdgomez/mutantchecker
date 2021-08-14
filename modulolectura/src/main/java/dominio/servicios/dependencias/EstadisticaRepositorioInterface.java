package dominio.servicios.dependencias;

import dominio.entidades.Estadistica;
import reactor.core.publisher.Mono;

public interface EstadisticaRepositorioInterface {
    Mono<Estadistica> consulta();
}
