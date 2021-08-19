package infraestructura;

import dominio.servicios.dependencias.CacheInterface;
import infraestructura.cache.CacheService;
import infraestructura.persistencia.mongo.PersonaRepositorio;
import io.micronaut.context.annotation.Factory;

import javax.inject.Inject;
import javax.inject.Singleton;

@Factory
public class AppContext {

    @Inject
    PersonaRepositorio personaRepositorio;

    @Inject
    CacheService cacheService;

    @Singleton
    dominio.servicios.ServicioGuardarPersona guardarPersona() {
        return new dominio.servicios.ServicioGuardarPersona(this.personaRepositorio, this.cacheService);
    }
}