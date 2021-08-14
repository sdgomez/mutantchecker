package infraestructura.cache;

import dominio.entidades.Estadistica;
import dominio.servicios.dependencias.CacheInterface;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import reactor.core.publisher.Mono;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CacheService implements CacheInterface {

    @Inject
    StatefulRedisConnection<String, String> connection;

    @Override
    public String getValor(String clave) {
        RedisCommands<String, String> commands = connection.sync();
        return commands.get(clave);
    }
    public Mono<Estadistica> buscarEstadisticaEnCache(){
        String numMutantes = getValor("numeroDeMutantes");
        String numHumanos = getValor("numeroDeHumanos");
        String r = getValor("ratio");
        Long numeroDeMutantes;
        Long numeroDeHumanos;
        String ratio;
        if (numMutantes == null){
            numeroDeMutantes = 0l;
        }else{
            numeroDeMutantes = Long.parseLong(getValor("numeroDeMutantes"));
        }
        if (numHumanos == null){
            numeroDeHumanos = 0l;
        }else{
            numeroDeHumanos = Long.parseLong(getValor("numeroDeHumanos"));
        }
        if (r == null){
            ratio = "0.0";
        }else{
            ratio = getValor("ratio");
        }
        return Mono.just(
                new Estadistica(
                        numeroDeMutantes,
                        numeroDeHumanos,
                        Float.parseFloat(ratio)
                )
        );
    }
}
