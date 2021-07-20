package dk.kattehale.algorithms;

import dk.kattehale.Main;

public class SelectionSort {

    public static int[] array = Main.getArray();

    public static void runSort(int[] array) {
        int length = array.length;

        for (int i=0; i < length-1; i++) {

            int jMin = i;

            for (int j=i+1; j < length; j++) {
                if (array[j] < array[jMin]) {
                    jMin = j;
                }
            }

            swap(i, jMin);

        }
    }

    public static void swap(int firstIndex, int secondIndex) {
        int temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
        Main.updateArray();
    }

}
