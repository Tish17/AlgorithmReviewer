package ru.tishtech.algorithmreviewer.algorithm;

public class GeneralActions {

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static int[] generateRandomArray(int leftBorder, int rightBorder, int arraySize) {
        int[] array = new int[arraySize];
        for (int i = 0; i < arraySize; i++)
            array[i] = (int) (Math.random() * (rightBorder - leftBorder)) + leftBorder;
        return array;
    }
}
