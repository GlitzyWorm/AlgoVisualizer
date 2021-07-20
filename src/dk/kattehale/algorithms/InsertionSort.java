package dk.kattehale.algorithms;

import dk.kattehale.Main;

public class InsertionSort {

    public static int[] array = Main.getArray();

    public static void runSort(int[] array) {
        for(int i = 0; i < array.length; i++) {
            int key = array[i];
            int j = i-1;

            while (j >= 0 && array[j] > key) {
                array[j+1] = array[j];
                j--;
                Main.updateArray();
            }
            array[j+1] = key;
        }
        Main.updateArray();
    }
}