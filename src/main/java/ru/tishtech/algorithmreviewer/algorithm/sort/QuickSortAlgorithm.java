package ru.tishtech.algorithmreviewer.algorithm.sort;

public class QuickSortAlgorithm {

    public static void quickSort(int[] array, int start, int end) {
        if (end - start < 2) return;
        int pivotIndex = partition(array, start, end);
        quickSort(array, start, pivotIndex);
        quickSort(array, pivotIndex + 1, end);
    }

    public static int partition(int[] array, int start, int end) {
        int pivot = array[start];
        int i = start;
        int j = end;
        while (i < j) {
            while (i < j && array[--j] >= pivot);
            if (i < j) array[i] = array[j];
            while (i < j && array[++i] <= pivot);
            if (i < j) array[j] = array[i];
        }
        array[i] = pivot;
        return i;
    }
}
