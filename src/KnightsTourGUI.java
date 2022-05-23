package src;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

import static src.KnightsTour.BOARD_SIZE;
import static src.KnightsTour.manual;
import static src.KnightsTour.auto;
import static src.KnightsTour.guided;
import static src.KnightsTour.sm;
import static src.KnightsTour.generateButtons;
import static src.KnightsTour.updatePreference;

public class KnightsTourGUI extends JFrame {
	// Holds all references of each cell/square on the chessboard
	Cell cellArray[][] = new Cell[BOARD_SIZE][BOARD_SIZE];

	// Container for all the buttons/cells on the chessboard
	JPanel chessBoard;

	// A label that indicates the current mode of the application
	public JLabel modeLabel;

	// Buttons for the interface
	public JButton autoBtn, manualBtn, guidedBtn, resetBtn;

	// Separator to be placed between the change state buttons and reset button
	public JSeparator verticalLine;

	// Toggles whether or not the order of the knight's moves will be shown
	JCheckBox orderCheckBox;

	/**
	 * Generates the application window/frame
	 */
	public KnightsTourGUI() {
		chessBoard = new JPanel(new GridLayout(BOARD_SIZE, BOARD_SIZE));
		
		JPanel controlPanel = new JPanel(new BorderLayout());
		JPanel topPanel = new JPanel(new BorderLayout());
		JPanel btnPanel = new JPanel();

		modeLabel = new JLabel("Mode: Auto", SwingConstants.CENTER);

		autoBtn = new InterfaceBtn("Auto Tour");
		manualBtn = new InterfaceBtn("Manual Tour");
		guidedBtn = new InterfaceBtn("Guided Tour");
		resetBtn = new InterfaceBtn("Reset");

		autoBtn.addActionListener(e -> sm.change(auto));
		manualBtn.addActionListener(e -> sm.change(manual));
		guidedBtn.addActionListener(e -> sm.change(guided));
		resetBtn.addActionListener(e -> sm.reset());

		autoBtn.setVisible(false);
		resetBtn.setVisible(false);

		verticalLine = new JSeparator(SwingConstants.VERTICAL);
		Dimension d = verticalLine.getPreferredSize();
		d.height = autoBtn.getPreferredSize().height;
		verticalLine.setPreferredSize(d);
		verticalLine.setVisible(false);

		btnPanel.add(autoBtn);
		btnPanel.add(manualBtn);
		btnPanel.add(guidedBtn);
		btnPanel.add(verticalLine);
		btnPanel.add(resetBtn);

		orderCheckBox = new JCheckBox("Show Order");
		orderCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		orderCheckBox.setFocusPainted(false);
		orderCheckBox.addItemListener(e -> updatePreference(e));

		JSeparator horizontal = new JSeparator(SwingConstants.HORIZONTAL);

		topPanel.add(modeLabel, BorderLayout.NORTH);
		topPanel.add(horizontal);
		topPanel.add(orderCheckBox, BorderLayout.SOUTH);

		controlPanel.add(topPanel, BorderLayout.NORTH);
		controlPanel.add(btnPanel);

		add(chessBoard);
		add(controlPanel, BorderLayout.SOUTH);

		generateButtons(this);

		setTitle("C Triple J Knight's Tour");
		setIconImage(new ImageIcon("icon.png").getImage());
		setSize(400, 400);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	/**
	 * A function made for the purpose of designing the cells on the chessboard
	 * 
	 * @param btn the general cell/button to be designed
	 */
	static void designBtn(JButton btn) {
		btn.setBackground(new Color(255,251,236,255));
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
