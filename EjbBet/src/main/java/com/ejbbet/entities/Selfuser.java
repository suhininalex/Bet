/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejbbet.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author llama
 */
@Entity
@Table(name = "SELFUSER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Selfuser.findAll", query = "SELECT s FROM Selfuser s"),
    @NamedQuery(name = "Selfuser.findByIdUser", query = "SELECT s FROM Selfuser s WHERE s.idUser = :idUser"),
    @NamedQuery(name = "Selfuser.findByLogname", query = "SELECT s FROM Selfuser s WHERE s.logname = :logname"),
    @NamedQuery(name = "Selfuser.findByPassword", query = "SELECT s FROM Selfuser s WHERE s.password = :password"),
    @NamedQuery(name = "Selfuser.findByBalance", query = "SELECT s FROM Selfuser s WHERE s.balance = :balance"),
    @NamedQuery(name = "Selfuser.findByFullname", query = "SELECT s FROM Selfuser s WHERE s.fullname = :fullname")})
public class Selfuser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_USER")
    private Integer idUser;
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "FULLNAME")
    private String fullname;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Collection<Bet> betCollection;

    public Selfuser() {
    }

    public Selfuser(Integer idUser) {
        this.idUser = idUser;
    }

    public Selfuser(Integer idUser, String logname, String password, float balance, String fullname) {
        this.idUser = idUser;
        this.logname = logname;
        this.password = password;
        this.balance = balance;
        this.fullname = fullname;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @XmlTransient
    public Collection<Bet> getBetCollection() {
        return betCollection;
    }

    public void setBetCollection(Collection<Bet> betCollection) {
        this.betCollection = betCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUser != null ? idUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Selfuser)) {
            return false;
        }
        Selfuser other = (Selfuser) object;
        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ejbbet.entities.Selfuser[ idUser=" + idUser + " ]";
    }
    
}
