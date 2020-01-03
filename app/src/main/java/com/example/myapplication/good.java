package com.example.myapplication;

import java.util.HashMap;
import java.util.Map;

public class good {
    String new_good_name;
    Integer new_good_price;

    public good(String new_good_name, Integer new_good_price) {
        this.new_good_name = new_good_name;
        this.new_good_price = new_good_price;
    }

    public String getNew_good_name() {
        return new_good_name;
    }

    public void setNew_good_name(String new_good_name) {
        this.new_good_name = new_good_name;
    }

    public Integer getNew_good_price() {
        return new_good_price;
    }

    public void setNew_good_price(Integer new_good_price) {
        this.new_good_price = new_good_price;
    }


}
