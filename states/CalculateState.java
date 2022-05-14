package states;

import java.util.LinkedList;
import java.util.Queue;
import src.Cell;

public class CalculateState extends BaseState {
    private Queue<Cell> orderedCells = new LinkedList<Cell>();

    @Override
    public void enter(Cell btn) {
        createTour(btn);
    }

    private void createTour(Cell btn) {
        
    }
}
