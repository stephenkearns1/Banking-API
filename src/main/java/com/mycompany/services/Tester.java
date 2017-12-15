/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycompany.models.Account;
import com.mycompany.models.Transaction;
import com.mycompany.storage.DBPresistance;

/**
 *
 * @author jjb20
 */
public class Tester {

    public static void main(String[] args) {
        Tester.Lodgement(1, "1232", 50);
        Tester.Withdrawal(100, "1234", 50);
    }

    public static Transaction Lodgement(int accountId, String cardNum, double amount) {
        DBPresistance presistance = new DBPresistance();

        Account acc = new Account();
        
        double originalBal = 0.0, newBal = 0.0;
            Transaction trans = new Transaction();
            presistance.Begin();
            presistance.Presist(acc);
            trans.setBalance(acc.getBalance());
            trans.setCardNum(cardNum);
            trans.setAmount(amount);
            originalBal = trans.getBalance();
            newBal = originalBal + amount;
            trans.setNewBalance(newBal);
            trans.setAccount(acc);
            acc.AddTrans(trans);

            presistance.Presist(acc);
            presistance.Presist(trans);
            presistance.Commit();
            
            presistance.refresh(acc);
            for (Transaction object : acc.getTransactions()) {
                System.out.println(object);
            
        }
                        presistance.Close();


            return trans;
        
    }
    
     public static Transaction Withdrawal(int accountId, String cardNum, double amount) {
        DBPresistance presistance = new DBPresistance();

        Account acc = new Account();
        
        double originalBal = 0.0, newBal = 0.0;
            Transaction trans = new Transaction();
            presistance.Begin();
            presistance.Presist(acc);
            trans.setBalance(acc.getBalance());
            trans.setCardNum(cardNum);
            trans.setAmount(amount);
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
            for (Transaction object : acc.getTransactions()) {
                System.out.println(object);
            
            }
            
            presistance.Close();


            return trans;
        
    }

}
