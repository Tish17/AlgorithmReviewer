package ru.tishtech.algorithmreviewer.model;

public class Selection {

    private int index;
    private int value;
    private String comma = " ";
    private String color = "black";

    public Selection(int index, int value) {
        this.index = index;
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getComma() {
        return comma;
    }

    public void setComma(String comma) {
        this.comma = comma;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Bubble{" +
                "value=" + value +
                ", comma='" + comma + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
