package ru.tishtech.algorithmreviewer.model;

public class SortModel {

    private String name;
    private double averageTime;

    public SortModel(String name, double averageTime) {
        this.name = name;
        this.averageTime = averageTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAverageTime() {
        return averageTime;
    }

    public void setAverageTime(double averageTime) {
        this.averageTime = averageTime;
    }
}
