public class Main {
    public static void main(String[] args) {
        Integer[][] matriz = {
                {  0,  1,  2,  3,  4,  5 },
                {  6,  7,  8,  9, 10, 11 },
                { 12, 13, 14, 15, 16, 17 },
                { 18, 19, 20, 21, 22, 23 },
                { 24, 25, 26, 27, 28, 29 },
                { 30, 31, 32, 33, 34, 35 }
        };
        int ancho = matriz[0].length;
        int alto = matriz.length;
        // fila en la que comienza
        for (int fcomienza = 1; fcomienza <= alto; fcomienza++){
            for(int i =alto - fcomienza, j = 0; i >= 0; i--, j++){
               System.out.println(" matriz["+i+"]["+j+"] = " + matriz[i][j]);
            }
            System.out.println("----- Fin arreglo 1------");
            for(int i =alto - 1, j = fcomienza; i >= 0 && j< ancho; i--, j++){
                System.out.println(" matriz["+i+"]["+j+"] = " + matriz[i][j]);
            }
            System.out.println("----- Fin arreglo 2------");
        }
        /*System.out.println("----- Parte 2 ------");
        for (int fcomienza = 1; fcomienza <= ancho; fcomienza++){
            for(int i =alto - 1, j = fcomienza; i >= 0 && j< ancho; i--, j++){
                System.out.println(" matriz["+i+"]["+j+"] = " + matriz[i][j]);
            }
            System.out.println("----- Fin arreglo 2------");
        } */

        /*int numeroDeDiagonales = (matriz.length + matriz[0].length) - 1;
        for(int y=0; y <= matriz.length; y++ ){
            for(int i = matriz.length - 1; i >= 0 ; i--){
                //System.out.println("i = " + i + "");
                // System.out.println("i = " + i + " j = " + j);
                // System.out.println("matriz["+i+"]["+j+"] = " + matriz[i][j]);
                //System.out.println("matriz["+i+"]["+i+"] = " + matriz[i][i]);
                int j = i - y;
                if (j >= 0){
                    System.out.println("matriz["+(i-y)+"]["+i+"] = " + matriz[j][i]);
                }else{
                    System.out.println("valor de i para else: "+i);
                    System.out.println("valor de j: " + j);
                }
                // System.out.println("matriz["+i+"]["+i+"] = " + matriz[i][i]);
            }
            System.out.println("---- Finaliza diagonal --- ");
        } */
       /* for(int i = matriz.length - 1; i >= 0 ; i--){
            System.out.println("i = " + i + "");
                // System.out.println("i = " + i + " j = " + j);
                // System.out.println("matriz["+i+"]["+j+"] = " + matriz[i][j]);
            //System.out.println("matriz["+i+"]["+i+"] = " + matriz[i][i]);
            int j = i - 1;
            if (j >= 0){
                System.out.println("matriz["+(i-1)+"]["+i+"] = " + matriz[i-1][i]);
            }
            System.out.println("matriz["+i+"]["+i+"] = " + matriz[i][i]);
            System.out.println("---- Finaliza diagonal --- ");
        } */
    }
}
// 2021-06-03T08:13:50Z
// 2021-08-13T16:34:04Z