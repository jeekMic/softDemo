package com.example.administrator.myp2p.widget;


public class RadarData {

    private String title;
    private double percentage;

    public RadarData(String title, double percentage) {
        this.title = title;
        this.percentage = percentage;
    }

    public String getTitle() {
        return title;
    }

    public double getPercentage() {
        return percentage;
    }
}
