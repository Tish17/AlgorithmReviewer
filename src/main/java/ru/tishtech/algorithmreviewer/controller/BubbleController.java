package ru.tishtech.algorithmreviewer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.tishtech.algorithmreviewer.service.BubbleService;

@Controller
@RequestMapping("/bubble")
public class BubbleController {

    @Autowired
    private BubbleService bubbleService;

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
        bubbleService.bubbleAdd(bubbleArrayString, model);
        return "bubble";
    }

    @PostMapping("/start")
    public String bubbleStart(Model model) {
        bubbleService.bubbleStart(model);
        return "bubble";
    }

    @PostMapping(value = "/action", params = {"yes"})
    public String bubbleActionYes(@RequestParam int leftBubbleIndex,
                                  @RequestParam int rightBubbleIndex, Model model) {
        bubbleService.bubbleActionYes(leftBubbleIndex, rightBubbleIndex, model);
        return "bubble";
    }

    @PostMapping("/action")
    public String bubbleActionNoOrNext(@RequestParam int leftBubbleIndex,
                                       @RequestParam int rightBubbleIndex,
                                       @RequestParam(required = false) String next, Model model) {
        bubbleService.bubbleActionNoOrNext(leftBubbleIndex, rightBubbleIndex, next, model);
        return "bubble";
    }

}
