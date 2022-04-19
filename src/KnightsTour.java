package src;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class KnightsTour {

    public static void main(String[] args) {
        KnightsTourGUI window = new KnightsTourGUI();
        window.frame.setVisible(true);

        window.generateButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
                for (int i = 0; i < Integer.parseInt(window.ChessSizeInput.getText()); i++) {
                    //input code here, basically add a new button to the panel based on the ChessSizeInput
                }
			}
		});
    }
}