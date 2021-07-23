package dk.kattehale.algorithms;

import dk.kattehale.Main;
import dk.kattehale.drawBars;

public class QuickSort {

    private static int findPivot(int[] array, int low, int high) {
        for(int k = low; k < high; k++) {
            drawBars.setBarColor(k, 0);
        }

        int pivot = array[high];
        int i = low-1;
        drawBars.setBarColor(i+1, 0);

        drawBars.setBarColor(high, 2);

        for (int j = low; j <= high-1; j++) {
            if(array[j] <= pivot) {
                drawBars.setBarColor(i+1, -1);
                i++;
                drawBars.setBarColor(i+1, 1);
                swap(array, i, j);

            }

        }

        drawBars.setBarColor(high, -1);

        for (int k = low; k < high; k++) {
            if (k != i) {
                drawBars.setBarColor(k, -1);
            }
        }
        swap(array,i+1, high);
        return i+1;
    }

    private static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivot = findPivot(array,low,high);
            drawBars.setBarColor(pivot, -1);
            quickSort(array, low, pivot-1);
            quickSort(array, pivot+1,high);
        }
    }

    private static void swap(int[] array, int firstIndex, int secondIndex) {
        int temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
        Main.updateArray(Main.speed);
    }


    public static void runSort(int[] array) {
        System.out.println("QuickSort.runSort Called");
        quickSort(array,0,array.length-1);
    }

}
