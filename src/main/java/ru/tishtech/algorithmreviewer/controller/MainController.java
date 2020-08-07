package ru.tishtech.algorithmreviewer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.tishtech.algorithmreviewer.service.AlgorithmService;

@Controller
public class MainController {

    @GetMapping("/")
    public String mainPage() {
        return "main";
    }

    @PostMapping("/")
    public String go(@RequestParam String sortName,
                     @RequestParam int leftBorder,
                     @RequestParam int rightBorder,
                     @RequestParam int arraySize, Model model) {
        model.addAttribute("result",
                AlgorithmService.calculate(sortName, leftBorder, rightBorder, arraySize));
        return "done";
    }
}
