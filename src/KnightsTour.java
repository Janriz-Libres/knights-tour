package src;

import java.awt.event.*;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import java.util.*;

public class KnightsTour {
    static final int BOARD_SIZE = 8;
    static final int HORSE_MOVES = 8;

    static Cell cellArray[][] = new Cell[BOARD_SIZE][BOARD_SIZE];
    static int moveOffsets[][] = {
        {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}
    };
    static List<Cell> neighborCells = new ArrayList<Cell>();
    // static List<Cell> neighborCellsCPUFormat = new ArrayList<Cell>();

    static Cell currentCell = null;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new KnightsTourGUI());
    }

    static public void beginKnightsTour(JButton btn) {
    }

    static public void generateButtons(KnightsTourGUI window) {
        for (int row = 0; row < KnightsTour.BOARD_SIZE; row++) {
            for (int col = 0; col < KnightsTour.BOARD_SIZE; col++) {
                Cell btn = new Cell(String.valueOf(row) + String.valueOf(col));

                btn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        Cell button = (Cell) e.getComponent();
                        buttonManager(button);
                    }
                });

                cellArray[row][col] = btn;
                window.buttonPanel.add(btn);
            }
        }
    }

    static public void findNeighbors(Cell btn) {
        String pos = btn.getPos();
        System.out.println("Evaluating Position: " + pos);
        String[] parts = pos.split("");

        // Generate neighbor position
        for (int i = 0; i < HORSE_MOVES; i++) {
            System.out.println("Solving position " + i + " neighbour...");
            System.out.println("String index 0:" + parts[0]);
            System.out.println("String index 1:" + parts[1]);

            int row = Integer.parseInt(parts[0]) + moveOffsets[i][0];
            int col = Integer.parseInt(parts[1]) + moveOffsets[i][1];

            // Check if the neighbor is in the board
            if (row >= 0 & row < BOARD_SIZE & col >= 0 & col < BOARD_SIZE) {
                Cell tempBtn = cellArray[row][col];

                if (!tempBtn.isVisited())
                    neighborCells.add(tempBtn);

                // if (!tempBtn.isVisited()) {
                //     neighborCells.add(tempBtn);
                //     neighborCellsCPUFormat.add(tempBtn);
                // } else {
                //     neighborCellsCPUFormat.add(null);
                // }
            }
        }

        // Set color of the neighbor
        for (int i = 0; i < neighborCells.size(); i++)
            neighborCells.get(i).setBackground(Color.GREEN);

        // System.out.println("Solved Neighbors: " +
        // Arrays.toString(neighborCellsCPUFormat.toArray()));
    }

    static public void resetButtonState() {
        for (int i = 0; i < neighborCells.size(); i++) {
            Cell button = neighborCells.get(i);
            button.setBackground(null);
        }

        neighborCells.clear();
    }

    static public void buttonManager(Cell btn) {
        if (btn.isVisited())
            return;

        if (neighborCells.contains(btn) || neighborCells.isEmpty()) {
            if (!neighborCells.isEmpty())
                resetButtonState();

            btn.setBackground(Color.RED);
            btn.setVisited(true);
            findNeighbors(btn);
        }
    }
}