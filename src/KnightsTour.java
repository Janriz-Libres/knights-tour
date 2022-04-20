package src;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.awt.Color;

import javax.swing.JButton;

public class KnightsTour {
    static HashMap<Integer, JButton> ButtonArray = new HashMap<Integer, JButton>();
    static int operationNeighRowList[] = {2, 1, -1, -2, -2, -1, 1, 2};
    static int operationNeighColList[] = {1, 2, 2, 1, -1, -2, -2, -1};
    static List<Integer> neighPosition = new ArrayList<Integer>();
    static List<Integer> neighPositionCPUFormat = new ArrayList<Integer>();
    static List<Integer> visitedPosition = new ArrayList<Integer>();

    static KnightsTourGUI window = new KnightsTourGUI();
    static boolean isPicking = false;
    public static void main(String[] args) {
        window.frame.setVisible(true);

        window.generateButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
                window.rows = Integer.parseInt(window.ChessSizeInput.getText());
                window.cols = Integer.parseInt(window.ChessSizeInput.getText());
                int chessboardSize = window.rows -1;

                for (int i = 0, j; i <= chessboardSize; i++) {
                    j = 0;
                    JButton btn = new JButton("" + i+j);
                    window.buttonPanel.add(btn);
                    btn.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseReleased(MouseEvent e) {
                            JButton button = (JButton) e.getSource();
                            buttonManager(button);
                        }
                    });
                    ButtonArray.put(concat(i,j), btn);
                    for (int k = 1; k <= chessboardSize; k++) {
                        j++;
                        JButton btn2 = new JButton("" + i+j);
                        window.buttonPanel.add(btn2);
                        btn2.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseReleased(MouseEvent e) {
                                JButton button = (JButton) e.getSource();
                                buttonManager(button);
                            }
                        });
                        ButtonArray.put(concat(i,j), btn2);
                    }
                }
			}
		});
    }

    static int concat(int a, int b)
    {
        // Convert both the integers to string
        String s1 = Integer.toString(a);
        String s2 = Integer.toString(b);
 
        // Concatenate both strings
        String s = s1 + s2;
 
        // Convert the concatenated string
        // to integer
        int c = Integer.parseInt(s);
 
        // return the formed integer
        return c;
    }

    static public void beginKnightsTour(JButton btn) {
    }

    static public void findNeighbors(JButton btn) {
        String btnText = btn.getText();
        String[] parts = btnText.split("");

        //Generate neighbor position
        for (int i = 0; i < operationNeighRowList.length; i++) {
            int tempAnswerRow = Integer.parseInt(parts[0]) + operationNeighRowList[i];
            int tempAnswerCol = Integer.parseInt(parts[1]) + operationNeighColList[i];
            
            //Check if the neighbor is in the board
            if (tempAnswerRow >= 0 && tempAnswerRow <= window.rows - 1 && tempAnswerCol >= 0 && tempAnswerCol <= window.cols - 1 && !visitedPosition.contains(concat(tempAnswerRow, tempAnswerCol))) {
                neighPosition.add(concat(tempAnswerRow, tempAnswerCol));
                neighPositionCPUFormat.add(concat(tempAnswerRow, tempAnswerCol));
            } else {
                neighPositionCPUFormat.add(null);
            }
        }

        //Set color of the neighbor
        for (int i = 0; i < neighPosition.size(); i++) {
            JButton button = ButtonArray.get(neighPosition.get(i));
            button.setBackground(Color.GREEN);
            System.out.println("Hello!");
        }
    }

    static public void resetButtonState() {
        for (int i = 0; i < neighPosition.size(); i++) {
            JButton button = ButtonArray.get(neighPosition.get(i));
            button.setBackground(null);
        }

        neighPosition.clear();
    }

    static public void buttonManager(JButton btn) {
        if (!visitedPosition.contains(Integer.parseInt(btn.getText()))) {
            if (visitedPosition.isEmpty()) {
                btn.setBackground(Color.RED);
                visitedPosition.add(Integer.parseInt(btn.getText()));
                findNeighbors(btn);
            } else if (neighPosition.contains(Integer.parseInt(btn.getText()))) {
                visitedPosition.add(Integer.parseInt(btn.getText()));
                btn.setBackground(Color.RED);
                neighPosition.remove(neighPosition.indexOf(Integer.parseInt(btn.getText())));
     
                resetButtonState();
                findNeighbors(btn);
            }
        }
    }
}