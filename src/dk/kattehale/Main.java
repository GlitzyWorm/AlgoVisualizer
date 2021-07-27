package dk.kattehale;

import dk.kattehale.algorithms.*;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.Random;

public class Main {

    // Width and height of the screen
    public static int width = 1200;
    public static int height = 700;


    static Thread thread2;

    // The amount of data (and bars) to use (works best with a max of a 100 data points).
    public static int dataCount = 1000;

    // The sorting speed
    public static int speed = 10;

    // The array that holds the values from 0-100
    public static int[] array;

    public static drawBars bars = new drawBars();

    // Array with names of sorting algorithms available
    public static String[] sortNames = {"BubbleSort", "InsertionSort", "SelectionSort", "QuickSort", "MergeSort", "HeapSort", "RadixSort"};

    // Array with fixed amount of dataCounts
    public static Integer[] dataValues = {10, 50, 100, 200, 400, 600};

    static JComboBox sortList;
    static JComboBox dataList;
    static JButton startSort;
    static JSlider speedSlider;
    static JButton goBack;

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
        iconLabel.setIcon(new ImageIcon(Objects.requireNonNull(Main.class.getResource("/logo.png"))));

        // Doesn't work when building artifact
//        iconLabel.setIcon(new ImageIcon("resources/logo.png"));

