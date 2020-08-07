package ru.tishtech.algorithmreviewer.algorithm;

import ru.tishtech.algorithmreviewer.algorithm.sort.BubbleSortAlgorithm;

import java.util.Arrays;

public class AlgorithmChecker {

    public static void main(String[] args) {
        int[] array = {78, 12, 15, 4, 10, 10, 15, 15};
        BubbleSortAlgorithm.bubbleSort(array);
        System.out.println(Arrays.toString(array));
    }
}
