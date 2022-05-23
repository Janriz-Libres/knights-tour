package src;

import java.awt.Color;
import javax.swing.JButton;

public class InterfaceBtn extends JButton {
    InterfaceBtn(String text) {
        setText(text);
        setBackground(new Color(84,68,50));
		setForeground(Color.WHITE);
		setFocusPainted(false);
    }
}
