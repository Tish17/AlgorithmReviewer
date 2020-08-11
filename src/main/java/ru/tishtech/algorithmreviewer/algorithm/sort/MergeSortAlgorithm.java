package ru.tishtech.algorithmreviewer.algorithm.sort;

public class MergeSortAlgorithm {

    public static void mergeSort(int[] array, int start, int end) {
        if (end - start < 2) return;
        int middle = (start + end) / 2;
        mergeSort(array, start, middle);
        mergeSort(array, middle, end);
        merge(array, start, middle, end);
    }

    public static void merge(int[] array, int start, int middle, int end) {
        if (array[middle - 1] <= array[middle]) return;
        int i = start;
        int j = middle;
        int tempIndex = 0;
        int[] tempArray = new int[end - start];
        while (i < middle && j < end) {
            tempArray[tempIndex++] = array[i] <= array[j] ? array[i++] : array[j++];
        }
        System.arraycopy(array, i, array, start + tempIndex, middle - i);
        System.arraycopy(tempArray, 0, array, start, tempIndex);
    }
}
