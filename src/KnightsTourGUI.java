package src;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;


public class KnightsTourGUI extends JFrame {
    JFrame frame;
	JPanel buttonPanel;
	int rows, cols = 1;

	public KnightsTourGUI() {
		initialize();
	}

    private void initialize() {
		//Frame
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
	

		//Button Panel
		buttonPanel = new JPanel();
		frame.getContentPane().add(buttonPanel);
		buttonPanel.setLayout(new GridLayout(8, 8, 0, 0));
	}

	private void buttonDesign(JButton btn) {
		//Change the design of the buttons in the chess board using the variable "btn"
	}

	//Applying the button design to all buttons from buttonDesign()
	public void applyButtonDesign() {
		for (int i = 0; i < KnightsTour.ButtonArray.size(); i++) {
			JButton btn = KnightsTour.ButtonArray.get(i);
			buttonDesign(btn);
		}
	}
}