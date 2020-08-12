package ru.tishtech.algorithmreviewer.algorithm;

import ru.tishtech.algorithmreviewer.algorithm.sort.CountingSortAlgorithm;

import java.util.Arrays;

public class AlgorithmChecker {

    public static void main(String[] args) {
//        int[] array = {20, 35, -15, 7, 55, 1, -22};
        int[] array = {2, 5, 9, 8, 2, 8, 7, 7, 4, 3};
        CountingSortAlgorithm.countingSort(array, 2, 10);
        System.out.println(Arrays.toString(array));
    }
}
