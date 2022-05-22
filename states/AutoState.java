package states;

import static src.KnightsTour.BOARD_SIZE;
import static src.KnightsTour.neighborCells;
import static src.KnightsTour.app;
import static src.KnightsTour.currentPos;
import static src.KnightsTour.moveSet;
import static src.KnightsTour.resetAll;
import static src.KnightsTour.resetNeighbors;
import static src.KnightsTour.moveKnight;
import static src.KnightsTour.findNeighbors;
import static src.KnightsTour.selectCell;
import static src.KnightsTour.cntFutureNeighs;
import static src.KnightsTour.updateMoveOrder;

import src.Cell;
import javax.swing.JOptionPane;

public class AutoState extends BaseState {
    // The thread responsible for finding a knight's tour
    public Thread tour;

    // Keeps track of whether or not the thread should be terminated/stopped
    private boolean terminate = false;

    @Override
    public void enter() {
        resetAll();
        app.modeLabel.setText("Mode: Auto");
        terminate = false;
    }

    @Override
    public void processBtnEvent(Cell btn) {
        if (currentPos != null)
            return;
        
        tour = new Thread(() -> createTour(btn));
        tour.start();
    }

    /**
     * Creates a full knight's tour on the chessboard according to cell clicked by the user as the
     * starting position
     * 
     * @param btn the starting position/cell
     */
    private void createTour(Cell btn) {
        for (int i = 0; i < BOARD_SIZE * BOARD_SIZE - 1; i++) {
            updateMoveOrder(btn);

            if (currentPos != null)
                resetNeighbors();

            moveKnight(btn);
            findNeighbors(btn);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Cell destination = selectCell();
            btn = destination;

            if (terminate)
                return;
        }

        neighborCells.clear();
        moveKnight(btn);

        JOptionPane.showMessageDialog(app, "Knight's Tour Complete!");
    }

    @Override
    public void findFutureNeighs(Cell btn, int index) {
        cntFutureNeighs(btn, index);
    }

    @Override
    public void exit() {
        terminate = true;
        moveSet = 0;
    }
}
