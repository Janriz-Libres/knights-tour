package states;

import javax.swing.JOptionPane;
import src.Cell;
import static src.KnightsTour.*;

public class ManualState extends BaseState {
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
        }

        // Update the position of the knight on the chessboard and mark the cell it is on as visited
        moveKnight(btn);

        if (visitedCells.size() == BOARD_SIZE * BOARD_SIZE) {
            JOptionPane.showMessageDialog(app, "Congratulations! You successfully made a tour!");
            return;
        }

        // Proceed to find neighbor cells (valid moves)
        findNeighbors(currentPos);
    }
}
