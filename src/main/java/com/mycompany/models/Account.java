/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
public class Account implements Serializable {
   @Id 
   @GeneratedValue(strategy = GenerationType.AUTO)
   private int accountId;
   private int sortCode;
   private double balance;
   

   @ManyToOne(fetch=FetchType.EAGER)
   @JoinColumn(name="cust_id")
   private Customer customer;
   
   public Account() {
   
   }
   
   public Account(int sortCode, int accountId, double balance) {
        this.sortCode = sortCode;
        this.accountId = accountId;
        this.balance = balance;
    }

    public void setSortCode(int sortCode) {
        this.sortCode = sortCode;
    }

    public void setAccountNum(int accountId) {
        this.accountId = accountId;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
   

    public int getSortCode() {
        return sortCode;
    }

    public int getAccountNum() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }
   
    public void setCustomer(Customer c){
        customer = c;
    }
    
    public Customer getCustomer(){
        return customer;
    }

    
   
    
}
