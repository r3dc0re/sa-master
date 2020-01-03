package com.example.myapplication;

public class event {
    String event_name ;
    String event_date;
    Integer event_m;

    public event(String event_name, String event_date, Integer pay_m) {
        this.event_name = event_name;
        this.event_date = event_date;
        this.event_m = event_m;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_date() {
        return event_date;
    }

    public void setEvent_date(String event_date){
        this.event_date = event_date;
    }

    public Integer getPay_m() {
        return event_m;
    }

    public void setPay_casy(Integer pay_m) {
        this.event_m = event_m;
    }
}
