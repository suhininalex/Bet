/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejbbet.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author llama
 */
@Entity
@Table(name = "OWNERUSER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Owneruser.findAll", query = "SELECT o FROM Owneruser o"),
    @NamedQuery(name = "Owneruser.findByIdOwner", query = "SELECT o FROM Owneruser o WHERE o.idOwner = :idOwner"),
    @NamedQuery(name = "Owneruser.findByLogname", query = "SELECT o FROM Owneruser o WHERE o.logname = :logname"),
    @NamedQuery(name = "Owneruser.findByPassword", query = "SELECT o FROM Owneruser o WHERE o.password = :password"),
    @NamedQuery(name = "Owneruser.findByBalance", query = "SELECT o FROM Owneruser o WHERE o.balance = :balance")})
public class Owneruser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_OWNER")
    private Integer idOwner;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "LOGNAME")
    private String logname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "PASSWORD")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BALANCE")
    private float balance;

    public Owneruser() {
    }

    public Owneruser(Integer idOwner) {
        this.idOwner = idOwner;
    }

    public Owneruser(Integer idOwner, String logname, String password, float balance) {
        this.idOwner = idOwner;
        this.logname = logname;
        this.password = password;
        this.balance = balance;
    }

    public Integer getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(Integer idOwner) {
        this.idOwner = idOwner;
    }

    public String getLogname() {
        return logname;
    }

    public void setLogname(String logname) {
        this.logname = logname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOwner != null ? idOwner.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Owneruser)) {
            return false;
        }
        Owneruser other = (Owneruser) object;
        if ((this.idOwner == null && other.idOwner != null) || (this.idOwner != null && !this.idOwner.equals(other.idOwner))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ejbbet.entities.Owneruser[ idOwner=" + idOwner + " ]";
    }
    
}
