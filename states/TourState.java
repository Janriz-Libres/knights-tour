package states;

import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JOptionPane;

import src.Cell;

import static src.KnightsTour.*;

public class TourState extends BaseState {
    private Queue<Cell> orderedCells = new LinkedList<Cell>();

    @Override
    public void enter(Cell btn) {
        createTour(btn);
    }

    private void createTour(Cell btn) {
        for (int i = 0; i < BOARD_SIZE * BOARD_SIZE; i++) {
            orderedCells.add(btn);

            if (currentPos != null)
                neighborCells.clear();

            findNeighbors(btn);

            Cell destination = selectCell();
            btn = destination;
        }

        JOptionPane.showMessageDialog(app, "Knight Tour finished!");
    }

    @Override
    public void findFutureNeighs(Cell btn, int index) {
        findFutureNeighs(btn, index);
    }
}
