package src;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;
import java.awt.Color;

import javax.swing.JButton;

public class KnightsTour {
    static HashMap<Integer, JButton> ButtonArray = new HashMap<Integer, JButton>();
    static int operationNeighRowList[] = {-2,-1,1,2,2,1,-1,-2};
    static int operationNeighColList[] = {1,2,2,1,-1,-2,-2,-1};
    static List<Integer> neighPosition = new ArrayList<Integer>();
    static List<Integer> neighPositionCPUFormat = new ArrayList<Integer>();
    static List<Integer> visitedPosition = new ArrayList<Integer>();

    static KnightsTourGUI window = new KnightsTourGUI();
    static boolean isPicking = false;
    public static void main(String[] args) {
        window.frame.setVisible(true);

        //Generate 8x8 buttons and apply the button design
        for (int i = 0, j; i <= 7; i++) {
            j = 0;
            generateButtons(i, j);
            for (int k = 1; k <= 7; k++) {
                j++;
                generateButtons(i, j);
            }
        }
        window.applyButtonDesign();
    }

    static int concat(int a, int b)
    {
        // Convert both the integers to string
        String s1 = Integer.toString(Math.abs(a));
        String s2 = Integer.toString(Math.abs(b));
 
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
        DecimalFormat formatter = new DecimalFormat("00");
        
        String btnKey = formatter.format(getKeyByValue(ButtonArray, btn)); 
        System.out.println("Evaluating Position: " + btnKey);
        String[] parts = btnKey.split("");

        //Generate neighbor position
        for (int i = 0; i < operationNeighRowList.length; i++) {
            System.out.println("Solving position " + i + " neighbour...");
            System.out.println("String index 0:" + parts[0]);
            System.out.println("String index 1:" + parts[1]);
            int tempAnswerRow = Integer.parseInt(parts[0]) + operationNeighRowList[i];
            int tempAnswerCol = Integer.parseInt(parts[1]) + operationNeighColList[i];
            
            //Check if the neighbor is in the board
            if (tempAnswerRow >= 0 & tempAnswerRow <= 7 & tempAnswerCol >= 0 & tempAnswerCol <= 7 & !visitedPosition.contains(concat(tempAnswerRow, tempAnswerCol))) {
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
        }
        System.out.println("Solved Neighbors: " + Arrays.toString(neighPositionCPUFormat.toArray()));
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

    static public void generateButtons(int i, int j) {
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
    }

    public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
        for (Entry<T, E> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
}