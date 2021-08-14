package application.servicios.helpers;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CoincidenciasOblicuasHelper {
    @Inject
    private CoincidenciasBaseHelper coincidenciasBaseHelper;

    // se encuentran coincidencias recorriendo la matriz desde la parte superior derecha hacia
    // la parte inferior izquierda
    public int obtenerCoincidenciasOblicuasInversas(char[][] matriz){
        Integer altura = matriz.length, anchura = matriz[0].length;
        int contadorCoincidencias = 0;
        for (
                Integer diagonal = 1 - anchura;
                diagonal <= altura - 1;
                diagonal += 1
        ) {
            int numeroElementosDiagonal = (diagonal > 0) ? (anchura - diagonal) : (diagonal + anchura);
            char[] vec = new char[numeroElementosDiagonal];
            for (
                    Integer vertical = Math.max(0, diagonal), horizontal = -Math.min(0, diagonal), indVecNuevo = 0;
                    vertical < altura && horizontal < anchura && numeroElementosDiagonal >= 4;
                    vertical += 1, horizontal += 1, indVecNuevo++
            ) {
                vec[indVecNuevo] = matriz[vertical][horizontal];
            }
            contadorCoincidencias += coincidenciasBaseHelper.obtenerCoincidenciasVector(vec);
        }
        return contadorCoincidencias;
    }
    // se encuentran coincidencias recorriendo la matriz desde la parte superior izquierda hacia
    // la parte inferior derecha
    public int obtenerCoincidenciasOblicuas(char[][] matriz){
        int ancho = matriz[0].length;
        int alto = matriz.length;
        // fila en la que comienza
        int contadorCoincidencias = 0;
        for (int fcomienza = 1; fcomienza <= alto; fcomienza++){
            char[] vec1 = new char[(alto - fcomienza) + 1];
            for(int i =alto - fcomienza, j = 0; i >= 0; i--, j++){
                vec1[j] = matriz[i][j];
            }
            contadorCoincidencias +=  coincidenciasBaseHelper.obtenerCoincidenciasVector(vec1);
            for(int i =alto - 1, j = fcomienza, k = 0; i >= 0 && j< ancho; i--, j++, k++){
                vec1[k] = matriz[i][j];
            }
            contadorCoincidencias +=  coincidenciasBaseHelper.obtenerCoincidenciasVector(vec1);
        }
        System.out.println(" contadorCoincidencias : " + contadorCoincidencias);
        return contadorCoincidencias;
    }
}
