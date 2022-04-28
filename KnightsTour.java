import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class KnightsTour {
    static final int BOARD_SIZE = 8;
    static final int KNIGHT_MOVES = 8;

    // Holds all references of each cell/square on the chessboard
    static Cell cellArray[][] = new Cell[BOARD_SIZE][BOARD_SIZE];

    // Stores the knight's movements relative to its origin
    static int moveOffsets[][] = {
        {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}
    };

    // Stores the knight's valid moves
    static List<Cell> neighborCells = new ArrayList<Cell>();

    // Keeps track of the knight's current position on the chessboard
    static Cell currentPos;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new KnightsTourGUI());
    }

    static public void beginKnightsTour(JButton btn) {
        // TODO
    }

    /**
     * Populate the chessboard with cells/squares
     * 
     * @param frame a reference to the instance variable JFrame
     */
    static public void generateButtons(KnightsTourGUI frame) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                // Instantiates a new cell and stores its location via constructor
                Cell btn = new Cell(String.valueOf(row) + String.valueOf(col));

                // Adds a mouseListener that will trigger if the user clicks on any of the cells
                btn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        Cell button = (Cell) e.getComponent();
                        processBtnEvent(button);
                    }
                });

                // Decorates the button accordingly
                frame.applyButtonDesign(btn);

                // Store a reference of the cell and add it as component
                cellArray[row][col] = btn;
                frame.buttonPanel.add(btn);
            }
        }
    }

    /**
     * Provides the logic for determining if the cell is a valid move/neighbor or not
     * 
     * @param btn the cell or button that the user has clicked on
     */
    static public void processBtnEvent(Cell btn) {
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
        btn.setBackground(Color.RED);
        btn.setVisited(true);
        
        // Keep track of the knight's new position and proceed to find neighbor cells (valid moves)
        currentPos = btn;
        findNeighbors(currentPos);
    }

    /**
     * Resets the decorative states of neighbor cells
     */
    static public void resetButtonState() {
        for (int i = 0; i < neighborCells.size(); i++) {
            Cell button = neighborCells.get(i);
            button.setBackground(null);
        }

        neighborCells.clear();
    }

    /**
     * Finds valid moves/neighbors for the knight
     * 
     * @param btn cell where the knight is currently located
     */
    static public void findNeighbors(Cell btn) {
        // Get the location of the current cell
        String pos = btn.locate();
        System.out.println("Evaluating Position: " + pos);

        // Checks for valid neighbors
        for (int i = 0; i < KNIGHT_MOVES; i++) {
            System.out.println("Solving position " + i + " neighbour...");

            // Get the corresponding row and column of current cell
            String parts[] = pos.split("");
            
            System.out.println("String index 0:" + parts[0]);
            System.out.println("String index 1:" + parts[1]);

            // Calculate neighbor's presumed position on the board
            int tempRow = Integer.parseInt(parts[0]) + moveOffsets[i][0];
            int tempCol = Integer.parseInt(parts[1]) + moveOffsets[i][1];

            // Verify the neighbor's position by checking if it's on the board
            if (tempRow >= 0 & tempRow < BOARD_SIZE & tempCol >= 0 & tempCol < BOARD_SIZE) {
                Cell button = cellArray[tempRow][tempCol];

                // If button is not visited, add it to the list of neighbors and set its color
                if (!button.isVisited()) {
                    neighborCells.add(button);
                    button.setBackground(Color.GREEN);
                }
            }
        }
    }
}