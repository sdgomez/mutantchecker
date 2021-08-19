package application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;

import java.io.Serializable;
@Introspected
public class ConsultaEstadisticasResponseDTO implements Serializable {

    @JsonProperty("count_mutant_dna")
    private Long numeroDeMutantes;
    @JsonProperty("count_human_dna")
    private Long numeroDeHumanos;
    @JsonProperty("ratio")
    private float ratio;
    public ConsultaEstadisticasResponseDTO(){}
    public ConsultaEstadisticasResponseDTO(Long numeroDeMutantes, Long numeroDeHumanos, float ratio) {
        this.numeroDeMutantes = numeroDeMutantes;
        this.numeroDeHumanos = numeroDeHumanos;
        this.ratio = ratio;
    }

    public Long getNumeroDeMutantes() {
        return numeroDeMutantes;
    }

    public void setNumeroDeMutantes(Long numeroDeMutantes) {
        this.numeroDeMutantes = numeroDeMutantes;
    }

    public Long getNumeroDeHumanos() {
        return numeroDeHumanos;
    }

    public void setNumeroDeHumanos(Long numeroDeHumanos) {
        this.numeroDeHumanos = numeroDeHumanos;
    }

    public float getRatio() {
        return ratio;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }

}
