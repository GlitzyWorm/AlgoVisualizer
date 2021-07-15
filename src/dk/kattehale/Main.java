package dk.kattehale;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Main {

    public static int width = 800;
    public static int height = 500;
    public static int dataCount = 10;

    public static void main(String[] args) {
        JFrame frame = new JFrame("AlgoVisualizer");
        frame.setLayout(new BorderLayout());

        JPanel[] panelArray = new JPanel[dataCount];
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.LINE_AXIS));
        container.setBackground(Color.blue);
        container.setPreferredSize(new Dimension(width,height));
        for (int i = 0; i < dataCount; i++) {
            panelArray[i] = new JPanel();
            panelArray[i].setAlignmentY(JComponent.BOTTOM_ALIGNMENT);
            panelArray[i].setAlignmentX(JComponent.CENTER_ALIGNMENT);

            Dimension d = new Dimension(width/dataCount,300);
            panelArray[i].setPreferredSize(d);
            panelArray[i].setMaximumSize(d);

//            BorderFactory.createEmptyBorder(0,10,0,0);
            javax.swing.border.Border border = BorderFactory.createLineBorder(Color.black, 1);
            panelArray[i].setBorder(border);
            panelArray[i].setBackground(Color.gray);
            panelArray[i].setVisible(true);
            container.add(panelArray[i]);
        }

        frame.add(container, BorderLayout.SOUTH);

//        JPanel panel = new JPanel();
//        panel.setBackground(Color.black);
//        panel.setLocation(300,0);
//        panel.setSize(200,400);
//        frame.add(container);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width,height);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
