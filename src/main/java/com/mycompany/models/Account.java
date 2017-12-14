/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

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
   @ManyToOne(cascade=CascadeType.ALL)
   @JoinColumn(name="cust_id")
   private Customer customer;
   
   public Account() {
   
   }
   
 @OneToMany
 private Collection<Transaction> transactions = new ArrayList<Transaction>();

public Collection<Transaction> getTransactions() {
      return transactions;
   }
   public void setTransactions(Collection<Transaction> transactions) {
      this.transactions = transactions;
 }
    @XmlTransient
    public Customer getCustomer(){
        return customer;
    }

    public Account(int accountId, int sortCode, double balance, Customer customer) {
        this.accountId = accountId;
        this.sortCode = sortCode;
        this.balance = balance;
        this.customer = customer;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getSortCode() {
        return sortCode;
    }

    public void setSortCode(int sortCode) {
        this.sortCode = sortCode;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
 
   public void setCustomer(Customer c){
        customer = c;
    }
    
  
    
}
