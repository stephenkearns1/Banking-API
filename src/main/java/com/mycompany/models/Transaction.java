/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.models;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
    private int Id;
    private String type;
    private double amount;
    private double balance;
    private double newBalance;
    private Date date;
    private String cardNum;
    
    @ManyToOne(cascade=CascadeType.ALL)
    private Account account;
    
    public Transaction(){
        
    }

    public Transaction(int Id, String type, double amount, double balance, double newBalance, Date date, String cardNum, Account account) {
        this.Id = Id;
        this.type = type;
        this.amount = amount;
        this.balance = balance;
        this.newBalance = newBalance;
        this.date = date;
        this.cardNum = cardNum;
        this.account = account;
    }

    

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setNewBalance(double newBalance) {
        this.newBalance = newBalance;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    
    public void setAccount(Account account) {
        this.account = account;
    }
    
    
    public int getId() {
        return Id;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalance() {
        return balance;
    }

    public double getNewBalance() {
        return newBalance;
    }

    public Date getDate() {
        return date;
    }

    public String getCardNum() {
        return cardNum;
    }
    
    

    public Account getAccount() {
        return account;
    }

}
