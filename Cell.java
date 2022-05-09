import javax.swing.JButton;

public class Cell extends JButton {
    // Stores the location of the cell on the chessboard
    private String location;

    // Keeps track if the cell is already visited
    private boolean visited = false;
    private boolean isNull = false;

    Cell(String pos) {
        this.location = pos;
    }

    // Getter function for checking the button's location on the board
    public String locate() {
        return location;
    }

    /**
     * In order for the array neighborCells to have exactly 8 values, we need to add all of the buttons even if it is outside
     * the board. For that to work we will add a null button to the array. To identify the null button we will use the isNull()
     */
    public boolean isNull() {
        return isNull;
    }

    public void setNull(boolean currentNull) {
        this.isNull = currentNull;
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
