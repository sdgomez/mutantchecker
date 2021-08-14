package application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;

import java.util.ArrayList;
import java.util.List;

@Introspected
public class ValidateMutanteRequestDTO {
    @JsonProperty("dna")
    private List<String> dna = new ArrayList<>();

    public ValidateMutanteRequestDTO(){}
    public List<String> getDna() {
        return dna;
    }
    public void setDna(List<String> dna) {
        this.dna = dna;
    }
    public ValidateMutanteRequestDTO(List<String> dna) {
        this.dna = dna;
    }
}
