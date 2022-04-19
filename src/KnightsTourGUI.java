package src;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class KnightsTourGUI extends JFrame {
    JFrame frame;
	JTextField ChessSizeInput;
	JButton generateButton;
	JPanel buttonPanel;
	int rows, cols = 1;

	public KnightsTourGUI() {
		initialize();
	}

    private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(4, 0, 0, 0));
		
		JLabel InputChessSizeLabel = new JLabel("Input Chess Board Size:");
		frame.getContentPane().add(InputChessSizeLabel);
		
		ChessSizeInput = new JTextField();
		frame.getContentPane().add(ChessSizeInput);
		ChessSizeInput.setColumns(10);
		
		generateButton = new JButton("Generate");
		frame.getContentPane().add(generateButton);

		buttonPanel = new JPanel();
		frame.getContentPane().add(buttonPanel);
		buttonPanel.setLayout(new GridLayout(8, 8, 0, 0));
	}
}
