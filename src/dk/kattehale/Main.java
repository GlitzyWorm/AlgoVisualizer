package dk.kattehale;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static int width = 800;
    public static int height = 500;
    public static int dataCount = 10;

    public static void main(String[] args) {
        JFrame frame = new JFrame("AlgoVisualizer");

        JPanel[] panelArray = new JPanel[dataCount];
        JPanel container = new JPanel();
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        /*c.insets = new Insets(0,1,0,1);
        c.anchor = GridBagConstraints.SOUTH;*/

        for (int i = 0; i < dataCount; i++) {
            panelArray[i] = new JPanel();
            panelArray[i].setPreferredSize(new Dimension(100,100));
            panelArray[i].setSize(120,400);
            panelArray[i].setLocation(100,0);
            c.gridx = i*2;
            c.gridy = 0;

            javax.swing.border.Border border = BorderFactory.createLineBorder(Color.black, 1);
            panelArray[i].setBorder(border);
            panelArray[i].setBackground(Color.gray);
            panelArray[i].setVisible(true);
            container.add(panelArray[i], c);
        }
        frame.getContentPane().add(container);

//        JPanel panel = new JPanel();
//        panel.setBackground(Color.black);
//        panel.setLocation(300,0);
//        panel.setSize(200,400);
//        frame.add(container);

        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width,height);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
