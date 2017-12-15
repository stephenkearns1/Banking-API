/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycompany.models.Account;
import com.mycompany.models.Transaction;
import com.mycompany.storage.DBPresistance;
import java.util.Date;

/**
 *
 * @author jjb20
 */
public class Tester {

    public static void main(String[] args) {
        Tester.Transfer(1, 2, 500);
    }

    public static String Transfer(int accountId, int accountTo, double amount) {
        DBPresistance presistance = new DBPresistance();

        AccountService service;
        service = new AccountService();
        
        Account accf = (Account) presistance.Find(Account.class, accountId);
        Account accTo = (Account) presistance.Find(Account.class, accountTo);
        if(accf == null){
            return "Account not found";
        }
        else if( accTo == null){
            return "The account you are transfering to has not be found";
        }
        else{
            
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
            
            presistance.Presist(accTo);
            presistance.Presist(accf);
            presistance.Commit();
            presistance.refresh(accTo);
            presistance.refresh(accf);
            presistance.Close();
        }
        
        return "";
    }
}