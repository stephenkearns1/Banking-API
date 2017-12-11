package com.mycompany.storage;

import com.mycompany.models.Customer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Stephen Kearns
 */
public class DBPresistance {
    private EntityManagerFactory emFactory;
    private EntityManager entityManager;
    private EntityTransaction emTransaction;
    
    public DBPresistance(){
        emFactory = Persistence.createEntityManagerFactory("bank-connection");
        entityManager = emFactory.createEntityManager();
        emTransaction = entityManager.getTransaction();
    }
    
    public void OpenEntityManagerInstance(){
        if(!(entityManager.isOpen())){
            entityManager = emFactory.createEntityManager();
            emTransaction = entityManager.getTransaction();
        }
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
    
    public void Presist(Object obj){
       entityManager.persist(obj);
    }
    
    public void Commit(){
        emTransaction.commit();
    } 
    
    public void Begin(){
        emTransaction.begin();
    }
    
    public void Remove(Object obj){
        entityManager.remove(obj);
    }
    
    public void Rollback(){
        emTransaction.rollback();
    } 
    
    public void Close(){
        entityManager.close();
    }
    
    public Object Find(Class entity, int key){
        return entityManager.find(entity, key);
    }
    
      
    public Object Find(Class entity, Customer c){
        return entityManager.find(entity, c);
    }
    
}
