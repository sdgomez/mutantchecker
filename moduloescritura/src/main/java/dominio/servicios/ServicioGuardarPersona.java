package dominio.servicios;

import dominio.entidades.Persona;
import dominio.servicios.dependencias.CacheInterface;
import dominio.servicios.dependencias.PersonaRepositorioInterface;
import reactor.core.publisher.Mono;

public class ServicioGuardarPersona {
    PersonaRepositorioInterface personaRepositorioInterface;
    CacheInterface cacheInterface;
    public ServicioGuardarPersona(PersonaRepositorioInterface personaRepositorioInterface, CacheInterface cacheInterface){
        this.personaRepositorioInterface = personaRepositorioInterface;
        this.cacheInterface = cacheInterface;
    }
    public Mono<Persona> guardar(Persona persona){
        return insertarEnFuenteDeDatos(persona)
                .onErrorResume(Mono::error);
    }
    public Mono<Persona> insertarEnFuenteDeDatos(Persona persona){
        return cacheInterface.incrementarValor(persona).flatMap(ok -> personaRepositorioInterface.guardar(persona));
    }

}
