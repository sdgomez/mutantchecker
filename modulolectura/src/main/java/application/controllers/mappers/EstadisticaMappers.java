package application.controllers.mappers;

import application.dtos.ConsultaEstadisticasResponseDTO;
import dominio.entidades.Estadistica;

import javax.inject.Singleton;

@Singleton
public class EstadisticaMappers {
    public ConsultaEstadisticasResponseDTO estadisticaToResponseDTO(Estadistica estadistica){
        return new ConsultaEstadisticasResponseDTO(
                estadistica.getNumeroDeMutantes(),
                estadistica.getNumeroDeHumanos(),
                estadistica.getRatio()
        );
    }
}
