package src;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import static src.KnightsTour.BOARD_SIZE;
import static src.KnightsTour.manual;
import static src.KnightsTour.auto;
import static src.KnightsTour.guided;
import static src.KnightsTour.sm;
import static src.KnightsTour.generateButtons;

public class KnightsTourGUI extends JFrame {
	// Holds all references of each cell/square on the chessboard
	Cell cellArray[][] = new Cell[BOARD_SIZE][BOARD_SIZE];

	// Container for all the buttons/cells on the chessboard
	JPanel chessBoard;

	// A label that indicates the current mode of the application
	public JLabel modeLabel;

	/**
	 * Generates the application window/frame
	 */
	public KnightsTourGUI() {
		chessBoard = new JPanel(new GridLayout(BOARD_SIZE, BOARD_SIZE));
		
		JPanel controlPanel = new JPanel(new BorderLayout());
		JPanel btnPanel = new JPanel();

		modeLabel = new JLabel("Mode: Auto", SwingConstants.CENTER);

		JButton autoBtn = new JButton("Auto Tour");
		autoBtn.setBackground(new Color(84,68,50));
		autoBtn.setForeground(Color.WHITE);
		autoBtn.addActionListener(e -> sm.change(auto));

		JButton manualBtn = new JButton("Manual Tour");
		manualBtn.setBackground(new Color(84,68,50));
		manualBtn.setForeground(Color.WHITE);
		manualBtn.addActionListener(e -> sm.change(manual));

		JButton guidedBtn = new JButton("Guided Tour");
		guidedBtn.setBackground(new Color(84,68,50));
		guidedBtn.setForeground(Color.WHITE);
		guidedBtn.addActionListener(e -> sm.change(guided));

		btnPanel.add(autoBtn);
		btnPanel.add(manualBtn);
		btnPanel.add(guidedBtn);

		controlPanel.add(modeLabel, BorderLayout.NORTH);
		controlPanel.add(btnPanel);

		add(chessBoard);
		add(controlPanel, BorderLayout.SOUTH);

		generateButtons(this);

		setTitle("Knight's Tour");
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
		btn.setBackground(new Color(144,119,85,255));
	}

	/**
	 * Decorates the designated cell that the user should click on during the guided tour
	 * 
	 * @param btn the highlighted cell
	 */
	public static void designSelectedCell(JButton btn) {
		btn.setBackground(Color.GREEN);
	}
}
