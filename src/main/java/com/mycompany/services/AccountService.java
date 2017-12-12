/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycompany.models.Account;
import com.mycompany.storage.DBPresistance;
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
        
        
    
    
}