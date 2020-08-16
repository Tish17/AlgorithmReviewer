package ru.tishtech.algorithmreviewer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.tishtech.algorithmreviewer.algorithm.sort.BubbleSortAlgorithm;
import ru.tishtech.algorithmreviewer.model.Bubble;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/bubble")
public class BubbleController {

    private List<Bubble> bubbles;
    private int bubbleSwapCount;

    @GetMapping("/first")
    public String bubbleFirstPage() {
        return "bubble-first";
    }

    @GetMapping
    public String bubblePage() {
        return "bubble";
    }

    @GetMapping("/add")
    public String bubbleAddPage() {
        return "bubble-add";
    }

    @PostMapping("/add")
    public String bubbleAdd(@RequestParam String bubbleArrayString, Model model) {
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
        return "bubble";
    }

    @PostMapping("/start")
    public String bubbleStart(Model model) {
        Bubble leftBubble = bubbles.get(0);
        Bubble rightBubble = bubbles.get(1);
        leftBubble.setColor("blue");
        rightBubble.setColor("blue");
        addAttributesToModel(model, bubbles, leftBubble, rightBubble, false,
                true, true, true, false);
        return "bubble";
    }

    @PostMapping(value = "/action", params = {"yes"})
    public String bubbleActionYes(@RequestParam int leftBubbleIndex,
                                  @RequestParam int rightBubbleIndex, Model model) {
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
            addAttributesToModel(model, bubbles, leftBubble, rightBubble, false,
                    false, true, false, true);
            if (--bubbleSwapCount == 0) {
                for (Bubble bubble : bubbles) bubble.setColor("green");
                addAttributesToModel(model, bubbles, null, null, false,
                        false, false, false, false);
                model.addAttribute("sortResult", true);
                model.addAttribute("bubbleSwapCount", BubbleSortAlgorithm.bubbleSwapCount);
            }
        } else {
            addAttributesToModel(model, bubbles, leftBubble, rightBubble, false,
                    true, true, true, false);
            model.addAttribute("actionResult", "Wrong!");
        }
        return "bubble";
    }

    @PostMapping("/action")
    public String bubbleActionNo(@RequestParam int leftBubbleIndex, @RequestParam int rightBubbleIndex,
                                 @RequestParam(required = false) String next, Model model) {
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
        return "bubble";
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

}
