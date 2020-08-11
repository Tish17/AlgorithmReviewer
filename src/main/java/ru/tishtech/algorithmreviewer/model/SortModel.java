package ru.tishtech.algorithmreviewer.model;

public class SortModel implements Comparable<SortModel> {

    private String name;
    private Double averageTime;

    public SortModel(String name, Double averageTime) {
        this.name = name;
        this.averageTime = averageTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAverageTime() {
        return averageTime;
    }

    public void setAverageTime(Double averageTime) {
        this.averageTime = averageTime;
    }

    @Override
    public int compareTo(SortModel otherSortModel) {
        return this.getAverageTime().compareTo(otherSortModel.getAverageTime());
    }
}
