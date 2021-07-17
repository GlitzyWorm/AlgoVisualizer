package dk.kattehale;

import javax.swing.*;
import java.awt.*;

public class drawBars extends JPanel {

    int width = Main.getWidth();
    int height = Main.getHeight();
    int dataCount = Main.getDataCount();

    int barWidth = width/dataCount;
    int[] array = Main.getArray();

    public Dimension getPreferredSize() {
        return new Dimension(width,height);
    }

    public drawBars() {
        setBorder(BorderFactory.createLineBorder(Color.black, 1));
    }

    // Call this function to repaint the bars
    public void repaintBars() {
        repaint();
    }

    // This function paints the bars
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < dataCount; i++) {

            int barHeight = (height/100)*array[i];

            g.setColor(Color.DARK_GRAY);
            g.fillRect(i*barWidth,height, barWidth, barHeight-height);
        }

    }

    public int getValue(int index) {
        return array[index];
    }
}
