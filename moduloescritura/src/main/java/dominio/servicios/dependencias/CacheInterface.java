package dominio.servicios.dependencias;

import dominio.entidades.Persona;
import reactor.core.publisher.Mono;

public interface CacheInterface {
    void setValor(String clave, String valor);
    String getValor(String clave);
    Mono<Boolean> incrementarValor(Persona persona);
}
