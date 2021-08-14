package infraestructura.persistencia.mongo;

import dominio.entidades.Persona;
import dominio.servicios.dependencias.PersonaRepositorioInterface;
import infraestructura.persistencia.mongo.mappers.PersonaMappers;
import reactor.core.publisher.Mono;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PersonaRepositorio implements PersonaRepositorioInterface {

    @Inject
    private MongoContext mongoContext;

    @Inject
    PersonaMappers personaMappers;

    @Override
    public Mono<Persona> guardar(Persona persona) {
        return Mono.just(personaMappers.personaToPersonaModel(persona))
                .flatMap(
                        personaModel -> Mono.from(mongoContext.getMongoCollectionPersona()
                                .insertOne(personaModel)))
                .map(success -> persona)
                .onErrorResume(error -> {
                    // LOG.errorWithHttpAndStackTrace(new Exception(error));
                    return Mono.error(new Exception(error));
                });
    }
}
