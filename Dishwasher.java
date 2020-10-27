package StatechartSkeleton;

public class Dishwasher extends ReactiveClass {
    int cycles = 0;
    Drier drier;
    Tank tank;
    Jet jet;
    String name;
    State activeState;
    State defaultState = null;
    // TODO: Add attributes
    private int dryTime = 2000;
    public Dishwasher(){
        // TODO: Add states and transitions/timeouts.
        //       Initialize subsystems (tank, jets, drier) using the abstract factory.
        name = "Dishwasher";
        State doorOpen = addState("doorOpen");
        State doorClosed = addState("doorClosed");

        State operational =addState("operational");
        State faulty = addState("faulty");
        faulty.addTransition("evService",operational);


        State off = addState("off");
        State drying = addState("drying");

        drying.addTimeout(dryTime, ()->cycles<2, ()-> {drier.react("evOff"); cycles++; }, off);
        off.addTransition("evOpen", doorOpen);
        setDefaultState(off);
    }
}
