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
        long bubbleTime = 0;
        long countingTime = 0;
        long insertionTime = 0;
        long mergeTime = 0;
        long selectionTime = 0;
        long shellTime = 0;
        long quickTime = 0;
        for (int i = 0; i < iterationQuantity; i++) {
            int[] baseArray = GeneralActions.generateRandomArray(leftBorder, rightBorder, arraySize);
            if (sortNames.contains("bubble")) {
                int[] sortableArray = new int[arraySize];
                System.arraycopy(baseArray, 0, sortableArray, 0, arraySize);
                Date start = new Date();
                BubbleSortAlgorithm.bubbleSort(sortableArray);
                bubbleTime += new Date().getTime() - start.getTime();
            }
            if (sortNames.contains("counting")) {
                int[] sortableArray = new int[arraySize];
                System.arraycopy(baseArray, 0, sortableArray, 0, arraySize);
                Date start = new Date();
                CountingSortAlgorithm.countingSort(sortableArray, leftBorder, rightBorder);
                countingTime += new Date().getTime() - start.getTime();
            }
            if (sortNames.contains("insertion")) {
                int[] sortableArray = new int[arraySize];
                System.arraycopy(baseArray, 0, sortableArray, 0, arraySize);
                Date start = new Date();
                InsertionSortAlgorithm.insertionSort(sortableArray);
                insertionTime += new Date().getTime() - start.getTime();
            }
            if (sortNames.contains("merge")) {
                int[] sortableArray = new int[arraySize];
                System.arraycopy(baseArray, 0, sortableArray, 0, arraySize);
                Date start = new Date();
                MergeSortAlgorithm.mergeSort(sortableArray, 0, sortableArray.length);
                mergeTime += new Date().getTime() - start.getTime();
            }
            if (sortNames.contains("quick")) {
                int[] sortableArray = new int[arraySize];
                System.arraycopy(baseArray, 0, sortableArray, 0, arraySize);
                Date start = new Date();
                QuickSortAlgorithm.quickSort(sortableArray, 0, sortableArray.length);
                quickTime += new Date().getTime() - start.getTime();
            }
            if (sortNames.contains("selection")) {
                int[] sortableArray = new int[arraySize];
                System.arraycopy(baseArray, 0, sortableArray, 0, arraySize);
                Date start = new Date();
                SelectionSortAlgorithm.selectionSort(sortableArray);
                selectionTime += new Date().getTime() - start.getTime();
            }
            if (sortNames.contains("shell")) {
                int[] sortableArray = new int[arraySize];
                System.arraycopy(baseArray, 0, sortableArray, 0, arraySize);
                Date start = new Date();
                ShellSortAlgorithm.shellSort(sortableArray);
                shellTime += new Date().getTime() - start.getTime();
            }
        }
        if (sortNames.contains("bubble"))
            sortModels.add(new SortModel("Bubble", bubbleTime * 1.0 / iterationQuantity));
        if (sortNames.contains("counting"))
            sortModels.add(new SortModel("Counting", countingTime * 1.0 / iterationQuantity));
        if (sortNames.contains("insertion"))
            sortModels.add(new SortModel("Insertion", insertionTime * 1.0 / iterationQuantity));
        if (sortNames.contains("merge"))
            sortModels.add(new SortModel("Merge", mergeTime * 1.0 / iterationQuantity));
        if (sortNames.contains("quick"))
            sortModels.add(new SortModel("Quick", quickTime * 1.0 / iterationQuantity));
        if (sortNames.contains("selection"))
            sortModels.add(new SortModel("Selection", selectionTime * 1.0 / iterationQuantity));
        if (sortNames.contains("shell"))
            sortModels.add(new SortModel("Shell", shellTime * 1.0 / iterationQuantity));
        return sortModels;
    }
}
