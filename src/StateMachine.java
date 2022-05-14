package src;

import states.*;

public class StateMachine {
    private BaseState currentState = new ManualState();

    public void change(BaseState state) {
        currentState.exit();
        currentState = state;
        currentState.enter();
    }

    public void processBtnEvent(Cell btn) {
        currentState.processBtnEvent(btn);
    }
}
