package com.example.myapplication;
import java.util.Map;
import java.util.HashMap;
public class User {
    private String profileImgUri;
    private String uid;
    private Integer cash;
    private String isuser;
    private String username;
    private Integer give_cash;
    private String goods_name;
    private Integer goods_cash;
    private String event_name;
    private String event_date;
    private Integer event_m;
    private String new_good_name;
    private String new_good_price;
    private String eventid;
    private String goodid;
    private Integer fakeIndex;


    public User(String profileImgUri , String uid, Integer cash, String isuser, String username)
    {
        this.profileImgUri = profileImgUri;
        this.uid = uid;
        this.cash = cash;
        this.isuser = isuser;
        this.username=username;
    }
    public User(Integer give_cash,String goods_name,Integer goods_cash)
    {
        this.give_cash=give_cash;
        this.goods_name = goods_name;
        this.goods_cash=goods_cash;
    }
    public User(String event_name,String event_date,Integer event_m, String eventid)
    {
        this.event_name = event_name;
        this.event_date = event_date;
        this.event_m = event_m;
        this.eventid = eventid;
    }

    public User(String new_good_name, String new_good_price,String goodid)
    {
        this.new_good_name = new_good_name;
        this.new_good_price = new_good_price;
        this.goodid = goodid;
    }

    public User(String goods_name, Integer goods_cash)
    {
        this.goods_name = goods_name;
        this.goods_cash=goods_cash;
    }

    public User(Integer fakeIndex, String uid, String username){
        this.fakeIndex = fakeIndex;
        this.username = username;
        this.uid = uid;
    }

    public User(Integer give_cash)
    {
        this.give_cash = give_cash;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("give_cash",  give_cash);
        result.put("goods_name", goods_name);
        result.put("goods_cash", goods_cash);
        return result;
    }




    public Map<String,Object> MapCash(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("give_cash",  give_cash);
        return result;
    }

    public Map<String,Object> MapGood(){
        HashMap<String,Object> result = new HashMap<>();
        result.put("goods_name", goods_name);
        result.put("goods_cash", goods_cash);
        return result;
    }

    public Map<String, Object> Map2(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("event_name", event_name);
        result.put("event_date", event_date);
        result.put("event_m",event_m);
        result.put("eventid",eventid);
        return result;
    }

    public Map<String, Object> Map3(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("new_good_name",new_good_name);
        result.put("new_good_price",new_good_price);
        result.put("goodid",goodid);
        return result;
    }



    public User() {}


    public String getProfileImgUri() {
        return profileImgUri;
    }

    public void setProfileImgUri(String profileImgUri) {
        this.profileImgUri = profileImgUri;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getCash() {
        return cash;
    }

    public void setCash(Integer cash) {
        this.cash = cash;
    }

    public String getIsuser() {
        return isuser;
    }

    public void setIsuser(String isuser) {
        this.isuser = isuser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getGive_cash() {
        return give_cash;
    }

    public void setGive_cash(Integer give_cash) {
        this.give_cash = give_cash;
    }

    public Integer getGoods_cash() {
        return goods_cash;
    }

    public void setGoods_cash(Integer goods_cash) {
        this.goods_cash = goods_cash;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

}
