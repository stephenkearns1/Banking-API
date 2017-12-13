/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.models;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author barry, Stephen
 */

@Entity
@Table
@XmlRootElement
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int transID;
    private String transType;
    private int amount, post_bal;
    private Date date;
    private int accountID, accountID_2;

    public Transaction() {
    }

    
    
    public Transaction(int transID, String transType, int amount, int post_bal, Date date, int accountID) {
        this.transID = transID;
        this.transType = transType;
        this.amount = amount;
        this.post_bal = post_bal;
        this.date = date;
        this.accountID = accountID;
        this.accountID_2 = accountID_2;
        
    }

    public int getAccountID_2() {
        return accountID_2;
    }

    public void setAccountID_2(int accountID_2) {
        this.accountID_2 = accountID_2;
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

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }
    
    public int getPost_bal() {
        return post_bal;
    }

    public void setPost_bal(int post_bal) {
        this.post_bal = post_bal;
    }

}
