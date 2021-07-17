package dk.kattehale;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Random;

public class Main {

    // Width and height of the screen
    public static int width = 800;
    public static int height = 600;

    // The amount of data (and bars) to use
    public static int dataCount = 10;

    public static int[] array = new int[dataCount];

    public static drawBars bars = new drawBars();

    public static void main(String[] args) {
        // Creates and shows GUI
        createAndShowGUI();

        // Assigns random values from 1-75 to the array
        assignArray();

        // Updates array every second 10 times (used for testing)
        updateArray();
    }

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("AlgoVisualizer");
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        JPanel panel = new JPanel();
        panel.setBackground(Color.gray);

        // Draws bars to the JFrame.
        frame.add(bars);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width,height);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void assignArray() {
        Random rand = new Random();

        // Every value in the array gets a random number from 1 to 75.
        for (int i = 0; i < dataCount; i++) {
            array[i] = (rand.nextInt(75)+1);
            System.out.println(array[i]);
        }
    }

    public static void updateArray() {
        // Loops through 10 times and assigns new values to the array and repaints the bars every second.
        for (int i = 0; i < 10; i++) {
            assignArray();
            bars.repaintBars();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static int[] getArray() {
        return array;
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public static int getDataCount() {
        return dataCount;
    }
}



