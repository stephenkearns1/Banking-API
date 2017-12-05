/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.models;

/**
 *
 * @author barry
 */
public class Account {
    
   private int sortCode;
   private int accountNum;
   private double balance;
   
   public Account(int sortCode, int accountNum, double balance) {
        this.sortCode = sortCode;
        this.accountNum = accountNum;
        this.balance = balance;
    }

    public void setSortCode(int sortCode) {
        this.sortCode = sortCode;
    }

    public void setAccountNum(int accountNum) {
        this.accountNum = accountNum;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
   

    public int getSortCode() {
        return sortCode;
    }

    public int getAccountNum() {
        return accountNum;
    }

    public double getBalance() {
        return balance;
    }
   

    
   
    
}
