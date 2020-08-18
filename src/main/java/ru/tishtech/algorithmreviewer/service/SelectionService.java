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

    public void selectionSelect(int selectedIndex, Model model) {
        selectedIndices.add(selectedIndex);
        if (selectedIndices.size() == 1) {
            Selection firstSelectedSelection = selections.get(selectedIndices.get(0));
            firstSelectedSelection.setColor("blue");
        } else if (selectedIndices.size() == 2) {
            Selection secondSelectedSelection = selections.get(selectedIndices.get(1));
            secondSelectedSelection.setColor("blue");
            model.addAttribute("firstSelectedIndex", selectedIndices.get(0));
            model.addAttribute("secondSelectedIndex", selectedIndices.get(1));
            model.addAttribute("formShown", true);
            model.addAttribute("swapShown", true);
        } else {
            Selection firstSelectedSelection = selections.get(selectedIndices.get(0));
            Selection secondSelectedSelection = selections.get(selectedIndices.get(1));
            firstSelectedSelection.setColor("black");
            secondSelectedSelection.setColor("black");
            selectedIndices = new ArrayList<>();
        }
        model.addAttribute("selections", selections);
    }

    public void selectionActionSwap(int firstSelectedIndex, int secondSelectedIndex, Model model) {
        selectedIndices = new ArrayList<>();
        Selection firstSelectedSelection = selections.get(firstSelectedIndex);
        Selection secondSelectedSelection = selections.get(secondSelectedIndex);
        Selection tempSelection = firstSelectedSelection;
        firstSelectedSelection = secondSelectedSelection;
        secondSelectedSelection = tempSelection;
        firstSelectedSelection.setIndex(firstSelectedIndex);
        secondSelectedSelection.setIndex(secondSelectedIndex);
        selections.set(firstSelectedIndex, firstSelectedSelection);
        selections.set(secondSelectedIndex, secondSelectedSelection);
        model.addAttribute("selections", selections);
        model.addAttribute("firstSelectedIndex", firstSelectedIndex);
        model.addAttribute("secondSelectedIndex", secondSelectedIndex);
        model.addAttribute("formShown", true);
        model.addAttribute("nextShown", true);
    }

    public void selectionActionNext(int firstSelectedIndex, int secondSelectedIndex, Model model) {
        Selection firstSelectedSelection = selections.get(firstSelectedIndex);
        Selection secondSelectedSelection = selections.get(secondSelectedIndex);
        firstSelectedSelection.setColor("black");
        secondSelectedSelection.setColor("black");
        model.addAttribute("selections", selections);
    }

    private void actionsAfterSortingSelectionArray(Model model) {
        for (Selection selection : selections) selection.setColor("green");
        model.addAttribute("sortResult", true);
        model.addAttribute("bubbleSwapCount", SelectionSortAlgorithm.selectionSwapCount);
    }
}
