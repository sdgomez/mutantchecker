package controllers;

import io.micronaut.http.HttpResponseFactory;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;

import java.util.List;

@Controller("/")
public class MutanteCtrl {
    @Post("/mutant")
    public MutableHttpResponse<String> addSale(@Body ValidateMutanteRequestDTO validateMutanteRequestDTO){
        char[][] matriz = crearMatriz(validateMutanteRequestDTO);
        int coincidencias = obtenerCoincidenciasMatriz(matriz);

        if (coincidencias > 1){
            return HttpResponseFactory.INSTANCE.<String>status(HttpStatus.OK).body("numero de coincidencias: " + coincidencias);
        }else{
            return HttpResponseFactory.INSTANCE.<String>status(HttpStatus.FORBIDDEN);
        }
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
               // System.out.println("["+i+"]["+y+"] = " + chars[y]);
            }
        }
        return matriz;
    }
    // se encuentran coincidencias recorriendo la matriz desde la parte superior derecha hacia
    // la parte inferior izquierda
    private int obtenerCoincidenciasOblicuasInversas(char[][] matriz){
        Integer altura = matriz.length, anchura = matriz[0].length;
        int contadorCoincidencias = 0;
        for (
            // Recorre los inicios de cada diagonal en los bordes de la matriz.
                Integer diagonal = 1 - anchura; // Comienza con un número negativo.
                diagonal <= altura - 1; // Mientras no llegue a la última diagonal.
                diagonal += 1 // Avanza hasta el comienzo de la siguiente diagonal.
        ) {
            int numeroElementosDiagonal = (diagonal > 0) ? (anchura - diagonal) : (diagonal + anchura);
            char[] vec = new char[numeroElementosDiagonal];
            for (
                // Recorre cada una de las diagonales a partir del extremo superior izquierdo.
                    Integer vertical = Math.max(0, diagonal), horizontal = -Math.min(0, diagonal), indVecNuevo = 0;
                    vertical < altura && horizontal < anchura && numeroElementosDiagonal >= 4; // Mientras no excedan los límites.
                    vertical += 1, horizontal += 1, indVecNuevo++ // Avanza en diagonal incrementando ambos ejes.
            ) {
                vec[indVecNuevo] = matriz[vertical][horizontal];
                // Muestra cada punto de la matriz ordenadamente.
                // System.out.println("matriz["+vertical+"]["+horizontal+"] = " + matriz[vertical][horizontal]);
               // System.out.println("vec["+indVecNuevo+"] = " + vec[indVecNuevo]);
            }
            //System.out.println("----- fin vector oblicuo ------");
            contadorCoincidencias += obtenerCoincidenciasVector(vec);
        }
        return contadorCoincidencias;
    }
    // se encuentran coincidencias recorriendo la matriz desde la parte superior izquierda hacia
    // la parte inferior derecha
    private int obtenerCoincidenciasOblicuas(char[][] matriz){
        int ancho = matriz[0].length;
        int alto = matriz.length;
        // fila en la que comienza
        int contadorCoincidencias = 0;
        for (int fcomienza = 1; fcomienza <= alto; fcomienza++){
            char[] vec1 = new char[(alto - fcomienza) + 1];
            char[] vec2 = new char[(alto - fcomienza) + 1];
            for(int i =alto - fcomienza, j = 0; i >= 0; i--, j++){
              //  System.out.println(" matriz["+i+"]["+j+"] = " + matriz[i][j]);
                vec1[j] = matriz[i][j];
            }
            System.out.println("----- Fin arreglo 1------");
            for(int i =alto - 1, j = fcomienza, k = 0; i >= 0 && j< ancho; i--, j++, k++){
            //    System.out.println(" matriz["+i+"]["+j+"] = " + matriz[i][j]);
                vec2[k] = matriz[i][j];
            }
            contadorCoincidencias +=  obtenerCoincidenciasVector(vec1) + obtenerCoincidenciasVector(vec2);
           // System.out.println("----- Fin arreglo 2------");
        }
        System.out.println(" contadorCoincidencias : " + contadorCoincidencias);
        return contadorCoincidencias;
    }
    // obtiene las coincidencias de una matriz cuadrada
    private int obtenerCoincidenciasMatriz(char[][] matriz){
        int ancho = matriz[0].length;
        int alto = matriz.length;
        int contadorCoincidencias = 0;
        for (int i = 0; i < ancho; i++){
            char[] vectorHorizontal = new char[ancho];
            char[] vectorVertical = new char[alto];
            for (int j = 0; j < alto; j++){
                vectorHorizontal[j] = matriz[i][j];
                vectorVertical[j] = matriz[j][i];
                // System.out.println("matriz horizontal["+i+"]["+j+"] = " + matriz[i][j]);
                // System.out.println("matriz vertical["+j+"]["+i+"] = " + matriz[j][i]);
            }
            int coincidenciasHorizontales = obtenerCoincidenciasVector(vectorHorizontal);
            int coincidenciasVerticales = obtenerCoincidenciasVector(vectorVertical);
            contadorCoincidencias = contadorCoincidencias + coincidenciasHorizontales + coincidenciasVerticales;
        }
        return contadorCoincidencias + obtenerCoincidenciasOblicuasInversas(matriz) + obtenerCoincidenciasOblicuas(matriz);
    }
    // obtiene las coincidencias de un vector
    private int obtenerCoincidenciasVector(char[] vec){
        int contadorIguales = 0, contadorCoincidencias = 0;
        for (int i = 0; i < vec.length; i++){
            char valor = vec[i];
            System.out.println("vec["+ i +"] = " + valor);
            if (i != (vec.length - 1)){
                char valorSiguiente = vec[i + 1];
                if (valor == valorSiguiente && valor != '\u0000'){
                    System.out.println("iguales... valor: " + valor+ " valor siguiente = " + valorSiguiente);
                    contadorIguales++;
                    if (contadorIguales == 3){
                        System.out.println("----- coincidencia encontrada ------");
                        contadorCoincidencias++;
                    }
                }else{
                    contadorIguales = 0;
                }
            }
        }
        System.out.println("----- fin vector ------");
        return contadorCoincidencias;
    }
}
