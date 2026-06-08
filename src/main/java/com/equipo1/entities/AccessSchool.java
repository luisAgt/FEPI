/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.equipo1.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author XPxTBxLLX
 */
@Entity
@Table(name = "access_school")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccessSchool.findAll", query = "SELECT a FROM AccessSchool a"),
    @NamedQuery(name = "AccessSchool.findByIdAccessSchool", query = "SELECT a FROM AccessSchool a WHERE a.idAccessSchool = :idAccessSchool"),
    @NamedQuery(name = "AccessSchool.findByCheckDate", query = "SELECT a FROM AccessSchool a WHERE a.checkDate = :checkDate"),
    @NamedQuery(name = "AccessSchool.findByStatus", query = "SELECT a FROM AccessSchool a WHERE a.status = :status"),
    @NamedQuery(name = "AccessSchool.findByGate", query = "SELECT a FROM AccessSchool a WHERE a.gate = :gate")})
public class AccessSchool implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_access_school")
    private Integer idAccessSchool;
    @Column(name = "check_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkDate;
    @Size(max = 45)
    private String status;
    private Integer gate;
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @ManyToOne
    private Users idUser;

    public AccessSchool() {
    }

    public AccessSchool(Integer idAccessSchool) {
        this.idAccessSchool = idAccessSchool;
    }

    public Integer getIdAccessSchool() {
        return idAccessSchool;
    }

    public void setIdAccessSchool(Integer idAccessSchool) {
        this.idAccessSchool = idAccessSchool;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getGate() {
        return gate;
    }

    public void setGate(Integer gate) {
        this.gate = gate;
    }

    public Users getIdUser() {
        return idUser;
    }

    public void setIdUser(Users idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAccessSchool != null ? idAccessSchool.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccessSchool)) {
            return false;
        }
        AccessSchool other = (AccessSchool) object;
        if ((this.idAccessSchool == null && other.idAccessSchool != null) || (this.idAccessSchool != null && !this.idAccessSchool.equals(other.idAccessSchool))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.equipo1.entities.AccessSchool[ idAccessSchool=" + idAccessSchool + " ]";
    }
    
}
