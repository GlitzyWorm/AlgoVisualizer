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

    // Sets a border at the bottom of the bars
    public drawBars() {
        setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.black));
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

            g.setColor(new Color(109, 37, 37));
            g.fillRect(i*barWidth,height, barWidth, barHeight-height);
        }

    }
}
