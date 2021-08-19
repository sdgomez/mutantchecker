package consultaEstadisticas;

import application.dtos.ConsultaEstadisticasResponseDTO;
import dominio.entidades.Estadistica;
import dominio.servicios.ServicioConsultarEstadistica;
import infraestructura.cache.CacheService;
import infraestructura.persistencia.mongo.EstadisticaRepositorio;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import javax.inject.Inject;

import static org.mockito.Mockito.*;

@MicronautTest
public class ConsultaEstadisticaTest {

    @Inject
    EstadisticaRepositorio estadisticaRepositorio;

    @Inject
    CacheService cacheService;

    @Inject
    @Client("/mutantchecker")
    RxHttpClient client;

    @Inject
    EmbeddedApplication<?> application;

    @Inject
    ServicioConsultarEstadistica servicioConsultarEstadistica;

    @MockBean(EstadisticaRepositorio.class)
    EstadisticaRepositorio estadisticaRepositorio() {
        return spy(new EstadisticaRepositorio());
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
    void testHealth(){
        final String result = client.toBlocking().retrieve(HttpRequest.GET("/queries/health"), String.class);
        Assertions.assertEquals(
                "OK",
                result
        );
    }

    @Test
    void testEstadistica(){
        Estadistica estadistica = new Estadistica(40L,100L, 40/100);
        doReturn(Mono.just(estadistica)).when(estadisticaRepositorio).consulta();
        final ConsultaEstadisticasResponseDTO result = client.toBlocking().retrieve(HttpRequest.GET("/queries/stats"), ConsultaEstadisticasResponseDTO.class);
        Assertions.assertEquals(
                40L,
                result.getNumeroDeMutantes()
        );
    }
    @Test
    void testServicioConsultarEstadistica(){
        Estadistica estadistica = new Estadistica(50L,30L, 50/30);
        doReturn(Mono.just(estadistica)).when(cacheService).buscarEstadisticaEnCache();
        Estadistica estadisticaResult = servicioConsultarEstadistica.buscarEstadistica().block();
        Assertions.assertEquals(
                30L,
                estadisticaResult.getNumeroDeHumanos()
        );
    }
}
