package dk.kattehale.algorithms;

import dk.kattehale.Main;

public class SelectionSort {

    public static void runSort(int[] array) {
        int length = array.length;

        for (int i=0; i < length-1; i++) {

            int jMin = i;
            for (int j=i+1; j < length; j++) {
                if (array[j] < array[jMin]) {
                    jMin = j;
                }
            }
            swap(array, i, jMin);
        }
    }

    public static void swap(int[] array, int firstIndex, int secondIndex) {
        int temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
        Main.updateArray();
    }

}
