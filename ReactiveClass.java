package StatechartSkeleton;

public class ReactiveClass {
    protected String name;
    protected State activeState;
    protected State defaultState = null;

    public void setActiveState(State activeState) {
      this.activeState = activeState;
      this.activeState.setActive();
    }
    public State addState(String name){
        return new State(name, this);
    }
    public void setDefaultState(State defaultState){
        this.defaultState=defaultState;
    }
    public void startBehaviour(){
        assert defaultState != null : "Default state is null";
        setActiveState(defaultState);
    }
    public void react(String eventName){
      for (Transition transition activeState.transitions){
        if (transition.isTriggered()){
          transition.executeOperation();
          transition.setActive();
          break;
        }
      }
    }

    public String getName() {
        return name;
    }
}
