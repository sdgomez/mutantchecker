package application.controllers;

import application.controllers.mappers.EstadisticaMappers;
import application.dtos.ConsultaEstadisticasResponseDTO;
import application.servicios.ConsultaEstadisticasService;
import dominio.entidades.Estadistica;
import io.micronaut.http.HttpResponseFactory;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import reactor.core.publisher.Mono;

import javax.inject.Inject;

@Controller("${micronaut.context-path}/queries")
public class ConsultaEstadisticasCtrl {
    @Inject
    ConsultaEstadisticasService consultaEstadisticasService;

    @Inject
    EstadisticaMappers estadisticaMappers;

    // el controller se encarga de manejar la respuesta HTTP de la api, entre ellas manejo de errores.
    @Get("/stats")
    public Mono<MutableHttpResponse<ConsultaEstadisticasResponseDTO>> consultarEstadisticas(){
        return consultaEstadisticasService.consultarEstadisticas()
                .map(estadistica -> estadisticaMappers.estadisticaToResponseDTO(estadistica))
                .map(dto -> HttpResponseFactory.INSTANCE.<Estadistica>status(HttpStatus.OK).body(dto));
    }
    @Get("/health")
    public String health(){
        return "OK";
    }
}
