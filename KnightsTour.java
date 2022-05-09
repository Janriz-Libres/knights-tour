import java.awt.event.*;
import javax.swing.*;
import java.util.Timer;
import java.util.*;

public class KnightsTour {
    static final int BOARD_SIZE = 8;
    static final int KNIGHT_MOVES = 8;

    // Holds a reference to the GUI frame or window
    static KnightsTourGUI app;

    // Stores the knight's movements relative to its origin
    static int moveOffsets[][] = {
        {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}
    };
    static List<Integer> moveOrder = new ArrayList<>();

    static int programState = 1;
    static int breakPoint = 0;
    static int futNeighCnt = 0;
    static int currIteration = 0;
    static int animationSPEED = 2000;

    // Stores the knight's valid moves
    static List<Cell> neighborCells = new ArrayList<Cell>();
    static List<Integer> availFutNeigh = new ArrayList<Integer>();

    // Keeps track of the knight's current position on the chessboard
    static Cell currentPos;

    static Timer timer = new Timer();

    public static void main(String[] args) {
        moveOrder.add(3);moveOrder.add(4);moveOrder.add(2);moveOrder.add(6);moveOrder.add(1);moveOrder.add(5);moveOrder.add(7);moveOrder.add(8);
        SwingUtilities.invokeLater(() -> {
            app = new KnightsTourGUI();
        });
    }

    /**
     * Automatically finds and picks the next move for the knight to make
     * 
     * @param frame a reference to the instance variable JFrame
     */
    static public void beginAutoKnightsTour(Cell btn) {
        //Begins the tour with the chosen cell as the starting point

        if (currIteration >= 63) {
            return;
        }
        processBtnEvent(btn);

        timer.scheduleAtFixedRate(new TimerTask() {
            Cell currentButton = btn;
            
            //Loops the program in a timed fashion until it reached the end of the tour
            @Override
            public void run() {
                currIteration++;
                System.out.println("-----------------------------------");
                System.out.println("Current iteration: " + currIteration);
                System.out.println("Chosen Cell: " + currentButton.locate());

                //Checks if the program has reached the end of the tour
                if (currIteration >= 63) {
                    timer.cancel();
                }
                
                findFutNeigh();
    
                System.out.println("Finding lowest possible moves...");
                /*
                    Setting the currentButton to the next cell in the tour by calculating the smallest number in the 
                    list of available future neighbors
                */
                currentButton = FindLowestPossibleMoves();

                //Process the chosen cell
                processBtnEvent(currentButton);
            }
        }, animationSPEED, animationSPEED); //The speed of the animation is by default set to 100ms
        timer.purge(); //Deletes all the old tasks of timer that are not running
    }

    /**
     * Populate the chessboard with cells/squares
     * 
     * @param frame a reference to the instance variable JFrame
     */
    static public void generateButtons(KnightsTourGUI frame) {
        for (int col = 0; col < BOARD_SIZE; col++) {
            for (int row = 0; row < BOARD_SIZE; row++) {
                // Instantiates a new cell and stores its location via constructor
            
                Cell btn = new Cell(String.valueOf(row) + String.valueOf(col));

                // Adds a mouseListener that will trigger if the user clicks on any of the cells
                btn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        Cell button = (Cell) e.getComponent();

                        //Changes the program state to manual, auto and guided knights tour
                        switch (programState) {
                            case 0:
                                //Manual Knights Tour
                                processBtnEvent(button);
                                break;
                            case 1:
                                //Auto Knights Tour
                                beginAutoKnightsTour(button);
                                break;
                            case 2:
                                //Guided Knights Tour
                                break;
                            default:
                                break;
                        }   
                    }
                });

                // Decorates the button accordingly
                KnightsTourGUI.designBtn(btn);

