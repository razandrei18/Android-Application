package com.example.mytown;

public class Eveniment {

    private String eventDenumire;
    private String eventData;
    private String eventImg;

    public Eveniment(String eventDenumire, String eventData, String eventImg) {
        this.eventDenumire = eventDenumire;
        this.eventData = eventData;
        this.eventImg = eventImg;
    }

    public String getEventDenumire() {
        return eventDenumire;
    }

    public void setEventDenumire(String eventDenumire) {
        this.eventDenumire = eventDenumire;
    }

    public String getEventData() {
        return eventData;
    }

    public void setEventData(String eventData) {
        eventData = eventData;
    }

    public String getEventImg() {
        return eventImg;
    }

    public void setEventImg(String eventImg) {
        this.eventImg = eventImg;
    }
}
