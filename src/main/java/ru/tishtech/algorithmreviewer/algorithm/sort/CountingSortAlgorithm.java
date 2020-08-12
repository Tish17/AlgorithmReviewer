package ru.tishtech.algorithmreviewer.algorithm.sort;

public class CountingSortAlgorithm {

    public static void countingSort(int[] array, int leftBorder, int rightBorder) {
        int[] countArray = new int[rightBorder - leftBorder + 1];
        for (int i = 0; i < array.length; i++) countArray[array[i] - leftBorder]++;
        int index = 0;
        for (int i = leftBorder; i < rightBorder; i++) {
            for (int j = 0; j < countArray[i - leftBorder]; j++) array[index++] = i;
        }
    }
}
