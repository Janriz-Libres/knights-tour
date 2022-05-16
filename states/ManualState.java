package states;

import static src.KnightsTour.BOARD_SIZE;
import static src.KnightsTour.neighborCells;
import static src.KnightsTour.app;
import static src.KnightsTour.currentPos;
import static src.KnightsTour.visitedCells;
import static src.KnightsTour.resetAll;
import static src.KnightsTour.resetNeighbors;
import static src.KnightsTour.moveKnight;
import static src.KnightsTour.findNeighbors;

import src.Cell;
import javax.swing.JOptionPane;

public class ManualState extends BaseState {
    @Override
    public void enter() {
        resetAll();
        app.modeLabel.setText("Mode: Manual");
    }

    @Override
    public void processBtnEvent(Cell btn) {
        if (btn.isVisited())
            return;

        // Will only execute if it is not the first time that the user has picked a cell
        if (currentPos != null) {
            // If the cell is not a valid move then exit out of the function
            if (!neighborCells.contains(btn))
                return;
            
            resetNeighbors();
        }

        // Update the position of the knight on the chessboard and mark the cell it is on as visited
        moveKnight(btn);

        if (visitedCells.size() == BOARD_SIZE * BOARD_SIZE) {
            JOptionPane.showMessageDialog(app, "Congratulations! You successfully made a tour!");
            return;
        }

        // Proceed to find neighbor cells (valid moves)
        findNeighbors(currentPos);

        if (noNeighbors())
            JOptionPane.showMessageDialog(app, "Game Over! You're trapped.");
    }

    private boolean noNeighbors() {
        for (int i = 0; i < neighborCells.size(); i++) {
            if (neighborCells.get(i) != null)
                return false;
        }

        return true;
    }
}
