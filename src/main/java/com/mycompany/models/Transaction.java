/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.models;

import java.util.Date;

/**
 *
 * @author barry
 */
public class Transaction {
    private int transID;
    private String transType;
    private int amount;
    private Date date;
    private int accountNum;
    
    public Transaction(int transID, String transType, int amount, Date date, int accountID) {
        this.transID = transID;
        this.transType = transType;
        this.amount = amount;
        this.date = date;
        this.accountNum = accountID;
        
    }
    public int getTransID() {
        return transID;
    }

    public void setTransID(int transID) {
        this.transID = transID;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(int accountNum) {
        this.accountNum = accountNum;
    }
    
    
    
    
}
