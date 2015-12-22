/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejbbet.entities;

import com.ejbbet.entities.Event.Status;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author llama
 */
@Entity
@Table(name = "EVENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Event.findAll", query = "SELECT e FROM Event e"),
    @NamedQuery(name = "Event.findByIdEvent", query = "SELECT e FROM Event e WHERE e.idEvent = :idEvent"),
    @NamedQuery(name = "Event.findByDescription", query = "SELECT e FROM Event e WHERE e.description = :description"),
    @NamedQuery(name = "Event.findByExpirationtime", query = "SELECT e FROM Event e WHERE e.expirationtime = :expirationtime"),
    @NamedQuery(name = "Event.findByStatus", query = "SELECT e FROM Event e WHERE e.status = :status"),
    @NamedQuery(name = "Event.findUnexpiredByStatus", query = "SELECT e FROM Event e WHERE e.status = :status AND e.expirationtime>CURRENT_TIMESTAMP")})
public class Event implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_EVENT")
    private Integer idEvent;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EXPIRATIONTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationtime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STATUS")
    private int status;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idEvent")
    private Payment payment;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEvent")
    private Collection<Outcome> outcomeCollection;
    @JoinColumn(name = "ID_COMPANY", referencedColumnName = "ID_COMPANY")
    @ManyToOne(optional = false)
    private Companyuser idCompany;

    public Event() {
    }

    public Event(Integer idEvent) {
        this.idEvent = idEvent;
    }

    public Event(Integer idEvent, String description, Date expirationtime, int status) {
        this.idEvent = idEvent;
        this.description = description;
        this.expirationtime = expirationtime;
        this.status = status;
    }

    public Integer getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(Integer idEvent) {
        this.idEvent = idEvent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getExpirationtime() {
        return expirationtime;
    }

    public void setExpirationtime(Date expirationtime) {
        this.expirationtime = expirationtime;
    }

    public Status getStatus(){
        return Status.getFromCode(status);
    }

    public void setStatus(Status status){
        this.status = status.code;
    }
    
    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @XmlTransient
    public Collection<Outcome> getOutcomeCollection() {
        return outcomeCollection;
    }

    public void setOutcomeCollection(Collection<Outcome> outcomeCollection) {
        this.outcomeCollection = outcomeCollection;
    }

    public Companyuser getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(Companyuser idCompany) {
        this.idCompany = idCompany;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEvent != null ? idEvent.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Event)) {
            return false;
        }
        Event other = (Event) object;
        if ((this.idEvent == null && other.idEvent != null) || (this.idEvent != null && !this.idEvent.equals(other.idEvent))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ejbbet.entities.Event[ idEvent=" + idEvent + " ]";
    }
    
    public static enum Status {
        Open (0), 
        Closed (1), 
        Processing(2);
        
        private final int code;

        Status(int code) {
            this.code = code;
        }
        
        public static Status getFromCode(int code){
            switch (code) {
                case 0: return Open;
                case 1: return Closed;
                case 2: return Processing;
                default: throw new IllegalArgumentException("Accepted codes are 0-2");
            }
        }

        public int getCode() {
            return code;
        }
    }
    
}
