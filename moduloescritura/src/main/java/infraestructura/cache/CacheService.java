package infraestructura.cache;

import dominio.entidades.Persona;
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
    public void setValor(String clave, String valor) {
        RedisCommands<String, String> commands = connection.sync();
        commands.set(clave, valor);
    }

    @Override
    public String getValor(String clave) {
        RedisCommands<String, String> commands = connection.sync();
        return commands.get(clave);
    }

    public Mono<Boolean> incrementarValor(Persona persona){
        try{
            String numMutantes = getValor("numeroDeMutantes");
            String numHumanos = getValor("numeroDeHumanos");
            Long numeroDeMutantes;
            Long numeroDeHumanos;
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
            if (persona.getEsMutante()){
                numeroDeMutantes = numeroDeMutantes + 1;
                setValor("numeroDeMutantes", numeroDeMutantes.toString());
            }else{
                numeroDeHumanos = numeroDeHumanos + 1;
                setValor("numeroDeHumanos", numeroDeHumanos.toString());
            }
            if (numeroDeHumanos > 0 && numeroDeMutantes > 0){
                Float ratio = (float) (numeroDeMutantes / (numeroDeMutantes + numeroDeHumanos));
                setValor("ratio", ratio.toString());
            }else{
                Float ratio = 0.0f;
                setValor("ratio", ratio.toString());
            }
            return Mono.just(true);
        }catch (Throwable t){
            return Mono.error(new Exception("No se pudo acceder a la cache: " + t.getMessage() ));
        }
    }
}
