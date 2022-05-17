package src;

import states.BaseState;
import static src.KnightsTour.auto;

public class StateMachine {
    private BaseState currentState = auto;

    void change(BaseState state) {
        currentState.exit();

        try {
            if (auto.tour != null)
                auto.tour.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        currentState = state;
        currentState.enter();
    }

    void processBtnEvent(Cell btn) {
        currentState.processBtnEvent(btn);
    }

    void findFutureNeighs(Cell btn, int index) {
        currentState.findFutureNeighs(btn, index);
    }
}
