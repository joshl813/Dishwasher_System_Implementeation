package StatechartSkeleton;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BooleanSupplier;

public class State {
    public static final String TIMEOUT_EVENT_NAME = "TIMEOUT";
    protected ReactiveClass callback;
    protected String name;
    protected boolean isActive = false;
    protected List<Transition> transitions = new LinkedList<>();
    protected long timeout = 0;

    /**
     *
     * @param event the string defining the event
     * @return  whether a transition was made
     */
    public boolean operation(String event){
      for (Transition transition: transitions){
          if(transition.trigger.isTriggered(event)){
              if (event.equals(TIMEOUT_EVENT_NAME)){
                System.out.println(callback.getName()+" received a timout! ("+timeout+")");
              }
          t.executeOperation();
          return true;
      }
    }
        return false;
    }


    // TODO: Add methods for adding transitions/timeouts that do not have a guard/action
    public void addTransition(String event,  State destination){
        addTransition(event, () -> true, () ->{}, destination);
    }
    public void addTimeout(long timeout, BooleanSupplier predicate, Runnable runnable,  State destination) {
        if (this.timeout != 0){
            assert this.timeout == timeout : "Cannot set two different timeouts to the same state!";
        }
        addTransition(TIMEOUT_EVENT_NAME,predicate,runnable,destination);
    }

    public void addTransition(String event, BooleanSupplier predicate, Runnable runnable, State destination){
        assert !event.equals(TIMEOUT_EVENT_NAME): "Timeouts are set by the setTimeOut method";
        addTransitionSafe(event, predicate, runnable, destination);
    }

    private void addTransitionSafe(String event, BooleanSupplier predicate, Runnable runnable, State destination){
        transitions.add(new Transition(event,predicate,runnable,destination));
    }

    public void setActive(){
        isActive = true;
        System.out.println(callback.getName()+" moved to State "+name);
        if (timeout>0){
            Thread newThread = new Thread(() -> {
                try {
                  Timer timer = new Timer(TIMEOUT_EVENT_NAME);
                  TimerTask timerTask = new TimerTask() {
                    public void run(){
                      operation(TIMEOUT_EVENT_NAME);
                      }
                    };
              timer.schedule(timerTask, timeout);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            newThread.start();
        }
    }
    public State(String name, ReactiveClass callback){
        this.name=name;
        this.callback=callback;

    }
}
