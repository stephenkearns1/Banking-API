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
    private int transID;
    private String type, card, description;
    private double amount;
    private double nBalance;
    private Date date;
    
    @ManyToOne(cascade=CascadeType.ALL)
    private Account account;
    
    public Transaction(){
        
    }
    
    public Account getAccount()  
    {  
        return account;  
    }  
    public void setAccount(Account account)  
    {  
        this.account = account;  
    }  

    public Transaction(int transID, String type, String card, String description, double amount, double nBalance, Date date) {
        this.transID = transID;
        this.type = type;
        this.card = card;
        this.description = description;
        this.amount = amount;
        this.nBalance = nBalance;
        this.date = date;
    }

    public int getTransID() {
        return transID;
    }

    public void setTransID(int transID) {
        this.transID = transID;
    }
    
   

    public String getType() {
        return type;
    } 

    public void setType(String type) {
        this.type = type;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getnBalance() {
        return nBalance;
    }

    public void setnBalance(double nBalance) {
        this.nBalance = nBalance;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

   
   
    
    
    
}
