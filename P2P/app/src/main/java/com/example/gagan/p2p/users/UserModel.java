package com.example.gagan.p2p.users;

import java.util.Random;

/**
 * Created by Gagan on 6/25/2018.
 */

public class UserModel {
    private String name;
    private int loc;
    private String picUri;
    private String id;
    private int amount;


    public void setLoc(int loc) {
        this.loc = loc;
    }

    public int getLoc() {
        return loc;
    }

    public String getPicUri() {
        return picUri;
    }

    public void setPicUri(String picUri) {
        this.picUri = picUri;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
