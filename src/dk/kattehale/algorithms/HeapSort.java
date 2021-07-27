package dk.kattehale.algorithms;

import dk.kattehale.Main;

public class HeapSort {

    private static boolean isChildLargerThanRoot(int child, int largest, int n, int[] array) {
        return child < n && array[child] > array[largest];
    }

    private static void toBinaryTreeArray(int[] array, int n, int rootIndex) {
        int largest = rootIndex;
        int leftChild = 2 * rootIndex + 1;
        int rightChild = 2 * rootIndex + 2;

        if (isChildLargerThanRoot(leftChild, largest, n, array)) {
            largest = leftChild;
        }
        if (isChildLargerThanRoot(rightChild,largest,n, array)) {
            largest = rightChild;
        }
        if (largest != rootIndex) {
            swap(array,rootIndex,largest);
            toBinaryTreeArray(array, n, largest);
        }
    }

    public static void runSort(int[] array) {
        int n = array.length;

        for (int i = n/2-1; i >= 0; i--) {
            toBinaryTreeArray(array, n, i);
        }
        for (int i = n-1; i >= 0; i--) {
            swap(array,0,i);
            toBinaryTreeArray(array, i,0);
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
