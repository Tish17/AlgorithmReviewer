package ru.tishtech.algorithmreviewer.algorithm.sort;

public class RadixSortAlgorithm {

    public static void radixSort(int[] array, int radix, int width) {
        for (int i = 0; i < width; i++) radixSingleSort(array, i, radix);
    }

    public static void radixSingleSort(int[] array, int position, int radix) {
        int elementQuantity = array.length;
        int[] countArray = new int[radix];
        for (int value : array) countArray[getDigit(position, value, radix)]++;
        for (int j = 1; j < radix; j++) countArray[j] += countArray[j - 1];
        int[] tempArray = new int[elementQuantity];
        for (int tempIndex = elementQuantity - 1; tempIndex >= 0; tempIndex--)
            tempArray[--countArray[getDigit(position, array[tempIndex], radix)]] = array[tempIndex];
        System.arraycopy(tempArray, 0, array, 0, elementQuantity);
    }

    public static int getDigit(int position, int value, int radix) {
        return value / (int) Math.pow(radix, position) % radix;
    }
}
