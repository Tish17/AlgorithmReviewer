package ru.tishtech.algorithmreviewer.algorithm;

public class SearchDuplicateAlgorithm {

    public static int searchDuplicate(int[] array) {
        if (array.length <= 1) return -1;
        int slow = array[0];
        int fast = array[array[0]];
        while (slow != fast) {
            slow = array[slow];
            fast = array[array[fast]];
        }
        slow = array[0];
        while (slow != fast) {
            slow = array[slow];
            fast = array[array[fast]];
        }
        return slow;
    }
}
