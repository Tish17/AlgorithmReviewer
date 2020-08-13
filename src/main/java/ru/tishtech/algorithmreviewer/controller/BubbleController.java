package ru.tishtech.algorithmreviewer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.tishtech.algorithmreviewer.model.Bubble;
import ru.tishtech.algorithmreviewer.repository.BubbleRepository;

import java.sql.SQLOutput;
import java.util.List;

@Controller
public class BubbleController {

    @Autowired
    private BubbleRepository bubbleRepository;

    @GetMapping("/bubble")
    public String bubblePage(Model model) {
        model.addAttribute("bubbles", bubbleRepository.findAll());
        return "bubble";
    }

    @GetMapping("/bubble/add")
    public String bubbleAddPage(Model model) {
        model.addAttribute("bubble", new Bubble());
        return "bubble-add";
    }

    @GetMapping("/bubble/array/add")
    public String bubbleArrayAddPage() {
        return "bubble-array-add";
    }

    @PostMapping("/bubble/save")
    public String bubbleSave(Bubble bubble) {
        bubbleRepository.save(bubble);
        return "redirect:/bubble";
    }

    @PostMapping("/bubble/array/save")
    public String bubbleArraySave(@RequestParam String bubbleArrayString) {
        String[] bubbleArray = bubbleArrayString.split(",");
        for (String bubbleElement : bubbleArray)
            bubbleRepository.save(new Bubble(Integer.valueOf(bubbleElement)));
        return "redirect:/bubble";
    }

    @PostMapping("/bubble/start")
    public String bubbleStart(Model model) {
        List<Bubble> bubbles = bubbleRepository.findAll();
        model.addAttribute("bubbles", bubbles);
        model.addAttribute("left", bubbles.get(0).getValue());
        model.addAttribute("right", bubbles.get(1).getValue());
        model.addAttribute("started", true);
        model.addAttribute("buttonShown", true);
        return "bubble";
    }

    @PostMapping(value = "/bubble/action", params = {"yes"})
    public String bubbleActionYes() {
        System.out.println("Yes");
        return "redirect:/bubble";
    }

    @PostMapping(value = "/bubble/action", params = {"no"})
    public String bubbleActionNo() {
        System.out.println("No");
        return "redirect:/bubble";
    }
}
