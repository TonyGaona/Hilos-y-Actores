import akka.actor.AbstractActor;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class ActorMulti extends AbstractActor{
    private Matriz m1;
    private Matriz m2;
    private Matriz output;
    private int rowIndex;
    private int colIndex;
    private ActorSystem System1;

    public ActorMulti ( Matriz m1, Matriz m2, Matriz output, int rowIndex, int colIndex, ActorSystem System1) {
        this.m1 = m1;
        this.m2 = m2;
        this.output = output;
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
        this.System1 = System1;
    }

    @Override
    public AbstractActor.Receive createReceive() {
        for (var k = 0; k < output.getValues().length; k++) {
            for (var m = 0; m < output.getValues()[k].length; m++) {
                output.getValues()[rowIndex][colIndex] = calcValue(m1.getRow(rowIndex), m2.getColumn(colIndex));
            }
        }
        return receiveBuilder().build();

    }

    private int calcValue(int[] row, int[] col) {
        int element = 0;
        for (var i = 0; i < row.length; i++) {
            element += row[i] * col[i];
        }
        return element;
    }

    public static Props props(Matriz m1, Matriz m2, Matriz output, int rowIndex, int colIndex, ActorSystem Padre) {
        return Props.create(ActorMulti.class, () -> new ActorMulti(m1, m2, output, rowIndex, colIndex, Padre));
    }
}
