package application.servicios.helpers;

import application.dtos.ValidateMutanteRequestDTO;
import dominio.entidades.Persona;
import reactor.core.publisher.Mono;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class ValidarMutanteHelper {
    @Inject
    CoincidenciasXYHelper coincidenciasXYHelper;

    @Inject
    CoincidenciasOblicuasHelper coincidenciasOblicuasHelper;

    public Mono<Persona> obtenerInfoPersona(ValidateMutanteRequestDTO validateMutanteRequestDTO){
        Mono<Integer> coincidencias = obtenerCoincidenciasEnADN(validateMutanteRequestDTO);
        return coincidencias.map(c -> new Persona(validateMutanteRequestDTO.getDna(), c, c > 1));
    }
    public Mono<ValidateMutanteRequestDTO> validarTamanioADN(ValidateMutanteRequestDTO validateMutanteRequestDTO){
        List<String> dnas = validateMutanteRequestDTO.getDna().stream()
                .map(String::toUpperCase).collect(Collectors.toList());
        if (
                dnas.size() > 0 &&
                dnas.size() == dnas.get(0).length()
        ){
            return Mono.just(validateMutanteRequestDTO);
        }else{
            return Mono.error(new Exception("Los datos Ingresados no son correctos... Verifique e intente nuevamente"));
        }
    }
    private Mono<Integer> obtenerCoincidenciasEnADN(ValidateMutanteRequestDTO validateMutanteRequestDTO){
        Mono<ValidateMutanteRequestDTO> requestValido = validarTamanioADN(validateMutanteRequestDTO);
        return requestValido.map(req -> {
            char[][] matriz = crearMatriz(validateMutanteRequestDTO);
            return obtenerCoincidenciasMatriz(matriz);
        });
    }
    // convierte el request en una matriz
    private char[][] crearMatriz(ValidateMutanteRequestDTO validateMutanteRequestDTO){
        List<String> dnas = validateMutanteRequestDTO.getDna();
        int numeroColumnas = !dnas.isEmpty() ? dnas.get(0).length() : 0;
        int numeroDeFilas = dnas.size();
        char[][] matriz = new char[numeroDeFilas][numeroColumnas];
        for (int i = 0; i < dnas.size(); i++){
            char[] chars = dnas.get(i).toCharArray();
            for (int y = 0; y < chars.length; y ++){
                matriz[i][y] = chars[y];
            }
        }
        return matriz;
    }
    private int obtenerCoincidenciasMatriz(char[][] matriz){
        return
                coincidenciasXYHelper.obtenerCoincidenciasHorizontalesYVerticales(matriz) +
                        coincidenciasOblicuasHelper.obtenerCoincidenciasOblicuas(matriz) +
                        coincidenciasOblicuasHelper.obtenerCoincidenciasOblicuasInversas(matriz);
    }
}
