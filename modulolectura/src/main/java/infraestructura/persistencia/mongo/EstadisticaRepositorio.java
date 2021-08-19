package infraestructura.persistencia.mongo;

import dominio.entidades.Estadistica;
import dominio.servicios.dependencias.EstadisticaRepositorioInterface;
import org.bson.conversions.Bson;
import reactor.core.publisher.Mono;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.mongodb.client.model.Filters.eq;

@Singleton
public class EstadisticaRepositorio implements EstadisticaRepositorioInterface {

    @Inject
    private MongoContext mongoContext;

    // Ratio de conversión = ( Conversiones / Visitas totales ) x 100
    @Override
    public Mono<Estadistica> consulta() {
        return obtenerElNumeroDeMutantes().flatMap( numeroDeMutantes ->
                obtenerElNumeroDeHumanos().map(numeroDeHumanos ->
                        new Estadistica(numeroDeMutantes, numeroDeHumanos, (numeroDeMutantes / (numeroDeMutantes + numeroDeHumanos)))
                ))
                .onErrorResume(error -> {
                    // LOG.errorWithHttpAndStackTrace((Exception)(error));
                    return Mono.error(new Exception(error));
                });
    }
    public Mono<Long> obtenerElNumeroDeHumanos(){
        Bson filtro =eq("esMutante", false);
        return contar(filtro);
    }
    public Mono<Long> obtenerElNumeroDeMutantes(){
        Bson filtro =eq("esMutante", true);
        return contar(filtro);
    }
    public Mono<Long> contar(Bson filtro){
        return Mono.from(mongoContext.getMongoCollectionPersona()
                        .countDocuments(filtro))
                .map(numeroDeRegistros -> numeroDeRegistros)
                .onErrorResume(error -> {
                    // LOG.errorWithHttpAndStackTrace((Exception)(error));
                    return Mono.error(new Exception(error));
                });
    }
}
