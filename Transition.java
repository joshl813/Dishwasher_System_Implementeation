package StatechartSkeleton;

import java.util.function.BooleanSupplier;

public class Transition {
    String trigger;
    BooleanSupplier predicate;
    Runnable operation;
    private State destination;

    public Transition(String trigger, BooleanSupplier predicate, Runnable operation, State destination){
        this.trigger = trigger;
        this.predicate = predicate;
        this.operation = operation;
        this.destination = destination;
    }
    public State getDestination() {
        return destination;
    }
    public boolean isTriggered(String event){
        if(trigger==event){
          return true;
        }
        return false;
    }
    public void executeOperation(){
        operation.run();
    }
}
