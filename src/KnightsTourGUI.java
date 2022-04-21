package src;

import java.awt.*;
import javax.swing.*;

public class KnightsTourGUI extends JFrame {
	JPanel buttonPanel;

	public KnightsTourGUI() {
		setLayout(new GridLayout(1, 0, 0, 0));
	
		//Button Panel
		buttonPanel = new JPanel();
		add(buttonPanel);
		buttonPanel.setLayout(new GridLayout(8, 8, 0, 0));

		KnightsTour.generateButtons(this);
		applyButtonDesign();

		setBounds(100, 100, 600, 400);
		setMinimumSize(new Dimension(600, 400));
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void buttonDesign(JButton btn) {
		//Change the design of the buttons in the chess board using the variable "btn"
	}

	//Applying the button design to all buttons from buttonDesign()
	public void applyButtonDesign() {
		for (int i = 0; i < KnightsTour.BOARD_SIZE; i++) {
			for (int j = 0; j < KnightsTour.BOARD_SIZE; j++) {
				JButton btn = KnightsTour.cellArray[i][j];
				buttonDesign(btn);
			}
		}
	}
}