package src;

import javax.swing.JButton;

public class Cell extends JButton {
    private String pos;
    private boolean visited = false;

    Cell(String pos) {
        this.pos = pos;
    }

    public String getPos() {
        return pos;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean a) {
        visited = a;
    }
}
