package states;

import static src.KnightsTour.currentPos;
import static src.KnightsTour.app;
import static src.KnightsTour.updateMoveOrder;
import static src.KnightsTour.resetNeighbors;
import static src.KnightsTour.moveKnight;
import static src.KnightsTour.findNeighbors;
import static src.KnightsTour.selectCell;
import static src.KnightsTour.cntFutureNeighs;
import static src.KnightsTour.showResetBtn;
import static src.KnightsTourGUI.designSelectedCell;

import src.Cell;
import javax.swing.JOptionPane;

public class GuidedState extends BaseState {
    // Stores the cell that the knight should occupy next in tour progressions
    private Cell destination;

    @Override
    public void enter() {
        app.modeLabel.setText("Mode: Guided");
        app.guidedBtn.setVisible(false);
    }

    @Override
    public void processBtnEvent(Cell btn) {
        if (currentPos != null) {
            if (!btn.equals(destination))
                return;

            resetNeighbors();
        } else {
            showResetBtn();
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
    public void reset() {
        destination = null;
    }

    @Override
    public void exit() {
        destination = null;
        app.guidedBtn.setVisible(true);
    }
}
