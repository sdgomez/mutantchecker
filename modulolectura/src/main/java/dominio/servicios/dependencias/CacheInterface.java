package dominio.servicios.dependencias;

import dominio.entidades.Estadistica;
import reactor.core.publisher.Mono;

public interface CacheInterface {
    String getValor(String clave);
    Mono<Estadistica> buscarEstadisticaEnCache();
}
