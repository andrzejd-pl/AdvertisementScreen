package com.wiatrzyk.damian

public class Advertisement {

    private int type;
    private Source source;
    private int duration;
    private String name;

    public Advertisement(int type, Source source, int duration, String name){
        this.type = type;
        this.source = source;
        this.duration = duration;
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public int getType() {
        return type;
    }

    public Source getSource() {
        return source;
    }

    public String getName() {
        return name;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setName(String name) {
        this.name = name;
    }
}
