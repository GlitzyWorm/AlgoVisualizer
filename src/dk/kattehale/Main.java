package dk.kattehale;

import dk.kattehale.algorithms.BubbleSort;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Random;

public class Main {

    // Width and height of the screen
    public static int width = 800;
    public static int height = 600;

    // The amount of data (and bars) to use (works best with a max of a 100 data points).
    public static int dataCount = 50;

    public static int[] array = new int[dataCount];
    public static String[] sortNames = {"BubbleSort"};

    public static drawBars bars = new drawBars();

    static JComboBox sortList;

    public static void main(String[] args) {
        // Creates and shows GUI
        createAndShowGUI();

        // Assigns random values from 1-75 to the array
        assignArray();


        // Updates array every second 10 times (used for testing)
//        updateArray();
    }

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("AlgoVisualizer");
        frame.setLayout(new BorderLayout());

        // Draws bars to the JFrame.
        frame.add(bars, BorderLayout.CENTER);

        // JPanel container to contain the following
        JPanel menuContainer = new JPanel();

        // Adds a "Start" button to begin the sorting
        JButton startSort = new JButton("Start");
        startSort.addActionListener(e -> {
            // Creates a new thread to prevent the GUI from freezing
            new Thread(Main::doSort).start();
        });

        // Adds a dropdown menu to pick a sorting algorithm
        sortList = new JComboBox(sortNames);
        sortList.setSelectedIndex(0);

        menuContainer.add(sortList);
        menuContainer.add(startSort);

        frame.add(menuContainer, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Every value in the array gets a random number from 1 to 75.
    public static void assignArray() {
        Random rand = new Random();

        for (int i = 0; i < dataCount; i++) {
            array[i] = (rand.nextInt(75)+1);
            System.out.println(array[i]);
        }
    }

    // Checks which sorting algorithm is picked and executes
    public static void doSort() {

        String selectedSort = Objects.requireNonNull(sortList.getSelectedItem()).toString();

        switch (selectedSort) {
            case "BubbleSort" -> BubbleSort.runSort(array);
        }

    }

    // Updates the visualization of the array
    public static void updateArray() {
        bars.repaint();
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }


    // Loops through 10 times and assigns new values to the array and repaints the bars every second. (Used for testing)
    public static void testArray() {
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


    /* Getters for array, width, height and dataCount */

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



