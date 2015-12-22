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

@Entity
@Table(name = "COMPANYUSER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Companyuser.findAll", query = "SELECT c FROM Companyuser c"),
    @NamedQuery(name = "Companyuser.findByIdCompany", query = "SELECT c FROM Companyuser c WHERE c.idCompany = :idCompany"),
    @NamedQuery(name = "Companyuser.findByLogname", query = "SELECT c FROM Companyuser c WHERE c.logname = :logname"),
    @NamedQuery(name = "Companyuser.findByPassword", query = "SELECT c FROM Companyuser c WHERE c.password = :password"),
    @NamedQuery(name = "Companyuser.findByBalance", query = "SELECT c FROM Companyuser c WHERE c.balance = :balance"),
    @NamedQuery(name = "Companyuser.findByFullname", query = "SELECT c FROM Companyuser c WHERE c.fullname = :fullname")})
public class Companyuser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_COMPANY")
    private Integer idCompany;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCompany")
    private Collection<Event> eventCollection;

    public Companyuser() {
    }

    public Companyuser(Integer idCompany) {
        this.idCompany = idCompany;
    }

    public Companyuser(Integer idCompany, String logname, String password, float balance, String fullname) {
        this.idCompany = idCompany;
        this.logname = logname;
        this.password = password;
        this.balance = balance;
        this.fullname = fullname;
    }

    public Integer getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(Integer idCompany) {
        this.idCompany = idCompany;
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
    public Collection<Event> getEventCollection() {
        return eventCollection;
    }

    public void setEventCollection(Collection<Event> eventCollection) {
        this.eventCollection = eventCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCompany != null ? idCompany.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Companyuser)) {
            return false;
        }
        Companyuser other = (Companyuser) object;
        if ((this.idCompany == null && other.idCompany != null) || (this.idCompany != null && !this.idCompany.equals(other.idCompany))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ejbbet.entities.Companyuser[ idCompany=" + idCompany + " ]";
    }
    
}
