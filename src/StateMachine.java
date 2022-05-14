package src;

import states.*;
import static src.KnightsTour.manual;

public class StateMachine {
    private BaseState currentState = manual;

    public void change(BaseState state, Cell... btn) {
        currentState.exit();
        currentState = state;

        if (btn.length != 0)
            currentState.enter(btn[0]);
        else
            currentState.enter();
    }

    public void processBtnEvent(Cell btn) {
        currentState.processBtnEvent(btn);
    }
}
