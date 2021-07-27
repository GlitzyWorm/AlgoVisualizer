package dk.kattehale.algorithms;

import dk.kattehale.Main;
import dk.kattehale.drawBars;

public class BubbleSort {

    public static void runSort(int[] array) {
        int length = array.length;

        for (int i=0; i < length-1; i++) {

            for (int j=0; j < length - i - 1; j++) {
                if (array[j] > array[j+1]) {
                    drawBars.setBarColor(j+1,1);
                    swap(array, j, j+1);
                }
                drawBars.setBarColor(j,-1);
                if (j != 0) {
                    drawBars.setBarColor(j-1,-1);
                }
                drawBars.setBarColor(j+1,-1);
            }

            drawBars.setBarColor(i,-1);
        }
        Main.setGoBackVisible(true);
    }

    private static void swap(int[] array, int firstIndex, int secondIndex) {
        int temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
        Main.updateArray(Main.speed);
    }

}
