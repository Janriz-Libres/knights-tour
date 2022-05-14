package src;

import javax.swing.*;
import java.util.*;
import static src.KnightsTourGUI.*;

public class KnightsTour {
    // Set constant attributes
    public static final int BOARD_SIZE = 8;
    static final int KNIGHT_MOVES = 8;

    // Holds a reference to the GUI frame or window
    public static KnightsTourGUI app;

    // Stores the knight's movements relative to its origin
    static int moveOffsets[][] = {
        {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}
    };

    // Stores the knight's valid moves
    public static List<Cell> neighborCells = new ArrayList<Cell>();

    // Keeps track of visited cells
    public static List<Cell> visitedCells = new ArrayList<Cell>();

    // Keeps track of the knight's current position on the chessboard
    public static Cell currentPos;

    static StateMachine sm = new StateMachine();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            app = new KnightsTourGUI();
        });
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
                Cell btn = new Cell(String.valueOf(col) + String.valueOf(row));

                // Adds a mouseListener that will trigger if the user clicks on any of the cells
                btn.addActionListener(e -> sm.processBtnEvent((Cell) e.getSource()));

                // Decorates the button accordingly
                designBtn(btn);

                // Store a reference of the cell and add it as component
                frame.cellArray[col][row] = btn;
                frame.chessBoard.add(btn);
            }
        }
    }

    /**
     * Moves the knight to the cell/button provided as argument
     * 
     * @param btn the cell to be occupied by the knight
     */
    static public void moveKnight(Cell btn) {
        designCurrentCell(btn);
        btn.setVisited(true);
        visitedCells.add(btn);
        
        // Keep track of the knight's new position
        currentPos = btn;
    }

    /**
     * Resets the decorative states of neighbor cells
     */
    static public void resetButtonState() {
        for (int i = 0; i < neighborCells.size(); i++) {
            Cell button = neighborCells.get(i);
            button.setBackground(new java.awt.Color(229,223,214));
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
                Cell button = app.cellArray[tempRow][tempCol];

                // If button is not visited, add it to the list of neighbors and set its color
                if (!button.isVisited()) {
                    neighborCells.add(button);
                    designNeighbor(button);
                }
            }
        }
    }
}