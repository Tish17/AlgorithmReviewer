package ru.tishtech.algorithmreviewer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.tishtech.algorithmreviewer.model.SortModel;
import ru.tishtech.algorithmreviewer.service.AlgorithmService;

import java.util.Collections;
import java.util.List;

@Controller
public class MainController {

    @GetMapping("/")
    public String mainPage() {
        return "main";
    }

    @PostMapping("/")
    public String go(@RequestParam List<String> sortNames,
                     @RequestParam int leftBorder,
                     @RequestParam int rightBorder,
                     @RequestParam int arraySize,
                     @RequestParam int iterationQuantity, Model model) {
        List<SortModel> sortModels = AlgorithmService.calculate(sortNames, leftBorder, rightBorder,
                arraySize, iterationQuantity);
        Collections.sort(sortModels);
        model.addAttribute("sortModels", sortModels);
        return "done";
    }
}
