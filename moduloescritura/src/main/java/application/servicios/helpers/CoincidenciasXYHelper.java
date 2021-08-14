package application.servicios.helpers;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CoincidenciasXYHelper {
    @Inject
    private CoincidenciasBaseHelper coincidenciasBaseHelper;

    // obtiene las coincidencias de una matriz cuadrada
    public int obtenerCoincidenciasHorizontalesYVerticales(char[][] matriz){
        int ancho = matriz[0].length;
        int alto = matriz.length;
        int contadorCoincidencias = 0;
        for (int i = 0; i < ancho; i++){
            char[] vectorHorizontal = new char[ancho];
            char[] vectorVertical = new char[alto];
            for (int j = 0; j < alto; j++){
                vectorHorizontal[j] = matriz[i][j];
                vectorVertical[j] = matriz[j][i];
            }
            int coincidenciasHorizontales = coincidenciasBaseHelper.obtenerCoincidenciasVector(vectorHorizontal);
            int coincidenciasVerticales = coincidenciasBaseHelper.obtenerCoincidenciasVector(vectorVertical);
            contadorCoincidencias += coincidenciasHorizontales + coincidenciasVerticales;
        }
        return contadorCoincidencias;
    }
}
