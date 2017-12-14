package com.mycompany.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 *
 * @author barry, Stephen
 */

@Entity
@Table
@XmlRootElement
//@XmlAccessorType(XmlAccessType.FIELD)
//@XmlSeeAlso({Account.class})
public class Customer implements Serializable {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int cust_id;
private String fname,sname,email,address,password,securityQ,securityAns;
private int pin;


@OneToMany(targetEntity = Account.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "customer")
private List<Account> accounts;

    public Customer() {
    }

    public Customer(String Fname, String Sname, String email, String address, String password, String securityQ, String securityAns, int pin) {
        this.fname = Fname;
        this.sname = Sname;
        this.email = email;
        this.address = address;
        this.password = password;
        this.securityQ = securityQ;
        this.securityAns = securityAns;
        this.pin = pin;
    }


    public String getFname() {
        return fname;
    }

    public void setFname(String Fname) {
        this.fname = Fname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String Sname) {
        this.sname = Sname;
    }

     public int getCustomerID() {
        return cust_id;
    }

    public void setCustomerID(int customerID) {
        this.cust_id = customerID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecurityQ() {
        return securityQ;
    }

    public void setSecurityQ(String securityQ) {
        this.securityQ = securityQ;
    }

    public String getSecurityAns() {
        return securityAns;
    }

    public void setSecurityAns(String securityAns) {
        this.securityAns = securityAns;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }
    

    
    /*
      Add OneToMany RelationShip for Accounts 
    */
    @XmlElementWrapper(name = "accounts")
    @XmlElementRef()
    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
    
    public void addAccount(Account acc){
        accounts.add(acc);
    }
    
  
}
