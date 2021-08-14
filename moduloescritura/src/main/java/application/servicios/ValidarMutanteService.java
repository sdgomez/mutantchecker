package application.servicios;

import application.dtos.ValidateMutanteRequestDTO;
import application.servicios.helpers.ValidarMutanteHelper;
import dominio.entidades.Persona;
import dominio.servicios.ServicioGuardarPersona;
import reactor.core.publisher.Mono;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ValidarMutanteService {
    @Inject
    ValidarMutanteHelper validarMutanteHelper;

    @Inject
    ServicioGuardarPersona servicioGuardarPersona;
    // transformo DTOs en objetos de dominio y viceversa
    // orquesto entre la lógica de validación de mutantes
    // y la lógica de inserción en la base de datos
    public Mono<Persona> obtenerInfoPersona(ValidateMutanteRequestDTO validateMutanteRequestDTO){
        return Mono.just(validarMutanteHelper.obtenerInfoPersona(validateMutanteRequestDTO)) // meter dentro del pool
                .flatMap(persona ->
                        persona.flatMap(p -> servicioGuardarPersona.guardar(p))
                );
    }
}
