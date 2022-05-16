package states;

import javax.swing.JOptionPane;
import src.Cell;
import static src.KnightsTour.*;

public class AutoState extends BaseState {
    @Override
    public void enter() {
        resetAll();
        app.modeLabel.setText("Mode: Auto");
    }

    @Override
    public void processBtnEvent(Cell btn) {
        if (currentPos != null)
            return;
        
        Thread tour = new Thread(() -> createTour(btn));
        tour.start();
    }

    private void createTour(Cell btn) {
        for (int i = 0; i < BOARD_SIZE * BOARD_SIZE - 1; i++) {
            updateMoveOrder(btn);

            if (currentPos != null)
                resetNeighbors();

            moveKnight(btn);
            findNeighbors(btn);

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Cell destination = selectCell();
            btn = destination;
        }

        neighborCells.clear();
        moveKnight(btn);

        JOptionPane.showMessageDialog(app, "Knight Tour finished!");
    }

    @Override
    public void findFutureNeighs(Cell btn, int index) {
        cntFutureNeighs(btn, index);
    }
}
