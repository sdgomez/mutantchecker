package application.servicios;

import dominio.entidades.Estadistica;
import dominio.servicios.ServicioConsultarEstadistica;
import reactor.core.publisher.Mono;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ConsultaEstadisticasService {
    @Inject
    ServicioConsultarEstadistica servicioConsultarEstadistica;

    public Mono<Estadistica> consultarEstadisticas(){
        return servicioConsultarEstadistica.consulta();
    }
    // transformo DTOs en objetos de dominio y viceversa
    // orquesto entre la lógica de validación de mutantes
    // y la lógica de inserción en la base de datos
}
