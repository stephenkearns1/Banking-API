/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycompany.models.Account;
import com.mycompany.models.Customer;
import com.mycompany.models.Transaction;
import com.mycompany.storage.DBPresistance;
import java.util.Date;
import java.util.List;

/**
 *
 * @author barry
 */
public class AccountService {

    DBPresistance presistance;

    public AccountService() {
        presistance = new DBPresistance();
    }

    public List getAccount() {
        return null;
    }

    /*
    This method retrieves the details of a customer's account
     */
    public Account getAccount(int id) {
        Account account = (Account) presistance.Find(Account.class, id);
        return account;
    }

    /*public Account getAccount(Account a){
        Account account = (Account) presistance.(Account.class, a);
        return account;
    } */
    public String CreateAccount(int cust_id) {
        //if(!AccountAlreadyExists(a)){
        //Acquire a connection, even tho a new instance will be created on each request
        Customer customer = (Customer) presistance.Find(Customer.class, cust_id);
        Account acc = new Account();
        acc.setBalance(0.00);
        acc.setCustomer(customer);

        customer.addAccount(acc);

        presistance.Begin();
        presistance.Presist(acc);
        presistance.Presist(customer);
        presistance.Commit();

        /* Close the connection unless, pooling is implemented */
        presistance.Close();
        return "Account Created";

        //}
        //else {
        //    return "Account already exists";
        // }
    }

    /*
    This method retrieves the balance of a customer's account
     */
    public Account getBalance(int id) {
        Account a = (Account) presistance.Find(Account.class, id);
        return a;

    }

    /*
    This method deletes an account owned by a customer
     */
    public boolean deleteAccount(int accountId) {
        Account account = getAccount(accountId);
        boolean deleted = false;
        if (account != null) {
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

    public List<Account> getAccounts(int id) {
        Customer cust = (Customer) presistance.Find(Customer.class, id);
        return cust.getAccounts();
    }

    /* Transfer methods go here */
    public String Lodgement(int accountId, String cardNum, double amount) {
        /* 
        Find the account, 
        add the card number to the account add the amount to the balance
        Return the trans 
         */
        Account acc = (Account) presistance.Find(Account.class, accountId);
        double originalBal = 0.0, newBal = 0.0;
        if (acc != null) {
            Transaction trans = new Transaction();

            presistance.Begin();
            presistance.Presist(acc);
            trans.setBalance(acc.getBalance());
            trans.setCardNum(cardNum);
            trans.setAmount(amount);
            trans.setType("Lodgement");
            trans.setDate(new Date());
            originalBal = trans.getBalance();
            newBal = originalBal + amount;
            trans.setNewBalance(newBal);
            trans.setAccount(acc);
            acc.AddTrans(trans);
            acc.setBalance(newBal);

            presistance.Presist(acc);
            presistance.Presist(trans);
            presistance.Commit();

            presistance.refresh(acc);
            presistance.Close();

            return "You have lodged " +amount+ "into account ID:" +accountId+". Your new balance is" + newBal;
        }

        return "Account not found";
    }

    public String Withdraw(int accountId, String cardNum, double amount) {
        /* 
        Find the account, 
        add the card number to the account add the amount to the balance
        Return the trans 
         */
        Account acc = (Account) presistance.Find(Account.class, accountId);
        double originalBal = 0.0, newBal = 0.0;
        if (acc != null) {
            Transaction trans = new Transaction();
            presistance.Begin();
            presistance.Presist(acc);
            trans.setBalance(acc.getBalance());
            trans.setCardNum(cardNum);
            trans.setAmount(amount);
            trans.setType("Withdraw");
            trans.setDate(new Date());
            originalBal = trans.getBalance();
            newBal = originalBal - amount;
            trans.setNewBalance(newBal);
            trans.setAccount(acc);
            acc.setBalance(newBal);
            acc.AddTrans(trans);

            presistance.Presist(acc);
            presistance.Presist(trans);
            presistance.Commit();
            presistance.refresh(acc);
            presistance.Close();

            return "You have withdrew " +amount+ "from account ID:" +accountId+". Your new balance is" + newBal;

        }

        return "Account not found";
    }

    public String Transfer(int accountId, int accountTo, double amount) {
        Account accf = (Account) presistance.Find(Account.class, accountId);
        Account accTo = (Account) presistance.Find(Account.class, accountTo);
        if (accf == null) {
            return "Account not found";
        } else if (accTo == null) {
            return "The account you are transfering to has not be found";
        } else {
            
            
            double originalBal = 0.0, newBal = 0.0;
             Transaction trans = new Transaction();

             
            presistance.Begin();
            presistance.Presist(accf);
            presistance.Presist(accTo);
            trans.setBalance(accf.getBalance());
            trans.setCardNum("111");
            trans.setAmount(amount);
            trans.setType("Withdraw");
            trans.setDate(new Date());
            originalBal = trans.getBalance();
            newBal = originalBal - amount;
            trans.setNewBalance(newBal);
            trans.setAccount(accTo);
            accf.setBalance(newBal);
            accf.AddTrans(trans);
            
            
            trans.setBalance(accTo.getBalance());
            trans.setCardNum("1111");
            trans.setAmount(amount);
            trans.setType("Lodgement");
            trans.setDate(new Date());
            originalBal = trans.getBalance();
            newBal = originalBal + amount;
            trans.setNewBalance(newBal);
            trans.setAccount(accTo);
            accTo.AddTrans(trans);
            accTo.setBalance(newBal);
            
            presistance.Presist(accf);
            presistance.Presist(accTo);
            presistance.Commit();
            presistance.refresh(accf);
            presistance.refresh(accTo);
            presistance.Close();
            System.out.print("account 1 has withdrawn" +amount+ "Account 2 new balance is " +newBal+ "original balance" +originalBal);
            return "Transfer successful";
        }

    }

    /*
      
     */
}
