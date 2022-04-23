import java.awt.*;
import javax.swing.*;

public class KnightsTourGUI extends JFrame {
	JPanel buttonPanel;

	/**
	 * Generates the application window/frame
	 */
	public KnightsTourGUI() {
		buttonPanel = new JPanel(new GridLayout(KnightsTour.BOARD_SIZE, KnightsTour.BOARD_SIZE));
		add(buttonPanel);

		KnightsTour.generateButtons(this);

		setMinimumSize(new Dimension(450, 300));
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