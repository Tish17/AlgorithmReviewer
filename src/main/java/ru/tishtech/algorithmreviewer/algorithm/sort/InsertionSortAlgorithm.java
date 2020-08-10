package ru.tishtech.algorithmreviewer.algorithm.sort;

public class InsertionSortAlgorithm {

    public static void insertionSort(int[] array) {
        for (int firstUnsortedIndex = 1; firstUnsortedIndex < array.length; firstUnsortedIndex++) {
            int newElement = array[firstUnsortedIndex];
            int i = firstUnsortedIndex;
            while (i > 0 && array[i - 1] > newElement) {
                array[i] = array[i - 1];
                i--;
            }
            array[i] = newElement;
        }
    }
}
