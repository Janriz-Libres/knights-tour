package src;

import java.awt.*;
import javax.swing.*;

import static src.KnightsTour.BOARD_SIZE;
import static src.KnightsTour.generateButtons;

public class KnightsTourGUI extends JFrame {
	// Holds all references of each cell/square on the chessboard
	Cell cellArray[][] = new Cell[BOARD_SIZE][BOARD_SIZE];

	// Container for all the buttons/cells on the chessboard
	JPanel chessBoard;

	/**
	 * Generates the application window/frame
	 */
	public KnightsTourGUI() {
		chessBoard = new JPanel(new GridLayout(BOARD_SIZE, BOARD_SIZE));
		
		JPanel controlPanel = new JPanel(new BorderLayout());
		JPanel btnPanel = new JPanel();

		JLabel modeLabel = new JLabel("Mode: Manual", SwingConstants.CENTER);

		JButton autoBtn = new JButton("Auto Tour");
		JButton manualBtn = new JButton("Manual Tour");
		JButton guidedBtn = new JButton("Guided Tour");

		btnPanel.add(autoBtn);
		btnPanel.add(manualBtn);
		btnPanel.add(guidedBtn);

		controlPanel.add(modeLabel, BorderLayout.NORTH);
		controlPanel.add(btnPanel);

		add(chessBoard);
		add(controlPanel, BorderLayout.SOUTH);

		generateButtons(this);

		setMinimumSize(new Dimension(400, 400));
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * A function made for the purpose of designing the cells on the chessboard
	 * 
	 * @param btn the general cell/button to be designed
	 */
	static void designBtn(JButton btn) {
		btn.setBackground(new Color(229,223,214));
	}

	/**
	 * Decorates or designs the cell that is the current position of the knight
	 * 
	 * @param btn the current position or cell of the knight
	 */
	static void designCurrentCell(JButton btn) {
		btn.setBackground(new Color(84,68,50));
	}

	/**
	 * Decorates buttons which are valid neighbors or moves
	 * 
	 * @param btn a valid neighbor cell/button to be designed
	 */
	static void designNeighbor(JButton btn) {
		btn.setBackground(new Color(180,160,126));
	}
}
