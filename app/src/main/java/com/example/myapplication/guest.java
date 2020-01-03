package com.example.myapplication;
import java.util.Map;
import java.util.HashMap;
public class guest {
    private String uid;
    private String username;
    private String eventid;

    public guest(String uid, String username){
        this.uid = uid;
        this.username = username;
    }

    public guest(String eventid){
        this.eventid = eventid;
    }

    public Map<String, Object> MapGuest(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("username",username);
        result.put("uid",uid);
        return result;
    }

    public Map<String, Object> MapEvent(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("eventid",eventid);
        return result;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
