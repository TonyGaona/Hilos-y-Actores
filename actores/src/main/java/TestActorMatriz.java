import akka.actor.ActorSystem;

public class TestActorMatriz {
    public static void main(String[] args) throws InterruptedException {
        //Creacion de matrices a multiplicar
        int[][] m1 = {
                {1, 2},
                {3, 4},
                {5, 6},
        };
        Matriz matriz1 = new Matriz(m1);

        int[][] m2 = {
                {1, 2, 3},
                {3, 4, 5}
        };
        Matriz matriz2 = new Matriz(m2);

        //Creacion del actor padre
        ActorSystem System1 = ActorSystem.create("Padre");

        //Clase matriz en la cual se almacenara el resultado
        Matriz resultado = matriz1.MultiplyWithActors(matriz2,System1);

        //Impresion de la matriz resultante de la operacion
        System.out.printf("El resultado de la multiplicacion entre" +
                "\nMatriz1:%s \nMatriz2:%S \nResultado:%s",matriz1,matriz2,resultado);
        Runtime.getRuntime().exit(0);
    }
}