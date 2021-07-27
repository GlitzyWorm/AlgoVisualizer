package dk.kattehale.algorithms;

import dk.kattehale.Main;

import java.util.Arrays;

public class RadixSort {

    private static int radix = 10;
    private static int[] countingArray = new int[radix];

    public static void runSort(int[] array) {
        int largest = Arrays.stream(array).max().orElse(Integer.MIN_VALUE);
        int[] result = new int[array.length];

        for(int exp = 1; largest/exp > 0; exp *= radix) {
            countingArray = countingSort(array, exp);

            for(int i = 0; i < result.length; i++) {
                array[i] = (result[i]=array[i]);
                Main.updateArray(Main.speed/2);
            }
            for(int i = 1; i < radix; i++) {
                countingArray[i] += countingArray[i-1];
            }
            for(int i = array.length - 1; i > -1; --i) {
                array[--countingArray[(result[i]/exp)%radix]] = result[i];
                Main.updateArray(Main.speed/2);

            }

        }
        Main.setGoBackVisible(true);
    }

    private static int[] countingSort(int[] array, int exp) {
        Arrays.fill(countingArray, 0);
        for (int i = 0; i < array.length; i++) {
            countingArray[(array[i]/exp)%radix]++;
        }
        return countingArray;
    }

}
