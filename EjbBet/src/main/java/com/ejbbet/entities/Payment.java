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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author llama
 */
@Entity
@Table(name = "PAYMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Payment.findAll", query = "SELECT p FROM Payment p"),
    @NamedQuery(name = "Payment.findByIdPayment", query = "SELECT p FROM Payment p WHERE p.idPayment = :idPayment"),
    @NamedQuery(name = "Payment.findByStatus", query = "SELECT p FROM Payment p WHERE p.status = :status")})
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PAYMENT")
    private Integer idPayment;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STATUS")
    private int status;
    @JoinColumn(name = "ID_EVENT", referencedColumnName = "ID_EVENT")
    @OneToOne(optional = false)
    private Event idEvent;
    @JoinColumn(name = "ID_WINNEROUTCOME", referencedColumnName = "ID_OUTCOME")
    @ManyToOne(optional = false)
    private Outcome idWinneroutcome;

    public Payment() {
    }

    public Payment(Integer idPayment) {
        this.idPayment = idPayment;
    }

    public Payment(Integer idPayment, int status) {
        this.idPayment = idPayment;
        this.status = status;
    }

    public Integer getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(Integer idPayment) {
        this.idPayment = idPayment;
    }

    public Event getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(Event idEvent) {
        this.idEvent = idEvent;
    }

    public Outcome getIdWinneroutcome() {
        return idWinneroutcome;
    }

    public void setIdWinneroutcome(Outcome idWinneroutcome) {
        this.idWinneroutcome = idWinneroutcome;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPayment != null ? idPayment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Payment)) {
            return false;
        }
        Payment other = (Payment) object;
        if ((this.idPayment == null && other.idPayment != null) || (this.idPayment != null && !this.idPayment.equals(other.idPayment))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ejbbet.entities.Payment[ idPayment=" + idPayment + " ]";
    }
    
    public Status getStatus(){
        return Payment.Status.getFromCode(status);
    }
    
    public void setStatus(Status status){
        this.status = status.code;
    }
    
    public static enum Status {
        Waiting (0), 
        Paid (1);
        
        private final int code;

        Status(int code) {
            this.code = code;
        }
        
        public static Status getFromCode(int code){
            switch (code) {
                case 0: return Waiting;
                case 1: return Paid;
                default: throw new IllegalArgumentException("Accepted codes are 0-2");
            }
        }

        public int getCode() {
            return code;
        }
    }
}
