import akka.actor.ActorRef;
import akka.actor.ActorSystem;

public class Matriz {
    private int[][] values;
    private ActorSystem Padre;

    public Matriz(int[][] values) {
        this.values = values;
    }

    public Matriz MultiplyWithActors(Matriz matriz2, ActorSystem System1) throws InterruptedException {
        //Validacion de reglas de la multiplicacion de matrices
        if (values.length == matriz2.values[0].length) {
            int[][] output = new int[values.length][matriz2.values[0].length];
            Matriz out = new Matriz(output);

            for (var i = 0; i < output.length; i++) {
                for (var j = 0; j < output[i].length; j++) {
                    //Para cada multiplicacion se crea una "Llamada" a actorMulti para calcular el valor
                    System1.actorOf(ActorMulti.props(this, matriz2, out,i,j,System1), "Multiplicador"+i+j);
                }
            }
            return out;
        } else {
            throw new IllegalArgumentException("Las matrices no se pueden multiplicar");
        }
    }
    public int[] getRow(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < values.length) {
            return values[rowIndex];
        } else {
            throw new IllegalArgumentException("Indice no válido");
        }
    }
    public int[] getColumn(int colIndex) {
        int[] output = new int[values.length];
        if (colIndex < values[0].length) {
            for (var i = 0; i < values.length; i++) {
                output[i] = values[i][colIndex];
            }
        }
        return output;
    }

    public int[][] getValues() {
        return values;
    }

    public String toString() {
        String output = "";
        for (var fila : values) {
            output += "{\t";
            for (var value : fila) {
                output += value + "\t";
            }

            output += "}\n";
        }
        return "\n" + output;
    }
}
