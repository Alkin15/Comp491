package com.example.layerzero;

public class Reflections {
    private String sensory;
    private String emotional;
    private String brief;
    private int emotionalSignificance;
    private int sensorySignificance;
    private int intellectualSignificance;

    public int getEmotionalSignificance() {
        return emotionalSignificance;
    }

    public void setEmotionalSignificance(int emotionalSignificance) {
        this.emotionalSignificance = emotionalSignificance;
    }

    public int getSensorySignificance() {
        return sensorySignificance;
    }

    public void setSensorySignificance(int sensorySignificance) {
        this.sensorySignificance = sensorySignificance;
    }

    public int getIntellectualSignificance() {
        return intellectualSignificance;
    }

    public void setIntellectualSignificance(int intellectualSignificance) {
        this.intellectualSignificance = intellectualSignificance;
    }

    public String getIntellectual() {
        return intellectual;
    }

    public void setIntellectual(String intellectual) {
        this.intellectual = intellectual;
    }

    private String intellectual;

    public String getSensory() {
        return sensory;
    }

    public void setSensory(String sensory) {
        this.sensory = sensory;
    }

    public String getEmotional() {
        return emotional;
    }

    public void setEmotional(String emotional) {
        this.emotional = emotional;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }
}
