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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "BET")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bet.findAll", query = "SELECT b FROM Bet b"),
    @NamedQuery(name = "Bet.findByIdBet", query = "SELECT b FROM Bet b WHERE b.idBet = :idBet"),
    @NamedQuery(name = "Bet.findByAmount", query = "SELECT b FROM Bet b WHERE b.amount = :amount"),
    @NamedQuery(name = "Bet.findByStatus", query = "SELECT b FROM Bet b WHERE b.status = :status"),
    @NamedQuery(name = "Bet.findByK", query = "SELECT b FROM Bet b WHERE b.k = :k")})
public class Bet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_BET")
    private Integer idBet;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AMOUNT")
    private float amount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STATUS")
    private int status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "K")
    private float k;
    @JoinColumn(name = "ID_OUTCOME", referencedColumnName = "ID_OUTCOME")
    @ManyToOne(optional = false)
    private Outcome idOutcome;
    @JoinColumn(name = "USER", referencedColumnName = "ID_USER")
    @ManyToOne(optional = false)
    private Selfuser user;

    public Bet() {
    }

    public Bet(Integer idBet) {
        this.idBet = idBet;
    }

    public Bet(Integer idBet, float amount, int status, float k) {
        this.idBet = idBet;
        this.amount = amount;
        this.status = status;
        this.k = k;
    }

    public Integer getIdBet() {
        return idBet;
    }

    public void setIdBet(Integer idBet) {
        this.idBet = idBet;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getK() {
        return k;
    }

    public void setK(float k) {
        this.k = k;
    }

    public Outcome getIdOutcome() {
        return idOutcome;
    }

    public void setIdOutcome(Outcome idOutcome) {
        this.idOutcome = idOutcome;
    }

    public Selfuser getUser() {
        return user;
    }

    public void setUser(Selfuser user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBet != null ? idBet.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bet)) {
            return false;
        }
        Bet other = (Bet) object;
        if ((this.idBet == null && other.idBet != null) || (this.idBet != null && !this.idBet.equals(other.idBet))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ejbbet.entities.Bet[ idBet=" + idBet + " ]";
    }

    public Status getStatus(){
        return Status.getFromCode(status);
    }
    
    public void setStatus(Status status){
        this.status = status.code;
    }
    
    public static enum Status {
        Open (0), 
        Paid (1);
        
        private final int code;

        Status(int code) {
            this.code = code;
        }
        
        public static Status getFromCode(int code){
            switch (code) {
                case 0: return Open;
                case 1: return Paid;
                default: throw new IllegalArgumentException("Accepted codes are 0-2");
            }
        }
        
        public int getCode() {
            return code;
        }
    }
}