                // Store a reference of the cell and add it as component
                frame.cellArray[row][col] = btn;
                frame.buttonPanel.add(btn);
            }
        }
    }

    /**
     * Provides the logic for determining if the cell is a valid move/neighbor or not.
     * Processes the breakpoints logic for Tie Breaking rules.
     * As well as finding the neighbors of the current cell.
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
        KnightsTourGUI.designCurrentCell(btn);
        btn.setVisited(true);
        
        currentPos = btn;

        /*
            Changes the break points depending on the current position.

            According to the Tie Breaking rules, the breakpoint must no go back to the previous breakpoint. For that
            we will base the breakpoint to the current position of the knight if and only if the breakpoint is not less than the
            previous breakpoints.
        */
        if (currentPos.locate().equals("76") && breakPoint == 0) {
            moveOrder.clear();
            moveOrder.add(8);moveOrder.add(7);moveOrder.add(6);moveOrder.add(4);moveOrder.add(2);moveOrder.add(1);moveOrder.add(3);moveOrder.add(5);
            breakPoint = 1;
        } else if (currentPos.locate().equals("22") && breakPoint == 1) {
            moveOrder.clear();
            moveOrder.add(5);moveOrder.add(1);moveOrder.add(8);moveOrder.add(6);moveOrder.add(7);moveOrder.add(3);moveOrder.add(4);moveOrder.add(2);
            breakPoint = 2;
        } else if (currentPos.locate().equals("01") && breakPoint == 2) {
            moveOrder.clear();
            moveOrder.add(5);moveOrder.add(1);moveOrder.add(3);moveOrder.add(4);moveOrder.add(2);moveOrder.add(6);moveOrder.add(7);moveOrder.add(8);
            breakPoint = 3;
        } else if (currentPos.locate().equals("75") && breakPoint == 3) {
            moveOrder.clear();
            moveOrder.add(2);moveOrder.add(1);moveOrder.add(4);moveOrder.add(3);moveOrder.add(5);moveOrder.add(6);moveOrder.add(7);moveOrder.add(8);
            breakPoint = 4;
        }

        // Finds all possible neighbors of the chosen cell
        for (int i = 0; i < KNIGHT_MOVES; i++) {
            Cell neighbor = findNeighbors(currentPos, i);
            neighborCells.add(neighbor);
            if (!neighbor.isVisited()) {
                KnightsTourGUI.designNeighbor(neighbor);
            }
        }
    }

    /**
     * Resets the decorative states of neighbor cells
     */
    static public void resetButtonState() {
        for (int i = 0; i < neighborCells.size(); i++) {
            //Ignores the deletion of the valied cell
            if (neighborCells.get(i).isNull() == true)
                continue;
            Cell button = neighborCells.get(i);
            KnightsTourGUI.designBtn(button);
        }

        neighborCells.clear();
    }

    /**
     * Finds valid moves/neighbors for the knight
     * 
     * @param btn cell where the knight is currently located
     */
    static public Cell findNeighbors(Cell btn, int i) {
        Cell button = new Cell("");
        // Get the location of the current cell
        String pos = btn.locate();

        // Checks for valid neighbors

        // Get the corresponding row and column of current cell
        String parts[] = pos.split("");

        // Calculate neighbor's presumed position on the board
            int tempRow = Integer.parseInt(parts[0]) + moveOffsets[i][0];
            int tempCol = Integer.parseInt(parts[1]) + moveOffsets[i][1];

        // Verify the neighbor's position by checking if it's on the board
        if (tempRow >= 0 & tempRow < BOARD_SIZE & tempCol >= 0 & tempCol < BOARD_SIZE) {
            button = app.cellArray[tempRow][tempCol];

            

            // If button is not visited, add it to the list of neighbors and set its null value to false
            if (!button.isVisited()) {
                button.setNull(false);
                return button;
            } else {
                button.setNull(true);
                return button;
            }
        }
        button.setNull(true);
        return button;
    }
    
    /**
     * Finds the future neighbors of all possible neighbors of the chosen cell
     */
    static public void findFutNeigh() {
        int futNeighCnt = 0;
        availFutNeigh.clear();

        //Iterates on each neighborCells array to calculate the future neighbors
        for (int i = 0; i < neighborCells.size(); i++) {
            /*
                Within the neighborCells array, it contains cells that are not valid(getNULLVal() == true)
                Hence we will only calculate the future neighbors of the valid cells
            */
            if (neighborCells.get(i).isNull() != true) {
                //Calculates the future neighbors of the possible neighbor
                for (int j = 0; j < KNIGHT_MOVES; j++) {
                    //We will reuse the findNeighbors() method to calculate the future neighbors
                    Cell futureNeighbor = findNeighbors(neighborCells.get(i), j);
                    //Check if the calculated future neighbor is not visited and is a valid neighbor
                    if (!futureNeighbor.isVisited() && futureNeighbor.isNull() != true) {
                        futNeighCnt++;
                    }
                }
            }
            //The sum of all the future neighbors of the current possible neighbor is added to the array
            availFutNeigh.add(futNeighCnt);
            futNeighCnt = 0;
        }

        System.out.println("Calculated Future Neighbor of Possible Neighbors: " + Arrays.toString(availFutNeigh.toArray()));
    }

    /**
     * Finds the lowest future moves of each possible neighbors.
     * As well as identifying Tie and applying Tie Breaking Rules
     */
    public static Cell FindLowestPossibleMoves () {
        int chosenNeighIndex = 8;
        int currentNeighIndex = 0;
        int finalSelection = 0;
        int smallest = Integer.MAX_VALUE; 

        //Iterates on each available future neighbors array to find the smallest value
        for(int i=0; i<availFutNeigh.size(); i++) {
            if(availFutNeigh.get(i) > 0 && availFutNeigh.get(i) < smallest) {
                smallest = availFutNeigh.get(i);
                finalSelection = i;
                
            /*
                The solution to a bug, in which if it is in the last iteration the value of the smallest will be 2147483647.
                This is because the smallest value is initialized to the maximum value of an integer.
                To solve this, we will check if the current iteration is the last one, 
                and if it is, we will set the smallest value to 0.
            */
            } else if (currIteration >= 63) {
                smallest = 0;
            }
        }
        System.out.println("The smallest number of future neighbors is: " + smallest);

        /*
            Check if the current iteration is the last iteration and return the last possible neighbor.
            Why check "smallest == 0"? It is because at the last cell, there will be no more future neighbors thuz the result
            of the smallest value will be 0.
        */
        if (currIteration >= 63 && smallest == 0) {
            //Returns the last possible neighbor
            for (int i = 0; i < neighborCells.size(); i++) {
                if (neighborCells.get(i).isNull() == false) {
                    return neighborCells.get(i);
                }
            }    
        }

        //identifying tie's on multiple possible neighbors with the same smallest number of future neighbors
        System.out.println("Using the Move order: " + Arrays.toString(moveOrder.toArray()));
        for (int i = 7; i > 0; i--) {
            if (availFutNeigh.get(i) == smallest) { //Identify the duplicate possible neighbor
                currentNeighIndex = moveOrder.indexOf(i) + 1; //Checks the position of the duplicate neighbor in the moveOrder array
                if (currentNeighIndex < chosenNeighIndex) { //If the position of the duplicate neighbor cell is less than the previous then,
                    chosenNeighIndex = currentNeighIndex; //set the chosenNeighIndex to the position of the duplicate neighbor cell
                    finalSelection = i; //set the final selection to the index of the current cell 
                }
            }
        }
        //Returns the chosen neighbor
        return neighborCells.get(finalSelection);
    }
}