        // Label for slider
        JLabel sliderLabel = new JLabel("Number of bars");
        sliderLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        // Label for dropdown
        JLabel sortLabel = new JLabel("Sorting algorithm");
        sortLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        // Label for speedSlider
        JLabel speedLabel = new JLabel("Sorting speed");
        speedLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        // Slider to choose the sorting speed
        speedSlider = new JSlider(JSlider.HORIZONTAL, 0,40,10);
        speedSlider.setMajorTickSpacing(5);
        speedSlider.setMinorTickSpacing(1);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);
        speedSlider.setSnapToTicks(true);
        speedSlider.setPreferredSize(new Dimension(300,50));
        speedSlider.setFocusable(false);

        // JComboBox to choose the amount of bars/dataCount
        dataList = new JComboBox(dataValues);
        dataList.setSelectedIndex(2);
        dataList.setPreferredSize(new Dimension(150,25));
        dataList.setFocusable(false);

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
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 4;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.PAGE_START;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0,0,100,0);
        cardSelect.add(iconLabel,c);

        // Placement of dataList Label
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.insets = new Insets(150,0,0,0);
        c.anchor = GridBagConstraints.SOUTH;
        c.fill = GridBagConstraints.NONE;
        cardSelect.add(sliderLabel, c);

        // Placement of dataCount dropdown
        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.insets = new Insets(10,0,0,0);
        c.anchor = GridBagConstraints.SOUTH;
        c.fill = GridBagConstraints.NONE;
        cardSelect.add(dataList, c);

        // Placement of speedLabel
        c.gridx = 2;
        c.gridy = 3;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.insets = new Insets(150,0,0,0);
        c.anchor = GridBagConstraints.SOUTH;
        c.fill = GridBagConstraints.NONE;
        cardSelect.add(speedLabel, c);

        // Placement of speedSlider
        c.gridx = 2;
        c.gridy = 4;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.insets = new Insets(10,50,0,50);
        c.anchor = GridBagConstraints.SOUTH;
        c.fill = GridBagConstraints.NONE;
        cardSelect.add(speedSlider, c);

        // Placement of sortLabel
        c.gridx = 4;
        c.gridy = 3;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.insets = new Insets(0,0,0,0);
        c.anchor = GridBagConstraints.SOUTH;
        cardSelect.add(sortLabel, c);

        // Placement of sorting algorithm dropdown
        c.gridx = 4;
        c.gridy = 4;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.insets = new Insets(10,0,0,0);
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

        // Add return button to previous card
        goBack = new JButton("Return");
        goBack.setFont(new Font("Dialog", Font.PLAIN, 18));
        goBack.setFocusPainted(false);
        goBack.setVisible(true);



        cardPerform.add(bars, BorderLayout.CENTER);
        cardPerform.add(goBack, BorderLayout.SOUTH);

        // Card container
        CardLayout cardLayout = new CardLayout();
        JPanel cardContainer = new JPanel(cardLayout);
        cardContainer.add(cardSelect, SELECTSORT);
        cardContainer.add(cardPerform, PERFORMSORT);

        // Starts new thread to handle button click. Otherwise the GUI freezes
        // Goes to next card - do the sorting
        Thread thread = new Thread(() -> startSort.addActionListener(e -> {
            if(thread2 != null) {
                thread2.interrupt();
            }
            dataCount = (int) dataList.getSelectedItem();
            speed = speedSlider.getValue();
            assignArray(dataCount);
            cardLayout.show(cardContainer, PERFORMSORT);
            doSort();
        }));
        thread.start();

        // When sorting is finished goBack button becomes visible and returns to main screen when clicked
        goBack.addActionListener(e -> cardLayout.show(cardContainer, SELECTSORT));

        // Adds cardContainer and sets up the JFrame
        frame.add(cardContainer, BorderLayout.CENTER);
        ImageIcon img = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/frameIcon.png")));
        frame.setIconImage(img.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Every value in the array gets a random number from 1 to 75.
    public static void assignArray(int dc) {
        array = new int[dc];
        System.out.println("assignArray called");

        bars.setStatesLength(array.length);

        for (int i = 0; i < dc; i++) {
            drawBars.setBarColor(i, -1);
            array[dc-1-i] = dc-i;
        }

        bars.repaintBars();
        bars.setArray(array);
        bars.setBarWidth(width/dc);
        bars.setDataCount(dc);
    }

    // Shuffles the array to place the values in a random order
    public static void shuffle() {
        longSleep(1000);
        Random rng = new Random();
        for (int i = 0; i < array.length; i++) {
            int swapWithIndex = rng.nextInt(array.length - 1);
            int temp = array[i];
            array[i] = array[swapWithIndex];
            array[swapWithIndex] = temp;
            drawBars.setBarColor(i, 1);
            if (i != 0) {
                drawBars.setBarColor(i-1, -1);
            }
            Main.updateArray(10);
        }
        drawBars.setBarColor(array.length-1, -1);
        Main.updateArray(0);
        longSleep(1000);

    }

    // Checks which sorting algorithm is picked and executes
    public static void doSort() {

        // Starts a second thread to avoid freezing the GUI
        thread2 = new Thread(() -> {
            System.out.println("doSort is running");
            switch (Objects.requireNonNull(sortList.getSelectedItem()).toString()) {
                case "BubbleSort" -> {
                    goBack.setVisible(false);
                    System.out.println("BubbleSort running");
                    shuffle();
                    BubbleSort.runSort(array);
                }
                case "InsertionSort" -> {
                    goBack.setVisible(false);
                    System.out.println("InsertionSort running");
                    shuffle();
                    InsertionSort.runSort(array);
                }
                case "SelectionSort" -> {
                    goBack.setVisible(false);
                    System.out.println("SelectionSort running");
                    shuffle();
                    SelectionSort.runSort(array);
                }
                case "QuickSort" -> {
                    goBack.setVisible(false);
                    System.out.println("QuickSort running");
                    shuffle();
                    QuickSort.runSort(array);
                }
                case "MergeSort" -> {
                    goBack.setVisible(false);
                    System.out.println("MergeSort running");
                    shuffle();
                    MergeSort.runSort(array);
                }
                case "HeapSort" -> {
                    goBack.setVisible(false);
                    System.out.println("HeapSort running");
                    shuffle();
                    HeapSort.runSort(array);
                }
                case "RadixSort" -> {
                    goBack.setVisible(false);
                    System.out.println("RadixSort running");
                    shuffle();
                    RadixSort.runSort(array);
                }
                default -> {
                    goBack.setVisible(false);
                    System.out.println("Default running");
                    shuffle();
                    QuickSort.runSort(array);
                }
            }
        });
        thread2.start();
    }

    // Updates the visualization of the array
    public static void updateArray(int delay) {
        bars.repaint();
        longSleep(delay);
    }

    private static void longSleep(int duration) {
        try {
            Thread.sleep(duration);
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

    /* Setter for goBack */
    public static void setGoBackVisible(boolean val) {
        goBack.setVisible(val);
    }
}



