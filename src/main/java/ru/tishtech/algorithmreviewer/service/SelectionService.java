package ru.tishtech.algorithmreviewer.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.tishtech.algorithmreviewer.algorithm.GeneralActions;
import ru.tishtech.algorithmreviewer.algorithm.sort.SelectionSortAlgorithm;
import ru.tishtech.algorithmreviewer.model.Selection;

import java.util.ArrayList;
import java.util.List;

@Service
public class SelectionService {

    private List<Selection> selections;
    private List<Integer> selectedIndices = new ArrayList<>();
    private int selectionSwapCount;
    private int firstUnsortedIndex = 0;

    public void selectionAdd(String selectionArrayString, Model model) {
        String[] selectionArrayOfStrings = selectionArrayString.split(",");
        selections = new ArrayList<>();
        int[] selectionArrayOfNumbers = new int[selectionArrayOfStrings.length];
        for (int i = 0; i < selectionArrayOfStrings.length; i++) {
            selections.add(new Selection(i, Integer.parseInt(selectionArrayOfStrings[i])));
            selectionArrayOfNumbers[i] = Integer.parseInt(selectionArrayOfStrings[i]);
        }
        SelectionSortAlgorithm.selectionSort(selectionArrayOfNumbers);
        selectionSwapCount = SelectionSortAlgorithm.selectionSwapCount;
        Selection lastSelection = selections.get(selections.size() - 1);
        lastSelection.setComma("");
        model.addAttribute("selections", selections);
        model.addAttribute("startShown", true);
    }

    public void selectionAddRandom(int leftBorder, int rightBorder, int arraySize, Model model) {
        int[] randomArray = GeneralActions.generateRandomArray(leftBorder, rightBorder, arraySize);
        selections = new ArrayList<>();
        for (int i = 0; i < randomArray.length; i++)
            selections.add(new Selection(i, randomArray[i]));
        SelectionSortAlgorithm.selectionSort(randomArray);
        selectionSwapCount = SelectionSortAlgorithm.selectionSwapCount;
        Selection lastSelection = selections.get(selections.size() - 1);
        lastSelection.setComma("");
        model.addAttribute("selections", selections);
    }

    public void selectionStart(Model model) {
        if (selectionSwapCount == 0) {
            actionsAfterSortingSelectionArray(model);
        } else {
            Selection leftSelection = selections.get(0);
            Selection rightSelection = selections.get(1);
            leftSelection.setColor("blue");
            rightSelection.setColor("blue");
        }
        model.addAttribute("selections", selections);
        model.addAttribute("selectShown", true);
    }

    public void selectionActionArray(int selectedIndex, Model model) {
        selectedIndices.add(selectedIndex);
        if (selectedIndices.size() == 2) {
            Selection firstSelectedSelection = selections.get(selectedIndices.get(0));
            Selection secondSelectedSelection = selections.get(selectedIndices.get(1));
            firstSelectedSelection.setColor("blue");
            secondSelectedSelection.setColor("blue");
            model.addAttribute("selections", selections);
            model.addAttribute("doesShown", true);
            model.addAttribute("formShown", true);
            model.addAttribute("yesNoShown", true);
            model.addAttribute("nextShown", false);
        }
    }

    public void selectionActionYes(Model model) {
    }

    private void actionsAfterSortingSelectionArray(Model model) {
        for (Selection selection : selections) selection.setColor("green");
        model.addAttribute("sortResult", true);
        model.addAttribute("bubbleSwapCount", SelectionSortAlgorithm.selectionSwapCount);
    }
}
