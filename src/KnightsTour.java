package src;

import javax.swing.SwingUtilities;
import java.util.List;
import java.util.ArrayList;

import states.AutoState;
import states.GuidedState;
import states.ManualState;

import static src.KnightsTourGUI.*;

public class KnightsTour {
    // Set constant attributes
    public static final int BOARD_SIZE = 8;
    public static final int KNIGHT_MOVES = 8;

    // Holds a reference to the GUI frame or window
    public static KnightsTourGUI app;

    // Stores the knight's movements relative to its origin
    private static int moveOffsets[][] = {
        {1, -2}, {2, -1}, {2, 1}, {1, 2}, {-1, 2}, {-2, 1}, {-2, -1}, {-1, -2}
    };

    // Move orders for 0 mod 8
    private static int moveOrders[][] = {
        {3, 4, 2, 6, 1, 5, 7, 8},
        {8, 7, 6, 4, 2, 1, 3, 5},
        {5, 1, 8, 6, 7, 3, 4, 2},
        {5, 1, 3, 4, 2, 6, 7, 8},
        {2, 1, 4, 3, 5, 6, 7, 8}
    };

    public static int moveSet = 0;

    // Stores the knight's valid moves
    public static List<Cell> neighborCells = new ArrayList<Cell>();

    // Keeps track of visited cells
    public static List<Cell> visitedCells = new ArrayList<Cell>();

    public static int[] futureNeighbors = new int[KNIGHT_MOVES];

    // Keeps track of the knight's current position on the chessboard
    public static Cell currentPos;

    // Define program states
    static ManualState manual = new ManualState();
    static AutoState auto = new AutoState();
    static GuidedState guided = new GuidedState();

    // A StateMachine class that handles the program's state transitions and behavior
    static StateMachine sm = new StateMachine();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            app = new KnightsTourGUI();
        });
    }

    /**
     * Populate the chessboard with cells/squares
     * 
     * @param frame a reference to the instance variable JFrame
     */
    static void generateButtons(KnightsTourGUI frame) {
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
    public static void moveKnight(Cell btn) {
        designCurrentCell(btn);
        btn.setVisited(true);
        visitedCells.add(btn);
        
        // Keep track of the knight's new position
        currentPos = btn;
    }

    public static void resetAll() {
        currentPos = null;
        resetNeighbors();
        resetVisited();
    }

    private static void resetVisited() {
        for (int i = 0; i < visitedCells.size(); i++) {
            Cell btn = visitedCells.get(i);
            designBtn(btn);
            btn.setVisited(false);
        }

        visitedCells.clear();
    }

    /**
     * Resets the decorative states of neighbor cells
     */
    public static void resetNeighbors() {
        for (int i = 0; i < neighborCells.size(); i++) {
            Cell button = neighborCells.get(i);
            
            if (button != null)
                designBtn(button);
        }

        neighborCells.clear();
    }

    public static void updateMoveOrder(Cell btn) {
        String pos = btn.locate();

        if (pos.equals("76") && moveSet == 0)
            moveSet = 1;
        else if (pos.equals("22") && moveSet == 1)
            moveSet = 2;
        else if (pos.equals("01") && moveSet == 2)
            moveSet = 3;
        else if (pos.equals("75") && moveSet == 3)
            moveSet = 4;
    }

    /**
     * Finds valid moves/neighbors for the knight
     * 
     * @param btn cell where the knight is currently located
     */
    public static void findNeighbors(Cell btn) {
        // Get the location of the current cell
        String pos = btn.locate();

        // Checks for valid neighbors
        for (int i = 0; i < KNIGHT_MOVES; i++) {
            // Get the corresponding row and column of current cell
            String parts[] = pos.split("");

            // Calculate neighbor's presumed position on the board
            int tempCol = Integer.parseInt(parts[0]) + moveOffsets[i][0];
            int tempRow = Integer.parseInt(parts[1]) + moveOffsets[i][1];

            // Verify the neighbor's position by checking if it's on the board
            if (isOnBoard(tempCol, tempRow)) {
                Cell button = app.cellArray[tempCol][tempRow];

                // If button is not visited, add it to the list of neighbors and set its color
                if (!button.isVisited()) {
                    neighborCells.add(button);
                    sm.findFutureNeighs(button, i);
                    designNeighbor(button);
                    continue;
                }
            }

            neighborCells.add(null);
        }
    }

    public static void cntFutureNeighs(Cell btn, int index) {
        String pos = btn.locate();
        int neighborCnt = 0;

        for (int i = 0; i < KNIGHT_MOVES; i++) {
            String parts[] = pos.split("");

            int tempCol = Integer.parseInt(parts[0]) + moveOffsets[i][0];
            int tempRow = Integer.parseInt(parts[1]) + moveOffsets[i][1];

            if (isOnBoard(tempCol, tempRow)) {
                Cell button = app.cellArray[tempCol][tempRow];

                if (!button.isVisited())
                    neighborCnt++;
            }
        }

        futureNeighbors[index] = neighborCnt;
    }

    private static boolean isOnBoard(int tempCol, int tempRow) {
        return tempRow >= 0 && tempRow < BOARD_SIZE && tempCol >= 0 && tempCol < BOARD_SIZE;
    }

    public static Cell selectCell() {
        List<Integer> btnIndexes = new ArrayList<Integer>();
        int leastNeighborCnt = KNIGHT_MOVES;

        for (int i = 0; i < KNIGHT_MOVES; i++) {
            if (neighborCells.get(i) == null)
                continue;

            int neighborCnt = futureNeighbors[i];
            
            if (neighborCnt < leastNeighborCnt) {
                leastNeighborCnt = neighborCnt;
                btnIndexes.clear();
                btnIndexes.add(i);
                continue;
            }

            if (neighborCnt == leastNeighborCnt)
                btnIndexes.add(i);
        }

        futureNeighbors = new int[KNIGHT_MOVES];
        
        if (btnIndexes.size() == 1)
            return neighborCells.get(btnIndexes.get(0));
        
        return handleTies(btnIndexes);
    }

    private static Cell handleTies(List<Integer> btnIndexes) {
        int index = 0;

        for (int i = 0; i < KNIGHT_MOVES; i++) {
            index = moveOrders[moveSet][i];

            if (btnIndexes.contains(index))
                return neighborCells.get(index);
        }

        return null;
    }
}
