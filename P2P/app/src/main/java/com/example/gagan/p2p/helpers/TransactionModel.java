package com.example.gagan.p2p.helpers;

import com.example.gagan.p2p.users.UserModel;

/**
 * Created by Gagan on 6/26/2018.
 */

public class TransactionModel {
    private String fromId;
    private String toId;
    private String transactionId;
    private int moneyToBeSent;
    private String id;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public int getMoneyToBeSent() {
        return moneyToBeSent;
    }

    public void setMoneyToBeSent(int moneyToBeSent) {
        this.moneyToBeSent = moneyToBeSent;
    }

    public TransactionModel(UserModel from, UserModel to, int moneyToBeSent, String transactionId) {
        this.fromId = from.getId();
        this.toId = to.getId();
        this.moneyToBeSent = moneyToBeSent;
        this.transactionId = transactionId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
