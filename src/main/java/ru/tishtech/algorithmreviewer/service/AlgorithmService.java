package ru.tishtech.algorithmreviewer.service;

import ru.tishtech.algorithmreviewer.algorithm.GeneralActions;
import ru.tishtech.algorithmreviewer.algorithm.sort.BubbleSortAlgorithm;
import ru.tishtech.algorithmreviewer.algorithm.sort.InsertionSortAlgorithm;
import ru.tishtech.algorithmreviewer.algorithm.sort.SelectionSortAlgorithm;

import java.util.Date;

public class AlgorithmService {

    public static double calculate(String sortName, int leftBorder, int rightBorder, int arraySize) {
        long sum = 0;
        for (int i = 0; i < 5; i++) {
            int[] array = GeneralActions.generateRandomArray(leftBorder, rightBorder, arraySize);
            Date start = new Date();
            if (sortName.equals("bubble")) BubbleSortAlgorithm.bubbleSort(array);
            else if (sortName.equals("insertion")) InsertionSortAlgorithm.insertionSort(array);
            else if (sortName.equals("selection")) SelectionSortAlgorithm.selectionSort(array);
            sum += new Date().getTime() - start.getTime();
        }
        return sum / 5.0;
    }
}
