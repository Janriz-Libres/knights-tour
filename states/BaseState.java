package states;

import src.Cell;

public abstract class BaseState {
    public void enter() {};
    public void exit() {};
    public void processBtnEvent(Cell btn) {};
    public void findFutureNeighs(Cell btn, int index) {};
}
