package ru.tishtech.algorithmreviewer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.tishtech.algorithmreviewer.service.SelectionService;

import java.util.List;

@Controller
@RequestMapping("/selection")
public class SelectionController {

    @Autowired
    private SelectionService selectionService;

    @GetMapping("/first")
    public String selectionFirstPage() {
        return "selection-first";
    }

    @GetMapping
    public String selectionPage() {
        return "selection";
    }

    @GetMapping("/add")
    public String selectionAddPage() {
        return "selection-add";
    }

    @PostMapping("/add")
    public String selectionAdd(@RequestParam String selectionArrayString, Model model) {
        selectionService.selectionAdd(selectionArrayString, model);
        return "selection";
    }

    @PostMapping(value = "/add", params = {"random"})
    public String selectionAddRandom(@RequestParam int leftBorder, @RequestParam int rightBorder,
                                     @RequestParam int arraySize, Model model) {
        selectionService.selectionAddRandom(leftBorder, rightBorder, arraySize, model);
        return "selection";
    }

    @PostMapping("/start")
    public String selectionStart(Model model) {
        selectionService.selectionStart(model);
        return "bubble";
    }

    @PostMapping("/action/array")
    public String selectionActionArray(@RequestParam int selectedIndex, Model model) {
        selectionService.selectionActionArray(selectedIndex, model);
        return "selection";
    }

    @PostMapping(value = "/action", params = {"yes"})
    public String selectionActionYes(Model model) {
        selectionService.selectionActionYes(model);
        return "selection";
    }
}
