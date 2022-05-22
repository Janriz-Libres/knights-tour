package src;

import states.BaseState;
import static src.KnightsTour.auto;

public class StateMachine {
    // Keeps track of the current state of the program. The default is Auto.
    private BaseState currentState = auto;

    /**
     * Changes the state of the program
     * 
     * @param state the program's next state 
     */
    void change(BaseState state) {
        currentState.exit();

        // If the previous state is running a thread, wait for the thread to finish before proceeding
        try {
            if (auto.tour != null)
                auto.tour.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        currentState = state;
        currentState.enter();
    }

    /**
     * Implements an event for a button click
     * 
     * @param btn the clicked button
     */
    void processBtnEvent(Cell btn) {
        currentState.processBtnEvent(btn);
    }

    /**
     * Attempts to find neighbors of a knight's valid neighbor cell
     * 
     * @param btn the neighbor cell
     * @param index the neighbor cell's index in the <code>neighborCells</code> arraylist
     */
    void findFutureNeighs(Cell btn, int index) {
        currentState.findFutureNeighs(btn, index);
    }
}
