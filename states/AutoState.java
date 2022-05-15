package states;

import src.Cell;

import static src.KnightsTour.sm;
import static src.KnightsTour.tour;
import static src.KnightsTour.app;

public class AutoState extends BaseState {
    @Override
    public void enter() {
        app.modeLabel.setText("Mode: Auto");
    }

    @Override
    public void processBtnEvent(Cell btn) {
        sm.change(tour, btn);
    }
}
