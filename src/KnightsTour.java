package src;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

public class KnightsTour {
    static List<JButton> ButtonArrayCol = new ArrayList<JButton>();
    static List<JButton> ButtonArrayRow = new ArrayList<JButton>();
    public static void main(String[] args) {
        KnightsTourGUI window = new KnightsTourGUI();
        window.frame.setVisible(true);

        window.generateButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
                window.rows = Integer.parseInt(window.ChessSizeInput.getText());
                window.cols = Integer.parseInt(window.ChessSizeInput.getText());
                int chessboardSize = window.rows -1;

                for (int i = 0, j; i <= chessboardSize; i++) {
                    j = 0;
                    JButton btn = new JButton("(" + i+","+j+")");
                    window.buttonPanel.add(btn);
                    ButtonArrayCol.add(btn);
                    for (int k = 1; k <= chessboardSize; k++) {
                        j++;
                        JButton btn2 = new JButton("(" + i+","+j+")");
                        window.buttonPanel.add(btn2);
                        ButtonArrayRow.add(btn2);
                    }
                }
			}
		});
    }
}