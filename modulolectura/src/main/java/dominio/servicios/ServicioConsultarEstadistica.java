package dominio.servicios;

import dominio.entidades.Estadistica;
import dominio.servicios.dependencias.CacheInterface;
import dominio.servicios.dependencias.EstadisticaRepositorioInterface;
import reactor.core.publisher.Mono;

public class ServicioConsultarEstadistica {
    EstadisticaRepositorioInterface estadisticaRepositorioInterface;
    CacheInterface cacheInterface;
    public ServicioConsultarEstadistica(EstadisticaRepositorioInterface estadisticaRepositorioInterface, CacheInterface cacheInterface){
        this.estadisticaRepositorioInterface = estadisticaRepositorioInterface;
        this.cacheInterface = cacheInterface;
    }
    public Mono<Estadistica> consulta(){
        return buscarEstadistica();
    }

    public Mono<Estadistica> buscarEstadistica(){
        try{
            return cacheInterface.buscarEstadisticaEnCache();
        }catch (Throwable throwable){
            return estadisticaRepositorioInterface.consulta()
                    .onErrorResume(Mono::error);
        }
    }
}
