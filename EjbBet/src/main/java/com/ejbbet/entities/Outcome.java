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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "OUTCOME")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Outcome.findAll", query = "SELECT o FROM Outcome o"),
    @NamedQuery(name = "Outcome.findByIdOutcome", query = "SELECT o FROM Outcome o WHERE o.idOutcome = :idOutcome"),
    @NamedQuery(name = "Outcome.findByK", query = "SELECT o FROM Outcome o WHERE o.k = :k"),
    @NamedQuery(name = "Outcome.findByName", query = "SELECT o FROM Outcome o WHERE o.name = :name")})
public class Outcome implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idWinneroutcome")
    private Collection<Payment> paymentCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_OUTCOME")
    private Integer idOutcome;
    @Basic(optional = false)
    @NotNull
    @Column(name = "K")
    private float k;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NAME")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOutcome")
    private Collection<Bet> betCollection;
    @JoinColumn(name = "ID_EVENT", referencedColumnName = "ID_EVENT")
    @ManyToOne(optional = false)
    private Event idEvent;

    public Outcome() {
    }

    public Outcome(Integer idOutcome) {
        this.idOutcome = idOutcome;
    }

    public Outcome(Integer idOutcome, float k, String name) {
        this.idOutcome = idOutcome;
        this.k = k;
        this.name = name;
    }

    public Integer getIdOutcome() {
        return idOutcome;
    }

    public void setIdOutcome(Integer idOutcome) {
        this.idOutcome = idOutcome;
    }

    public float getK() {
        return k;
    }

    public void setK(float k) {
        this.k = k;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<Bet> getBetCollection() {
        return betCollection;
    }

    public void setBetCollection(Collection<Bet> betCollection) {
        this.betCollection = betCollection;
    }

    public Event getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(Event idEvent) {
        this.idEvent = idEvent;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOutcome != null ? idOutcome.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Outcome)) {
            return false;
        }
        Outcome other = (Outcome) object;
        if ((this.idOutcome == null && other.idOutcome != null) || (this.idOutcome != null && !this.idOutcome.equals(other.idOutcome))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ejbbet.entities.Outcome[ idOutcome=" + idOutcome + " ]";
    }

    @XmlTransient
    public Collection<Payment> getPaymentCollection() {
        return paymentCollection;
    }

    public void setPaymentCollection(Collection<Payment> paymentCollection) {
        this.paymentCollection = paymentCollection;
    }
    
}
