/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycompany.models.Account;
import com.mycompany.models.Transaction;
import com.mycompany.storage.DBPresistance;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author barry
 */
public class AccountService {
     DBPresistance presistance;
    
     public AccountService(){
         presistance = new DBPresistance();
         } 
  
     
     public List getAccount(){
        return null;
    }
    
   
    /*
    This method retrieves the details of a customer's account
    */
    public Account getAccount(int id){
        Account account = (Account) presistance.Find(Account.class, id);
        return account;
    }
    
    /*public Account getAccount(Account a){
        Account account = (Account) presistance.(Account.class, a);
        return account;
    } */
    
    public String CreateAccount(Account a){
        if(!AccountAlreadyExists(a)){
             //Acquire a connection, even tho a new instance will be created on each request 
             presistance.OpenEntityManagerInstance();
             presistance.Begin();
             presistance.Presist(a);
             presistance.Commit();

             /* Close the connection unless, pooling is implemented */
             presistance.Close();
             return "Account Created";
      
        }
        else {
            return "Account already exists";
        }
    }
        
    /*
    This method retrieves the balance of a customer's account
    */
    public Account getBalance(int id){
        
        Account a = (Account) presistance.Find(Account.class, id);
        presistance.Close();
        return a;
       
    }
    public Account updateBalance(Account a){
        Account acc = (Account) presistance.Find(Account.class, a.getAccountNum());
            presistance.Begin();
            a.setBalance(a.getBalance());
            presistance.Commit();
            presistance.Close();
            return a;
    }
    /*
    This method deletes an account owned by a customer
    */
    public boolean deleteAccount(int accountId){
       Account account = getAccount(accountId);
       boolean deleted = false;
       if(account != null){
           presistance.Begin();
           presistance.Remove(account);
           presistance.Commit();
           presistance.Close();
           deleted = true;
           return deleted;
       }
       return deleted;
    }
  
   
     private boolean AccountAlreadyExists(Account a) {
        boolean exists = false;
       /* Account account = (Account) presistance.Find(Account.class, a);
        if(account != null){
            return exists = true;
         */   
       return exists;
        }
     
     public List getTransactions() {
        return null;
    }

    public Transaction getTransactions(int account_id) {
        Transaction transaction = (Transaction) presistance.Find(Transaction.class, account_id);
        return transaction;
    }

    public Transaction getWithdrawels(int account_id) {
        Transaction transaction = (Transaction) presistance.Find(Transaction.class, account_id);
        return transaction;
    }

    public Transaction getLodgements(int account_id) {
        Transaction transaction = (Transaction) presistance.Find(Transaction.class, account_id);
        return transaction;
    }

    public Transaction getTransfers(int account_id) {
        Transaction transaction = (Transaction) presistance.Find(Transaction.class, account_id);
        return transaction;
    }

    public Transaction withdrawal(int accountID, int amount) {
        Transaction withdrawal = transaction(accountID, amount, "withdrawal");
        return withdrawal;
    }

    public Transaction lodgement(int account_no, int amount) {
        Transaction lodgement = transaction(account_no, amount, "lodgement");
        return lodgement;
    }

    public List<Transaction> transfer(int account_to, int account_from, int amount) {
        List<Transaction> transfer = new ArrayList<>();
        transfer.add(withdrawal(account_from, amount));
        transfer.add(lodgement(account_to, amount));
        return transfer;
    }

    private Transaction transaction(int accountID, int amount, String transType) {
        presistance.OpenEntityManagerInstance();
        Account a = getBalance(accountID);
        int current_bal = (int) a.getBalance();
        int new_bal = current_bal;

        // Calculate New Balance
        if (transType.equalsIgnoreCase("withdrawal")) {
            new_bal = current_bal - amount;
        } else if (transType.equalsIgnoreCase("lodgement")) {
            new_bal = current_bal + amount;
        }

        // Update Account
        a.setBalance(new_bal);
        presistance.OpenEntityManagerInstance();
        updateBalance(a);

        // Add Transaction
        Transaction t = new Transaction(0 ,transType, amount, new_bal, new Date(), accountID);
        //Acquire a connection, even tho a new instance will be created on each request 
        presistance.OpenEntityManagerInstance();
        presistance.Begin();
        presistance.Presist(t);
        presistance.Commit();

        /* Close the connection unless, pooling is implemented */
        presistance.Close();

        return t;
    }

        
        
    
    
}
