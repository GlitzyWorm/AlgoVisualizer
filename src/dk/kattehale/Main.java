package dk.kattehale;

import dk.kattehale.algorithms.BubbleSort;
import dk.kattehale.algorithms.InsertionSort;
import dk.kattehale.algorithms.QuickSort;
import dk.kattehale.algorithms.SelectionSort;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Random;

public class Main {

    // Width and height of the screen
    public static int width = 1200;
    public static int height = 700;

    // The amount of data (and bars) to use (works best with a max of a 100 data points).
    public static int dataCount = 100;

    public static int[] array;


    public static drawBars bars = new drawBars();

    static JComboBox sortList;
    public static String[] sortNames = {"BubbleSort", "InsertionSort", "SelectionSort", "QuickSort"};
    static JButton startSort;
    static JSlider dataSlider;

    // Name of cards for CardLayout
    final static String SELECTSORT = "selectionCard";
    final static String PERFORMSORT = "performSort";


    public static void main(String[] args) {
        // Creates and shows GUI
        createAndShowGUI();

        // Updates array every second 10 times (used for testing)
        // updateArray();
    }

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("AlgoVisualizer");
        frame.setLayout(new BorderLayout());

        // JPanel Cards
        // cardSelect: select sorting algorithm and start button
        JPanel cardSelect = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Title Icon
        JLabel iconLabel = new JLabel();
        iconLabel.setIcon(new ImageIcon("src/dk/kattehale/resources/logo.png"));

        // Label for slider
        JLabel sliderLabel = new JLabel("Number of bars");
        sliderLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        // Label for dropdown
        JLabel sortLabel = new JLabel("Sorting algorithm");
        sortLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        // Slider to choose the amount of dataCounts
        dataSlider = new JSlider(JSlider.HORIZONTAL, 0,100,50);
        dataSlider.setMajorTickSpacing(20);
        dataSlider.setMinorTickSpacing(10);
        dataSlider.setPaintTicks(true);
        dataSlider.setPaintLabels(true);
        dataSlider.setSnapToTicks(true);
        dataSlider.setPreferredSize(new Dimension(300,50));
        dataSlider.setFocusable(false);

        // Dropdown menu to choose the sorting algorithm
        sortList = new JComboBox(sortNames);
        sortList.setSelectedIndex(0);
        sortList.setPreferredSize(new Dimension(150,25));
        sortList.setFocusable(false);

        // Button to start the sorting
        startSort = new JButton("Start");
        startSort.setPreferredSize(new Dimension(75,40));
        startSort.setFont(new Font("Dialog", Font.PLAIN, 18));
        startSort.setFocusPainted(false);

        // Placement of title icon
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 4;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.PAGE_START;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0,0,100,0);
        cardSelect.add(iconLabel,c);

        // Placement of sliderLabel
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.insets = new Insets(150,0,0,0);
        c.anchor = GridBagConstraints.SOUTH;
        c.fill = GridBagConstraints.NONE;
        cardSelect.add(sliderLabel, c);

        // Placement of slider
        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.insets = new Insets(0,0,0,0);
        c.anchor = GridBagConstraints.SOUTH;
        c.fill = GridBagConstraints.NONE;
        cardSelect.add(dataSlider, c);

        // Placement of sortLabel
        c.gridx = 3;
        c.gridy = 3;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.insets = new Insets(0,10,0,0);
        c.anchor = GridBagConstraints.SOUTH;
        cardSelect.add(sortLabel, c);

        // Placement of dropdown
        c.gridx = 3;
        c.gridy = 4;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.insets = new Insets(0,10,10,0);
        c.anchor = GridBagConstraints.SOUTH;
        cardSelect.add(sortList, c);

        // Placement of button
        c.gridx = 2;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.insets = new Insets(50,0,0,0);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        cardSelect.add(startSort, c);

        // cardPerform: visualize the chosen sorting algorithm
        JPanel cardPerform = new JPanel(new BorderLayout());
        cardPerform.add(bars, BorderLayout.CENTER);

        // Card container
        CardLayout cardLayout = new CardLayout();
        JPanel cardContainer = new JPanel(cardLayout);
        cardContainer.add(cardSelect, SELECTSORT);
        cardContainer.add(cardPerform, PERFORMSORT);

        // Starts new thread to handle button click. Otherwise the GUI freezes
        Thread thread = new Thread(() -> startSort.addActionListener(e -> {
            dataCount = dataSlider.getValue();
            assignArray(dataCount);
            cardLayout.show(cardContainer, PERFORMSORT);
            doSort();
        }));
        thread.start();

        // Adds cardContainer and sets up the JFrame
        frame.add(cardContainer, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Every value in the array gets a random number from 1 to 75.
    public static void assignArray(int dc) {
        array = new int[dc];

        for (int i = 0; i < dc; i++) {
            array[dc-1-i] = dc-i;
        }

        bars.repaintBars();
        bars.setArray(array);
        bars.setBarWidth(width/dc);
        bars.setDataCount(dc);
    }

    // Shuffles the array to place the values in a random order
    public static void shuffle() {
        longSleep();
        Random rng = new Random();
        for (int i = 0; i < array.length; i++) {
            int swapWithIndex = rng.nextInt(array.length - 1);
            int temp = array[i];
            array[i] = array[swapWithIndex];
            array[swapWithIndex] = temp;

            Main.updateArray();
        }
        longSleep();

    }

    // Checks which sorting algorithm is picked and executes
    public static void doSort() {

        // Starts a second thread to avoid freezing the GUI
        Thread thread2 = new Thread(() -> {
            System.out.println("doSort is running");
            switch (Objects.requireNonNull(sortList.getSelectedItem()).toString()) {
                case "BubbleSort" -> {
                    System.out.println("BubbleSort running");
                    shuffle();
                    BubbleSort.runSort(array);
                }
                case "InsertionSort" -> {
                    System.out.println("InsertionSort running");
                    shuffle();
                    InsertionSort.runSort(array);
                }
                case "SelectionSort" -> {
                    System.out.println("SelectionSort running");
                    shuffle();
                    SelectionSort.runSort(array);
                }
                case "QuickSort" -> {
                    System.out.println("QuickSort running");
                    shuffle();
                    QuickSort.runSort(array);
                }
                default -> {
                    shuffle();
                    InsertionSort.runSort(array);
                }
            }
        });
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

    private static void longSleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    // Loops through 10 times and assigns new values to the array and repaints the bars every second. (Used for testing)
    public static void testArray() {
        for (int i = 0; i < 10; i++) {
            assignArray(dataCount);
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



