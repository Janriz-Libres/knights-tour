package src;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;

public class KnightsTourGUI extends JFrame {
    JFrame frame;

	public KnightsTourGUI() {
		initialize();
	}

    private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
