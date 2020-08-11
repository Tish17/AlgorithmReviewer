package ru.tishtech.algorithmreviewer.service;

import ru.tishtech.algorithmreviewer.algorithm.GeneralActions;
import ru.tishtech.algorithmreviewer.algorithm.sort.*;
import ru.tishtech.algorithmreviewer.model.SortModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AlgorithmService {

    public static List<SortModel> calculate(List<String> sortNames, int leftBorder, int rightBorder,
                                            int arraySize, int iterationQuantity) {
        List<SortModel> sortModels = new ArrayList<>();
        if (sortNames.contains("bubble")) {
            long fullTime = 0;
            for (int i = 0; i < iterationQuantity; i++) {
                int[] array = GeneralActions.generateRandomArray(leftBorder, rightBorder, arraySize);
                Date start = new Date();
                BubbleSortAlgorithm.bubbleSort(array);
                fullTime += new Date().getTime() - start.getTime();
            }
            sortModels.add(new SortModel("Bubble", fullTime * 1.0 / iterationQuantity));
        }
        if (sortNames.contains("insertion")) {
            long fullTime = 0;
            for (int i = 0; i < iterationQuantity; i++) {
                int[] array = GeneralActions.generateRandomArray(leftBorder, rightBorder, arraySize);
                Date start = new Date();
                InsertionSortAlgorithm.insertionSort(array);
                fullTime += new Date().getTime() - start.getTime();
            }
            sortModels.add(new SortModel("Insertion", fullTime * 1.0 / iterationQuantity));
        }
        if (sortNames.contains("merge")) {
            long fullTime = 0;
            for (int i = 0; i < iterationQuantity; i++) {
                int[] array = GeneralActions.generateRandomArray(leftBorder, rightBorder, arraySize);
                Date start = new Date();
                MergeSortAlgorithm.mergeSort(array, 0, array.length);
                fullTime += new Date().getTime() - start.getTime();
            }
            sortModels.add(new SortModel("Merge", fullTime * 1.0 / iterationQuantity));
        }
        if (sortNames.contains("selection")) {
            long fullTime = 0;
            for (int i = 0; i < iterationQuantity; i++) {
                int[] array = GeneralActions.generateRandomArray(leftBorder, rightBorder, arraySize);
                Date start = new Date();
                SelectionSortAlgorithm.selectionSort(array);
                fullTime += new Date().getTime() - start.getTime();
            }
            sortModels.add(new SortModel("Selection", fullTime * 1.0 / iterationQuantity));
        }
        if (sortNames.contains("shell")) {
            long fullTime = 0;
            for (int i = 0; i < iterationQuantity; i++) {
                int[] array = GeneralActions.generateRandomArray(leftBorder, rightBorder, arraySize);
                Date start = new Date();
                ShellSortAlgorithm.shellSort(array);
                fullTime += new Date().getTime() - start.getTime();
            }
            sortModels.add(new SortModel("Shell", fullTime * 1.0 / iterationQuantity));
        }
        return sortModels;
    }
}
