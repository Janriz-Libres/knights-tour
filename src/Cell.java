package src;

import javax.swing.JButton;

public class Cell extends JButton {
    // Stores the location of the cell on the chessboard
    private String location;

    // Keeps track if the cell is already visited
    private boolean visited = false;

    Cell(String pos) {
        this.location = pos;
    }

    // Getter function for checking the button's location on the board
    public String locate() {
        return location;
    }

    // Getter function to check if button is visited
    public boolean isVisited() {
        return visited;
    }

    // Setter function to mark button as visited
    public void setVisited(boolean a) {
        visited = a;
    }
}
