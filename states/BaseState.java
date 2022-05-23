package states;

import src.Cell;

public abstract class BaseState {
    /**
     * Executes at the beginning of a state
     */
    public void enter() {};

    /**
     * Executes when the state ends
     */
    public void exit() {};

    /**
     * All code and logic that will be executed if a button is clicked while in a certain state must be
     * written within this function
     * 
     * @param btn the button that was clicked by the user
     */
    public void processBtnEvent(Cell btn) {};

    /**
     * Finds neighbors of a knight's valid neighbor cell. All states that attempt to create knight's tours
     * must implement this function
     * 
     * @param btn the neighbor cell
     * @param index the index that corresponds to the neighbor cell
     */
    public void findFutureNeighs(Cell btn, int index) {};

    /**
     * Resets the chessboard back to its original state
     */
    public void reset() {};
}
