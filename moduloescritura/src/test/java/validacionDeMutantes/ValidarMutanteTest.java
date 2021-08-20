package validacionDeMutantes;
import application.dtos.ValidateMutanteRequestDTO;
import dominio.entidades.Persona;
import dominio.servicios.dependencias.PersonaRepositorioInterface;
import infraestructura.cache.CacheService;
import infraestructura.persistencia.mongo.PersonaRepositorio;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@MicronautTest
public class ValidarMutanteTest {
    @Inject
    PersonaRepositorioInterface personaRepositorioInterface;

    @Inject
    PersonaRepositorio personaRepositorio;

    @Inject
    CacheService cacheService;

    @Inject
    @Client("/mutantchecker")
    RxHttpClient client;

    @Inject
    EmbeddedApplication<?> application;

    @MockBean(PersonaRepositorio.class)
    PersonaRepositorio personaRepositorio() {
        return spy(new PersonaRepositorio());
    }

    @MockBean(CacheService.class)
    CacheService cacheService() {
        return spy(new CacheService());
    }

    @Test
    void testItWorks() {
        Assertions.assertTrue(application.isRunning());
    }

    @Test
    void testEsMutante(){
        List<String> dna = new ArrayList<String>();
        dna.add("AGGGGA");
        dna.add("CAGTGC");
        dna.add("TGAGGT");
        dna.add("GGAAGG");
        dna.add("CCCCTA");
        dna.add("TCACTG");
        Persona persona = new Persona(
                dna,
                6,
                true
        );
        doReturn(Mono.just(true)).when(cacheService).incrementarValor(any());
        doReturn(Mono.just(persona)).when(personaRepositorio).guardar(any());
        ValidateMutanteRequestDTO validateMutanteRequestDTO = new ValidateMutanteRequestDTO(dna);
        final String result = client.toBlocking().retrieve(HttpRequest.POST("/mutant", validateMutanteRequestDTO), String.class);
        Assertions.assertEquals(
                "numero de coincidencias: 6",
                result
        );
        verify(personaRepositorio).guardar(any());
    }
    @Test
    void testNoEsMutante(){
        List<String> dna = new ArrayList<String>();
        dna.add("AGTA");
        dna.add("TTGT");
        dna.add("GATG");
        dna.add("ATGA");
        Persona persona = new Persona(
                dna,
                0,
                false
        );
        doReturn(Mono.just(true)).when(cacheService).incrementarValor(any());
        doReturn(Mono.just(persona)).when(personaRepositorio).guardar(any());
        ValidateMutanteRequestDTO validateMutanteRequestDTO = new ValidateMutanteRequestDTO(dna);
        try{
            client.toBlocking().retrieve(HttpRequest.POST("/mutant", validateMutanteRequestDTO), String.class);
        } catch (Exception e){
            Assertions.assertEquals(
                    "Forbidden",
                    e.getMessage()
            );
        }
        verify(personaRepositorio).guardar(any());
    }
}
