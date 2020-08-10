package ru.tishtech.algorithmreviewer.algorithm;

import ru.tishtech.algorithmreviewer.algorithm.sort.ShellSortAlgorithm;

import java.util.Arrays;

public class AlgorithmChecker {

    public static void main(String[] args) {
        int[] array = {78, 12, 15, 4, 10, 10, 15, 15};
        ShellSortAlgorithm.shellSort(array);
        System.out.println(Arrays.toString(array));
    }
}
