package infraestructura;

import dominio.servicios.ServicioConsultarEstadistica;
import infraestructura.cache.CacheService;
import infraestructura.persistencia.mongo.EstadisticaRepositorio;
import io.micronaut.context.annotation.Factory;

import javax.inject.Inject;
import javax.inject.Singleton;

@Factory
public class AppContext {

    @Inject
    EstadisticaRepositorio estadisticaRepositorio;

    @Inject
    CacheService cacheService;

    @Singleton
    ServicioConsultarEstadistica servicioConsultarEstadistica() {
        return new ServicioConsultarEstadistica(this.estadisticaRepositorio, this.cacheService);
    }
}