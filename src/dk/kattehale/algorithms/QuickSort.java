package dk.kattehale.algorithms;

import dk.kattehale.Main;

public class QuickSort {

    private static int findPivot(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low-1;

        for (int j = low; j <= high-1; j++) {
            if(array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array,i+1, high);
        return i+1;
    }

    private static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivot = findPivot(array,low,high);
            quickSort(array, low, pivot-1);
            quickSort(array, pivot+1,high);
        }
    }

    private static void swap(int[] array, int firstIndex, int secondIndex) {
        int temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
        Main.updateArray();
    }


    public static void runSort(int[] array) {
        System.out.println("QuickSort.runSort Called");
        quickSort(array,0,array.length-1);
    }

}
