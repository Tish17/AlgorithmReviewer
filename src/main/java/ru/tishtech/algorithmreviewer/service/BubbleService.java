package ru.tishtech.algorithmreviewer.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.tishtech.algorithmreviewer.algorithm.GeneralActions;
import ru.tishtech.algorithmreviewer.algorithm.sort.BubbleSortAlgorithm;
import ru.tishtech.algorithmreviewer.model.Bubble;

import java.util.ArrayList;
import java.util.List;

@Service
public class BubbleService {

    private List<Bubble> bubbles;
    private int bubbleSwapCount;

    public void bubbleAdd(String bubbleArrayString, Model model) {
        String[] bubbleArrayOfStrings = bubbleArrayString.split(",");
        bubbles = new ArrayList<>();
        int[] bubbleArrayOfNumbers = new int[bubbleArrayOfStrings.length];
        for (int i = 0; i < bubbleArrayOfStrings.length; i++) {
            bubbles.add(new Bubble(i, Integer.parseInt(bubbleArrayOfStrings[i])));
            bubbleArrayOfNumbers[i] = Integer.parseInt(bubbleArrayOfStrings[i]);
        }
        BubbleSortAlgorithm.bubbleSort(bubbleArrayOfNumbers);
        bubbleSwapCount = BubbleSortAlgorithm.bubbleSwapCount;
        Bubble lastBubble = bubbles.get(bubbles.size() - 1);
        lastBubble.setComma("");
        addAttributesToModel(model, bubbles, null, null, true,
                false, false, false, false);
    }

    public void bubbleAddRandom(int leftBorder, int rightBorder, int arraySize, Model model) {
        int[] randomArray = GeneralActions.generateRandomArray(leftBorder, rightBorder, arraySize);
        bubbles = new ArrayList<>();
        for (int i = 0; i < randomArray.length; i++)
            bubbles.add(new Bubble(i, randomArray[i]));
        BubbleSortAlgorithm.bubbleSort(randomArray);
        bubbleSwapCount = BubbleSortAlgorithm.bubbleSwapCount;
        Bubble lastBubble = bubbles.get(bubbles.size() - 1);
        lastBubble.setComma("");
        addAttributesToModel(model, bubbles, null, null, true,
                false, false, false, false);
    }

    public void bubbleStart(Model model) {
        if (bubbleSwapCount == 0) {
            actionsAfterSortingArray(model);
        } else {
            Bubble leftBubble = bubbles.get(0);
            Bubble rightBubble = bubbles.get(1);
            leftBubble.setColor("blue");
            rightBubble.setColor("blue");
            addAttributesToModel(model, bubbles, leftBubble, rightBubble, false,
                    true, true, true, false);
        }
    }

    public void bubbleActionYes(int leftBubbleIndex, int rightBubbleIndex, Model model) {
        Bubble leftBubble = bubbles.get(leftBubbleIndex);
        Bubble rightBubble = bubbles.get(rightBubbleIndex);
        if (leftBubble.getValue() > rightBubble.getValue()) {
            Bubble tempBubble = leftBubble;
            leftBubble = rightBubble;
            rightBubble = tempBubble;
            leftBubble.setIndex(leftBubbleIndex);
            rightBubble.setIndex(rightBubbleIndex);
            if (rightBubbleIndex == bubbles.size() - 1) {
                leftBubble.setComma(",");
                rightBubble.setComma("");
            }
            bubbles.set(leftBubbleIndex, leftBubble);
            bubbles.set(rightBubbleIndex, rightBubble);
            if (--bubbleSwapCount == 0) {
                actionsAfterSortingArray(model);
            } else {
                addAttributesToModel(model, bubbles, leftBubble, rightBubble, false,
                        false, true, false, true);
            }
        } else {
            addAttributesToModel(model, bubbles, leftBubble, rightBubble, false,
                    true, true, true, false);
            model.addAttribute("actionResult", "Wrong!");
        }
    }

    public void bubbleActionNoOrNext(int leftBubbleIndex, int rightBubbleIndex, String next, Model model) {
        Bubble leftBubble = bubbles.get(leftBubbleIndex);
        Bubble rightBubble = bubbles.get(rightBubbleIndex);
        if (leftBubble.getValue() < rightBubble.getValue() || next != null) {
            Bubble oldLeftBubble = leftBubble;
            oldLeftBubble.setColor("black");
            if (rightBubbleIndex == bubbles.size() - 1) {
                Bubble oldRightBubble = rightBubble;
                oldRightBubble.setColor("black");
                leftBubble = bubbles.get(0);
                rightBubble = bubbles.get(1);
                leftBubble.setColor("blue");
            } else {
                leftBubble = bubbles.get(rightBubbleIndex);
                rightBubble = bubbles.get(rightBubbleIndex + 1);
            }
            rightBubble.setColor("blue");
        } else {
            model.addAttribute("actionResult", "Wrong!");
        }
        addAttributesToModel(model, bubbles, leftBubble, rightBubble, false,
                true, true, true, false);
    }

    private void addAttributesToModel(Model model, List<Bubble> bubbles,
                                      Bubble leftBubble, Bubble rightBubble,
                                      boolean startShown, boolean expressionShown,
                                      boolean formShown, boolean yesNoShown, boolean nextShown) {
        model.addAttribute("bubbles", bubbles);
        model.addAttribute("leftBubble", leftBubble);
        model.addAttribute("rightBubble", rightBubble);
        model.addAttribute("startShown", startShown);
        model.addAttribute("expressionShown", expressionShown);
        model.addAttribute("formShown", formShown);
        model.addAttribute("yesNoShown", yesNoShown);
        model.addAttribute("nextShown", nextShown);
    }

    private void actionsAfterSortingArray(Model model) {
        for (Bubble bubble : bubbles) bubble.setColor("green");
        addAttributesToModel(model, bubbles, null, null, false,
                false, false, false, false);
        model.addAttribute("sortResult", true);
        model.addAttribute("bubbleSwapCount", BubbleSortAlgorithm.bubbleSwapCount);
    }
}
