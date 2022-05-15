package states;

import static src.KnightsTour.*;
import javax.swing.JOptionPane;

import src.Cell;

public class ManualState extends BaseState {
    @Override
    public void enter() {
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
            
            resetButtonState();
            neighborCells.clear();
        }

        // Update the position of the knight on the chessboard and mark the cell it is on as visited
        moveKnight(btn);

        if (visitedCells.size() == BOARD_SIZE * BOARD_SIZE)
            JOptionPane.showMessageDialog(app, "Congratulations! You successfully made a tour!");

        // Proceed to find neighbor cells (valid moves)
        findNeighbors(currentPos);

        if (neighborCells.size() == 0)
            JOptionPane.showMessageDialog(app, "Game Over! You're trapped.");
    }
}
