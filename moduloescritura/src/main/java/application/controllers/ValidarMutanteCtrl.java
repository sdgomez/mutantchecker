package application.controllers;

import application.dtos.ValidateMutanteRequestDTO;
import application.servicios.ValidarMutanteService;
import dominio.entidades.Persona;
import io.micronaut.http.HttpResponseFactory;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import reactor.core.publisher.Mono;

import javax.inject.Inject;

@Controller("${micronaut.context-path}")
public class ValidarMutanteCtrl {
    @Inject
    ValidarMutanteService validarMutanteService;

    // el controller se encarga de manejar la respuesta HTTP de la api, entre ellas manejo de errores.
    @Post("/mutant")
    public Mono<MutableHttpResponse<String>> validarSiEsMutante(@Body ValidateMutanteRequestDTO validateMutanteRequestDTO){
        Mono<Persona> persona = validarMutanteService.obtenerInfoPersona(validateMutanteRequestDTO);
        return persona.map(p -> {
            if (p.getEsMutante()){
                return HttpResponseFactory.INSTANCE.<String>status(HttpStatus.OK).body("numero de coincidencias: " + p.getCantidadDeSecuenciasNitrogenadas());
            }else{
                return HttpResponseFactory.INSTANCE.<String>status(HttpStatus.FORBIDDEN);
            }
        });
    }
    @Get("/health")
    public String health(){
        return "OK";
    }
}
