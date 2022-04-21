package src;

import java.awt.*;
import javax.swing.*;

public class KnightsTourGUI extends JFrame {
	JPanel buttonPanel;

	public KnightsTourGUI() {
		buttonPanel = new JPanel(new GridLayout(KnightsTour.BOARD_SIZE, KnightsTour.BOARD_SIZE));
		add(buttonPanel);

		KnightsTour.generateButtons(this);

		setMinimumSize(new Dimension(600, 400));
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Change the design of the buttons in the chess board here.
	 * 
	 * @param btn the button to be designed
	 */
	public void applyButtonDesign(JButton btn) {
		// TODO
	}
}