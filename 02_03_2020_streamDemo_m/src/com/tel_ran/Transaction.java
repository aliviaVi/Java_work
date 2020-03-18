package com.tel_ran;

import java.util.Date;

public class Transaction {
    private String uuid;
    private State transactState;
    private long sum;
    private Date created;

    public Transaction(String uuid, State transactState, long sum) {
        this.uuid = uuid;
        this.transactState = transactState;
        this.sum = sum;

    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public State getTransactState() {
        return transactState;
    }

    public void setTransactState(State transactState) {
        this.transactState = transactState;
    }

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}

