package dk.kattehale;

import javax.swing.*;
import java.awt.*;

public class drawBars extends JPanel {

    int width = Main.getWidth();
    int height = Main.getHeight();
    int dataCount = Main.getDataCount();

    int[] array = Main.getArray();
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

    // TODO: Add colors to the bars to show which bar is being edited
    // This function paints the bars
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < dataCount; i++) {

            int barHeight = (height/dataCount)*array[i];

            g.setColor(new Color(153, 37, 54));
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

}
