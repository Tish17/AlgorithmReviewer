package ru.tishtech.algorithmreviewer.algorithm.sort;

import ru.tishtech.algorithmreviewer.algorithm.GeneralActions;

public class BubbleSortAlgorithm {

    public static int bubbleSwapCount = 0;

    public static void bubbleSort(int[] array) {
        for (int lastUnsortedIndex = array.length - 1; lastUnsortedIndex > 0; lastUnsortedIndex--) {
            for (int i = 0; i < lastUnsortedIndex; i++) {
                if (array[i] > array[i + 1]) {
                    GeneralActions.swap(array, i, i + 1);
                    bubbleSwapCount++;
                }
            }
        }
    }
}
