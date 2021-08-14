package dominio.servicios.dependencias;

import dominio.entidades.Persona;
import reactor.core.publisher.Mono;

public interface PersonaRepositorioInterface {
    Mono<Persona> guardar(Persona persona);
}
