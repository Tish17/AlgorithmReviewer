package ru.tishtech.algorithmreviewer.algorithm.sort;

import ru.tishtech.algorithmreviewer.algorithm.GeneralActions;

public class SelectionSortAlgorithm {

    public static int selectionSwapCount;

    public static void selectionSort(int[] array) {
        selectionSwapCount = 0;
        for (int lastUnsortedIndex = array.length - 1; lastUnsortedIndex > 0; lastUnsortedIndex--) {
            int largest = 0;
            for (int i = 1; i <= lastUnsortedIndex; i++) {
                if (array[i] > array[largest]) largest = i;
            }
            GeneralActions.swap(array, largest, lastUnsortedIndex);
            selectionSwapCount++;
        }
    }
}
