package ru.tishtech.algorithmreviewer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.tishtech.algorithmreviewer.algorithm.GeneralActions;
import ru.tishtech.algorithmreviewer.algorithm.sort.BubbleSortAlgorithm;

import java.util.Date;

@Controller
public class MainController {

    @GetMapping("/")
    public String mainPage() {
        return "main";
    }

    @PostMapping("/")
    public String go(@RequestParam int leftBorder, @RequestParam int rightBorder,
                     @RequestParam int arraySize, Model model) {
        long sum = 0;
        for (int i = 0; i < 5; i++) {
            int[] array = GeneralActions.generateRandomArray(leftBorder, rightBorder, arraySize);
            Date start = new Date();
            BubbleSortAlgorithm.bubbleSort(array);
            sum += new Date().getTime() - start.getTime();
        }
        model.addAttribute("result", sum / 5.0);
        return "done";
    }
}
