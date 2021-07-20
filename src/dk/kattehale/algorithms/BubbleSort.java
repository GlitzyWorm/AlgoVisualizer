package dk.kattehale.algorithms;

import dk.kattehale.Main;

public class BubbleSort {

    public static int[] array = Main.getArray();

    public static void runSort(int[] array) {
        int length = array.length;

        for (int i=0; i < length-1; i++) {
            for (int j=0; j < length - i - 1; j++) {
                if (array[j] > array[j+1]) {
                    swap(j, j+1);
                }
            }
        }
    }

    public static void swap(int firstIndex, int secondIndex) {
        int temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
        Main.updateArray();
    }

}
