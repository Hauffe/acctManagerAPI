package com.example.acctmanagerapi.core.models;

public class Event {
    private String type;
    private String origin;
    private String destination;
    private int amount;

    public Event(String type, String origin, String destination, int amount) {
        this.type = type;
        this.origin = origin;
        this.destination = destination;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public int getAmount() {
        return amount;
    }
}
