package dominio.entidades;

import java.util.List;

public class Persona {
    private List<String> dna;
    private int cantidadDeSecuenciasNitrogenadas;
    private Boolean esMutante;
    public Persona(){}
    public Persona(List<String> dna, int cantidadDeSecuenciasNitrogenadas, Boolean esMutante) {
        this.dna = dna;
        this.cantidadDeSecuenciasNitrogenadas = cantidadDeSecuenciasNitrogenadas;
        this.esMutante = esMutante;
    }
    public List<String> getDna() {
        return dna;
    }
    public void setDna(List<String> dna) {
        this.dna = dna;
    }
    public int getCantidadDeSecuenciasNitrogenadas() {
        return cantidadDeSecuenciasNitrogenadas;
    }
    public void setCantidadDeSecuenciasNitrogenadas(int cantidadDeSecuenciasNitrogenadas) {
        this.cantidadDeSecuenciasNitrogenadas = cantidadDeSecuenciasNitrogenadas;
    }
    public Boolean getEsMutante() {
        return esMutante;
    }
    public void setEsMutante(Boolean esMutante) {
        this.esMutante = esMutante;
    }
}
