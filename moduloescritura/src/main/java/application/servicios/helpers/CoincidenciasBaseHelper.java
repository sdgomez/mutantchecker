package application.servicios.helpers;

import javax.inject.Singleton;

@Singleton
public class CoincidenciasBaseHelper {

    // obtiene las coincidencias de un vector
    public int obtenerCoincidenciasVector(char[] vec){
        int contadorIguales = 0, contadorCoincidencias = 0;
        for (int i = 0; i < vec.length && vec.length >= 4; i++){
            char valor = vec[i];
            System.out.println("vec["+ i +"] = " + valor);
            if (i != (vec.length - 1)){
                char valorSiguiente = vec[i + 1];
                if (valor == valorSiguiente && valor != '\u0000'){
                    // System.out.println("iguales... valor: " + valor+ " valor siguiente = " + valorSiguiente);
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
