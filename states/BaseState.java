package states;

import src.Cell;

public abstract class BaseState {
    public void enter(Cell btn) {};
    public void enter() {};
    public void exit() {};

    /**
     * Provides the logic for determining if the cell is a valid move/neighbor or not
     * 
     * @param btn the cell or button that the user has clicked on
     */
    public void processBtnEvent(Cell btn) {};
}
