package dk.kattehale.algorithms;

import dk.kattehale.Main;
import dk.kattehale.drawBars;

import java.util.Arrays;

public class MergeSort {

    private static int[] getSubArray(int[] array, int begin, int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = array[begin+i];
        }
        return arr;
    }

    private static void merge(int[] array, int left, int middle, int right) {
        int leftSize = middle - left + 1;
        int rightSize = right - middle;

        int[] leftArray = getSubArray(array, left, leftSize);
        int[] rightArray = getSubArray(array, middle+1,rightSize);

        int i = 0, j = 0, k = left;
        while (i < leftSize && j < rightSize) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                Main.updateArray(Main.speed);
                i++;
            } else {
                array[k] = rightArray[j];
                Main.updateArray(Main.speed);
                j++;
            }

            k++;

            // TODO: Fix coloring for MergeSort
            /*drawBars.setBarColor(k-1, -1);
            if(k < array.length-1) {
                drawBars.setBarColor(k, 1);
            } else {
                drawBars.setBarColor(k, -1);
            }*/
        }

        while (i < leftSize) {
            array[k] = leftArray[i];
            Main.updateArray(Main.speed/2);
            i++;
            k++;
        }

        while (j < rightSize) {
            array[k] = rightArray[j];
            Main.updateArray(Main.speed/2);
            j++;
            k++;
        }
    }

    private static void mergeSort(int[] array, int left, int right) {
       if(left < right) {
           int middle = (left+right)/2;

           mergeSort(array, left, middle);
           mergeSort(array, middle+1,right);
           merge(array, left, middle , right);
       } else {
           Main.setGoBackVisible(true);
       }

    }

    public static void runSort(int[] array) {
        int left = 0;
        int right = array.length-1;

        mergeSort(array, left, right);
    }

}
