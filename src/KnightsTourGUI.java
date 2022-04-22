package src;

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
	 * @param btn the button to be designed
	 */
	public void applyButtonDesign(JButton btn) {
		// TODO
	}
}