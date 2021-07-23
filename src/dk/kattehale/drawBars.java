package dk.kattehale;

import javax.swing.*;
import java.awt.*;

public class drawBars extends JPanel {

    int width = Main.getWidth();
    int height = Main.getHeight();
    int dataCount = Main.getDataCount();

    int[] array = Main.getArray();
    static int[] states;

    int barWidth = width/dataCount;

    public Dimension getPreferredSize() {
        return new Dimension(width,height);
    }

    // Can set a background color and a border around the frame
    public drawBars() {
        setBorder(BorderFactory.createMatteBorder(0,0,0,0,Color.black));
        setBackground(Color.white);
    }

    // Call this function to repaint the bars
    public void repaintBars() {
        repaint();
    }

    // Call this function to color the bar at index
    public static void setBarColor (int index, int state) {
        states[index] = state;
    }

    // TODO: Add colors to the bars to show which bar is being edited
    // This function paints the bars
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < dataCount; i++) {

            int barHeight = (height/dataCount)*array[i];

            switch (states[i]) {
                case 0 -> g.setColor(new Color(150, 150, 0));
                case 1 -> g.setColor(new Color(12, 97, 12));
                case 2 -> g.setColor(new Color(12, 23, 97));
                default -> g.setColor(new Color(153, 37, 54));
            }

            g.fillRect(i*barWidth,height-barHeight, barWidth, barHeight);
        }

    }


    /* Setters for array, barWidth, width and dataCount */
    public void setDataCount(int dataCount) {
        this.dataCount = dataCount;
    }

    public void setBarWidth(int barWidth) {
        this.barWidth = barWidth;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setStatesLength(int length) {
        states = new int[length];
    }
}
