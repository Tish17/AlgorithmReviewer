package ru.tishtech.algorithmreviewer.algorithm;

import ru.tishtech.algorithmreviewer.algorithm.sort.QuickSortAlgorithm;

import java.util.Arrays;

public class AlgorithmChecker {

    public static void main(String[] args) {
        int[] array = {20, 35, -15, 7, 55, 1, -22};
        QuickSortAlgorithm.quickSort(array, 0, array.length);
        System.out.println(Arrays.toString(array));
    }
}
