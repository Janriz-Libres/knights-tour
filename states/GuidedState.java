package states;

import static src.KnightsTour.moveSet;
import static src.KnightsTour.currentPos;
import static src.KnightsTour.app;
import static src.KnightsTour.resetAll;
import static src.KnightsTour.updateMoveOrder;
import static src.KnightsTour.resetNeighbors;
import static src.KnightsTour.moveKnight;
import static src.KnightsTour.findNeighbors;
import static src.KnightsTour.selectCell;
import static src.KnightsTour.cntFutureNeighs;
import static src.KnightsTourGUI.designSelectedCell;

import src.Cell;
import javax.swing.JOptionPane;

public class GuidedState extends BaseState {
    private Cell destination;

    @Override
    public void enter() {
        resetAll();
        app.modeLabel.setText("Mode: Guided");
    }

    @Override
    public void processBtnEvent(Cell btn) {
        if (currentPos != null) {
            if (!btn.equals(destination))
                return;

            resetNeighbors();
        }

        moveKnight(btn);
        findNeighbors(btn);
        updateMoveOrder(btn);
        
        destination = selectCell();
        
        if (destination != null)
            designSelectedCell(destination);
        else
            JOptionPane.showMessageDialog(app, "Guided Tour Complete!");
    }

    @Override
    public void findFutureNeighs(Cell btn, int index) {
        cntFutureNeighs(btn, index);
    }

    @Override
    public void exit() {
        destination = null;
        moveSet = 0;
    }
}
