package ru.tishtech.algorithmreviewer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.tishtech.algorithmreviewer.model.Bubble;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BubbleController {

    private List<Bubble> bubbles = new ArrayList<>();

    @GetMapping("/bubble")
    public String bubblePage(Model model) {
        model.addAttribute("bubbles", bubbles);
        return "bubble";
    }

//    @GetMapping("/bubble/add")
//    public String bubbleAddPage(Model model) {
//        model.addAttribute("bubble", new Bubble());
//        return "bubble-add";
//    }

    @GetMapping("/bubble/array/add")
    public String bubbleArrayAddPage() {
        return "bubble-array-add";
    }

    @PostMapping("/bubble/save")
    public String bubbleSave(Bubble bubble) {
        return "redirect:/bubble";
    }

    @PostMapping("/bubble/array/save")
    public String bubbleArraySave(@RequestParam String bubbleArrayString) {
        String[] bubbleArray = bubbleArrayString.split(",");
        for (int i = 0; i < bubbleArray.length; i++)
            bubbles.add(new Bubble(i, Integer.parseInt(bubbleArray[i])));
        Bubble lastBubble = bubbles.get(bubbles.size() - 1);
        lastBubble.setComma("");
        return "redirect:/bubble";
    }

    @PostMapping("/bubble/start")
    public String bubbleStart(Model model) {
        Bubble leftBubble = bubbles.get(0);
        Bubble rightBubble = bubbles.get(1);
        leftBubble.setColor("blue");
        rightBubble.setColor("blue");
        model.addAttribute("leftBubble", leftBubble);
        model.addAttribute("rightBubble", rightBubble);
        model.addAttribute("expressionShown", true);
        model.addAttribute("yesNoShown", true);
        model.addAttribute("bubbles", bubbles);
        return "bubble";
    }

    @PostMapping(value = "/bubble/action", params = {"yes"})
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
            bubbles.set(leftBubbleIndex, leftBubble);
            bubbles.set(rightBubbleIndex, rightBubble);
            model.addAttribute("nextShown", true);
        } else {
            model.addAttribute("actionResult", "Wrong!");
        }
        model.addAttribute("leftBubble", leftBubble);
        model.addAttribute("rightBubble", rightBubble);
        model.addAttribute("expressionShown", false);
        model.addAttribute("yesNoShown", false);
        model.addAttribute("bubbles", bubbles);
        return "bubble";
    }

    @PostMapping(value = "/bubble/action", params = {"no"})
    public String bubbleActionNo(@RequestParam int leftBubbleIndex,
                                 @RequestParam int rightBubbleIndex, Model model) {
        Bubble leftBubble = bubbles.get(leftBubbleIndex);
        Bubble rightBubble = bubbles.get(rightBubbleIndex);
        if (leftBubble.getValue() < rightBubble.getValue()) {
            Bubble oldBubble = leftBubble;
            oldBubble.setColor("black");
            leftBubble = bubbles.get(rightBubbleIndex);
            rightBubble = bubbles.get(rightBubbleIndex + 1);
            rightBubble.setColor("blue");
        } else {
            model.addAttribute("actionResult", "Wrong!");
        }
        model.addAttribute("leftBubble", leftBubble);
        model.addAttribute("rightBubble", rightBubble);
        model.addAttribute("expressionShown", true);
        model.addAttribute("yesNoShown", true);
        model.addAttribute("bubbles", bubbles);
        return "bubble";
    }

    @PostMapping(value = "/bubble/action", params = {"next"})
    public String bubbleActionNext(Model model) {
        model.addAttribute("expressionShown", true);
        model.addAttribute("yesNoShown", true);
        model.addAttribute("bubbles", bubbles);
        return "bubble";
    }
}
