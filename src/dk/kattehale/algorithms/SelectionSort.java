package dk.kattehale.algorithms;

import dk.kattehale.Main;
import dk.kattehale.drawBars;

public class SelectionSort {

    public static void runSort(int[] array) {
        int length = array.length;

        for (int i=0; i < length-1; i++) {

            int jMin = i;
            for (int j=i+1; j < length; j++) {
                if (array[j] < array[jMin]) {
                    jMin = j;
                    drawBars.setBarColor(jMin,1);
                }
            }
            drawBars.setBarColor(jMin,-1);
            if (i != 0) {
                drawBars.setBarColor(i-1,-1);
            }
            swap(array, i, jMin);
        }
        Main.setGoBackVisible(true);

    }

    public static void swap(int[] array, int firstIndex, int secondIndex) {
        int temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
        Main.updateArray(Main.speed);
    }

}
