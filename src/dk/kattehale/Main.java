package dk.kattehale;

import dk.kattehale.algorithms.BubbleSort;
import dk.kattehale.algorithms.InsertionSort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Random;

public class Main {

    // Width and height of the screen
    public static int width = 800;
    public static int height = 600;

    // The amount of data (and bars) to use (works best with a max of a 100 data points).
    public static int dataCount = 50;

    public static int[] array = new int[dataCount];
    public static String[] sortNames = {"BubbleSort", "InsertionSort"};

    public static drawBars bars = new drawBars();

    static JComboBox sortList;
    static JButton startSort;

    public static String selectedSort;

    // Name of cards for CardLayout
    final static String SELECTSORT = "selectionCard";
    final static String PERFORMSORT = "performSort";


    public static void main(String[] args) {

        // Assigns random values from 1-75 to the array
        assignArray();

        // Creates and shows GUI
        createAndShowGUI();

        // Updates array every second 10 times (used for testing)
//        updateArray();
    }

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("AlgoVisualizer");
        frame.setLayout(new BorderLayout());

        // Draws bars to the JFrame.
//        frame.add(bars, BorderLayout.CENTER);

        // JPanel Cards
        // cardSelect: select sorting algorithm and start button
        JPanel cardSelect = new JPanel();

        sortList = new JComboBox(sortNames);
        sortList.setSelectedIndex(0);

        startSort = new JButton("Start");

        cardSelect.add(sortList);
        cardSelect.add(startSort);

        // cardPerform: visualize the chosen sorting algorithm
        JPanel cardPerfom = new JPanel(new BorderLayout());
        cardPerfom.add(bars, BorderLayout.CENTER);

        // Card container
        CardLayout cardLayout = new CardLayout();
        JPanel cardContainer = new JPanel(cardLayout);
        cardContainer.add(cardSelect, SELECTSORT);
        cardContainer.add(cardPerfom, PERFORMSORT);

        // Starts new thread to handle button click. Otherwise the GUI freezes
        Thread thread = new Thread() {
            @Override
            public void run() {
                startSort.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cardContainer, PERFORMSORT);
                        doSort();
                    }
                });
            }
        };
        thread.start();

        // Adds cardContainer and sets up the JFrame
        frame.add(cardContainer, BorderLayout.CENTER);
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

        // Starts a second thread to avoid freezing the GUI
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                System.out.println("doSort is running");
                switch (Objects.requireNonNull(sortList.getSelectedItem()).toString()) {
                    case "BubbleSort":
                        System.out.println("BubbleSort running");
                        updateArray();
                        bars.repaint();
                        BubbleSort.runSort(array);
                        break;
                    case "InsertionSort":
                        System.out.println("InsertionSort running");
                        updateArray();
                        bars.repaint();
                        InsertionSort.runSort(array);
                        break;
                    default:
                        InsertionSort.runSort(array);
                        break;
                };
            }
        };
        thread2.start();
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



