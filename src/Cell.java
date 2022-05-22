package src;

import javax.swing.JButton;

public class Cell extends JButton {
    // Stores the location of the cell on the chessboard
    private String location;

    // Keeps track if the cell is already visited
    private boolean visited = false;

    /**
     * This constructor stores the cell's position on the chessboard
     * 
     * @param pos the position of the cell
     */
    Cell(String pos) {
        this.location = pos;
    }

    /**
     * Getter function for checking the button's location on the board
     * 
     * @return the button's location
     */
    public String locate() {
        return location;
    }

    /**
     * Getter function to check if button is visited
     * 
     * @return true if the button is visited, false if not
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * Setter function to mark button as visited
     * 
     * @param a the visited status of the button to be set
     */
    public void setVisited(boolean a) {
        visited = a;
    }
}
