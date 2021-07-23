package dk.kattehale.algorithms;

import dk.kattehale.Main;
import dk.kattehale.drawBars;

public class InsertionSort {

    public static void runSort(int[] array) {
        for(int i = 0; i < array.length; i++) {
            int key = array[i];

            drawBars.setBarColor(i, 1);

            int j = i-1;

            while (j >= 0 && array[j] > key) {
                array[j+1] = array[j];
                j--;
                if (j >= 0) {
                    drawBars.setBarColor(j, 0);
                }
                Main.updateArray(10);
                if (j >= 0) {
                    drawBars.setBarColor(j, -1);
                }
            }
            drawBars.setBarColor(i, -1);
            array[j+1] = key;
        }
        Main.updateArray(10);
    }
}